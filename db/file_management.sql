-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.2.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for file_mbarara_db
CREATE DATABASE IF NOT EXISTS `file_mbarara_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `file_mbarara_db`;

-- Dumping structure for table file_mbarara_db.department
CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` text DEFAULT NULL,
  `Status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.department: ~6 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `department_name`, `Status`) VALUES
	(7, 'IT', 1),
	(8, 'Directors office', 1),
	(9, 'ACCOUNTS G2G', 1),
	(10, 'HUMAN RESOURCE (HR)', 1),
	(11, 'RECORDS', 1),
	(12, 'Accountant MRRH', 1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.file
CREATE TABLE IF NOT EXISTS `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL,
  `file_name` text DEFAULT NULL,
  `file` text DEFAULT NULL,
  `passcode` text DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT 1,
  `Box_number` text DEFAULT NULL,
  `Shelve_number` text DEFAULT NULL,
  `Group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.file: ~47 rows (approximately)
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` (`file_id`, `staff_id`, `file_name`, `file`, `passcode`, `department_id`, `status`, `Box_number`, `Shelve_number`, `Group_id`) VALUES
	(9, 5, 'testing', '(METERITY REPORT).xls', 'QT7fyz', 7, 0, NULL, NULL, NULL),
	(10, 7, 'testing2', 'to convert-converted.pdf', NULL, 8, 0, NULL, NULL, NULL),
	(11, 6, 'tesing new', 'Computer template for chronic care2(1).docx', NULL, 7, 1, '', '', NULL),
	(12, 8, 'Mbarara RR Hospital Supplementary   Request 2020-2021 Final Draft', 'Mbarara RR Hospital Supplementary   Request 2020-2021 Final Draft.xlsx', NULL, 9, 1, '', '', NULL),
	(13, 5, 'VAT Reporting Guidelines', '1. Updated VAT reporting guidelines.pptx', NULL, 7, 1, '', '', NULL),
	(14, 5, ' PRIME VAT QUATERLY REPORT WORKSHEET', '2. PRIME VAT QUATERLY REPORT WORKSHEET.xls', NULL, 7, 1, '', '', NULL),
	(15, 5, 'VAT ANNUAL REPORT WORKSHEET ', '3. VAT ANNUAL REPORT WORKSHEET  (1).xls', NULL, 7, 1, '', '', NULL),
	(16, 5, 'MRRH_ Quarterly Financial Reporting Templates', '5. MRRH_ Quarterly Financial Reporting Templates.xlsx', NULL, 7, 1, '', '', NULL),
	(17, 5, 'G2G Equipment Budget', 'G2G Equipment Budget Revised.xlsx', NULL, 7, 1, '', '', NULL),
	(18, 10, 'MRRH PERFOMANCE REVIEW JULY_SEPT 2020 FINAL', 'MRRH PERFOMANCE REVIEW JULY_SEPT 2020 FINAL.pptx', NULL, 11, 1, '', '', NULL),
	(19, 8, 'IL', 'Mbarara G2G IL Amendment #1 as of July 8, 2020.pdf', NULL, 9, 1, '', '', NULL),
	(20, 8, 'PEPFAR REPORT', 'MRRH-Quarterly Financial Report-October 4th.xlsx', NULL, 9, 1, '', '', NULL),
	(21, 11, 'Financial statements 2019-20', 'Mbarara_RRH Interns Pay Schedule.pdf', NULL, 12, 1, '', '', NULL),
	(22, 5, '24 hour report', '24-Hour- Report Edited 24-06-2020.docx', NULL, 7, 1, '', '', NULL),
	(23, 8, 'END OF DEC 2020 FINANCIAL REPORT', 'MRRH G2G Financial Report Dec\'20 final.xlsx', NULL, 9, 1, '', '', NULL),
	(24, 8, 'ACCRUAL REPORT', 'MRRH FAR SF1034 & Financial Report   Apr- Jun\'20.xlsx', NULL, 9, 1, '', '', NULL),
	(25, 8, 'VAT REPORT', '2. PRIME VAT QUATERLY REPORT WORKSHEET.xls', NULL, 9, 1, '', '', NULL),
	(26, 8, 'DISBURSEMENT PLAN', 'MRRH FAR Letter to GATR April-June   qtr (1).docx', NULL, 9, 1, '', '', NULL),
	(27, 8, 'FINANCIAL REPORT', 'MRRH FAR SF1034  Financial Report    Apr- Jun\'20.xlsx', NULL, 9, 1, '', '', NULL),
	(28, 8, 'PENDING APN PAYMENTS DEC 2020', 'MRRH APN OUTREACH PENDING LIST.xlsx', NULL, 9, 1, '', '', NULL),
	(29, 5, 'testinj', 'proposal.docx', NULL, 7, 0, '', '', 1),
	(30, 5, 'Packing', 'MBARARA HOSPITAL PACKING SYSTEM.docx', NULL, 7, 1, '', '', 1),
	(31, 5, 'bnbnnb', 'WHO_NUT_97.4.pdf', NULL, 7, 0, '', '', 1),
	(32, 5, 'fff', 'WHO_NUT_97.4.pdf', NULL, 7, 0, '', '', 1),
	(33, 5, 'sds', 'WHO_NUT_97.4.pdf33.', NULL, 7, 0, '', '', 1),
	(34, 5, 'dfdg', 'WHO_NUT_9734.4', NULL, 7, 0, '', '', 1),
	(35, 5, 'Packing1', 'MBARARA HOSPITAL PACKING SYSTEM35.docx', NULL, 7, 0, '', '', 1),
	(36, 5, 'N computing requirements', 'N computing requirements36.docx', NULL, 7, 0, '', '', 1),
	(37, 9, 'Pension Form NS.14', 'Pension Form NS37.14', NULL, 10, 1, '', '', 2),
	(38, 9, 'Minutes of performance review', 'Minutes of the perfomance review presentation to external parties38.', NULL, 10, 1, '', '', 2),
	(39, 9, 'IT Needs', 'IT requirements for referral39.docx', NULL, 10, 1, '', '', 1),
	(40, 9, 'Implementation letter', 'MBARARA IL-617-IL-2020-MRRH (1) (2)40.pdf', NULL, 10, 1, '', '', 3),
	(41, 8, 'FUNDS REQUISITION QTR 3', 'FUNDS REQUISITION JAN 202141.docx', NULL, 9, 1, '', '', NULL),
	(42, 9, 'SHOs', 'Submission for SHOs42.docx', NULL, 10, 1, '', '', 3),
	(43, 7, 'audit responses to IAG', 'audit responses to IAG43.docx', NULL, 8, 1, '', '', NULL),
	(44, 7, 'MBARARA  HOSPITAL PRESSER word', 'MBARARA  HOSPITAL PRESSER word44.docx', NULL, 8, 1, '', '', NULL),
	(45, 7, 'appointment on locum', 'appointment on locum45.docx', NULL, 8, 1, '', '', NULL),
	(46, 7, 'ATWONGYERE_PAMELLAH_REPORT_APRIL_2020', 'ATWONGYERE_PAMELLAH_REPORT_APRIL_2020,,-1[1]46.docx', NULL, 8, 1, '', '', NULL),
	(47, 7, 'Biodata for the medical interns 2019-20', 'Biodata for the medical interns 2019-2047.doc', NULL, 8, 1, '', '', NULL),
	(48, 7, 'Computer maintenance  8-10-20', 'Computer maintenance  8-10-2048.docx', NULL, 8, 1, '', '', NULL),
	(49, 7, 'COMPUTERS MRRH', 'COMPUTERS MRRH49.docx', NULL, 8, 1, '', '', NULL),
	(50, 7, 'correspondaces_notes ', 'correspondaces_notes[1]50.docx', NULL, 8, 1, '', '', NULL),
	(51, 7, 'Course work - information behaviours', 'Course work - information behaviours51.docx', NULL, 8, 1, '', '', NULL),
	(52, 7, 'coursework - information behaviour', 'coursework - information behaviour52.docx', NULL, 8, 1, '', '', NULL),
	(53, 7, 'Data base  course work', 'Data base  course work53.docx', NULL, 8, 1, '', '', NULL),
	(54, 8, 'FAR ADVANCE REQUEST FEB 21', 'FAR ADVANCE REQUEST FEB 202154.pdf', NULL, 9, 1, '', '', NULL),
	(55, 8, 'CR ADVANCE REQUEST FEB 21', 'CR ADVANCE REQUEST FEB 202155.pdf', NULL, 9, 1, '', '', NULL),
	(56, 8, 'FAR ADVANCE REQUEST MARCH 21', 'FAR ADVANCE REQUEST MARCH 202156.pdf', NULL, 9, 1, '', '', NULL),
	(57, 8, 'CR ADVANCE REQUEST MARCH 21', 'CR ADVANCE REQUEST MARCH 202157.pdf', NULL, 9, 1, '', '', NULL),
	(58, 5, 'Mbarara RRH - ICT Needs Assessment Report 2020_Jul', 'Mbarara RRH - ICT Needs Assessment Report 2020_Jul58.pdf', NULL, 7, 1, '', '', NULL);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.grouped_files
