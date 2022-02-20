-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: hospital_management
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `name` varchar(25) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `special_at` varchar(20) NOT NULL,
  `joined_at` date NOT NULL,
  `ward_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ward_name` (`ward_name`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`ward_name`) REFERENCES `ward` (`ward_name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('Dr. Nitu',1,'Medicine','2020-09-12','Medicine'),('Dr. Saifullah',2,'Medicine','2020-09-20','Medicine'),('Dr. Millat',3,'Medicine','2020-09-20','Medicine'),('Dr. Marufa',4,'Psychiatry','2020-10-28','Psychiatry'),('Dr. Mayeesha',5,'Psychiatry','2020-02-14','Psychiatry'),('Dr. Amena',6,'Psychiatry','2020-09-30','Psychiatry'),('Dr. Hena',7,'Surgery','2020-03-05','Surgery'),('Dr. Rifah',8,'Surgery','2020-03-07','Surgery'),('Dr. Nayeem',9,'Surgery','2020-03-10','Surgery'),('Dr. Morshed',10,'Urology','2020-04-15','Urology'),('Dr. Richi',11,'Urology','2020-04-19','Urology'),('Dr. Tushar',12,'Urology','2020-04-22','Urology'),('Dr. Sakib',13,'Gynaecology','2020-01-24','Gynaecology'),('Dr. Sajib',14,'Gynaecology','2020-01-25','Gynaecology'),('Dr. Nahid',15,'Gynaecology','2020-01-28','Gynaecology'),('Dr. Tanvir',16,'Nephrology','2020-06-11','Nephrology'),('Dr. Shahriar',17,'Nephrology','2020-06-14','Nephrology'),('Dr. Urme',18,'Nephrology','2020-06-18','Nephrology'),('Dr. Riya',19,'Pediatrics','2020-07-03','Pediatrics'),('Dr. Asim',20,'Pediatrics','2020-07-07','Pediatrics'),('Dr. Ronok',21,'Pediatrics','2020-07-10','Pediatrics'),('Dr. Himaloy',22,'Pediatrics','2020-10-20','Pediatrics');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `name` varchar(20) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('Admin',1,'Incorrect'),('Shafaet',2,'incorrect'),('Max',3,'asdf'),('Emon',5,'as'),('Shakil',6,'qw');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `name` varchar(25) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `admitted_at` date NOT NULL,
  `release_at` date DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `seat_no` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `seat_no` (`seat_no`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`seat_no`) REFERENCES `seat` (`seat_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('Md. Farhad',1,'2020-10-19',NULL,'Admitted',11),('Abdullah Muin',2,'2020-10-19','2020-10-19','Released',5),('Md. Saifullah',3,'2020-10-20',NULL,'Admitted',1),('Md Shafaet',4,'2020-10-20',NULL,'Admitted',8);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_no` int NOT NULL AUTO_INCREMENT,
  `room_no` int NOT NULL,
  `ward_name` varchar(20) NOT NULL,
  `available` tinyint(1) NOT NULL,
  PRIMARY KEY (`seat_no`),
  KEY `ward_name` (`ward_name`),
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`ward_name`) REFERENCES `ward` (`ward_name`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,'Medicine',0),(2,1,'Medicine',1),(3,1,'Medicine',1),(4,1,'Medicine',1),(5,1,'Medicine',1),(6,2,'Medicine',1),(7,2,'Medicine',1),(8,2,'Medicine',0),(9,2,'Medicine',1),(10,2,'Medicine',1),(11,3,'Psychiatry',0),(12,3,'Psychiatry',1),(13,3,'Psychiatry',1),(14,3,'Psychiatry',1),(15,3,'Psychiatry',1),(16,4,'Psychiatry',1),(17,4,'Psychiatry',1),(18,4,'Psychiatry',1),(19,4,'Psychiatry',1),(20,4,'Psychiatry',1),(21,5,'Surgery',1),(22,5,'Surgery',1),(23,5,'Surgery',1),(24,5,'Surgery',1),(25,5,'Surgery',1),(26,6,'Surgery',1),(27,6,'Surgery',1),(28,6,'Surgery',1),(29,6,'Surgery',1),(30,6,'Surgery',1),(31,7,'Urology',1),(32,7,'Urology',1),(33,7,'Urology',1),(34,7,'Urology',1),(35,7,'Urology',1),(36,8,'Urology',1),(37,8,'Urology',1),(38,8,'Urology',1),(39,8,'Urology',1),(40,8,'Urology',1),(41,9,'Gynaecology',1),(42,9,'Gynaecology',1),(43,9,'Gynaecology',1),(44,9,'Gynaecology',1),(45,9,'Gynaecology',1),(46,10,'Gynaecology',1),(47,10,'Gynaecology',1),(48,10,'Gynaecology',1),(49,10,'Gynaecology',1),(50,10,'Gynaecology',1),(51,11,'Nephrology',1),(52,11,'Nephrology',1),(53,11,'Nephrology',1),(54,11,'Nephrology',1),(55,11,'Nephrology',1),(56,12,'Nephrology',1),(57,12,'Nephrology',1),(58,12,'Nephrology',1),(59,12,'Nephrology',1),(60,12,'Nephrology',1),(61,13,'Pediatrics',1),(62,13,'Pediatrics',1),(63,13,'Pediatrics',1),(64,13,'Pediatrics',1),(65,13,'Pediatrics',1),(66,14,'Pediatrics',1),(67,14,'Pediatrics',1),(68,14,'Pediatrics',1),(69,14,'Pediatrics',1),(70,14,'Pediatrics',1);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward` (
  `ward_name` varchar(20) NOT NULL,
  `number_of_rooms` int NOT NULL,
  `number_of_seats` int NOT NULL,
  PRIMARY KEY (`ward_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES ('Gynaecology',2,10),('Medicine',2,10),('Nephrology',2,10),('Pediatrics',2,10),('Psychiatry',2,10),('Surgery',2,10),('Urology',2,10);
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-20 14:29:56
