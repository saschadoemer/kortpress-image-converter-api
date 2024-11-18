package de.saschadoemer.kortpress;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "The request for adding a white background to an existing image.")
public class AddImageToWhiteBackgroundRequest {

    @Positive
    @NotNull
    @Schema(description = "The width of the white background.")
    private int width;

    @Positive
    @NotNull
    @Schema(description = "The height of the white background.")
    private int height;

    @NotNull
    @NotBlank
    @Schema(description = "The base64 encoded image.")
    private String base64EncodedImage;

}
