-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-03-2023 a las 20:14:40
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taller`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `correo_electronico` varchar(255) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nombres` varchar(100) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `piso` varchar(255) DEFAULT NULL,
  `telefono_linea` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `correo_electronico`, `apellido`, `calle`, `celular`, `codigo_postal`, `departamento`, `localidad`, `nombres`, `numero`, `piso`, `telefono_linea`) VALUES
(1, 'igorio@gmail.com', 'Gatito', 'Ricchieri', '+542914852310', NULL, NULL, 'Bahia Blanca', 'Igorio', '458', NULL, '+54291468512');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_vehiculo`
--

CREATE TABLE `cliente_vehiculo` (
  `cliente_id` bigint(20) NOT NULL,
  `vehiculo_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente_vehiculo`
--

INSERT INTO `cliente_vehiculo` (`cliente_id`, `vehiculo_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ordenes_trabajo`
--

CREATE TABLE `detalle_ordenes_trabajo` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `valor_total` decimal(19,2) DEFAULT NULL,
  `orden_trabajo_id` bigint(20) DEFAULT NULL,
  `repuesto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `detalle_ordenes_trabajo`
--

INSERT INTO `detalle_ordenes_trabajo` (`id`, `cantidad`, `valor_total`, `orden_trabajo_id`, `repuesto_id`) VALUES
(1, 2, '23766.00', 1, 2),
(2, 1, '12229.00', 1, 1),
(3, 2, '23766.00', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `tipo_empleado` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nombres` varchar(100) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `piso` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`tipo_empleado`, `id`, `apellido`, `calle`, `celular`, `codigo_postal`, `departamento`, `localidad`, `nombres`, `numero`, `piso`) VALUES
('Recep', 1, 'Garcia', 'San Juan', '+5429915842541', '8000', NULL, 'Bahia Blanca', 'Monica', '900', 'PB'),
('Admin', 2, 'Rodriguez', 'Rivadavia', '+5429915842541', '8000', NULL, 'Bahia Blanca', 'Pepe', '2301', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mano_obra`
--

CREATE TABLE `mano_obra` (
  `id` bigint(20) NOT NULL,
  `detalle` varchar(255) DEFAULT NULL,
  `duracion_hs` time DEFAULT NULL,
  `mecanico_id` bigint(20) DEFAULT NULL,
  `orden_trabajo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `mano_obra`
--

INSERT INTO `mano_obra` (`id`, `detalle`, `duracion_hs`, `mecanico_id`, `orden_trabajo_id`) VALUES
(1, 'Cambio de cilindro', '01:00:00', 2, 1),
(2, 'Cambio de aceite', '01:30:00', 2, 1),
(3, 'Cambio de cilindro', '01:00:00', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mecanicos`
--

CREATE TABLE `mecanicos` (
  `id` bigint(20) NOT NULL,
  `activo` char(1) DEFAULT NULL,
  `especialidad` varchar(255) DEFAULT NULL,
  `apellido` varchar(80) NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nombres` varchar(100) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `piso` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `mecanicos`
--

INSERT INTO `mecanicos` (`id`, `activo`, `especialidad`, `apellido`, `calle`, `celular`, `codigo_postal`, `departamento`, `localidad`, `nombres`, `numero`, `piso`) VALUES
(1, 'T', 'motor', 'Raul', 'Cabral', '+542914587402', '8000', NULL, 'Punta Alta', 'Carlos', '8742', NULL),
(2, 'T', 'frenos', 'Fernandez', 'Aguado', '+54291487510', '8000', NULL, 'Bahia Blanca', 'Daiana', '612', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes_trabajo`
--

CREATE TABLE `ordenes_trabajo` (
  `id` bigint(20) NOT NULL,
  `cantidad_cuotas` int(11) DEFAULT NULL,
  `detalle_falla` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_fin_reparacion` datetime(6) DEFAULT NULL,
  `fecha_ingreso` datetime(6) DEFAULT NULL,
  `fecha_pago` datetime(6) DEFAULT NULL,
  `forma_pago` varchar(255) DEFAULT NULL,
  `importe_total` decimal(19,2) DEFAULT NULL,
  `kilometraje` bigint(20) DEFAULT NULL,
  `nivel_combustible` varchar(255) DEFAULT NULL,
  `tipo_tarjeta` varchar(255) DEFAULT NULL,
  `administrativo_id` bigint(20) DEFAULT NULL,
  `recepcionista_id` bigint(20) DEFAULT NULL,
  `vehiculo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ordenes_trabajo`
--

INSERT INTO `ordenes_trabajo` (`id`, `cantidad_cuotas`, `detalle_falla`, `estado`, `fecha_fin_reparacion`, `fecha_ingreso`, `fecha_pago`, `forma_pago`, `importe_total`, `kilometraje`, `nivel_combustible`, `tipo_tarjeta`, `administrativo_id`, `recepcionista_id`, `vehiculo_id`) VALUES
(1, 3, 'frenos', 'CERRADA', '2023-03-23 15:55:09.000000', '2023-03-23 15:31:00.000000', '2023-03-23 16:02:09.000000', 'credito', '35995.00', 10000, '50%', 'visa', 2, 1, 1),
(2, 1, 'frenos', 'FACTURADA', '2023-03-23 16:06:51.000000', '2023-03-23 16:04:09.000000', '2023-03-23 16:13:47.000000', 'debito', '23766.00', 10000, '50%', 'visa', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repuestos`
--

CREATE TABLE `repuestos` (
  `id` bigint(20) NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `repuesto` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `repuestos`
--

INSERT INTO `repuestos` (`id`, `marca`, `modelo`, `repuesto`, `valor`) VALUES
(1, 'Total', 'Quartz 9000 x4L. 100% Sintético', 'Aceite para motor', '12229.00'),
(2, 'Peugeot', 'Peugeot 404, 68/71', 'Bomba de freno', '11883.00'),
(3, 'Chevrolet', 'Pick up 60/66 D28.57', 'Bomba de freno', '13611.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id` bigint(20) NOT NULL,
  `anio` int(11) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `patente` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id`, `anio`, `color`, `marca`, `modelo`, `patente`) VALUES
(1, 2019, 'blanco', 'RENAULT', 'Koleos', 'AB123CD');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f90r5ev5hwm529n393c92294x` (`correo_electronico`);

--
-- Indices de la tabla `cliente_vehiculo`
--
ALTER TABLE `cliente_vehiculo`
  ADD KEY `FK2ufsrlqafc9q4ww208bq2ejpq` (`vehiculo_id`),
  ADD KEY `FKispl4o9vyqm2bbpyle5y4dcx5` (`cliente_id`);

--
-- Indices de la tabla `detalle_ordenes_trabajo`
--
ALTER TABLE `detalle_ordenes_trabajo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2xbsyaw7n3a5xqd4ecpmjy0u2` (`orden_trabajo_id`),
  ADD KEY `FKdqnjjclygimbajtq306sto0te` (`repuesto_id`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `mano_obra`
--
ALTER TABLE `mano_obra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp4lvtoc1ldrxkt9k0xcl5dsyd` (`mecanico_id`),
  ADD KEY `FKtbkk8levawgkr8yek4jiac6t1` (`orden_trabajo_id`);

--
-- Indices de la tabla `mecanicos`
--
ALTER TABLE `mecanicos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ordenes_trabajo`
--
ALTER TABLE `ordenes_trabajo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3j37bsnw2ov9bdw008cso6btr` (`administrativo_id`),
  ADD KEY `FK9k57to63n1igtba7n6m19ri4t` (`recepcionista_id`),
  ADD KEY `FK8tmipf5cufvljctlbrcqt3ojt` (`vehiculo_id`);

--
-- Indices de la tabla `repuestos`
--
ALTER TABLE `repuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kxt53tfqxxydvqll7ldil6qu7` (`patente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_ordenes_trabajo`
--
ALTER TABLE `detalle_ordenes_trabajo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mano_obra`
--
ALTER TABLE `mano_obra`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `mecanicos`
--
ALTER TABLE `mecanicos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ordenes_trabajo`
--
ALTER TABLE `ordenes_trabajo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `repuestos`
--
ALTER TABLE `repuestos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente_vehiculo`
--
ALTER TABLE `cliente_vehiculo`
  ADD CONSTRAINT `FK2ufsrlqafc9q4ww208bq2ejpq` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculos` (`id`),
  ADD CONSTRAINT `FKispl4o9vyqm2bbpyle5y4dcx5` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`);

--
-- Filtros para la tabla `detalle_ordenes_trabajo`
--
ALTER TABLE `detalle_ordenes_trabajo`
  ADD CONSTRAINT `FK2xbsyaw7n3a5xqd4ecpmjy0u2` FOREIGN KEY (`orden_trabajo_id`) REFERENCES `ordenes_trabajo` (`id`),
  ADD CONSTRAINT `FKdqnjjclygimbajtq306sto0te` FOREIGN KEY (`repuesto_id`) REFERENCES `repuestos` (`id`);

--
-- Filtros para la tabla `mano_obra`
--
ALTER TABLE `mano_obra`
  ADD CONSTRAINT `FKp4lvtoc1ldrxkt9k0xcl5dsyd` FOREIGN KEY (`mecanico_id`) REFERENCES `mecanicos` (`id`),
  ADD CONSTRAINT `FKtbkk8levawgkr8yek4jiac6t1` FOREIGN KEY (`orden_trabajo_id`) REFERENCES `ordenes_trabajo` (`id`);

--
-- Filtros para la tabla `ordenes_trabajo`
--
ALTER TABLE `ordenes_trabajo`
  ADD CONSTRAINT `FK3j37bsnw2ov9bdw008cso6btr` FOREIGN KEY (`administrativo_id`) REFERENCES `empleados` (`id`),
  ADD CONSTRAINT `FK8tmipf5cufvljctlbrcqt3ojt` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculos` (`id`),
  ADD CONSTRAINT `FK9k57to63n1igtba7n6m19ri4t` FOREIGN KEY (`recepcionista_id`) REFERENCES `empleados` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
