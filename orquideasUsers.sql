-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2024 a las 02:51:24
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `orquideas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades`
--

CREATE TABLE `actividades` (
  `id` int(11) NOT NULL,
  `run_usuario` varchar(12) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_hora` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orquidea`
--

CREATE TABLE `orquidea` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `humedad` decimal(5,2) NOT NULL,
  `temperatura` decimal(5,2) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orquidea`
--

INSERT INTO `orquidea` (`id`, `nombre`, `humedad`, `temperatura`, `fecha_registro`, `estado`) VALUES
(1, 'cactus', 0.00, 0.00, '2024-11-30 17:18:44', 'Esperando Riego'),
(2, 'flor de chota', 0.00, 0.00, '2024-12-01 01:46:14', 'Esperando Riego');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programacion_riego`
--

CREATE TABLE `programacion_riego` (
  `id` int(11) NOT NULL,
  `planta_id` int(11) NOT NULL,
  `fecha_riego` date NOT NULL,
  `hora_riego` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `programacion_riego`
--

INSERT INTO `programacion_riego` (`id`, `planta_id`, `fecha_riego`, `hora_riego`) VALUES
(1, 1, '2024-11-01', '08:00:00'),
(2, 2, '2024-11-08', '08:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `run` varchar(12) NOT NULL,
  `digito_verificador` char(1) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `contraseña` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`run`, `digito_verificador`, `nombre`, `apellido`, `nombre_usuario`, `contraseña`) VALUES
('18314602', '5', 'brayan', 'onofre', 'boc', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('18760673', 'K', 'maria', 'riquelme', 'maripi', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `run_usuario` (`run_usuario`);

--
-- Indices de la tabla `orquidea`
--
ALTER TABLE `orquidea`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `programacion_riego`
--
ALTER TABLE `programacion_riego`
  ADD PRIMARY KEY (`id`),
  ADD KEY `planta_id` (`planta_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`run`),
  ADD UNIQUE KEY `nombre_usuario` (`nombre_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividades`
--
ALTER TABLE `actividades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `orquidea`
--
ALTER TABLE `orquidea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `programacion_riego`
--
ALTER TABLE `programacion_riego`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD CONSTRAINT `actividades_ibfk_1` FOREIGN KEY (`run_usuario`) REFERENCES `usuarios` (`run`) ON DELETE CASCADE;

--
-- Filtros para la tabla `programacion_riego`
--
ALTER TABLE `programacion_riego`
  ADD CONSTRAINT `programacion_riego_ibfk_1` FOREIGN KEY (`planta_id`) REFERENCES `orquidea` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
