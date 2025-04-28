package com.bluetab.triangulo.runner;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:_target/cucumber/cucumber.json", "html:target/cucumber-reports.html"},
        features = "src/test/resources/features",
        glue = {"com.bluetab.triangulo.stepDefinitions", "com.bluetab.triangulo.utils"},
        tags = "@triangulo_01",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class CucumberRunner {
}
