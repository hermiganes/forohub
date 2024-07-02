# ForoHub

![Estado del Proyecto](https://img.shields.io/badge/ESTADO-EN_DESARROLLO-green)
![Java JDK](https://img.shields.io/badge/Java_JDK-v17.0-blue)
![IDE](https://img.shields.io/badge/IDE-Intellij_IDEA-blue)
![Project](https://img.shields.io/badge/Project-Maven-blue)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v3.3.0-blue)
![MySQL](https://img.shields.io/badge/MySQL-v8.0-blue)

![GRÁFICA](https://images.pexels.com/photos/4050312/pexels-photo-4050312.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1)

## Introducción

**ForoHub** es una plataforma API REST desarrollada con Spring Boot, diseñada para facilitar la gestión de tópicos en una comunidad virtual. Permite a los usuarios autenticarse, crear, listar y eliminar tópicos de discusión de manera eficiente y segura mediante autentificación JWT.

## Características ✨

- **Registro de Usuarios**: Registro seguro de nuevos usuarios mediante endpoint `POST /signup`.
- **Autenticación JWT**: Generación de tokens JWT para inicio de sesión seguro con `POST /login`.
- **Gestión de Tópicos**: Creación (`POST /topicos`), listado (`GET /topicos` o `GET /topicos{id}` ), actualización (`PUT /topicos/{id}`) y eliminación (`DELETE /topicos/{id}`) de tópicos disponibles.

## Dependencias 🛠️

El proyecto hace uso de diversas tecnologías y herramientas indispensables:

- **Spring Security**: Para la gestión de la seguridad y autenticación.
- **JWT (JSON Web Tokens)**: Para la creación y validación de tokens de autenticación.
- **JPA (Java Persistence API)**: Para la persistencia de los datos.
- **MySQL**: Base de datos relacional utilizada para el almacenamiento de datos.
- **Lombok**: Para la reducción de código en la creación de getters y setters,
- **Flyway**: Para la migración y versionado de la base de datos.
- **Swagger**: Para la documentación automática de la API.

## Instalación 🚧

Para comenzar con ForoHub, sigue estos pasos:

1. **Clonar este repositorio**:
```bash
git clone https://github.com/hermiganes/forohub.git
```

2. **Configuración de la base de datos:**

Deberas configurar el archivo `application.properties` para modificar las credenciales a la base de datos. El proyecto está configurado para usar la base de datos "db_alura". Si necesitas crear la base de datos en MySQL, ejecuta el siguiente comando SQL:
```sql
CREATE DATABASE db_alura;
```
## Ejemplos de Solicitudes 

### - Autenticación 🔐

**Solicitud:**

```http
POST http://localhost:8080/login
```
**Body:**
```json

{
    "usuario": "nombre_usuario",
    "clave": "contraseña"
}
```
**Respuesta Esperada:**

```json
{
    "jwTtoken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc3VhcmlvMSIsImlzcyI6ImVzY2EiLCJpZCI6MSwiZXhwIjoxNzE5ODc4NjUwfQ.Khz2AQufY1erGkA5vM5ENLYz2B4-3bMfzuO5QdbCEA8"
}
```
### - Listado de un tópico por id 📖
**Solicitud:**

```http
POST http://localhost:8080/topico/{id}
```
**Respuesta Esperada:**

```json
{
  "id": 1,
  "titulo": "Error 4",
  "mensaje": "Este es mi problema",
  "autor": "Usuario2",
  "curso": "Curso de Java",
  "fecha": "2024-07-01T18:39:20"
}
```
### Borrar un topico
**Solicitud para desactivar un tópico:**

```http
DELETE http://localhost:8080/topico/{id}
```
**Solicitud para borrar un tópico de la base de datos:**

```http
DELETE http://localhost:8080/topico/{id}?borrar=true
```

## Documentación Adicional 📘

Para más detalles sobre los endpoints disponibles y la estructura de las solicitudes, visita la interfaz de Swagger en:
```
http://localhost:8080/swagger-ui/index.html

