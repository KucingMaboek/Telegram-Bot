-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2020 at 03:45 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `telegrambot`
--

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `chatId` varchar(30) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `requestCode` varchar(3) NOT NULL,
  `text` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `instalasi_listrik`
--

CREATE TABLE `instalasi_listrik` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `chatId` varchar(30) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `provinsi` varchar(255) DEFAULT NULL,
  `kota` varchar(255) DEFAULT NULL,
  `kecamatan` varchar(255) DEFAULT NULL,
  `kelurahan` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `nomorTelepon` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nik` varchar(255) DEFAULT NULL,
  `npwp` varchar(255) DEFAULT NULL,
  `layanan` varchar(255) DEFAULT NULL,
  `peruntukan` varchar(255) DEFAULT NULL,
  `daya` varchar(255) DEFAULT NULL,
  `tokenPerdana` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_gangguan`
--

CREATE TABLE `laporan_gangguan` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `chatId` varchar(30) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `provinsi` varchar(255) DEFAULT NULL,
  `kota` varchar(255) DEFAULT NULL,
  `kecamatan` varchar(255) DEFAULT NULL,
  `kelurahan` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `nomorTelepon` varchar(255) DEFAULT NULL,
  `keterangan` varchar(10000) DEFAULT NULL,
  `media` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_kecurangan`
--

CREATE TABLE `laporan_kecurangan` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `chatId` varchar(30) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `provinsi` varchar(255) DEFAULT NULL,
  `kota` varchar(255) DEFAULT NULL,
  `kecamatan` varchar(255) DEFAULT NULL,
  `kelurahan` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `nomorTelepon` varchar(255) DEFAULT NULL,
  `keterangan` varchar(10000) DEFAULT NULL,
  `media` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `permintaan_livechat`
--

CREATE TABLE `permintaan_livechat` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `chatId` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`chatId`);

--
-- Indexes for table `instalasi_listrik`
--
ALTER TABLE `instalasi_listrik`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `laporan_gangguan`
--
ALTER TABLE `laporan_gangguan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `laporan_kecurangan`
--
ALTER TABLE `laporan_kecurangan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `permintaan_livechat`
--
ALTER TABLE `permintaan_livechat`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `instalasi_listrik`
--
ALTER TABLE `instalasi_listrik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `laporan_gangguan`
--
ALTER TABLE `laporan_gangguan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `laporan_kecurangan`
--
ALTER TABLE `laporan_kecurangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permintaan_livechat`
--
ALTER TABLE `permintaan_livechat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
