package com.bluetab.triangulo.screenplay.questions;

import com.bluetab.triangulo.utils.PublicVariables;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GetProcessedImage implements Question<BufferedImage> {

    private final BufferedImage originalImage;

    public GetProcessedImage(BufferedImage originalImage) {
        this.originalImage = originalImage;
    }

    public static GetProcessedImage from(BufferedImage originalImage) {
        return new GetProcessedImage(originalImage);
    }

    @SneakyThrows
    @Override
    public BufferedImage answeredBy(Actor actor) {
        File outputFile = new File(PublicVariables.OUTPUT_FILE);
        ImageIO.write(originalImage, "png", outputFile);

        int x = 0;
        int y = 275;
        int width = 80;
        int height = 25;
        BufferedImage croppedImg = originalImage.getSubimage(x, y, width, height);

        BufferedImage processedImg = new BufferedImage(
                croppedImg.getWidth() * 15,
                croppedImg.getHeight() * 15,
                BufferedImage.TYPE_BYTE_GRAY
        );
        Graphics2D g = processedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(croppedImg, 0, 0, processedImg.getWidth(), processedImg.getHeight(), null);
        g.dispose();

        int newWidth = processedImg.getWidth() - 15;
        BufferedImage croppedProcessedImg = processedImg.getSubimage(15, 0, newWidth, processedImg.getHeight());

        File debugFile = new File(PublicVariables.OUTPUT_FILE_VALIDATOR);
        ImageIO.write(croppedProcessedImg, "png", debugFile);

        return croppedProcessedImg;
    }
}
