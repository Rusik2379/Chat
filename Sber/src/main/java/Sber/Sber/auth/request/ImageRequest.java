package Sber.Sber.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data

public class ImageRequest {

    private List<ImageRequest> images;

    @NotBlank
    private String name;

    @NotBlank
    private byte[] bytes;

    @NotBlank
    private boolean isPreviewImage;
}
