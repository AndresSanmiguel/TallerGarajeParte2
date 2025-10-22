# 🚗 Proyecto #2 — Sistema de Gestión de Garaje  
Aplicación CRUD desarrollada con Jakarta EE, Servlets, JDBC y JSP

Autor: Diego Andrés Sanmiguel Ortiz  
Correo: dandressanmiguel@uts.edu.co  
Repositorio: https://github.com/AndresSanmiguel/TallerGarajeParte2

---

## 📋 Descripción General
Este proyecto consiste en una aplicación web que permite gestionar los vehículos registrados en un garaje.  
Está desarrollada con Jakarta EE (Java EE) y usa una arquitectura multicapa que separa la lógica de negocio, la persistencia de datos y la presentación.  
El sistema permite realizar las operaciones básicas de un CRUD: crear, listar, actualizar y eliminar vehículos conectados a una base de datos MySQL.

---

## 🏗️ Arquitectura del Sistema
El sistema está organizado por capas siguiendo el modelo MVC (Modelo - Vista - Controlador):

```
┌─────────────────────────────────────────┐
│          CAPA DE PRESENTACIÓN           │
│        (JSP + Bootstrap + JS)           │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│         CAPA DE CONTROLADORES           │
│           (Servlets/Web)                │
│     com.garaje.controller               │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│          CAPA DE NEGOCIO                │
│        (Facade/Lógica)                  │
│       com.garaje.facade                 │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│        CAPA DE PERSISTENCIA             │
│              (DAO/JDBC)                 │
│      com.garaje.persistence             │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│            BASE DE DATOS                │
│              (MySQL)                    │
└─────────────────────────────────────────┘
```

---

## 📦 Estructura del Proyecto

```
com.garaje/
├── model/              # Entidades del dominio
│   └── Vehiculo.java
├── persistence/        # Acceso a datos (DAO)
│   └── VehiculoDAO.java
├── facade/             # Lógica de negocio
│   └── VehiculoFacade.java
├── controller/         # Controladores web (Servlets)
│   └── VehiculoServlet.java
└── exception/          # Excepciones personalizadas
    └── BusinessException.java
```

---

## 🗄️ Base de Datos (MySQL)

```sql
CREATE DATABASE garaje;
USE garaje;

CREATE TABLE vehiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL UNIQUE,
    marca VARCHAR(30) NOT NULL,
    modelo VARCHAR(30) NOT NULL,
    anio INT NOT NULL,
    color VARCHAR(20),
    propietario VARCHAR(50) NOT NULL
);
```

---

## ⚙️ Reglas de Negocio

1. No se permite registrar vehículos con placas duplicadas.  
2. El propietario debe tener al menos 5 caracteres.  
3. Marca, modelo y placa deben tener mínimo 3 caracteres.  
4. Solo se aceptan los colores: Rojo, Blanco, Negro, Azul y Gris.  
5. No se admiten vehículos con más de 20 años de antigüedad.  
6. Solo se pueden actualizar o eliminar vehículos existentes.  
7. No se puede eliminar un vehículo si el propietario es “Administrador”.  
8. Validación para evitar inyecciones SQL (DROP, DELETE, INSERT, etc.).  
9. Si la marca es Ferrari, el sistema genera una notificación en consola.  

---

## 🔧 Configuración del Proyecto

### Requisitos
- JDK: 21 o superior  
- Servidor: GlassFish 7.0.11  
- IDE: Apache NetBeans 20+  
- Base de datos: MySQL 8.0  
- Driver JDBC: mysql-connector-j-8.x.jar

### Configuración de la Base de Datos

Datos de conexión:
- Base de datos: garaje  
- Usuario: root  
- Contraseña: 123

DataSource en GlassFish:
- Nombre JNDI: jdbc/garaje  
- Driver: com.mysql.cj.jdbc.Driver  
- URL: jdbc:mysql://localhost:3306/garaje?useSSL=false&serverTimezone=UTC  

---

## 🚀 Cómo Ejecutarlo (NetBeans + GlassFish)

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/AndresSanmiguel/TallerGarajeParte2.git
   ```
2. Abrir el proyecto en NetBeans.  
3. Configurar el servidor GlassFish 7.0.11.  
4. Crear la base de datos garaje y ejecutar el script SQL.  
5. Configurar el DataSource jdbc/garaje.  
6. Ejecutar Clean and Build.  
7. Correr el proyecto y acceder en el navegador:  
   ```
   http://localhost:8080/TallerGarajeParte2
   ```

---

## 🧠 Flujo de Trabajo con Git

Ramas utilizadas:
- main: rama principal del proyecto.  
- feature/*: desarrollo de nuevas funciones.  
- fix/*: correcciones de errores.

Ejemplo:
```bash
git checkout -b feature/validacion-color
git add .
git commit -m "Implementa validación para colores permitidos"
git push origin feature/validacion-color
```

---

## 📊 Diagrama de Clases

```
Vehiculo
 ├── id: int
 ├── placa: String
 ├── marca: String
 ├── modelo: String
 ├── anio: int
 ├── color: String
 ├── propietario: String
 └── getters/setters

VehiculoDAO
 ├── listar()
 ├── buscarPorId()
 ├── agregar()
 ├── actualizar()
 ├── eliminar()
 └── existePlaca()

VehiculoFacade
 ├── validarVehiculo()
 ├── agregar()
 ├── actualizar()
 ├── eliminar()
 └── aplicarReglasNegocio()
```

---

## 🎨 Interfaz de Usuario
Diseñada con JSP y Bootstrap 5.  
Permite:
- Listar vehículos registrados.  
- Agregar, editar y eliminar.  
- Mostrar alertas con mensajes de validación.  

---

## 🛡️ Seguridad y Validaciones
- Uso de PreparedStatement para prevenir inyección SQL.  
- Validaciones tanto en cliente (HTML/JS) como en servidor (Facade).  
- Manejo de excepciones mediante BusinessException.  
- Sin exposición de errores al usuario final.  

---

## 📈 Mejoras Pendientes
- Implementar autenticación de usuarios.  
- Agregar búsqueda y filtros avanzados.  
- Reportes en PDF o Excel.  
- API REST para integraciones externas.  

---

## 👨‍💻 Autor
Nombre: Diego Andrés Sanmiguel Ortiz  
Correo: dandressanmiguel@uts.edu.co  
Programa: Tecnología en Desarrollo de Sistemas Informáticos — UTS  
Año: 2025  

---

Proyecto académico desarrollado con fines educativos para la Universidad de Santander (UTS).