CREATE TABLE IF NOT EXISTS `grouped_files` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Receiver_id` int(11) DEFAULT NULL,
  `Sender_id` int(11) DEFAULT NULL,
  `Group_id` int(11) DEFAULT NULL,
  `Time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Status` int(11) DEFAULT 1,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.grouped_files: ~5 rows (approximately)
/*!40000 ALTER TABLE `grouped_files` DISABLE KEYS */;
INSERT INTO `grouped_files` (`Id`, `Receiver_id`, `Sender_id`, `Group_id`, `Time`, `Status`) VALUES
	(1, 9, 5, 1, '2020-12-21 11:36:29', 1),
	(3, 7, 9, 2, '2020-12-22 12:25:58', 1),
	(4, 10, 9, 2, '2020-12-22 12:25:58', 1),
	(5, 11, 9, 2, '2020-12-22 12:25:58', 1),
	(7, 12, 9, 3, '2020-12-22 15:23:16', 1),
	(9, 9, 7, 4, '2021-01-13 12:05:23', 1);
/*!40000 ALTER TABLE `grouped_files` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.groups
CREATE TABLE IF NOT EXISTS `groups` (
  `Group_id` int(11) NOT NULL AUTO_INCREMENT,
  `Group_name` varchar(100) DEFAULT NULL,
  `Staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.groups: ~3 rows (approximately)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` (`Group_id`, `Group_name`, `Staff_id`) VALUES
	(1, 'IT department', 5),
	(2, 'FIN & ADMIN', 9),
	(3, 'HUMAN RESOURCE', 9),
	(4, 'SECRETARIAL WORK', 7);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.messaging
CREATE TABLE IF NOT EXISTS `messaging` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Sender_id` int(11) DEFAULT NULL,
  `Receiver_id` int(11) DEFAULT NULL,
  `Message` text DEFAULT NULL,
  `Time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Status` int(11) DEFAULT 1,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.messaging: ~11 rows (approximately)
