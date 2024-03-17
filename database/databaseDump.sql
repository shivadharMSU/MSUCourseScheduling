-- MySQL dump 10.13  Distrib 8.3.0, for macos14 (arm64)
--
-- Host: localhost    Database: msu_course_scheduling
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `class_room`
--

DROP TABLE IF EXISTS `class_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_room`
--

LOCK TABLES `class_room` WRITE;
/*!40000 ALTER TABLE `class_room` DISABLE KEYS */;
INSERT INTO `class_room` VALUES (1,'main hall 101'),(2,'main hall 202'),(3,'cs block 202'),(4,'cs block 203'),(5,'cs block 204'),(6,'cs block 206'),(7,'richardson hall 101'),(8,'richardson hall 102'),(9,'richardson hall 103'),(10,'richardson hall 104'),(11,'richardson hall 105');
/*!40000 ALTER TABLE `class_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_room_schdule`
--

DROP TABLE IF EXISTS `class_room_schdule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_room_schdule` (
  `room_schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` int DEFAULT NULL,
  `sem_id` int DEFAULT NULL,
  `schedule` text,
  PRIMARY KEY (`room_schedule_id`),
  KEY `class_room_schdule_class_room_FK` (`room_id`),
  KEY `class_room_schdule_Semester_FK` (`sem_id`),
  CONSTRAINT `class_room_schdule_class_room_FK` FOREIGN KEY (`room_id`) REFERENCES `class_room` (`room_id`),
  CONSTRAINT `class_room_schdule_Semester_FK` FOREIGN KEY (`sem_id`) REFERENCES `Semester` (`sem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_room_schdule`
--

LOCK TABLES `class_room_schdule` WRITE;
/*!40000 ALTER TABLE `class_room_schdule` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_room_schdule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_details`
--

DROP TABLE IF EXISTS `course_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_details` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `course_number` int DEFAULT NULL,
  `crn` int DEFAULT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `cmputer_required` tinyint DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` VALUES (1,100,40305,'Introduction to Computer Concepts.',3,0),(2,104,40332,'Python Programming I',3,1),(3,111,40338,'Fundamentals of Java Programming',3,0),(4,170,44915,'Discrete Mathematics',3,0),(5,114,42101,'Python Programming II',3,0),(6,212,40342,'Data Structures and Algorithms',3,0),(7,213,42627,'Data Structures and Algorithms in Python',3,0),(8,230,40344,'Computer Systems.',3,0),(9,231,42076,'Systems Programming',3,0),(10,274,42628,'Multimedia Computing',3,0),(11,275,43642,'Introduction to R Programming',3,0);
/*!40000 ALTER TABLE `course_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_professor_mapping`
--

DROP TABLE IF EXISTS `course_professor_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_professor_mapping` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_professor_mapping_course_details_FK` (`course_id`),
  KEY `course_professor_mapping_professor_details_FK` (`professor_id`),
  CONSTRAINT `course_professor_mapping_course_details_FK` FOREIGN KEY (`course_id`) REFERENCES `course_details` (`course_id`),
  CONSTRAINT `course_professor_mapping_professor_details_FK` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_professor_mapping`
--

LOCK TABLES `course_professor_mapping` WRITE;
/*!40000 ALTER TABLE `course_professor_mapping` DISABLE KEYS */;
INSERT INTO `course_professor_mapping` VALUES (1,1,1),(2,2,2),(5,4,4),(6,3,4),(7,5,5),(8,6,6),(9,7,7),(10,8,8),(11,9,9),(12,10,10),(13,11,11);
/*!40000 ALTER TABLE `course_professor_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_schedule`
--

DROP TABLE IF EXISTS `course_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_schedule` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `sem_id` int DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `schedule` varchar(100) DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `cross_section_schdule_id` bigint DEFAULT NULL,
  `course_duration` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `course_schedule_Semester_FK` (`sem_id`),
  KEY `course_schedule_section_FK` (`section_id`),
  KEY `course_schedule_course_details_FK` (`course_id`),
  KEY `course_schedule_professor_details_FK` (`professor_id`),
  KEY `course_schedule_class_room_FK` (`room_id`),
  CONSTRAINT `course_schedule_class_room_FK` FOREIGN KEY (`room_id`) REFERENCES `class_room` (`room_id`),
  CONSTRAINT `course_schedule_course_details_FK` FOREIGN KEY (`course_id`) REFERENCES `course_details` (`course_id`),
  CONSTRAINT `course_schedule_professor_details_FK` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`professor_id`),
  CONSTRAINT `course_schedule_section_FK` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`),
  CONSTRAINT `course_schedule_Semester_FK` FOREIGN KEY (`sem_id`) REFERENCES `Semester` (`sem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_schedule`
--

LOCK TABLES `course_schedule` WRITE;
/*!40000 ALTER TABLE `course_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_semester_mappping`
--

DROP TABLE IF EXISTS `course_semester_mappping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_semester_mappping` (
  `course_semester_mapping_id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint DEFAULT NULL,
  `sem_id` int DEFAULT NULL,
  `tenure` int DEFAULT NULL,
  `term_tenure` int DEFAULT NULL,
  PRIMARY KEY (`course_semester_mapping_id`),
  KEY `course_semester_mappping_Semester_FK` (`sem_id`),
  KEY `course_semester_mappping_course_details_FK` (`course_id`),
  CONSTRAINT `course_semester_mappping_course_details_FK` FOREIGN KEY (`course_id`) REFERENCES `course_details` (`course_id`),
  CONSTRAINT `course_semester_mappping_Semester_FK` FOREIGN KEY (`sem_id`) REFERENCES `Semester` (`sem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_semester_mappping`
--

LOCK TABLES `course_semester_mappping` WRITE;
/*!40000 ALTER TABLE `course_semester_mappping` DISABLE KEYS */;
INSERT INTO `course_semester_mappping` VALUES (1,1,3,4,NULL),(2,2,4,4,NULL),(3,3,3,4,NULL),(4,4,4,4,NULL),(5,5,3,4,NULL),(6,6,4,4,NULL),(7,7,3,4,NULL),(8,8,4,4,NULL),(9,9,3,4,NULL),(10,10,4,4,NULL),(11,11,3,4,NULL);
/*!40000 ALTER TABLE `course_semester_mappping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_availability`
--

DROP TABLE IF EXISTS `professor_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_availability` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `professor_id` bigint DEFAULT NULL,
  `sem_name_id` int DEFAULT NULL,
  `day_of_week` int DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `end_time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `temp_professor_availability_professor_details_FK` (`professor_id`),
  CONSTRAINT `temp_professor_availability_professor_details_FK` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_availability`
--

LOCK TABLES `professor_availability` WRITE;
/*!40000 ALTER TABLE `professor_availability` DISABLE KEYS */;
INSERT INTO `professor_availability` VALUES (1,1,1,1,'08:00:00.000000','10:00:00.000000'),(2,1,1,3,'14:00:00.000000','16:00:00.000000'),(3,1,2,5,'09:00:00.000000','11:00:00.000000'),(4,2,1,2,'10:00:00.000000','12:00:00.000000'),(5,2,1,4,'15:00:00.000000','17:00:00.000000'),(6,2,2,5,'13:00:00.000000','15:00:00.000000');
/*!40000 ALTER TABLE `professor_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_details`
--

DROP TABLE IF EXISTS `professor_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_details` (
  `professor_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `course_load` varchar(255) DEFAULT NULL,
  `professor_type` int DEFAULT NULL,
  PRIMARY KEY (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_details`
--

LOCK TABLES `professor_details` WRITE;
/*!40000 ALTER TABLE `professor_details` DISABLE KEYS */;
INSERT INTO `professor_details` VALUES (1,'John Doe','2,3,3,2',1),(2,'Jane Smith','2,3,3,2',2),(4,'Zhang, Wei ','2,3,3,1',2),(5,'Kousoulis, Minas','2,3,4,2',1),(6,'Antoniou, Maria','2,4,4,4',1),(7,'Zhang, Wei','2,3,2,1',2),(8,'Boddu, Harika','2,3,1,4',1),(9,'Aslandogan, Yuksel','2,1,2,2',2),(10,'Buhler, Gary ','1,2,2,1',1),(11,'Mbow, Abdou','2,1,2,1',2);
/*!40000 ALTER TABLE `professor_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_schedule`
--

DROP TABLE IF EXISTS `professor_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `professor_id` bigint DEFAULT NULL,
  `sem_id` int DEFAULT NULL,
  `schedule` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `professor_schedule_professor_details_FK` (`professor_id`),
  KEY `professor_schedule_Semester_FK` (`sem_id`),
  CONSTRAINT `professor_schedule_professor_details_FK` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`professor_id`),
  CONSTRAINT `professor_schedule_Semester_FK` FOREIGN KEY (`sem_id`) REFERENCES `Semester` (`sem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_schedule`
--

LOCK TABLES `professor_schedule` WRITE;
/*!40000 ALTER TABLE `professor_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `professor_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_type`
--

DROP TABLE IF EXISTS `professor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_type`
--

LOCK TABLES `professor_type` WRITE;
/*!40000 ALTER TABLE `professor_type` DISABLE KEYS */;
INSERT INTO `professor_type` VALUES (1,'Full-Time'),(2,'Part-Time'),(3,'Adjunct'),(4,'Visiting'),(5,'Emeritus');
/*!40000 ALTER TABLE `professor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `section_id` bigint NOT NULL,
  `section_no` varchar(100) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `max_capacity` int DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `cross_section_id` bigint DEFAULT NULL,
  `course_semester_mapping_id` bigint DEFAULT NULL,
  PRIMARY KEY (`section_id`),
  KEY `section_class_room_FK` (`room_id`),
  KEY `section_professor_details_FK` (`professor_id`),
  KEY `section_course_semester_mappping_FK` (`course_semester_mapping_id`),
  CONSTRAINT `section_class_room_FK` FOREIGN KEY (`room_id`) REFERENCES `class_room` (`room_id`),
  CONSTRAINT `section_course_semester_mappping_FK` FOREIGN KEY (`course_semester_mapping_id`) REFERENCES `course_semester_mappping` (`course_semester_mapping_id`),
  CONSTRAINT `section_professor_details_FK` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`professor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,'01',25,25,1,1,NULL,1),(2,'02',25,25,2,2,NULL,1),(3,'01',50,50,4,3,NULL,2),(4,'02',50,50,5,4,NULL,2),(5,'02',50,50,6,5,NULL,2),(6,'01',30,30,7,6,NULL,3),(7,'02',30,30,8,7,NULL,3),(8,'01',25,25,9,8,NULL,4),(9,'02',25,25,1,9,NULL,4),(10,'01',25,25,2,1,NULL,5),(11,'01',25,25,6,2,NULL,6),(12,'01',50,50,4,3,NULL,7),(13,'02',50,50,5,4,NULL,7),(14,'01',25,25,6,7,NULL,8),(15,'01',25,25,7,3,NULL,9),(16,'01',25,25,8,2,NULL,10),(17,'01',25,25,9,3,NULL,11),(18,'02',25,25,5,1,NULL,11);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_schedule`
--

DROP TABLE IF EXISTS `section_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section_schedule` (
  `section_scheduled_id` bigint NOT NULL AUTO_INCREMENT,
  `section_id` bigint DEFAULT NULL,
  `week_day` int DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `end_time` time(6) DEFAULT NULL,
  PRIMARY KEY (`section_scheduled_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_schedule`
--

LOCK TABLES `section_schedule` WRITE;
/*!40000 ALTER TABLE `section_schedule` DISABLE KEYS */;
INSERT INTO `section_schedule` VALUES (1,1,1,'14:00:00.000000','14:00:00.000000'),(2,2,2,'13:00:00.000000','15:00:00.000000'),(3,2,3,'13:00:00.000000','15:00:00.000000'),(4,3,1,'13:00:00.000000','14:00:00.000000'),(5,3,2,'13:00:00.000000','14:00:00.000000'),(6,4,3,'08:00:00.000000','10:00:00.000000'),(7,5,4,'11:00:00.000000','13:00:00.000000'),(8,6,2,'11:00:00.000000','15:00:00.000000'),(9,7,4,'12:00:00.000000','12:00:00.000000'),(10,8,5,'11:00:00.000000','15:00:00.000000'),(11,9,3,'14:00:00.000000','15:00:00.000000'),(12,10,1,'13:00:00.000000','13:00:00.000000'),(13,11,5,'09:00:00.000000','15:00:00.000000'),(14,12,4,'09:00:00.000000','15:00:00.000000'),(15,13,3,'10:00:00.000000','15:00:00.000000'),(16,14,2,'06:00:00.000000','15:00:00.000000'),(17,15,3,'14:00:00.000000','15:00:00.000000'),(18,16,1,'16:00:00.000000','15:00:00.000000'),(19,17,2,'08:00:00.000000','17:00:00.000000'),(20,17,4,'15:00:00.000000','15:00:00.000000'),(21,18,1,'08:00:00.000000','12:00:00.000000');
/*!40000 ALTER TABLE `section_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Semester`
--

DROP TABLE IF EXISTS `Semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Semester` (
  `sem_id` int NOT NULL AUTO_INCREMENT,
  `sem_name_id` int DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sem_id`),
  KEY `Semester_semester_name_FK` (`sem_name_id`),
  CONSTRAINT `Semester_semester_name_FK` FOREIGN KEY (`sem_name_id`) REFERENCES `semester_name` (`sem_name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semester`
--

LOCK TABLES `Semester` WRITE;
/*!40000 ALTER TABLE `Semester` DISABLE KEYS */;
INSERT INTO `Semester` VALUES (1,1,'2024'),(2,3,'2024'),(3,1,'2023'),(4,3,'2023'),(5,1,'2022'),(6,2,'2022'),(7,3,'2022'),(8,4,'2022'),(9,1,'2021'),(10,2,'2021'),(11,3,'2021'),(13,1,'2025'),(17,2,'2025');
/*!40000 ALTER TABLE `Semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_name`
--

DROP TABLE IF EXISTS `semester_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester_name` (
  `sem_name_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sem_name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_name`
--

LOCK TABLES `semester_name` WRITE;
/*!40000 ALTER TABLE `semester_name` DISABLE KEYS */;
INSERT INTO `semester_name` VALUES (1,'Spring'),(2,'Summer'),(3,'Fall'),(4,'Winter');
/*!40000 ALTER TABLE `semester_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `session_key` text,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'shiva','admin123','vgbhjnk',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'msu_course_scheduling'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-16 20:36:46
