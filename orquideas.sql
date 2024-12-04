-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2024 a las 21:18:56
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

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
-- Estructura de tabla para la tabla `orquidea`
--

CREATE TABLE `orquidea` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `humedad` decimal(5,2) NOT NULL,
  `temperatura` decimal(5,2) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `orquidea`
--

INSERT INTO `orquidea` (`id`, `nombre`, `humedad`, `temperatura`, `fecha_registro`, `estado`) VALUES
(1, 'cactus', '87.00', '22.42', '2024-11-30 17:18:44', 'Regando'),
(2, 'orquidea', '84.00', '27.28', '2024-11-30 17:18:44', 'Regando'),
(3, 'planta', '87.00', '23.49', '2024-12-01 16:35:19', 'Regando');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programacion_riego`
--

CREATE TABLE `programacion_riego` (
  `id` int(11) NOT NULL,
  `planta_id` int(11) NOT NULL,
  `fecha_riego` date NOT NULL,
  `hora_riego` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `programacion_riego`
--

INSERT INTO `programacion_riego` (`id`, `planta_id`, `fecha_riego`, `hora_riego`) VALUES
(1, 1, '2024-11-01', '08:00:00'),
(2, 2, '2024-11-08', '08:00:00'),
(3, 3, '2024-12-01', '14:00:00'),
(4, 1, '2024-12-04', '17:00:00'),
(5, 2, '2024-12-04', '17:00:00'),
(6, 3, '2024-12-04', '17:00:00'),
(7, 1, '2024-12-04', '17:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblhistorial`
--

CREATE TABLE `tblhistorial` (
  `id` int(11) NOT NULL,
  `planta_id` int(11) DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL,
  `tipo_actividad` varchar(255) DEFAULT NULL,
  `valor_anterior` varchar(255) DEFAULT NULL,
  `valor_nuevo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indices de la tabla `tblhistorial`
--
ALTER TABLE `tblhistorial`
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
-- AUTO_INCREMENT de la tabla `orquidea`
--
ALTER TABLE `orquidea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `programacion_riego`
--
ALTER TABLE `programacion_riego`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tblhistorial`
--
ALTER TABLE `tblhistorial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `programacion_riego`
--
ALTER TABLE `programacion_riego`
  ADD CONSTRAINT `programacion_riego_ibfk_1` FOREIGN KEY (`planta_id`) REFERENCES `orquidea` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `tblhistorial`
--
ALTER TABLE `tblhistorial`
  ADD CONSTRAINT `tblhistorial_ibfk_1` FOREIGN KEY (`planta_id`) REFERENCES `orquidea` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