/*!40000 ALTER TABLE `messaging` DISABLE KEYS */;
INSERT INTO `messaging` (`Id`, `Sender_id`, `Receiver_id`, `Message`, `Time`, `Status`) VALUES
	(210, 6, 5, 'hello antony', '2020-11-07 19:16:52', 0),
	(211, 5, 6, 'very well boss', '2020-11-07 19:21:43', 0),
	(212, 6, 5, 'yap', '2020-11-07 19:17:29', 0),
	(213, 5, 8, 'Hello', '2020-11-23 12:02:48', 0),
	(214, 10, 5, 'Reda this file', '2020-11-23 12:03:22', 0),
	(215, 5, 9, 'hello Anton', '2020-11-25 16:10:12', 0),
	(216, 5, 8, 'Are you there', '2020-12-08 14:02:11', 1),
	(217, 9, 11, 'Ben', '2021-01-06 12:08:05', 0),
	(218, 8, 9, 'Good afternoon Mr Balikuddembe.', '2021-01-06 12:46:14', 0),
	(219, 9, 8, 'Good afternoon Madam', '2021-01-13 11:59:47', 0),
	(220, 7, 9, 'Hello Sir, hope are doing well', '2021-01-13 12:00:14', 1),
	(221, 5, 12, 'hi', '2021-05-25 11:16:43', 1);
