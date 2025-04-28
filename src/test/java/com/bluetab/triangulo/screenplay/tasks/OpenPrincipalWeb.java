package com.bluetab.triangulo.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenPrincipalWeb implements Task {

    private String url;

    public OpenPrincipalWeb(String url) {
        this.url = url;
    }

    public static OpenPrincipalWeb open(String url) {
        return new OpenPrincipalWeb(url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );
    }
}
