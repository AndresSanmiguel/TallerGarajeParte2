CREATE DATABASE IF NOT EXISTS garaje;
USE garaje;

CREATE TABLE IF NOT EXISTS vehiculos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  placa VARCHAR(20) NOT NULL,
  marca VARCHAR(30) NOT NULL,
  modelo VARCHAR(30) NOT NULL,
  anio INT NOT NULL,
  color VARCHAR(20),
  propietario VARCHAR(50) NOT NULL,
  UNIQUE KEY ux_placa (placa)
);

INSERT INTO vehiculos (placa, marca, modelo, anio, color, propietario)
VALUES ('ABC123', 'Toyota', 'Corolla', 2015, 'Blanco', 'Juan Perez'),
       ('DEF456', 'Ferrari', 'F8', 2020, 'Rojo', 'Pedro Admin');
