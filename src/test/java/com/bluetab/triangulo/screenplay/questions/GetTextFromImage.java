package com.bluetab.triangulo.screenplay.questions;

import com.bluetab.triangulo.utils.PublicVariables;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class GetTextFromImage implements Question<String> {

    private final BufferedImage img;

    public GetTextFromImage(BufferedImage img) {
        this.img = img;
    }

    public static GetTextFromImage from(BufferedImage img) {
        return new GetTextFromImage(img);
    }

    @Override
    public String answeredBy(Actor actor) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(PublicVariables.OUTPUT_TESSDATA);
        tesseract.setLanguage(PublicVariables.OUTPUT_TESSDATA_ENG);

        String ocrResult;
        try {
            ocrResult = tesseract.doOCR(img)
                    .trim()
                    .replaceAll("[^a-zA-Z]", "")
                    .toLowerCase();

            return ocrResult;
        } catch (TesseractException e) {
            throw new AssertionError("OCR Error: " + e.getMessage());
        }
    }
}
