package Sber.Sber.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class SellRealtyRequest {

    @NotBlank
    private Long id_user;

    @NotBlank
    private Long id_client;

    @NotBlank
    private Long id_realty;

}
