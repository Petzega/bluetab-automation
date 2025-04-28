package com.bluetab.triangulo.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class TriangleCalculatorPage extends PageObject {

    public static final Target INPUT_NAME = Target.the("insertar nombre").located(By.id("username"));
    public static final Target CANVA_NAME = Target.the("canva para triangulos").located(By.id("triangleBox"));
    public static final Target INPUT_SIDE_A = Target.the("lado A").located(By.id("SideA"));
    public static final Target INPUT_SIDE_B = Target.the("lado B").located(By.id("SideB"));
    public static final Target INPUT_SIDE_C = Target.the("lado C").located(By.id("SideC"));
    public static final Target BUTTON_CHECK = Target.the("boton Check").located(By.xpath("//button[contains(text(), 'Check')]"));
}
