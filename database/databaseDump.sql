-- MySQL dump 10.13  Distrib 8.3.0, for macos14 (arm64)
--
-- Host: localhost    Database: MSUCourseScheduling
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_room`
--

LOCK TABLES `class_room` WRITE;
/*!40000 ALTER TABLE `class_room` DISABLE KEYS */;
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
  `term_tenure` int DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `cmputer_required` tinyint DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_professor_mapping`
--

LOCK TABLES `course_professor_mapping` WRITE;
/*!40000 ALTER TABLE `course_professor_mapping` DISABLE KEYS */;
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
-- Table structure for table `professor_details`
--

DROP TABLE IF EXISTS `professor_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_details` (
  `professor_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `course_load` text,
  `schedule` text,
  `professor_type` int DEFAULT NULL,
  PRIMARY KEY (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_details`
--

LOCK TABLES `professor_details` WRITE;
/*!40000 ALTER TABLE `professor_details` DISABLE KEYS */;
INSERT INTO `professor_details` VALUES (1,'Shiva','4,0,4,0','{}',1);
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
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `section_id` bigint NOT NULL,
  `course_id` int DEFAULT NULL,
  `sem_id` int DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `max_capacity` int DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semester`
--

LOCK TABLES `Semester` WRITE;
/*!40000 ALTER TABLE `Semester` DISABLE KEYS */;
INSERT INTO `Semester` VALUES (1,1,'2024'),(2,3,'2024'),(3,1,'2023'),(4,3,'2023');
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
-- Dumping routines for database 'MSUCourseScheduling'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-26 20:54:27
