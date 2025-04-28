package com.bluetab.triangulo.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetCanvasBase64 implements Question<String> {

    private final Target canvasTarget;

    public GetCanvasBase64(Target canvasTarget) {
        this.canvasTarget = canvasTarget;
    }

    public static GetCanvasBase64 from(Target canvasTarget) {
        return new GetCanvasBase64(canvasTarget);
    }

    @Override
    public String answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement canvasElement = canvasTarget.resolveFor(actor);
        String base64 = (String)((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].toDataURL('image/png').split(',')[1];",
                        canvasElement);
        return base64;
    }
}