/*!40000 ALTER TABLE `messaging` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.restrict_member
CREATE TABLE IF NOT EXISTS `restrict_member` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Staff_id` int(11) DEFAULT NULL,
  `File_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.restrict_member: ~0 rows (approximately)
/*!40000 ALTER TABLE `restrict_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `restrict_member` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.shared_files
CREATE TABLE IF NOT EXISTS `shared_files` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Receiver_id` int(11) DEFAULT NULL,
  `Sender_id` int(11) DEFAULT NULL,
  `File_id` int(11) DEFAULT NULL,
  `Time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `Status` int(11) DEFAULT 1,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.shared_files: ~8 rows (approximately)
/*!40000 ALTER TABLE `shared_files` DISABLE KEYS */;
INSERT INTO `shared_files` (`Id`, `Receiver_id`, `Sender_id`, `File_id`, `Time`, `Status`) VALUES
	(153, 5, 6, 11, '2020-11-07 19:18:35', 0),
	(154, 8, 5, 13, '2020-11-13 11:11:31', 1),
	(155, 8, 5, 14, '2020-11-13 11:11:39', 1),
	(156, 8, 5, 15, '2020-11-13 11:11:48', 1),
	(157, 8, 5, 16, '2020-11-13 11:11:58', 1),
	(158, 8, 5, 12, '2020-11-13 11:12:17', 1),
	(159, 9, 10, 18, '2020-11-25 16:10:55', 0),
	(160, 8, 11, 21, '2020-12-08 14:30:13', 1);
/*!40000 ALTER TABLE `shared_files` ENABLE KEYS */;

-- Dumping structure for table file_mbarara_db.staff
CREATE TABLE IF NOT EXISTS `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) DEFAULT NULL,
  `name` text DEFAULT NULL,
  `staff_title` text DEFAULT NULL,
  `user_name` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `Status` int(11) DEFAULT 1,
  `user_type` text DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table file_mbarara_db.staff: ~8 rows (approximately)
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`staff_id`, `department_id`, `name`, `staff_title`, `user_name`, `password`, `Status`, `user_type`) VALUES
	(5, 7, 'Muganzi Anton', 'IT', 'anton', '8e1b9a11fa3030029af3730caebddd7d', 1, 'Admin'),
	(6, 7, 'testing', 'IT', 'testing', 'ae2b1fca515949e5d54fb22b8ed95575', 0, 'User'),
	(7, 8, 'Esther', 'Secretary', 'esther', 'c7ec7622a90bff8a4f08ba7f4a3dd490', 1, 'User'),
	(8, 9, 'NAKAZIBA JULIET', 'SENIOR ASSISTANT ACCOUNTANT', 'nakaziba', 'dd15c018195613878893a8450b761286', 1, 'User'),
	(9, 10, 'Barikudembe Joseph', 'HR', 'hr', '369d808aadf3274f80e186f442d089c2', 1, 'Admin'),
	(10, 11, 'Sanyu Frank', 'RECORDS HOD', 'records', 'e887efb4b1c99b95bcf32dcaddae8e57', 1, 'User'),
	(11, 12, 'Ben Ahabwe', 'Account', 'ben', '7fe4771c008a22eb763df47d19e2c6aa', 1, 'User'),
	(12, 10, 'Emojong Pius', 'PHR', 'pemojong', 'b266e47eb657161825cded6cc0dd5730', 1, 'User');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
