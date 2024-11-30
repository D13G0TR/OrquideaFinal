CREATE DATABASE IF NOT EXISTS orquideas;
USE orquideas;


CREATE TABLE IF NOT EXISTS orquidea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    humedad DECIMAL(5,2) NOT NULL,
    temperatura DECIMAL(5,2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Crear tabla para la programación de riego
CREATE TABLE IF NOT EXISTS programacion_riego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    planta_id INT NOT NULL,
    fecha_riego DATE NOT NULL,
    hora_riego TIME NOT NULL,
    FOREIGN KEY (planta_id) REFERENCES orquidea(id) ON DELETE CASCADE
);

ALTER TABLE orquidea
ADD COLUMN estado VARCHAR(100) DEFAULT NULL;
