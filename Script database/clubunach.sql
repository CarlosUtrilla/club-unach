-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2020 a las 02:14:55
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clubunach`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `correo_electronico` varchar(40) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `contraseña` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `telefono`, `correo_electronico`, `direccion`, `contraseña`) VALUES
(1, 'Carlos Abraham Utrilla Zapoteco', '9612830447', 'itoma.za.1@gmail.com', 'Av. Simojovel #17, Col Los Manguitos', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `precio` int(11) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `marca` varchar(40) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `codigo`, `marca`, `stock`) VALUES
(1, 'Rufles', 13, 'A3C831', 'Sabritas', 17),
(2, 'Coca cola 600ml', 15, 'C62B51', 'Coca Cola', 16),
(3, 'Cafe Utrilla', 45, 'C5631', 'Utrilla', 18),
(4, 'Emperadores', 12, 'Z3BR4', 'Gamesa', 20),
(5, 'Azucar 1kg', 21, 'AZ540', 'Zucarmex', 20),
(6, 'Manzanita', 13, 'PS451', 'Pepsi', 20),
(7, 'Aceite', 15, 'AC589', 'Patrona', 20),
(8, 'Gansito', 13, 'MR056', 'Marinela', 20),
(9, 'Margarina', 12, 'LV34T', 'La villita', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `contenido` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `id_usuario`, `total`, `contenido`) VALUES
(3, 1, 13, 'CLUB UNACH\r\n===============================\r\nDirección: Blvd. Belisario Domínguez, Sin Nombre, Teran 29050, Tuxtla Gutiérrez, Chis.\r\nRector: Carlos Faustino Natarén Nandayapa\r\nTeléfono: 961 615 0440\r\n13:44:49 25/05/2020\r\n===============================\r\nCANT.   ARTICULO   PRE.UNIT   TOTAL\r\n1    Rufles    $13    $13\r\n===============================\r\nTOTAL DE COMPRA $13\r\nNOMBRE DEL CLIENTE: Carlos Abraham Utrilla Zapoteco\r\n!DISFRUTE SU COMPRA¡. VUELVA PRONTO\r\n==============================\r\nPOR LA CONCIENCIA DE LA NECECIDAD DE SERVIR'),
(4, 1, 13, 'CLUB UNACH\r\n===============================\r\nDirección: Blvd. Belisario Domínguez, Sin Nombre, Teran 29050, Tuxtla Gutiérrez, Chis.\r\nRector: Carlos Faustino Natarén Nandayapa\r\nTeléfono: 961 615 0440\r\n18:08:07 25/05/2020\r\n===============================\r\nCANT.   ARTICULO   PRE.UNIT   TOTAL\r\n1    Rufles    $13    $13\r\n===============================\r\nTOTAL DE COMPRA $13\r\nNOMBRE DEL CLIENTE: Carlos Abraham Utrilla Zapoteco\r\n!DISFRUTE SU COMPRA¡. VUELVA PRONTO\r\n===============================\r\nPOR LA CONCIENCIA DE LA NECECIDAD DE SERVIR'),
(5, 1, 30, 'CLUB UNACH\r\n===============================\r\nDirección: Blvd. Belisario Domínguez, Sin Nombre, Teran 29050, Tuxtla Gutiérrez, Chis.\r\nRector: Carlos Faustino Natarén Nandayapa\r\nTeléfono: 961 615 0440\r\n18:16:53 25/05/2020\r\n===============================\r\nCANT.   ARTICULO   PRE.UNIT   TOTAL\r\n2    Coca cola 600ml    $15    $30\r\n===============================\r\nTOTAL DE COMPRA $30\r\nNOMBRE DEL CLIENTE: Carlos Abraham Utrilla Zapoteco\r\n!DISFRUTE SU COMPRA¡. VUELVA PRONTO\r\n===============================\r\nPOR LA CONCIENCIA DE LA NECECIDAD DE SERVIR');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
