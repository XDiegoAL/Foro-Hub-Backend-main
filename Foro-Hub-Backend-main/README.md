<h1 align="center"> Forohub backend </h1>


<p align="center">
   <img src="https://github.com/BogdanRivera/Foro-Hub-Backend/assets/121648408/31b27130-9101-4e25-a972-1c6e7377c86a" alt="imagenforo" width="400">
</p>

<p align="center">
   <img src="https://img.shields.io/badge/STATUS-In_progress-blue" alt="status">
</p>


ForoHub API es una aplicación de backend desarrollada con Spring Boot como parte del proyecto final de ciertificación de Alura en la formación de "Desarrollador backend" la cual proporciona una plataforma de foros donde los usuarios pueden crear, leer, actualizar y eliminar tópicos de discusión. La API permite gestionar usuarios, cursos, y perfiles, facilitando la interacción entre ellos. Los tópicos están categorizados por cursos y pueden tener múltiples respuestas de diferentes usuarios. La API incluye endpoints para realizar operaciones CRUD en estos elementos, asegurando la integridad de los datos y proporcionando respuestas adecuadas a las solicitudes HTTP.

## Features

* Gestión de Usuarios: Registro, actualización y eliminación de usuarios.\
* Gestión de Tópicos: Creación, actualización, listado y eliminación de tópicos.\
* Gestión de Respuestas: Los usuarios pueden responder a tópicos y estas respuestas pueden ser gestionadas.\
* Gestión de Cursos: Administración de los cursos que categorizan los tópicos.\
* Autenticación y Autorización: Implementación de seguridad utilizando Spring Security.\
* Paginación y Filtros: Soporte para paginación en listados de tópicos y filtros específicos, como búsqueda por año y curso.\
* Documentación en OpenAPI3: Pruebas de la API en una página interactiva HTML.

## Tecnologías usadas

* Spring Boot: Framework principal para el desarrollo de la aplicación.\
* Spring Data JPA: Manejo de la persistencia de datos y consultas a la base de datos.\
* Spring Security: Implementación de seguridad para la autenticación y autorización de usuarios.\
* MySQL: Base de datos relacional utilizada para almacenar la información de la aplicación.\
* Insomnia: Herramienta para probar y consumir la API REST.\
* OpenAPI3: Documentación visual de ayuda para probar la API REST.\

## Como usar

### Requisitos Previos
Java Development Kit (JDK): Asegúrate de tener instalado JDK 8 o superior.
Maven: Debes tener Maven instalado para manejar las dependencias del proyecto.
MySQL: Instala MySQL y asegúrate de tener una base de datos creada para este proyecto.
IDE: Recomendado utilizar un IDE como IntelliJ IDEA, Eclipse, o Spring Tool Suite.


### Configuración de la Base de Datos
#### Crear una base de datos

```
CREATE DATABASE forohub;
```

### Configurar las Credenciales de la Base de Datos:
Asegúrate de que tu archivo application.properties en el directorio src/main/resources tenga la siguiente configuración:
```
spring.datasource.url=jdbc:mysql://localhost/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

```

```
Configuración del Proyecto
Clonar el Repositorio:
Clona el repositorio del proyecto desde GitHub o cualquier otra fuente de control de versiones.
```

## Configuración del Proyecto
1. Clona el repositorio del proyecto desde GitHub o cualquier otra fuente de control de versiones.
```
git clone https://github.com/tu_usuario/forohub-api.git
cd forohub-api

```
2. Compilar el Proyecto:
Usa Maven para compilar el proyecto y descargar todas las dependencias necesarias.
```
mvn clean install
```
3. Iniciar la Aplicación:
Ejecuta la aplicación usando Maven o tu IDE preferido.
```
mvn spring-boot:run
```

### Uso de la API
1. Prueba la API con Insomnia o Postman:
Puedes usar herramientas como Insomnia o Postman para probar los endpoints de la API.
2. Endpoints Principales:

Crear Tópico (POST):
```
POST /topicos
Content-Type: application/json

{
  "titulo": "Nuevo Tópico",
  "mensaje": "Este es el contenido del nuevo tópico",
  "status": "abierto",
  "cursoId": 1,
  "autorId": 1
}

```
Listar tópicos: 
```
GET /topicos?page=0&size=10

```

Actualizar tópico: 

```
PUT /topicos/{id}
Content-Type: application/json

{
  "titulo": "Tópico Actualizado",
  "mensaje": "Este es el mensaje actualizado",
  "status": "cerrado",
  "cursoId": 2,
  "autorId": 2
}

```
Eliminar tópico: 

```
DELETE /topicos/{id}

```



