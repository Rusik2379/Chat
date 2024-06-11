package Sber.Sber.auth.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor

public class AuthResponse {

    private String token;
    private String message;
    private HttpStatus response;
    private String email;
    private String role;
    private String companyname;
    private Long id;

    public AuthResponse(String token, String message, HttpStatus response, String email, String role, String companyname, Long id) {
        this.token = token;
        this.message = message;
        this.response = response;
        this.email = email;
        this.role = role;
        this.companyname = companyname;
        this.id = id;
    }

    public AuthResponse(String message, HttpStatus response) {
        this.message = message;
        this.response = response;
    }
}
