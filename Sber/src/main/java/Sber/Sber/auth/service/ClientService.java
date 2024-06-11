package Sber.Sber.auth.service;

import Sber.Sber.auth.request.*;
import Sber.Sber.auth.response.AuthResponse;
import Sber.Sber.models.*;
import Sber.Sber.repositories.*;
import Sber.Sber.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor


public class ClientService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RealtyRepository realtyRepository;
    private final ClientRepository clientRepository;

    public AuthResponse createClient(CreateClientRequest createClientRequest){

        Company company = companyRepository.findById(createClientRequest.getId_company())
                .orElseThrow(() -> new RuntimeException("Компания не найдена"));

        User user = userRepository.findById(createClientRequest.getId_user())
                .orElseThrow(() -> new RuntimeException("Автор не найден"));

        Client client = Client.builder()
                .email(createClientRequest.getEmail())
                .numberPhone(createClientRequest.getNumberPhone())
                .middlename(createClientRequest.getMiddlename())
                .firstname(createClientRequest.getFirstname())
                .lastname(createClientRequest.getLastname())
                .age(createClientRequest.getAge())
                .user(user)
                .company(company)
                .build();

        clientRepository.save(client);

        return AuthResponse.builder()
                .message("Realty created successfully")
                .response(HttpStatus.OK)
                .build();

    }

    public List<ClientRepository.ClientProjection> findByCompanyId(Long id) {
        return clientRepository.findByCompany_Id(id);
    }
}
