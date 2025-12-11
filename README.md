# Proyecto Alpina ADS

Repositorio del proyecto de **Análisis y Diseño de Sistemas** basado en la empresa Alpina.

## 1. Tecnologías que usamos y por qué

- **Java 17**  
  Versión LTS actual, es la que se está usando en el curso y nos permite usar APIs modernas sin complicarnos con compatibilidad.

- **Maven**  
  Nos simplifica:
  - gestión de dependencias (JUnit, Checkstyle, etc.),
  - ejecución de pruebas (`mvn test`),
  - y un único comando para todo el pipeline (`mvn verify`).

- **JUnit 5**  
  Framework estándar de pruebas unitarias en Java.  
  Lo usamos para validar la lógica de negocio de cada historia.

- **Checkstyle**  
  Herramienta de **análisis estático** que revisa el estilo y algunas buenas prácticas de código.  
  La integramos en la fase `verify` de Maven para que falle el build si rompemos reglas importantes.

- **GitHub Actions**  
  Plataforma de CI de GitHub.  
  Configuramos un workflow que corre `mvn verify` en cada push y en cada Pull Request, así el profe ve que:
  - el código compila,
  - los tests pasan,
  - y el análisis estático se ejecuta automáticamente.

---

## 2. Estructura del proyecto

El proyecto sigue la estructura típica de Maven:

```text
proyecto-alpina-ads/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  └─ java/
│  │     └─ com/alpina/core/   # Código de dominio (servicios, entidades, enums)
│  └─ test/
│     └─ java/
│        └─ com/alpina/core/   # Pruebas unitarias de cada servicio
└─ .github/
   └─ workflows/
      └─ ci.yml               # Workflow de GitHub Actions
