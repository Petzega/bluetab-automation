#language:es
  @triangulo
  Característica: Validar si las medidas de los lados ingresados forman un triangulo
    Como usuario validador
    Quiero ingresar la medida de tres lados
    Para validar la creacion correcta de un triangulo

    Antecedentes:
      Dado el usuario ingresa a la web Triangle Calculator

    @triangulo_01
    Esquema del escenario: [HAPPY PATH] Validar la creacion exitosa de un triangulo segun las medidas ingresadas
      Cuando ingresa el nombre "<nombre>" mas la medida "<medida_a>" del lado A con la medida "<medida_b>" del lado B mas la medida "<medida_c>" del lado C
      Entonces el sistema valida que los lados ingresados forman un triangulo de tipo "<tipo_triangulo>"
      Ejemplos:
        | nombre| medida_a | medida_b | medida_c | tipo_triangulo |
        | usuario | 3                | 4                | 5                | escaleno          |
        | usuario | 3                | 3                | 3                | equilatero          |
        | usuario | 3                | 3                | 4                | isosceles          |

    @triangulo_02
    Esquema del escenario: [UNHAPPY PATH] Validar mensaje error al intentar crear un triangulo con medidas invalidas
      Cuando ingresa el nombre "<nombre>" mas la medida "<medida_a>" del lado A con la medida "<medida_b>" del lado B mas la medida "<medida_c>" del lado C
      Entonces el sistema valida que los lados ingresados no forman un triangulo con el mensaje de error "<mensaje_error>"
      Ejemplos:
        | nombre| medida_a | medida_b | medida_c | mensaje_error                       |
        | usuario | 3                | 90                | 4                | no es triangulo                    |
        | usuario | ABC          | TEXTO         | PRUEBA  | no se debe ingresar texto |
        | usuario |                   |                      |                   | campos vacios                     |