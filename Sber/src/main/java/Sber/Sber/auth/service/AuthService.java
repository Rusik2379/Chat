package Sber.Sber.auth.service;

import Sber.Sber.auth.request.LoginRequest;
import Sber.Sber.auth.request.SignUpRequest;
import Sber.Sber.auth.response.AuthResponse;
import Sber.Sber.models.Company;
import Sber.Sber.models.User;
import Sber.Sber.repositories.CompanyRepository;
import Sber.Sber.repositories.UserRepository;
import Sber.Sber.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static Sber.Sber.models.enums.Role.ROLE_ADMIN;
import static Sber.Sber.models.enums.Role.ROLE_USER;
import static Sber.Sber.models.enums.Role.ROLE_DIRECTOR;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(SignUpRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new AuthResponse("User with this email already exists", HttpStatus.BAD_REQUEST);
        }

        //Company company;
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new AuthResponse("User with this email already exists", HttpStatus.BAD_REQUEST);
        }

        Company company = null;
        if (registerRequest.getCompanyname() != null && !registerRequest.getCompanyname().trim().isEmpty()) {
            company = companyRepository.findByCompanyname(registerRequest.getCompanyname().trim()).orElse(new Company(registerRequest.getCompanyname().trim()));
            companyRepository.save(company);
        }

        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .middlename(registerRequest.getMiddlename())
                .email(registerRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(registerRequest.getPassword()))
                .companyname(registerRequest.getCompanyname().trim())
                .company(company)
                .role(registerRequest.getCompanyname() == null || registerRequest.getCompanyname().trim() == ""? ROLE_USER : ROLE_DIRECTOR)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .message("User registered successfully")
                .response(HttpStatus.OK)
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        String message = user.getRole().equals(ROLE_ADMIN) ? "Admin logged in successfully" : "User logged in successfully";

        return AuthResponse.builder()
                .message(message)
                .response(HttpStatus.OK)
                .token(token)
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .companyname(user.getCompanyname())
                .id(user.getId())
                .build();
    }
}