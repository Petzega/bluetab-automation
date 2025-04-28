package com.bluetab.triangulo.utils;

import java.util.Map;

public class PublicVariables {

    public static final String OUTPUT_FILE = "src/test/resources/img/triangle.png";
    public static final String OUTPUT_FILE_VALIDATOR = "src/test/resources/img/triangle_text.png";
    public static final String OUTPUT_TESSDATA = "src/test/resources/tessdata";
    public static final String OUTPUT_TESSDATA_ENG = "eng";
    public static final String TRIANGULO_ESCALENO = "scalene";
    public static final String TRIANGULO_EQUILATERO = "equliateral";
    public static final String TRIANGULO_ISOSCELES = "isosceles";
    public static final String TRIANGULO_NOT_A_TRIANGLE = "notatriangle";
    public static final String TRIANGULO_TEXTO_ERROR = "no se debe ingresar texto";
    public static final String TRIANGULO_CAMPOS_VACIOS_ERROR = "campos vacios";
    public static final Map<String, String> TRIANGLE_TYPE_MAP = Map.of(
            "escaleno", TRIANGULO_ESCALENO,
            "equilatero", TRIANGULO_EQUILATERO,
            "isosceles", TRIANGULO_ISOSCELES
    );

    public static final Map<String, String> TRIANGLE_ERROR_MAP = Map.of(
            "no es triangulo", TRIANGULO_NOT_A_TRIANGLE,
            "no se debe ingresar texto", TRIANGULO_TEXTO_ERROR,
            "campos vacios", TRIANGULO_CAMPOS_VACIOS_ERROR
    );
}
