package Sber.Sber.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class InviteRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String companyname;
}