#language:es
  @triangulo
  Característica: Validar si las medidas de los lados ingresados forman un triangulo
    Como usuario validador
    Quiero ingresar la medida de tres lados
    Para validar la creacion correcta de un triangulo

    @triangulo_01
    Esquema del escenario: [HAPPY PATH] Validar la creacion exitosa de un triangulo segun las medidas ingresadas
      Dado el usuario ingresa a la web Triangle Calculator
      Cuando ingresa el nombre "<nombre>" mas la medida "<medida_a>" del lado A con la medida "<medida_b>" del lado B mas la medida "<medida_c>" del lado C
      Entonces el sistema valida que los lados ingresados forman un triangulo de tipo "tipo_triangulo"
      Ejemplos:
        | nombre| medida_a | medida_b | medida_c | tipo_triangulo |
        | usuario | 3                | 4                | 5                | Escaleno          |
        | usuario | 3                | 3                | 3                | Equilatero          |
        | usuario | 3                | 3                | 4                | Isosceles          |
        | usuario | 3                | 90                | 4                | Not a triangle          |
