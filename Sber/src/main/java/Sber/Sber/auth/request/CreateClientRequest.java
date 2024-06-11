package Sber.Sber.auth.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data

public class CreateClientRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;

    @NotBlank
    private String middlename;

    @NotBlank
    private Integer age;

    @NotBlank
    private String numberPhone;

    @NotBlank
    private Long id_user;

    @NotBlank
    private Long id_company;
}
