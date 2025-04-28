package com.bluetab.triangulo.screenplay.tasks;

import com.bluetab.triangulo.screenplay.userInterface.TriangleCalculatorPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class SetData implements Task {

    private String nombre;
    private String medida_a;
    private String medida_b;
    private String medida_c;

    public SetData(String nombre, String medida_a, String medida_b, String medida_c) {
        this.nombre = nombre;
        this.medida_a = medida_a;
        this.medida_b = medida_b;
        this.medida_c = medida_c;
    }

    public static SetData insertData(String nombre, String medida_a, String medida_b, String medida_c) {
        return Tasks.instrumented(SetData.class, nombre, medida_a, medida_b, medida_c);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(nombre).into(TriangleCalculatorPage.INPUT_NAME),
                Enter.theValue(medida_a).into(TriangleCalculatorPage.INPUT_SIDE_A),
                Enter.theValue(medida_b).into(TriangleCalculatorPage.INPUT_SIDE_B),
                Enter.theValue(medida_c).into(TriangleCalculatorPage.INPUT_SIDE_C),
                Click.on(TriangleCalculatorPage.BUTTON_CHECK)
        );
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
