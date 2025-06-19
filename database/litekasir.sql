-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Jun 2025 pada 16.17
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `litekasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id`, `nama`) VALUES
(1, 'Kopi'),
(2, 'Non-Kopi'),
(3, 'Snack'),
(4, 'Desert');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `id_kategori` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`id`, `nama`, `harga`, `stok`, `id_kategori`) VALUES
(2, 'Cappucino', 15000, 36, 1),
(3, 'Mochacinno', 15000, 16, 1),
(4, 'Choffelatte', 15000, 37, 1),
(5, 'Es Kopi Susu', 15000, 68, 2),
(6, 'Es Kopi Gula Aren', 15000, 70, 2),
(7, 'Es Coklat', 12000, 70, 2),
(8, 'Es Matcha', 15000, 70, 2),
(10, 'Ice Tea', 8000, 20, 2),
(15, 'Es Teler', 12000, 10, 2),
(17, 'White Coffee', 8000, 60, 1),
(19, 'Es Campur', 10000, 48, 2),
(31, 'Es GoodDay', 12000, 49, 2),
(33, 'Beng-Beng', 3000, 50, 3),
(52, 'Es Vanilla', 15000, 120, 2),
(55, 'Nabati', 2000, 49, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `tanggal` datetime NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL,
  `kasir` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `orders`
--

INSERT INTO `orders` (`id`, `tanggal`, `total`, `bayar`, `kembali`, `kasir`) VALUES
(29, '2025-06-03 02:01:26', 24000, 24000, 0, ''),
(30, '2025-06-03 02:03:33', 24000, 24000, 0, ''),
(31, '2025-06-03 02:10:35', 84000, 100000, 16000, ''),
(32, '2025-06-03 02:33:11', 12000, 20000, 8000, ''),
(33, '2025-06-03 02:34:33', 90000, 100000, 10000, ''),
(34, '2025-06-03 02:39:15', 42000, 50000, 8000, ''),
(35, '2025-06-03 02:45:47', 12000, 12000, 0, ''),
(36, '2025-06-03 02:46:19', 12000, 12000, 0, ''),
(37, '2025-06-03 03:08:34', 24000, 24000, 0, ''),
(38, '2025-06-03 03:12:15', 12000, 12000, 0, ''),
(39, '2025-06-03 03:16:27', 12000, 12000, 0, ''),
(40, '2025-06-03 03:19:46', 12000, 12000, 0, ''),
(41, '2025-06-03 03:28:12', 12000, 12000, 0, ''),
(42, '2025-06-03 13:47:58', 124000, 125000, 1000, ''),
(43, '2025-06-03 14:13:13', 27000, 30000, 3000, ''),
(44, '2025-06-03 14:14:34', 12000, 12000, 0, ''),
(45, '2025-06-03 14:16:31', 12000, 12000, 0, ''),
(46, '2025-06-03 14:17:49', 12000, 12000, 0, ''),
(47, '2025-06-03 14:31:33', 12000, 12000, 0, ''),
(48, '2025-06-03 14:44:46', 12000, 12000, 0, ''),
(49, '2025-06-03 14:53:20', 12000, 12000, 0, ''),
(50, '2025-06-03 14:53:59', 12000, 12000, 0, ''),
(51, '2025-06-03 14:59:52', 12000, 12000, 0, NULL),
(52, '2025-06-03 15:00:08', 12000, 12000, 0, NULL),
(53, '2025-06-03 15:01:58', 12000, 12000, 0, NULL),
(54, '2025-06-03 19:36:26', 162000, 162000, 0, NULL),
(55, '2025-06-03 19:41:03', 42000, 50000, 8000, NULL),
(56, '2025-06-03 20:44:02', 54000, 55000, 1000, NULL),
(57, '2025-06-03 22:14:50', 69000, 100000, 31000, NULL),
(58, '2025-06-03 22:19:21', 15000, 15000, 0, NULL),
(59, '2025-06-03 22:44:24', 70000, 100000, 30000, NULL),
(60, '2025-06-03 22:47:43', 70000, 100000, 30000, NULL),
(61, '2025-06-04 12:35:56', 65000, 100000, 35000, NULL),
(62, '2025-06-04 12:39:25', 59000, 100000, 41000, NULL),
(63, '2025-06-05 15:10:13', 39000, 50000, 11000, NULL),
(64, '2025-06-10 14:21:34', 12000, 20000, 8000, NULL),
(65, '2025-06-11 21:32:03', 12000, 20000, 8000, NULL),
(66, '2025-06-11 21:32:38', 12000, 20000, 8000, NULL),
(67, '2025-06-11 21:33:26', 12000, 20000, 8000, NULL),
(68, '2025-06-11 21:37:34', 12000, 20000, 8000, NULL),
(69, '2025-06-11 21:38:17', 12000, 20000, 8000, NULL),
(70, '2025-06-11 21:45:30', 12000, 20000, 8000, NULL),
(71, '2025-06-11 21:54:38', 24000, 30000, 6000, NULL),
(72, '2025-06-11 22:22:12', 48000, 50000, 2000, NULL),
(73, '2025-06-11 22:28:08', 108000, 110000, 2000, NULL),
(74, '2025-06-12 08:30:17', 57000, 60000, 3000, NULL),
(75, '2025-06-12 08:32:48', 65000, 100000, 35000, NULL),
(76, '2025-06-12 12:45:10', 6000, 10000, 4000, NULL),
(77, '2025-06-12 13:43:13', 120000, 200000, 80000, NULL),
(78, '2025-06-12 15:28:37', 15000, 20000, 5000, NULL),
(79, '2025-06-12 15:29:14', 15000, 15000, 0, NULL),
(80, '2025-06-12 15:29:54', 30000, 30000, 0, NULL),
(81, '2025-06-12 15:31:49', 30000, 30000, 0, NULL),
(82, '2025-06-13 14:15:03', 6000, 10000, 4000, NULL),
(83, '2025-06-13 15:24:55', 225000, 300000, 75000, NULL),
(84, '2025-06-13 22:07:26', 30000, 50000, 20000, NULL),
(85, '2025-06-13 22:39:50', 15000, 20000, 5000, NULL),
(86, '2025-06-13 23:08:42', 30000, 50000, 20000, NULL),
(87, '2025-06-13 23:09:21', 24000, 50000, 26000, NULL),
(88, '2025-06-14 15:50:48', 105000, 150000, 45000, NULL),
(89, '2025-06-14 16:22:54', 83000, 100000, 17000, NULL),
(90, '2025-06-14 16:25:37', 15000, 15000, 0, NULL),
(91, '2025-06-14 20:28:39', 83000, 100000, 17000, NULL),
(92, '2025-06-14 20:46:24', 72000, 100000, 28000, NULL),
(93, '2025-06-14 23:45:12', 72000, 80000, 8000, NULL),
(94, '2025-06-16 13:22:02', 53000, 100000, 47000, NULL),
(95, '2025-06-16 13:32:45', 80000, 100000, 20000, NULL),
(96, '2025-06-16 20:56:48', 45000, 50000, 5000, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `menu_id`, `nama_menu`, `qty`, `harga`, `subtotal`) VALUES
(1, 11, 1, 'Espresso', 1, 12000, 12000),
(2, 12, 1, 'Espresso', 2, 12000, 24000),
(3, 12, 2, 'Cappuccino', 2, 15000, 30000),
(4, 13, 1, 'Espresso', 2, 12000, 24000),
(5, 14, 1, 'Espresso', 2, 12000, 24000),
(6, 14, 2, 'Cappuccino', 3, 15000, 45000),
(7, 15, 1, 'Espresso', 4, 12000, 48000),
(8, 15, 2, 'Cappuccino', 3, 15000, 45000),
(9, 16, 1, 'Espresso', 2, 12000, 24000),
(10, 16, 2, 'Cappuccino', 1, 15000, 15000),
(11, 17, 1, 'Espresso', 2, 12000, 24000),
(12, 17, 2, 'Cappuccino', 2, 15000, 30000),
(13, 18, 1, 'Espresso', 2, 12000, 24000),
(14, 18, 2, 'Cappuccino', 2, 15000, 30000),
(15, 19, 1, 'Espresso', 3, 12000, 36000),
(16, 19, 2, 'Cappuccino', 2, 15000, 30000),
(17, 20, 1, 'Espresso', 1, 12000, 12000),
(18, 20, 2, 'Cappuccino', 5, 15000, 75000),
(19, 21, 1, 'Espresso', 3, 12000, 36000),
(20, 21, 2, 'Cappuccino', 2, 15000, 30000),
(21, 22, 1, 'Espresso', 2, 12000, 24000),
(22, 22, 2, 'Cappuccino', 3, 15000, 45000),
(23, 22, 15, 'es susu', 3, 8000, 24000),
(24, 23, 1, 'Espresso', 2, 12000, 24000),
(25, 23, 2, 'Cappuccino', 3, 15000, 45000),
(26, 24, 1, 'Espresso', 2, 12000, 24000),
(27, 24, 2, 'Cappuccino', 1, 15000, 15000),
(28, 25, 1, 'Espresso', 2, 12000, 24000),
(29, 25, 2, 'Cappuccino', 2, 15000, 30000),
(30, 25, 10, 'Ice Tea', 2, 8000, 16000),
(31, 26, 1, 'Espresso', 2, 12000, 24000),
(32, 26, 2, 'Cappuccino', 1, 15000, 15000),
(33, 26, 10, 'Ice Tea', 3, 8000, 24000),
(34, 26, 15, 'Es Teler', 4, 12000, 48000),
(35, 27, 1, 'Espresso', 3, 12000, 36000),
(36, 27, 2, 'Cappuccino', 1, 15000, 15000),
(37, 28, 1, 'Espresso', 1, 12000, 12000),
(38, 29, 1, 'Espresso', 2, 12000, 24000),
(39, 30, 1, 'Espresso', 2, 12000, 24000),
(40, 31, 1, 'Espresso', 2, 12000, 24000),
(41, 31, 2, 'Cappuccino', 2, 15000, 30000),
(42, 31, 3, 'Mochacinno', 2, 15000, 30000),
(43, 32, 1, 'Espresso', 1, 12000, 12000),
(44, 33, 1, 'Espresso', 1, 12000, 12000),
(45, 33, 2, 'Cappuccino', 1, 15000, 15000),
(46, 33, 3, 'Mochacinno', 1, 15000, 15000),
(47, 33, 10, 'Ice Tea', 1, 8000, 8000),
(48, 33, 15, 'Es Teler', 2, 12000, 24000),
(49, 33, 17, 'White Coffee', 2, 8000, 16000),
(50, 34, 1, 'Espresso', 1, 12000, 12000),
(51, 34, 2, 'Cappuccino', 1, 15000, 15000),
(52, 34, 3, 'Mochacinno', 1, 15000, 15000),
(53, 35, 1, 'Espresso', 1, 12000, 12000),
(54, 36, 1, 'Espresso', 1, 12000, 12000),
(55, 37, 1, 'Espresso', 2, 12000, 24000),
(56, 38, 1, 'Espresso', 1, 12000, 12000),
(57, 39, 1, 'Espresso', 1, 12000, 12000),
(58, 40, 1, 'Espresso', 1, 12000, 12000),
(59, 41, 1, 'Espresso', 1, 12000, 12000),
(60, 42, 1, 'Espresso', 2, 12000, 24000),
(61, 42, 2, 'Cappuccino', 2, 15000, 30000),
(62, 42, 3, 'Mochacinno', 2, 15000, 30000),
(63, 42, 10, 'Ice Tea', 1, 8000, 8000),
(64, 42, 15, 'Es Teler', 2, 12000, 24000),
(65, 42, 17, 'White Coffee', 1, 8000, 8000),
(66, 43, 1, 'Espresso', 1, 12000, 12000),
(67, 43, 2, 'Cappuccino', 1, 15000, 15000),
(68, 44, 1, 'Espresso', 1, 12000, 12000),
(69, 45, 1, 'Espresso', 1, 12000, 12000),
(70, 46, 1, 'Espresso', 1, 12000, 12000),
(71, 47, 1, 'Espresso', 1, 12000, 12000),
(72, 48, 1, 'Espresso', 1, 12000, 12000),
(73, 49, 1, 'Espresso', 1, 12000, 12000),
(74, 50, 1, 'Espresso', 1, 12000, 12000),
(75, 51, 1, 'Espresso', 1, 12000, 12000),
(76, 52, 1, 'Espresso', 1, 12000, 12000),
(77, 53, 1, 'Espresso', 1, 12000, 12000),
(78, 54, 1, 'Espresso', 2, 12000, 24000),
(79, 54, 2, 'Cappuccino', 2, 15000, 30000),
(80, 54, 3, 'Mochacinno', 4, 15000, 60000),
(81, 54, 10, 'Ice Tea', 1, 8000, 8000),
(82, 54, 15, 'Es Teler', 2, 12000, 24000),
(83, 54, 17, 'White Coffee', 2, 8000, 16000),
(84, 55, 1, 'Espresso', 1, 12000, 12000),
(85, 55, 2, 'Cappuccino', 1, 15000, 15000),
(86, 55, 3, 'Mochacinno', 1, 15000, 15000),
(87, 56, 1, 'Espresso', 2, 12000, 24000),
(88, 56, 2, 'Cappuccino', 1, 15000, 15000),
(89, 56, 3, 'Mochacinno', 1, 15000, 15000),
(90, 57, 1, 'Espresso', 2, 12000, 24000),
(91, 57, 2, 'Cappuccino', 2, 15000, 30000),
(92, 57, 3, 'Mochacinno', 1, 15000, 15000),
(93, 58, 2, 'Cappuccino', 1, 15000, 15000),
(94, 59, 2, 'Cappuccino', 1, 15000, 15000),
(95, 59, 3, 'Mochacinno', 1, 15000, 15000),
(96, 59, 15, 'Es Teler', 2, 12000, 24000),
(97, 59, 17, 'White Coffee', 2, 8000, 16000),
(98, 60, 2, 'Cappuccino', 1, 15000, 15000),
(99, 60, 3, 'Mochacinno', 1, 15000, 15000),
(100, 60, 15, 'Es Teler', 2, 12000, 24000),
(101, 60, 17, 'White Coffee', 2, 8000, 16000),
(102, 61, 2, 'Cappuccino', 2, 15000, 30000),
(103, 61, 3, 'Mochacinno', 1, 15000, 15000),
(104, 61, 15, 'Es Teler', 1, 12000, 12000),
(105, 61, 17, 'White Coffee', 1, 8000, 8000),
(106, 62, 1, 'Esspresso', 2, 12000, 24000),
(107, 62, 3, 'Mochacinno', 1, 15000, 15000),
(108, 62, 18, 'Es Campur', 2, 10000, 20000),
(109, 63, 1, 'Esspresso', 2, 12000, 24000),
(110, 63, 3, 'Mochacinno', 1, 15000, 15000),
(111, 64, 1, 'Esspresso', 1, 12000, 12000),
(112, 67, 1, 'Esspresso', 1, 12000, 12000),
(113, 68, 1, 'Esspresso', 1, 12000, 12000),
(114, 69, 1, 'Esspresso', 1, 12000, 12000),
(115, 70, 1, 'Esspresso', 1, 12000, 12000),
(116, 71, 1, 'Esspresso', 2, 12000, 24000),
(117, 72, 15, 'Es Teler', 3, 12000, 36000),
(118, 72, 1, 'Esspresso', 1, 12000, 12000),
(119, 73, 1, 'Esspresso', 4, 12000, 48000),
(120, 73, 2, 'Cappucino', 1, 15000, 15000),
(121, 73, 3, 'Mochacinno', 1, 15000, 15000),
(122, 73, 4, 'Choffelatte', 2, 15000, 30000),
(123, 74, 1, 'Esspresso', 1, 12000, 12000),
(124, 74, 3, 'Mochacinno', 1, 15000, 15000),
(125, 74, 4, 'Choffelatte', 2, 15000, 30000),
(126, 75, 1, 'Esspresso', 1, 12000, 12000),
(127, 75, 17, 'White Coffee', 1, 8000, 8000),
(128, 75, 2, 'Cappucino', 1, 15000, 15000),
(129, 75, 3, 'Mochacinno', 1, 15000, 15000),
(130, 75, 4, 'Choffelatte', 1, 15000, 15000),
(131, 76, 33, 'Beng-Beng', 2, 3000, 6000),
(132, 77, 2, 'Cappucino', 5, 15000, 75000),
(133, 77, 3, 'Mochacinno', 2, 15000, 30000),
(134, 77, 4, 'Choffelatte', 1, 15000, 15000),
(135, 78, 2, 'Cappucino', 1, 15000, 15000),
(136, 79, 2, 'Cappucino', 1, 15000, 15000),
(137, 80, 2, 'Cappucino', 2, 15000, 30000),
(138, 81, 2, 'Cappucino', 2, 15000, 30000),
(139, 82, 33, 'Beng-Beng', 2, 3000, 6000),
(140, 83, 2, 'Cappucino', 15, 15000, 225000),
(141, 84, 2, 'Cappucino', 2, 15000, 30000),
(142, 85, 2, 'Cappucino', 1, 15000, 15000),
(143, 86, 2, 'Cappucino', 2, 15000, 30000),
(144, 87, 15, 'Es Teler', 2, 12000, 24000),
(145, 88, 2, 'Cappucino', 1, 15000, 15000),
(146, 88, 3, 'Mochacinno', 3, 15000, 45000),
(147, 88, 4, 'Choffelatte', 3, 15000, 45000),
(148, 89, 17, 'White Coffee', 1, 8000, 8000),
(149, 89, 2, 'Cappucino', 2, 15000, 30000),
(150, 89, 3, 'Mochacinno', 1, 15000, 15000),
(151, 89, 4, 'Choffelatte', 2, 15000, 30000),
(152, 90, 2, 'Cappucino', 1, 15000, 15000),
(153, 91, 17, 'White Coffee', 1, 8000, 8000),
(154, 91, 2, 'Cappucino', 2, 15000, 30000),
(155, 91, 3, 'Mochacinno', 2, 15000, 30000),
(156, 91, 4, 'Choffelatte', 1, 15000, 15000),
(157, 92, 33, 'Beng-Beng', 2, 3000, 6000),
(158, 92, 53, 'Nabati', 2, 2000, 4000),
(159, 92, 2, 'Cappucino', 1, 15000, 15000),
(160, 92, 3, 'Mochacinno', 1, 15000, 15000),
(161, 92, 19, 'Es Campur', 2, 10000, 20000),
(162, 92, 31, 'Es GoodDay', 1, 12000, 12000),
(163, 93, 2, 'Cappucino', 3, 15000, 45000),
(164, 93, 3, 'Mochacinno', 1, 15000, 15000),
(165, 93, 33, 'Beng-Beng', 4, 3000, 12000),
(166, 94, 17, 'White Coffee', 1, 8000, 8000),
(167, 94, 2, 'Cappucino', 1, 15000, 15000),
(168, 94, 3, 'Mochacinno', 1, 15000, 15000),
(169, 94, 4, 'Choffelatte', 1, 15000, 15000),
(170, 95, 2, 'Cappucino', 2, 15000, 30000),
(171, 95, 3, 'Mochacinno', 1, 15000, 15000),
(172, 95, 5, 'Es Kopi Susu', 2, 15000, 30000),
(173, 95, 33, 'Beng-Beng', 1, 3000, 3000),
(174, 95, 55, 'Nabati', 1, 2000, 2000),
(175, 96, 2, 'Cappucino', 2, 15000, 30000),
(176, 96, 3, 'Mochacinno', 1, 15000, 15000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('admin','kasir') NOT NULL,
  `kode` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `kode`) VALUES
(1, 'ALFIAN', '123', 'admin', 'ADMIN123'),
(3, 'SANDI', '123', 'kasir', 'KASIR123'),
(4, 'DHARMA', '123', 'kasir', 'KASIR123'),
(5, 'INDRA', '123', 'kasir', 'KASIR123'),
(6, 'SULTON', '123', 'kasir', 'KASIR123'),
(11, 'ANDIKA', '123', 'admin', 'ADMIN123'),
(12, 'PUTRA', '123', 'admin', 'ADMIN123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_menu_kategori` (`id_kategori`);

--
-- Indeks untuk tabel `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT untuk tabel `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT untuk tabel `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=177;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `fk_menu_kategori` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
