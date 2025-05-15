# Bazar API REST

## Descripción

La **Bazar API REST** es una aplicación diseñada para demostrar mi capacidad en el desarrollo de servicios web modernos y eficientes. Este proyecto implementa una API RESTful que gestiona operaciones esenciales en un entorno de bazar: desde la gestión de productos hasta la administración de usuarios y transacciones. El objetivo es mostrar de forma clara y práctica mis habilidades técnicas y mi capacidad para desarrollar soluciones escalables, integrando calidad en cada línea de código.

## Características Principales

- **Arquitectura RESTful:** Cumple con las mejores prácticas de comunicación a través de HTTP.
- **Operaciones CRUD:** Permite crear, leer, actualizar y eliminar recursos de la aplicación.
- **Documentación Integrada:** Incluye documentación interactiva (por ejemplo, Swagger) para facilitar la comprensión del funcionamiento.
- **Seguridad y Validación:** Implementación básica de medidas de seguridad y validación de datos.
- **Contenerización con Docker:** Facilita el despliegue y asegura la consistencia entre entornos.

## Tecnologías Utilizadas

- **Java:** Lenguaje principal con el que se ha desarrollado la lógica de negocio.
- **Spring Boot:** Framework que permite desarrollar de forma rápida y robusta servicios web.
- **Maven:** Herramienta para gestionar dependencias y compilar el proyecto.
- **Docker:** Para el despliegue en contenedores y garantizar un entorno de ejecución confiable.
- **JPA/Hibernate:** Para la gestión de la persistencia de datos.

## Instrucciones de Instalación y Ejecución

### Requisitos Previos

- JDK 11 (o superior)
- Maven
- Docker (opcional, si se prefiere usar contenedores)

### Pasos para Ejecutar la Aplicación

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/matiasSonoda/bazar-app-deploy.git
   cd bazar-app-deploy/bazar
2. **Compilar el proyecto con Maven:**
   ```bash
   mvn clean install
3. **Ejecutar aplicacion:**
-  **Modo local:**
   ```bash
   mvn spring-boot:run
-  **Con Docker:**
   ```bash
   docker-compose up --build
4. **Acceder a la documentación:**
   Una vez iniciada la aplicacion, la documentacion interactiva estara disponible en http://localhost:8080/swagger-ui.html.

## Estructura del proyecto
  ```plaintext
  📦 
  ├─ bazar
  │  ├─ .env-template
  │  ├─ .gitattributes
  │  ├─ .gitignore
  │  ├─ .mvn
  │  │  └─ wrapper
  │  │     └─ maven-wrapper.properties
  │  ├─ Dockerfile
  │  ├─ mvnw
  │  ├─ mvnw.cmd
  │  ├─ pom.xml
  │  └─ src
  │     ├─ main
  │     │  ├─ java
  │     │  │  └─ com
  │     │  │     └─ api
  │     │  │        └─ bazar
  │     │  │           ├─ BazarApplication.java
  │     │  │           ├─ controller
  │     │  │           │  ├─ CustomerController.java
  │     │  │           │  ├─ ProductController.java
  │     │  │           │  └─ SaleController.java
  │     │  │           ├─ entity
  │     │  │           │  ├─ Customer.java
  │     │  │           │  ├─ Product.java
  │     │  │           │  ├─ ProductSale.java
  │     │  │           │  ├─ ProductSaleId.java
  │     │  │           │  ├─ Sale.java
  │     │  │           │  └─ dto
  │     │  │           │     ├─ CustomerDto.java
  │     │  │           │     ├─ HighestSaleDetailsDto.java
  │     │  │           │     ├─ ProductDto.java
  │     │  │           │     ├─ ProductSaleDto.java
  │     │  │           │     ├─ ProductSaleIdDto.java
  │     │  │           │     └─ SaleDto.java
  │     │  │           ├─ exception
  │     │  │           │  ├─ CustomerNotFoundException.java
  │     │  │           │  ├─ DuplicateResourceException.java
  │     │  │           │  ├─ EmptyStockException.java
  │     │  │           │  ├─ ProductNotFoundException.java
  │     │  │           │  └─ SaleNotFoundException.java
  │     │  │           ├─ repository
  │     │  │           │  ├─ CustomerRepository.java
  │     │  │           │  ├─ ProductRepository.java
  │     │  │           │  ├─ ProductSaleRepository.java
  │     │  │           │  └─ SaleRepository.java
  │     │  │           ├─ service
  │     │  │           │  ├─ CustomerService.java
  │     │  │           │  ├─ ProductSaleService.java
  │     │  │           │  ├─ ProductService.java
  │     │  │           │  └─ SaleService.java
  │     │  │           └─ utils
  │     │  │              └─ SaleUtils.java
  │     │  └─ resources
  │     │     └─ application.properties
  │     └─ test
  │        └─ java
  │           └─ com
  │              └─ api
  │                 └─ bazar
  │                    └─ BazarApplicationTests.java
  └─ docker-compose.yml
```
## Contacto
**Matias Sonoda** 
- Correo: matiasnsonoda@gmail.com 
- Linkedin: perfil de [LinkedIn](https://www.linkedin.com/in/matias-sonoda-840797198/)
- ¡Abierto a formar parte de un buen equipo de desarrollo para realizar las Apis mas eficaces!
