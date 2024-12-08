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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_room`
--

LOCK TABLES `class_room` WRITE;
/*!40000 ALTER TABLE `class_room` DISABLE KEYS */;
INSERT INTO `class_room` VALUES (1,'main hall 101'),(2,'main hall 202'),(3,'cs block 202'),(4,'cs block 203'),(5,'cs block 204'),(6,'cs block 206'),(7,'richardson hall 101'),(8,'richardson hall 102'),(9,'richardson hall 103'),(10,'richardson hall 104'),(11,'richardson hall 105'),(12,'richardson hall 106'),(13,'richardson hall 107'),(14,'richardson hall 108'),(15,'science block 101'),(16,'science block 102'),(17,'science block 103'),(18,'science block 104'),(19,'library 201'),(20,'library 202'),(21,'library 203'),(22,'engineering block 101'),(23,'engineering block 102'),(24,'engineering block 103'),(25,'engineering block 104'),(26,'cs lab 101'),(27,'cs lab 102'),(28,'cs lab 103'),(29,'cs lab 104'),(30,'cs lab 105'),(31,'arts block 101'),(32,'arts block 102'),(33,'arts block 103'),(34,'arts block 104'),(35,'arts block 105'),(36,'arts block 106'),(37,'arts block 107'),(38,'arts block 108'),(39,'music hall 101'),(40,'music hall 102'),(41,'music hall 103'),(42,'music hall 104'),(43,'sports complex 101'),(44,'sports complex 102'),(45,'sports complex 103'),(46,'sports complex 104'),(47,'sports complex 105'),(48,'auditorium 101'),(49,'auditorium 102'),(50,'auditorium 103'),(51,'auditorium 104');
/*!40000 ALTER TABLE `class_room` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` VALUES (1,100,40305,'introduction to computer science',3,1),(2,104,40332,'Python Programming I',3,1),(3,111,40338,'Fundamentals of Java Programming',3,0),(4,170,44915,'Discrete Mathematics',3,0),(5,114,42101,'Python Programming II',3,0),(6,212,40342,'Data Structures and Algorithms',3,0),(7,213,42627,'Data Structures and Algorithms in Python',3,0),(8,230,40344,'Computer Systems.',3,0),(9,231,42076,'Systems Programming',3,0),(10,274,42628,'Multimedia Computing',3,0),(11,275,43642,'Introduction to R Programming',3,0),(12,300,40350,'Advanced Python Programming',3,1),(13,305,40351,'Introduction to Machine Learning',3,1),(14,306,40352,'Intermediate Machine Learning',3,1),(15,307,40353,'Artificial Intelligence',3,1),(16,308,40354,'Introduction to Data Science',3,1),(17,309,40355,'Advanced Data Science',3,1),(18,310,40356,'Database Management Systems',3,0),(19,311,40357,'Advanced Database Concepts',3,0),(20,312,40358,'Cloud Computing Fundamentals',3,0),(21,313,40359,'Introduction to Web Development',3,0),(22,314,40360,'Advanced Web Development',3,1),(23,315,40361,'Mobile App Development',3,0),(24,316,40362,'Cybersecurity Fundamentals',3,0),(25,317,40363,'Network Security',3,0),(26,318,40364,'Computer Networks',3,0),(27,319,40365,'Introduction to Operating Systems',3,0),(28,320,40366,'Operating System Internals',3,0),(29,321,40367,'Compiler Design',3,0),(30,322,40368,'Theory of Computation',3,0),(31,323,40369,'Programming Languages Concepts',3,0),(32,324,40370,'Human-Computer Interaction',3,0),(33,325,40371,'Parallel Computing',3,1),(34,326,40372,'Distributed Systems',3,0),(35,327,40373,'Introduction to Game Development',3,1),(36,328,40374,'Advanced Game Development',3,1),(37,329,40375,'Software Engineering Fundamentals',3,0),(38,330,40376,'Agile Software Development',3,0),(39,331,40377,'Introduction to Algorithms',3,0),(40,332,40378,'Advanced Algorithms',3,0),(41,333,40379,'Introduction to Data Analytics',3,1),(42,334,40380,'Big Data Technologies',3,1),(43,335,40381,'Quantum Computing Basics',3,1),(44,336,40382,'Blockchain Fundamentals',3,1),(45,337,40383,'Computer Graphics',3,0),(46,338,40384,'Computer Vision',3,1),(47,339,40385,'Natural Language Processing',3,1),(48,340,40386,'Deep Learning',3,1),(49,341,40387,'Neural Networks and AI',3,1),(50,342,40388,'Introduction to Robotics',3,1),(51,343,40389,'Advanced Robotics',3,1),(52,344,40390,'Ethics in AI',3,0),(53,345,40391,'Computer Science Capstone Project',3,0),(54,346,40392,'Research Methods in Computer Science',3,0),(55,347,40393,'Data Mining',3,1),(56,348,40394,'Introduction to Cryptography',3,0),(57,349,40395,'Applied Cryptography',3,1),(58,350,40396,'Security and Privacy in Data',3,1),(59,351,40397,'Introduction to Bioinformatics',3,1),(60,352,40398,'Advanced Bioinformatics',3,1),(61,353,40399,'Advanced Topics in Computer Science',3,0),(62,101,40400,'Introduction to Artificial Intelligence',3,1),(63,102,40401,'Advanced Topics in Data Science',3,1),(64,103,40402,'Machine Learning for Beginners',3,1),(65,104,40403,'Deep Learning Concepts',3,1),(66,105,40404,'Natural Language Processing Basics',3,1),(67,106,40405,'Quantum Computing Introduction',3,1),(68,107,40406,'Blockchain and Cryptography',3,1),(69,108,40407,'Augmented Reality Development',3,0),(70,109,40408,'Internet of Things (IoT) Fundamentals',3,1),(71,110,40409,'Ethics in AI and Robotics',3,0),(72,100,0,'Test Course',3,1),(73,1000,0,'Srinith Course',3,1),(74,1000,0,'Srinith Course 2',3,1),(75,1000,0,'Srinith Course 3',3,1),(76,124,0,'Srinith data base',3,1),(77,1000,0,'Srinith Course 3',3,1),(78,344,0,'srinith course 4',4,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_professor_mapping`
--

LOCK TABLES `course_professor_mapping` WRITE;
/*!40000 ALTER TABLE `course_professor_mapping` DISABLE KEYS */;
INSERT INTO `course_professor_mapping` VALUES (1,1,1),(7,5,5),(8,6,6),(9,7,7),(10,8,8),(11,9,9),(12,10,10),(13,11,11),(14,1,2),(18,1,5),(19,1,6),(21,3,6),(22,4,7),(23,5,8),(24,6,9),(25,7,10),(26,8,11),(27,19,9),(28,20,10),(29,21,11),(30,22,37),(31,23,38),(32,24,39),(33,25,40),(34,26,41),(35,27,42),(36,28,43),(37,29,44),(38,30,45),(39,31,46),(40,32,47),(41,33,48),(42,34,49),(43,35,50),(44,36,51),(45,37,52),(46,38,53),(47,39,54),(48,40,55),(49,41,56),(50,42,57),(51,43,58),(52,44,59),(53,45,60),(54,46,61),(55,47,62),(56,48,63),(57,49,64),(58,50,65),(59,51,66),(60,52,67),(61,53,68),(62,54,69),(63,55,70),(64,56,71),(65,57,72),(66,58,73),(67,59,74),(68,60,75),(69,61,76),(70,62,77),(73,4,4),(74,3,4),(76,72,1),(77,72,2),(78,73,1),(79,73,2),(80,74,1),(81,74,2),(82,75,1),(83,75,2),(84,76,1),(85,76,5),(86,76,6),(87,77,1),(88,77,2),(89,78,1),(90,78,5),(91,78,10),(92,78,39),(93,78,45),(94,2,2),(95,2,4),(96,2,5),(97,2,11),(98,2,48);
/*!40000 ALTER TABLE `course_professor_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_semester_mapping`
--

DROP TABLE IF EXISTS `course_semester_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_semester_mapping` (
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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_semester_mapping`
--

LOCK TABLES `course_semester_mapping` WRITE;
/*!40000 ALTER TABLE `course_semester_mapping` DISABLE KEYS */;
INSERT INTO `course_semester_mapping` VALUES (1,1,3,4,NULL),(2,2,4,4,NULL),(3,3,3,4,NULL),(4,4,4,4,NULL),(5,5,3,4,NULL),(6,6,4,4,NULL),(7,7,3,4,NULL),(8,8,4,4,NULL),(9,9,3,4,NULL),(10,10,4,4,NULL),(11,11,3,4,NULL),(12,1,1,4,NULL),(13,3,1,4,NULL),(14,5,1,4,NULL),(15,7,1,4,NULL),(16,9,1,4,NULL),(17,11,1,4,NULL),(18,1,5,4,NULL),(19,3,5,4,NULL),(20,5,5,4,NULL),(21,7,5,4,NULL),(22,9,5,4,NULL),(23,11,5,4,NULL),(24,12,3,4,NULL),(25,13,4,4,NULL),(26,14,3,4,NULL),(27,15,4,4,NULL),(28,16,3,4,NULL),(29,17,4,4,NULL),(30,18,3,4,NULL),(31,19,4,4,NULL),(32,20,3,4,NULL),(33,21,4,4,NULL),(34,22,3,4,NULL),(35,23,4,4,NULL),(36,24,3,4,NULL),(37,25,4,4,NULL),(38,26,3,4,NULL),(39,27,4,4,NULL),(40,28,3,4,NULL),(41,29,4,4,NULL),(42,30,3,4,NULL),(43,31,4,4,NULL),(44,32,3,4,NULL),(45,33,4,4,NULL),(46,34,3,4,NULL),(47,35,4,4,NULL),(48,36,3,4,NULL),(49,37,4,4,NULL),(50,38,3,4,NULL),(51,39,4,4,NULL),(52,40,3,4,NULL),(53,41,4,4,NULL),(54,42,3,4,NULL),(55,43,4,4,NULL),(56,44,3,4,NULL),(57,45,4,4,NULL),(58,46,3,4,NULL),(59,47,4,4,NULL),(60,48,3,4,NULL),(61,49,4,4,NULL),(62,50,3,4,NULL),(63,51,4,4,NULL),(64,52,3,4,NULL),(65,53,4,4,NULL),(66,54,3,4,NULL),(67,55,4,4,NULL),(68,56,3,4,NULL),(69,57,4,4,NULL),(70,58,3,4,NULL),(71,59,4,4,NULL),(72,60,3,4,NULL),(73,61,4,4,NULL);
/*!40000 ALTER TABLE `course_semester_mapping` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_availability`
--

LOCK TABLES `professor_availability` WRITE;
/*!40000 ALTER TABLE `professor_availability` DISABLE KEYS */;
INSERT INTO `professor_availability` VALUES (56,95,NULL,3,'15:01:00.000000','16:00:00.000000'),(63,96,NULL,4,'15:33:00.000000','16:00:00.000000'),(64,1,NULL,4,'14:03:00.000000','15:00:00.000000'),(65,1,NULL,3,'17:55:00.000000','20:00:00.000000'),(66,2,NULL,4,'19:07:00.000000','20:08:00.000000'),(67,2,NULL,5,'19:07:00.000000','20:08:00.000000');
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
  `prof_status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`professor_id`),
  KEY `professor_details_professor_type_FK` (`professor_type`),
  CONSTRAINT `professor_details_professor_type_FK` FOREIGN KEY (`professor_type`) REFERENCES `professor_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_details`
--

LOCK TABLES `professor_details` WRITE;
/*!40000 ALTER TABLE `professor_details` DISABLE KEYS */;
INSERT INTO `professor_details` VALUES (1,'John Doe','2379',1,0),(2,'Jane Smith','2378',2,0),(4,'Zhang, Wei ','2378',2,1),(5,'Kousoulis, Minas','2378',1,1),(6,'Antoniou, Maria','2378',1,1),(7,'Zhang, Wei','2378',2,1),(8,'Boddu, Harika','2378',1,1),(9,'Aslandogan, Yuksel','2378',2,1),(10,'Buhler, Gary ','2378',1,1),(11,'Mbow, Abdou','2378',2,1),(37,'Peterson, David','2378',1,0),(38,'Norton, Emma','2378',2,1),(39,'Johnson, Mark','2378',2,1),(40,'Mitchell, Sarah','2378',1,1),(41,'Williams, Lucy','2378',1,1),(42,'Brown, Charles','2378',2,1),(43,'Green, Emily','2378',1,1),(44,'Taylor, Henry','2378',1,1),(45,'White, Olivia','2378',2,1),(46,'Harris, George','2378',1,1),(47,'Clark, Sophia','2378',1,1),(48,'Lewis, Brian','2378',2,1),(49,'Robinson, Chloe','2378',1,1),(50,'Walker, Robert','2378',2,1),(51,'Hall, Ella','2378',1,1),(52,'Young, Jack','2378',1,1),(53,'Allen, Mia','2378',1,1),(54,'King, William','2378',2,1),(55,'Wright, Ava','2378',1,1),(56,'Scott, Jacob','2378',2,1),(57,'Hill, Emily','2378',1,1),(58,'Adams, James','2378',1,1),(59,'Baker, Olivia','2378',2,1),(60,'Nelson, Charlotte','2378',2,1),(61,'Carter, Ethan','2378',1,1),(62,'Moore, Amelia','2378',1,1),(63,'Morris, Lucas','2378',2,1),(64,'Evans, Lily','2378',1,1),(65,'Turner, Benjamin','2378',2,1),(66,'Parker, Grace','2378',1,1),(67,'Collins, Alexander','2378',2,1),(68,'Edwards, Isla','2378',1,1),(69,'Stewart, Samuel','2378',2,1),(70,'Morris, Harper','2378',1,1),(71,'Rogers, Logan','2378',2,1),(72,'Reed, Ella','2378',1,1),(73,'Cook, Harrison','2378',2,1),(74,'Bell, Abigail','2378',1,1),(75,'Howard, Dylan','2378',1,1),(76,'Ward, Ella','2378',2,1),(77,'Cox, Mason','2378',1,1),(78,'Gray, Chloe','2378',2,1),(79,'James, Leo','2378',1,1),(80,'Wood, Isla','2378',1,1),(81,'Hughes, Sophie','2378',2,0),(82,'Bennett, Nathan','2378',1,0),(83,'Phillips, Zoe','2378',1,0),(84,'Foster, Daniel','2378',2,0),(85,'Bailey, Joshua','2378',1,0),(86,'Cooper, Isabella','2378',1,0),(95,'Sriniths','2379',2,1),(96,'Sriniths2','2379',3,1);
/*!40000 ALTER TABLE `professor_details` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_type`
--

LOCK TABLES `professor_type` WRITE;
/*!40000 ALTER TABLE `professor_type` DISABLE KEYS */;
INSERT INTO `professor_type` VALUES (1,'Full-Time'),(2,'Part-Time'),(3,'Adjunct'),(4,'Visiting'),(5,'Emeritus'),(6,'Assistant Professor'),(7,'Associate Professor'),(8,'Lecturer'),(9,'Visiting Professor'),(10,'Research Professor'),(11,'Teaching Professor'),(12,'Clinical Professor'),(13,'Adjunct Instructor'),(14,'Emeritus Professor'),(15,'Professor of Practice');
/*!40000 ALTER TABLE `professor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `section_id` bigint NOT NULL AUTO_INCREMENT,
  `section_no` varchar(100) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `max_capacity` int DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `cross_section_id` bigint DEFAULT NULL,
  `course_semester_mapping_id` bigint DEFAULT NULL,
  PRIMARY KEY (`section_id`),
  KEY `section_professor_details_FK` (`professor_id`),
  KEY `section_class_room_FK` (`room_id`),
  KEY `section_course_semester_mappping_FK` (`course_semester_mapping_id`),
  CONSTRAINT `section_class_room_FK` FOREIGN KEY (`room_id`) REFERENCES `class_room` (`room_id`),
  CONSTRAINT `section_course_semester_mappping_FK` FOREIGN KEY (`course_semester_mapping_id`) REFERENCES `course_semester_mapping` (`course_semester_mapping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,'01',25,25,1,1,NULL,1),(2,'02',25,25,2,1,NULL,1),(3,'01',50,50,4,3,NULL,2),(4,'02',50,50,5,4,NULL,2),(5,'02',50,50,6,5,NULL,2),(6,'01',30,30,7,6,NULL,3),(7,'02',30,30,8,7,NULL,3),(8,'01',25,25,9,8,NULL,4),(9,'02',25,25,1,9,NULL,4),(10,'01',25,25,2,1,NULL,5),(11,'01',25,25,6,2,NULL,6),(12,'01',50,50,4,3,NULL,7),(13,'02',50,50,5,4,NULL,7),(14,'01',25,25,6,7,NULL,8),(15,'01',25,25,7,3,NULL,9),(16,'01',25,25,8,2,NULL,10),(17,'01',25,25,9,3,NULL,11),(18,'02',25,25,5,1,NULL,11),(19,'03',77,99,5,8,1,1),(20,'01',25,25,1,1,NULL,12),(21,'02',25,25,2,1,NULL,12),(22,'03',77,77,5,8,1,12),(23,'01',30,30,7,6,NULL,13),(24,'02',30,30,8,7,NULL,13),(25,'01',25,25,2,1,NULL,14),(26,'01',50,50,4,3,NULL,15),(27,'02',50,50,5,4,NULL,15),(28,'01',25,25,7,3,NULL,16),(29,'01',25,25,9,3,NULL,17),(30,'02',25,25,5,1,NULL,17),(31,'03',32,54,2,6,3,11),(32,'02',43,5,5,3,3,9),(33,'01',25,25,1,1,NULL,18),(34,'02',25,25,2,1,NULL,18),(35,'03',77,77,5,8,1,18),(36,'01',30,30,7,6,NULL,19),(37,'02',30,30,8,7,NULL,19),(38,'01',25,25,2,1,NULL,20),(39,'01',50,50,4,3,NULL,21),(40,'02',50,50,5,4,NULL,21),(41,'01',25,25,7,3,NULL,22),(42,'02',43,43,5,3,3,22),(43,'01',25,25,9,3,NULL,23),(44,'02',25,25,5,1,NULL,23),(45,'03',32,32,2,6,3,23),(46,'04',54,666,1,1,2,1);
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
  PRIMARY KEY (`section_scheduled_id`),
  KEY `section_schedule_section_FK` (`section_id`),
  CONSTRAINT `section_schedule_section_FK` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_schedule`
--

LOCK TABLES `section_schedule` WRITE;
/*!40000 ALTER TABLE `section_schedule` DISABLE KEYS */;
INSERT INTO `section_schedule` VALUES (1,1,1,'13:00:00.000000','15:00:00.000000'),(2,2,1,'13:00:00.000000','15:00:00.000000'),(3,2,3,'13:00:00.000000','15:00:00.000000'),(4,3,1,'13:00:00.000000','14:00:00.000000'),(5,3,2,'13:00:00.000000','14:00:00.000000'),(6,4,3,'13:00:00.000000','15:00:00.000000'),(7,5,4,'11:00:00.000000','13:00:00.000000'),(8,6,1,'13:00:00.000000','15:00:00.000000'),(9,7,4,'12:00:00.000000','12:00:00.000000'),(10,8,5,'11:00:00.000000','15:00:00.000000'),(11,9,3,'14:00:00.000000','15:00:00.000000'),(12,10,1,'13:00:00.000000','15:00:00.000000'),(13,11,5,'09:00:00.000000','15:00:00.000000'),(14,12,4,'09:00:00.000000','15:00:00.000000'),(15,13,3,'10:00:00.000000','15:00:00.000000'),(16,14,2,'06:00:00.000000','15:00:00.000000'),(17,15,3,'14:00:00.000000','15:00:00.000000'),(18,16,1,'16:00:00.000000','15:00:00.000000'),(19,17,2,'08:00:00.000000','17:00:00.000000'),(20,17,4,'15:00:00.000000','15:00:00.000000'),(21,18,1,'08:00:00.000000','12:00:00.000000'),(24,19,1,'16:30:00.000000','16:30:00.000000'),(25,20,1,'15:00:00.000000','15:00:00.000000'),(26,21,1,'15:00:00.000000','15:00:00.000000'),(27,21,3,'15:00:00.000000','15:00:00.000000'),(28,22,1,'16:30:00.000000','16:30:00.000000'),(29,23,1,'15:00:00.000000','15:00:00.000000'),(30,24,4,'12:00:00.000000','12:00:00.000000'),(31,25,1,'15:00:00.000000','15:00:00.000000'),(32,26,4,'15:00:00.000000','15:00:00.000000'),(33,27,3,'15:00:00.000000','15:00:00.000000'),(34,28,3,'15:00:00.000000','15:00:00.000000'),(35,29,2,'17:00:00.000000','17:00:00.000000'),(36,29,4,'15:00:00.000000','15:00:00.000000'),(37,30,1,'12:00:00.000000','12:00:00.000000'),(38,31,4,'06:00:00.000000','08:00:00.000000'),(39,32,1,'10:00:00.000000','14:00:00.000000'),(40,33,1,'15:00:00.000000','15:00:00.000000'),(41,34,1,'15:00:00.000000','15:00:00.000000'),(42,34,3,'15:00:00.000000','15:00:00.000000'),(43,35,1,'16:30:00.000000','16:30:00.000000'),(44,36,1,'15:00:00.000000','15:00:00.000000'),(45,37,4,'12:00:00.000000','12:00:00.000000'),(46,38,1,'15:00:00.000000','15:00:00.000000'),(47,39,4,'15:00:00.000000','15:00:00.000000'),(48,40,3,'15:00:00.000000','15:00:00.000000'),(49,41,3,'15:00:00.000000','15:00:00.000000'),(50,42,1,'14:00:00.000000','14:00:00.000000'),(51,43,2,'17:00:00.000000','17:00:00.000000'),(52,43,4,'15:00:00.000000','15:00:00.000000'),(53,44,1,'12:00:00.000000','12:00:00.000000'),(54,45,4,'08:00:00.000000','08:00:00.000000'),(55,46,1,'08:00:00.000000','00:00:00.000000'),(56,46,4,'08:00:00.000000','00:00:00.000000');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semester`
--

LOCK TABLES `Semester` WRITE;
/*!40000 ALTER TABLE `Semester` DISABLE KEYS */;
INSERT INTO `Semester` VALUES (1,1,'2024'),(2,3,'2024'),(3,1,'2023'),(4,3,'2023'),(5,1,'2022'),(6,2,'2022'),(7,3,'2022'),(8,4,'2022'),(9,1,'2021'),(10,2,'2021'),(11,3,'2021'),(13,1,'2025'),(17,2,'2025'),(23,4,'2024'),(24,3,'2025');
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
/*!50003 DROP PROCEDURE IF EXISTS `fetchconflicts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchconflicts`(IN start_time VARCHAR(5),
    IN end_time VARCHAR(5),
    IN professor_id BIGINT,
    IN semester_id BIGINT,
    IN course_id BIGINT)
BEGIN
	SELECT * FROM `section` s  LEFT JOIN section_schedule ss ON ss.section_id = s.section_id 
    LEFT JOIN course_semester_mappping csm  ON  s.course_semester_mapping_id  = csm.course_semester_mapping_id
    where csm.course_id = course_id  and csm.sem_id = semester_id and s.professor_id = professor_id and (ss.start_time between start_time AND end_time OR   ss.end_time BETWEEN start_time AND end_time);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `testpro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `testpro`(IN room_id INT)
BEGIN
	select * from class_room r where r.room_id = room_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07 15:56:30
