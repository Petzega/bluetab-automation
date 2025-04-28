# bluetab-automation

* Usar el proyecto con java 17
* Ejecutar todas las pruebas
  - ./gradlew clean test
* Ejecutar usando tags
  - PowerShell: .\gradlew.bat "-Dcucumber.filter.tags=@triangulo" clean test
  - bash: ./gradlew -Dcucumber.filter.tags="@triangulo" clean test
