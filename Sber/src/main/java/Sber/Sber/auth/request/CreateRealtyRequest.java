package Sber.Sber.auth.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data

public class CreateRealtyRequest {

    private List<MultipartFile> images;

    @NotBlank
    private String realtyname;

    @NotBlank
    private Double price;

    @NotBlank
    private Double floor;

    @NotBlank
    private Double allfloor;

    @NotBlank
    private Double numberRooms;

    @NotBlank
    private Double square;

    @NotBlank
    private String type;

    @NotBlank
    private String adress;

    @NotBlank
    private Integer year;

    @NotBlank
    private String status;

    @NotBlank
    private String companyname;

    @NotBlank
    private Long id_user;

    @NotBlank
    private String description;

    @NotBlank
    private Long idimage;

}
