package com.bluetab.triangulo.stepDefinitions;

import com.bluetab.triangulo.screenplay.questions.GetCanvasBase64;
import com.bluetab.triangulo.screenplay.questions.GetProcessedImage;
import com.bluetab.triangulo.screenplay.questions.GetTextFromImage;
import com.bluetab.triangulo.screenplay.tasks.OpenPrincipalWeb;
import com.bluetab.triangulo.screenplay.tasks.SetData;
import com.bluetab.triangulo.screenplay.userInterface.TriangleCalculatorPage;
import com.bluetab.triangulo.utils.PublicVariables;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.equalTo;


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

       BufferedImage croppedProcessedImg = theActorInTheSpotlight().asksFor(GetProcessedImage.from(img));

       String result = theActorInTheSpotlight().asksFor(GetTextFromImage.from(croppedProcessedImg));

       String expected = PublicVariables.TRIANGLE_TYPE_MAP.getOrDefault(
               triangle.toLowerCase(), triangle.toLowerCase()
       );

        theActorInTheSpotlight().should(
                seeThat("Se validan los resultados", actor -> result, equalTo(expected))
        );
    }

    @SneakyThrows
    @Entonces("el sistema valida que los lados ingresados no forman un triangulo con el mensaje de error {string}")
    public void validateTriangleError(String error) {
        String base64Image = theActorInTheSpotlight().asksFor(GetCanvasBase64.from(TriangleCalculatorPage.CANVA_NAME));
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
        BufferedImage img = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));

        BufferedImage croppedProcessedImg = theActorInTheSpotlight().asksFor(GetProcessedImage.from(img));

        String result = theActorInTheSpotlight().asksFor(GetTextFromImage.from(croppedProcessedImg));

        String expected = PublicVariables.TRIANGLE_ERROR_MAP.getOrDefault(
                error.toLowerCase(), error.toLowerCase()
        );

        theActorInTheSpotlight().should(
                seeThat("Se mensaje de error", actor -> result, equalTo(expected))
        );
    }
}
