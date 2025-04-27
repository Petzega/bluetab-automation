package com.bluetab.triangulo.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "com.bluetab.triangulo.stepDefinitions",
        tags = "@triangulo_01"
)
public class CucumberRunner {

    static {
        WebDriverManager.chromedriver().setup();
    }

    public static WebDriver getDriver() {
        return new ChromeDriver();
    }
}
