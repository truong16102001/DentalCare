-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: swp391
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 07:43:53.042000','a','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,4,1),(2,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:13:01.498000','a','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,2,1),(3,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:16:41.107000','nn','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,2,1),(4,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:18:28.162000','dd','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,2,1),(5,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:20:01.265000','111','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,2,2),(6,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:20:40.213000','a','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,4,1),(7,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:21:35.560000','ff','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,4,4),(8,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:25:14.336000','aaa','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,4,1),(9,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:30:09.347000','a','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,1,2),(10,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:32:08.322000','qq','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,1,1),(11,'hn','2025-06-09',NULL,'truongx62001@gmail.com','2025-06-09 08:50:10.240000','r','Application for .NET Developer Position - Nguyen Dinh Truong','0366313061','Pending',NULL,NULL,2,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'/images/khamtongquat1.png'),(2,'/images/caovoirang1.png'),(3,'/images/taytrangrang1.png'),(4,'/images/chupxquangrang1.png');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoice_medicine_detail`
--

LOCK TABLES `invoice_medicine_detail` WRITE;
/*!40000 ALTER TABLE `invoice_medicine_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_medicine_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicines`
--

LOCK TABLES `medicines` WRITE;
/*!40000 ALTER TABLE `medicines` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `patient_report`
--

LOCK TABLES `patient_report` WRITE;
/*!40000 ALTER TABLE `patient_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `patient_report_images`
--

LOCK TABLES `patient_report_images` WRITE;
/*!40000 ALTER TABLE `patient_report_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_report_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `report_medicine`
--

LOCK TABLES `report_medicine` WRITE;
/*!40000 ALTER TABLE `report_medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,_binary '','ADMIN'),(2,_binary '','MANAGER'),(3,_binary '','DOCTOR'),(4,_binary '','RECEPTIONIST'),(5,_binary '','GUEST');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_image`
--

LOCK TABLES `service_image` WRITE;
/*!40000 ALTER TABLE `service_image` DISABLE KEYS */;
INSERT INTO `service_image` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `service_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Khám tổng quát',_binary '',100000,'Khám tổng quát'),(2,'Cạo vôi răng',_binary '',200000,'Cạo vôi răng'),(3,'Tẩy trắng răng',_binary '',1500000,'Tẩy trắng răng'),(4,'Chụp X-quang răng',_binary '',300000,'Chụp X-quang răng');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `slots`
--

LOCK TABLES `slots` WRITE;
/*!40000 ALTER TABLE `slots` DISABLE KEYS */;
INSERT INTO `slots` VALUES (1,'09:00:00.000000',_binary '','08:00:00.000000'),(2,'10:00:00.000000',_binary '','09:00:00.000000'),(3,'11:00:00.000000',_binary '','10:00:00.000000'),(4,'12:00:00.000000',_binary '','11:00:00.000000'),(5,'13:00:00.000000',_binary '','12:00:00.000000'),(6,'14:00:00.000000',_binary '','13:00:00.000000'),(7,'15:00:00.000000',_binary '','14:00:00.000000'),(8,'16:00:00.000000',_binary '','15:00:00.000000'),(9,'17:00:00.000000',_binary '','16:00:00.000000'),(10,'18:00:00.000000',_binary '','17:00:00.000000'),(11,'19:00:00.000000',_binary '','18:00:00.000000'),(12,'20:00:00.000000',_binary '','19:00:00.000000'),(13,'21:00:00.000000',_binary '','20:00:00.000000'),(14,'22:00:00.000000',_binary '','21:00:00.000000');
/*!40000 ALTER TABLE `slots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'123 Admin Street','data:image/png;base64,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','1990-01-01','admin@gmail.com','Adminnnnn','Female',_binary '','admin1234','0123456789',1),(2,'456 Manager Ave','','1991-02-02','manager@gmail.com','Manager User','Female',_binary '','manager123','0234567891',2),(3,'789 Doctor Blvd','','1992-03-03','doctor@gmail.com','Doctor User','Male',_binary '','doctor123','0345678912',3),(4,'321 Reception Rd','','1993-04-04','receptionist@gmail.com','Receptionist User','Female',_binary '','reception123','0456789123',4),(5,'654 Guest Ln','','1994-05-05','guest@gmail.com','Guest User','Other',_binary '','guest123','0567891234',5);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `working_schedules`
--

LOCK TABLES `working_schedules` WRITE;
/*!40000 ALTER TABLE `working_schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `working_schedules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 17:28:32
