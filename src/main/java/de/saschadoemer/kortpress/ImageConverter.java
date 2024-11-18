package de.saschadoemer.kortpress;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class ImageConverter {

    public static byte[] addWhiteBackground(BufferedImage bufferedImage, int width, int height) {

        log.debug("Create white background image with width {} and height {}", width, height);
        var background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        var graphics = background.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        log.debug("Draw image on white background");
        double ratio = Math.min((double) width / bufferedImage.getWidth(), (double) height / bufferedImage.getHeight());
        int scaledWidth = (int) (bufferedImage.getWidth() * ratio);
        int scaledHeight = (int) (bufferedImage.getHeight() * ratio);

        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;
        graphics.drawImage(bufferedImage, x, y, scaledWidth, scaledHeight, null);
        graphics.dispose();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(background, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("Error converting image to byte array", e);
            return null;
        }

    }
}
