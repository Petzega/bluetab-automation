package com.bluetab.triangulo.stepDefinitions;

import com.bluetab.triangulo.screenplay.questions.GetCanvasBase64;
import com.bluetab.triangulo.screenplay.tasks.OpenPrincipalWeb;
import com.bluetab.triangulo.screenplay.tasks.SetData;
import com.bluetab.triangulo.screenplay.userInterface.TriangleCalculatorPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static net.serenitybdd.screenplay.actors.OnStage.*;


public class TrianguloTest {

    @Managed
    WebDriver driver;

    @Steps(shared = true)
    Actor actor = Actor.named("Usuario");

    private static EnvironmentVariables environmentVariables;

    @Dado("el usuario ingresa a la web Triangle Calculator")
    public void goToWeb() {
        actor.can(BrowseTheWeb.with(driver));
        theActorCalled(actor.getName()).wasAbleTo(OpenPrincipalWeb.open(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("baseurl")));
    }

    @Cuando("ingresa el nombre {string} mas la medida {string} del lado A con la medida {string} del lado B mas la medida {string} del lado C")
    public void setData(String nombre, String medida_a, String medida_b, String medida_c) {
        theActorInTheSpotlight().attemptsTo(
                SetData.insertData(nombre, medida_a, medida_b, medida_c)
        );
    }

    @SneakyThrows
    @Entonces("el sistema valida que los lados ingresados forman un triangulo de tipo {string}")
    public void validateTriangle(String triangle) {
        String base64Image = theActorInTheSpotlight().asksFor(GetCanvasBase64.from(TriangleCalculatorPage.CANVA_NAME));
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
        BufferedImage img = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));

        File outputFile = new File("src/test/resources/img/triangle.png");
        ImageIO.write(img, "png", outputFile);

        int x = 0;
        int y = 275;
        int width = 60;
        int height = 25;
        BufferedImage croppedImg = img.getSubimage(x, y, width, height);

        // 3. Preprocesamiento: Escala de grises + escalado 15x
        BufferedImage processedImg = new BufferedImage(
                croppedImg.getWidth() * 15,  // Escala más agresiva
                croppedImg.getHeight() * 15,
                BufferedImage.TYPE_BYTE_GRAY
        );
        Graphics2D g = processedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC); // Mejor calidad
        g.drawImage(croppedImg, 0, 0, processedImg.getWidth(), processedImg.getHeight(), null);
        g.dispose();

        // Eliminar 13 píxeles de largo a la izquierda
        int newWidth = processedImg.getWidth() - 30;
        BufferedImage croppedProcessedImg = processedImg.getSubimage(30, 0, newWidth, processedImg.getHeight());

        // Guardar la imagen procesada recortada para depuración
        File debugFile = new File("src/test/resources/img/debug_english_text_cropped.png");
        ImageIO.write(croppedProcessedImg, "png", debugFile);

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/test/resources/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_SINGLE_WORD); // Modo para una palabra
        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY); // Mejor precisión

        String ocrResult;
        try {
            ocrResult = tesseract.doOCR(croppedProcessedImg)
                    .trim()
                    .replaceAll("[^a-zA-Z]", "") // Solo letras inglesas
                    .toLowerCase();

            System.out.println("OCR Result: " + ocrResult);
        } catch (TesseractException e) {
            throw new AssertionError("OCR Error: " + e.getMessage());
        }
    }
}
