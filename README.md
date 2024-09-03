# Kata Números romanos

### Fase 1

Desarrollar un componente que permita convertir números enteros a romanos y viceversa según el siguiente esquema:

* 1 ➔ I
* 2 ➔ II
* 3 ➔ III
* 4 ➔ IV
* 5 ➔ V
* 9 ➔ IX
* 21 ➔ XXI
* 50 ➔ L
* 100 ➔ C
* 500 ➔ D
* 1000 ➔ M


En ambos métodos de conversión, el componente debe validar si se ingresa un valor no permitido y responder con una excepción personalizada.

**Plus Fase 1:** Aplicar TDD o al menos hacer Tests unitarios del componente probando al menos 2 border cases para cada método de conversión.


### Fase 2

Exponer el método del componente que convierte valores numéricos arábigos a romanos en un endpoint (GET)
Exponer el método del componente que convierte valores numéricos romanos a arábigos en un endpoint (GET)

**Plus Fase 2:** Aplicar TDD (Test de integración usando la suite de Spring).


### Requerimientos/Restricciones

**Fase 1 y 2:** Usar Java 17 o superior. Maven o Gradle para la gestión de dependencias.
Para los puntos plus de cada fase, en lo relacionado a la infraestructura de tests se pueden usar las siguientes herramientas JUnit5+Mockito o Spock y Spring Boot Testing.
**Fase 2:** Usar Spring boot 3+.


### NOTAS

Compilacion y ejecucion de tests: 
    - mvn clean install

Deploy local (en la raiz del proyecto se encuentra el archivo .yaml):
    docker-compose -f docker-compose.yml up -d

- Tecnologias:

    - Java 17
    - Springboot version 3.3.3
    - Maven
    - Junit 5
    - Mockito
    - Jacoco
    - Docker