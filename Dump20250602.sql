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
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,_binary '','ADMIN'),(2,_binary '','MANAGER'),(3,_binary '','DOCTOR'),(4,_binary '','RECEPTIONIST'),(5,_binary '','PATIENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `avatar` longtext,
  `dob` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `roleid` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK9q63snka3mdh91as4io72espi` (`phone_number`),
  KEY `FKgrhs0suhl8cbodxn47xadxp94` (`roleid`),
  CONSTRAINT `FKgrhs0suhl8cbodxn47xadxp94` FOREIGN KEY (`roleid`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'123 Admin Street','data:image/png;base64,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','2028-01-02','admin@gmail.com','Adminnnnnn','Female',_binary '','ADMIN12345','0123456789',1),(2,'456 Manager Ave','','1991-02-02','manager@gmail.com','Manager User','Female',_binary '','manager123','0234567891',2),(3,'789 Doctor Blvd','','1992-03-03','doctor@gmail.com','Doctor User','Male',_binary '','doctor123','0345678912',3),(4,'321 Reception Rd','','1993-04-04','receptionist@gmail.com','Receptionist User','Female',_binary '','reception123','0456789123',4),(5,'654 Guest Ln','','1994-05-05','guest@gmail.com','Patient User','Other',_binary '','guest123','0567891234',5),(6,'hn',NULL,'2025-06-13','truongx62001@gmail.com','test1',NULL,_binary '','607128','0366313061',5);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-02 17:29:47
