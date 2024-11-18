package de.saschadoemer.kortpress;

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

    @PostMapping(value = "/white-background", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> addWhiteBackgroundToImage(@RequestBody AddImageToWhiteBackgroundRequest request) {
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
