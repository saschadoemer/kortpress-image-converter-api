package de.saschadoemer.kortpress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddImageToWhiteBackgroundRequest {

    private int width;
    private int height;
    private String base64EncodedImage;

}
