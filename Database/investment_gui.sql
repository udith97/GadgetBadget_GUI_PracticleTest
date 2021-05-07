-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2021 at 09:43 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `investment_gui`
--

CREATE TABLE `investment_gui` (
  `investmentID` int(11) NOT NULL,
  `projectName` varchar(100) NOT NULL,
  `researcherName` varchar(100) NOT NULL,
  `investorName` varchar(100) NOT NULL,
  `investAmount` double NOT NULL,
  `cardNo` varchar(100) NOT NULL,
  `cvvNo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `investment_gui`
--

INSERT INTO `investment_gui` (`investmentID`, `projectName`, `researcherName`, `investorName`, `investAmount`, `cardNo`, `cvvNo`) VALUES
(17, 'Windows Vista', 'Bill Gates', 'Windows', 100000, '987654321', '765'),
(18, 'Tesla', 'Elon Musk', 'Elon Musk', 100, '123456789', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `investment_gui`
--
ALTER TABLE `investment_gui`
  ADD PRIMARY KEY (`investmentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `investment_gui`
--
ALTER TABLE `investment_gui`
  MODIFY `investmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
