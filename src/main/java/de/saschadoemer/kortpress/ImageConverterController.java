package de.saschadoemer.kortpress;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/image-converter")
public class ImageConverterController {

    @Operation(
            summary = "Add a white background to an existing image. The image will be scaled and fitted into the center of the white background.",
            description = "The image will be scaled and fitted into the center of the white background. The white background will be created with the specified width and height.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The image with white background as PNG image.", content = @Content(mediaType = MediaType.IMAGE_PNG_VALUE)),
                    @ApiResponse(responseCode = "400", description = "Error adding white background to image.")
            }
    )
    @PostMapping(value = "/white-background", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> addWhiteBackgroundToImage(@Valid @RequestBody AddImageToWhiteBackgroundRequest request) {
        try {
            var width = request.getWidth();
            var height = request.getHeight();
            var image = Base64.getDecoder().decode(request.getBase64EncodedImage());
            var bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            var newImage = ImageConverter.addWhiteBackground(bufferedImage, width, height);
            return ResponseEntity.ok(newImage);
        } catch (Exception e) {
            log.error("Error adding white background to image", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
