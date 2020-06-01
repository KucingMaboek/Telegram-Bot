-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2020 at 08:56 PM
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
-- Database: `plnbot`
--

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `id` int(11) NOT NULL,
  `chatId` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `requestCode` varchar(3) NOT NULL,
  `text` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `conversation`
--

INSERT INTO `conversation` (`id`, `chatId`, `username`, `requestCode`, `text`) VALUES
(7, '692695611', 'kucingmaboek', '000', 'ya');

-- --------------------------------------------------------

--
-- Table structure for table `instalasi_listrik`
--

CREATE TABLE `instalasi_listrik` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `chatId` varchar(255) NOT NULL,
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

--
-- Dumping data for table `instalasi_listrik`
--

INSERT INTO `instalasi_listrik` (`id`, `date`, `chatId`, `nama`, `provinsi`, `kota`, `kecamatan`, `kelurahan`, `alamat`, `nomorTelepon`, `email`, `nik`, `npwp`, `layanan`, `peruntukan`, `daya`, `tokenPerdana`, `status`) VALUES
(1, '2020-06-02', '692695611', 'iqbal', 'jawa timur', 'jember', 'kaliwates', 'kepatihan', 'rumah', '123456', 'kkk@gmail.com', '123124012', 'a11e0241', 'prabayar', 'rumah tangga', '450', '5000', 'Belum di Proses');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_gangguan`
--

CREATE TABLE `laporan_gangguan` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `chatId` varchar(255) NOT NULL,
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
  `chatId` varchar(255) NOT NULL,
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
-- Dumping data for table `permintaan_livechat`
--

INSERT INTO `permintaan_livechat` (`id`, `date`, `time`, `chatId`, `username`, `status`) VALUES
(1, '2020-06-02', '01:32:17', '692695611', 'https://t.me/kucingmaboek', 'Belum di Proses');

-- --------------------------------------------------------

--
-- Table structure for table `tg_account_data`
--

CREATE TABLE `tg_account_data` (
  `chatId` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tg_account_data`
--

INSERT INTO `tg_account_data` (`chatId`, `username`, `first_name`, `last_name`) VALUES
('692695611', 'kucingmaboek', 'kucingmaboek', 'null');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `birth_date` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `birth_date`, `address`, `email`, `phone_number`) VALUES
(1, 'admin', 'admin', 'admin', '2020-06-02', 'default', 'admin@plnbot.com', 'default');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatId` (`chatId`);

--
-- Indexes for table `instalasi_listrik`
--
ALTER TABLE `instalasi_listrik`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatId` (`chatId`);

--
-- Indexes for table `laporan_gangguan`
--
ALTER TABLE `laporan_gangguan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatId` (`chatId`);

--
-- Indexes for table `laporan_kecurangan`
--
ALTER TABLE `laporan_kecurangan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatId` (`chatId`);

--
-- Indexes for table `permintaan_livechat`
--
ALTER TABLE `permintaan_livechat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatId` (`chatId`);

--
-- Indexes for table `tg_account_data`
--
ALTER TABLE `tg_account_data`
  ADD PRIMARY KEY (`chatId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `instalasi_listrik`
--
ALTER TABLE `instalasi_listrik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `conversation`
--
ALTER TABLE `conversation`
  ADD CONSTRAINT `conversation_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `tg_account_data` (`chatId`);

--
-- Constraints for table `instalasi_listrik`
--
ALTER TABLE `instalasi_listrik`
  ADD CONSTRAINT `instalasi_listrik_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `tg_account_data` (`chatId`);

--
-- Constraints for table `laporan_gangguan`
--
ALTER TABLE `laporan_gangguan`
  ADD CONSTRAINT `laporan_gangguan_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `tg_account_data` (`chatId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `laporan_kecurangan`
--
ALTER TABLE `laporan_kecurangan`
  ADD CONSTRAINT `laporan_kecurangan_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `tg_account_data` (`chatId`);

--
-- Constraints for table `permintaan_livechat`
--
ALTER TABLE `permintaan_livechat`
  ADD CONSTRAINT `permintaan_livechat_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `tg_account_data` (`chatId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
