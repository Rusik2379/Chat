package Sber.Sber.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data

public class DeleteUserRequest {
    @NotBlank
    private Long id;
}
