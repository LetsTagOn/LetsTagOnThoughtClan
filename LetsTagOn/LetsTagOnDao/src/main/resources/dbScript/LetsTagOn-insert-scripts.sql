-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lto
-- ------------------------------------------------------
-- Server version	5.5.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `Cause`
--

LOCK TABLES `Cause` WRITE;
/*!40000 ALTER TABLE `Cause` DISABLE KEYS */;
INSERT INTO `Cause`(`id`, `name`, `description`,`active`) VALUES (1,'ALL','All',1),(2,'ANIMAL_WELFARE','Animal Welfare',1),(3,'DISASTER_RELIEF','Disaster Relief',1),(4,'ENV_CONSERVATION','Environment and Conservation',1),(5,'HUMAN_RIGHT','Human Rights',1),(6,'ARTS_CULTURE','Arts and Culture',1);
/*!40000 ALTER TABLE `Cause` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `JobType` WRITE;
/*!40000 ALTER TABLE `JobType` DISABLE KEYS */;
INSERT INTO `JobType` VALUES (1,'ALL','All','1'),(2,'ANIMAL_WELFARE','Animal Welfare','1'),(3,'ARTS','Arts','1'),(4,'CULTURAL','Cultural','1'),(5,'SPORT','Sports','1'),(6,'SERVICE','Service','1'),(7,'OTHER','Other','1');
/*!40000 ALTER TABLE `JobType` ENABLE KEYS */;
UNLOCK TABLES;


/* - insert for attribute master table*/
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('1', 'TRANSPORT', 'Transport');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('2', 'DRIVING_LICENSE', 'Driving License Number');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('3', 'SHIRT_SIZE', 'T-Shirt Size');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('4', 'TITLE', 'Every Day Title');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('5', 'CAR', 'Type of Car');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('6', 'VOLUNTEERED_BEFORE', 'Have you volunteered before?');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('7', 'INFORMATION_SESSION', 'Have you attended an information?');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('8', 'DIETARY_REQUIREMENTS', 'Dietary Requirements');
INSERT INTO `lto`.`AdditionalProfileAttribute` (`id`, `name`, `description`) VALUES ('9', 'MEDICAL_HISTROY', 'Medical Histroy');



--
-- Table structure for table `UserType`
--
-- Dumping data for table `UserType`
--

LOCK TABLES `UserType` WRITE;
/*!40000 ALTER TABLE `UserType` DISABLE KEYS */;
INSERT INTO `UserType` VALUES (1,'Event Volunteering','Available for an event'),(2,'Micro Volunteering','Available for less than 3 hrs'),(3,'Short Term','Available for few days'),(4,'Long Term','Available for ongoing work'),(5,'Emergency Response','Help in case of a disaster'),(6,'Special Events','Help out for special Events'),(7,'General Volunteering','Help if needed'),(8,'Sponsor an Event','Interested to sponsor');
/*!40000 ALTER TABLE `UserType` ENABLE KEYS */;
UNLOCK TABLES;

-- Dumping data for table `UserTypeAttributeXref`
--

LOCK TABLES `UserTypeAttributeXref` WRITE;
/*!40000 ALTER TABLE `UserTypeAttributeXref` DISABLE KEYS */;
INSERT INTO `UserTypeAttributeXref` VALUES (1,1,1,1),(2,1,2,1),(3,2,2,1),(4,2,3,1),(5,3,3,1),(6,3,4,1),(7,4,4,1),(8,4,5,1),(9,5,5,1),(10,5,6,1),(11,6,6,1),(12,6,7,1),(13,7,7,1),(14,7,8,1),(15,8,8,1),(16,8,9,1),(17,1,1,1),(18,1,2,1),(19,2,2,1),(20,2,3,1),(21,3,3,1),(22,3,4,1),(23,4,4,1),(24,4,5,1),(25,5,5,1),(26,5,6,1),(27,6,6,1),(28,6,7,1),(29,7,7,1),(30,7,8,1),(31,8,8,1),(32,8,9,1);
/*!40000 ALTER TABLE `UserTypeAttributeXref` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-11 12:50:13

UNLOCK TABLES;