#language:es
  @triangulo
  Característica: Validar si las medidas de los lados ingresados forman un triangulo
    Como usuario validador
    Quiero ingresar la medida de tres lados
    Para validar la creacion correcta de un triangulo

    @triangulo_01
    Esquema del escenario: [HAPPY PATH] Validar la creacion exitosa de un triangulo segun las medidas ingresadas
      Dado el usuario ingresa a la web "Triangule Calculator"
      Cuando ingresa el nombre del usuario
      Y coloca la medida del lado A "medida_a" del lado B "medida_b" mas la medida del lado C "medida_c"
      Entonces el sistema valida que los lados ingresados forman un triangulo de tipo "tipo_triangulo"
      Ejemplos:
        | medida_a | medida_b | medida_c | tipo_triangulo |
        | 3        | 4        | 5        | Escaleno       |
        | 3        | 3        | 3        | Equilatero     |
        | 3        | 3        | 4        | Isosceles      |
