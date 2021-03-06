CREATE DATABASE  IF NOT EXISTS `lastever` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lastever`;
-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: lastever
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `activation`
--

DROP TABLE IF EXISTS `activation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activation` (
  `activationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `userID` bigint(20) NOT NULL,
  `activationCode` varchar(100) DEFAULT NULL,
  `activationFrom` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activationTo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`activationID`),
  KEY `ActivationToUser_idx` (`userID`),
  CONSTRAINT `ActivationToUser` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activation`
--

LOCK TABLES `activation` WRITE;
/*!40000 ALTER TABLE `activation` DISABLE KEYS */;
INSERT INTO `activation` VALUES (27,60,'OLdEdkXnh+v8hzepcVZoqqMhGfsh7egUynUpYa+icyEfG3BfMdaiTL7Hkfmy','2018-10-04 21:49:21','2018-10-05 21:49:21'),(28,62,'LdDRnLoYNDFjTwuN8GTcPEqotrCa214c9k4Uzofz4AOnKsDUnQNl1MAdXdYA','2019-01-07 14:40:42','2019-01-08 14:40:42'),(29,63,'38wViMFOwb9ChnpslW3Rx6hWXX6P8ReC1GXqgEwqEfShCCmzYRq2zfu1j7+O','2019-01-07 14:45:21','2019-01-08 14:45:21'),(30,64,'JKQ/kwhmn8/y/g2j05HdcV1WVBxtbZa2ZM6xtPmoA8YJNfJZJ+Bkz1/ZnA3c','2019-01-07 14:56:45','2019-01-08 14:56:45');
/*!40000 ALTER TABLE `activation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `divisionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `divisionName` varchar(100) NOT NULL,
  `divisionLogo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`divisionID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (1,'Womens',NULL),(2,'Mens',NULL),(3,'Co-Ed East',NULL),(4,'Co-Ed West',NULL),(8,'div',NULL);
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gamestatistics`
--

DROP TABLE IF EXISTS `gamestatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gamestatistics` (
  `statisticID` bigint(20) NOT NULL AUTO_INCREMENT,
  `gameID` bigint(20) NOT NULL,
  `playerID` bigint(20) NOT NULL,
  `goals` tinyint(4) NOT NULL,
  `yellowCards` tinyint(4) NOT NULL,
  `redCards` tinyint(4) NOT NULL,
  PRIMARY KEY (`statisticID`),
  KEY `GameStaticticsToSchedule` (`gameID`),
  KEY `GameStatisticsToPlayer` (`playerID`),
  CONSTRAINT `GameStaticticsToSchedule` FOREIGN KEY (`gameID`) REFERENCES `schedule` (`gameID`) ON DELETE CASCADE,
  CONSTRAINT `GameStatisticsToPlayer` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamestatistics`
--

LOCK TABLES `gamestatistics` WRITE;
/*!40000 ALTER TABLE `gamestatistics` DISABLE KEYS */;
INSERT INTO `gamestatistics` VALUES (1,1,2,0,0,0),(2,1,3,1,1,0),(3,1,10,1,0,0),(4,1,4,0,0,0),(5,1,7,0,2,1),(6,1,11,0,0,0),(7,2,5,0,0,0),(8,2,6,0,1,0),(9,2,12,1,0,0),(10,2,1,0,0,0),(11,2,8,1,0,0),(12,2,9,0,0,0),(13,3,4,1,0,1),(14,3,7,1,0,0),(15,3,11,1,1,0),(16,3,1,1,0,0),(17,3,8,0,1,0),(18,3,9,1,0,0),(19,4,2,0,0,0),(20,4,3,3,0,0),(21,4,10,0,0,0),(22,4,5,0,0,0),(23,4,6,0,1,0),(24,4,12,1,0,0),(25,5,13,0,0,0),(26,5,17,0,1,0),(27,5,23,0,1,0),(28,5,15,2,1,0),(29,5,18,1,0,0),(30,5,21,0,0,0),(31,6,14,0,0,0),(32,6,20,1,0,0),(33,6,24,0,0,0),(34,6,16,0,0,0),(35,6,19,1,1,0),(36,6,22,1,0,1),(37,7,16,0,0,0),(38,7,19,0,0,0),(39,7,22,2,0,0),(40,7,15,1,1,0),(41,7,18,0,1,0),(42,7,21,1,0,0),(43,8,14,0,0,0),(44,8,20,0,0,0),(45,8,24,0,0,0),(46,8,13,0,0,0),(47,8,17,0,0,0),(48,8,23,0,1,0),(49,9,1,4,1,0),(50,9,8,1,0,0),(51,9,9,0,0,0),(52,9,2,1,2,1),(53,9,3,0,0,1),(54,9,10,2,0,0),(55,10,2,0,1,0),(56,10,3,1,0,0),(57,10,10,0,1,0),(58,10,4,0,0,0),(59,10,7,2,0,0),(60,10,11,2,0,0),(61,11,5,1,0,1),(62,11,6,1,0,0),(63,11,12,1,1,0),(64,11,8,1,0,0),(65,11,9,0,0,0),(66,11,1,1,1,0),(67,12,1,0,0,0),(68,12,8,0,0,0),(69,12,9,0,0,0),(70,12,4,1,2,1),(71,12,7,1,1,0),(72,12,11,0,0,1);
/*!40000 ALTER TABLE `gamestatistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league`
--

DROP TABLE IF EXISTS `league`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league` (
  `leagueID` bigint(20) NOT NULL AUTO_INCREMENT,
  `leagueName` varchar(100) NOT NULL,
  `leaguePlayoffs` tinyint(1) NOT NULL DEFAULT '1',
  `leaguePlayoffTeams` smallint(5) unsigned DEFAULT NULL,
  `leagueStatus` varchar(100) NOT NULL DEFAULT 'Newly Created',
  PRIMARY KEY (`leagueID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league`
--

LOCK TABLES `league` WRITE;
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
INSERT INTO `league` VALUES (1,'Womens League',1,2,'In Progress'),(2,'Mens League',1,2,'Playoffs'),(3,'Co-Ed League',1,4,'Newly Created');
/*!40000 ALTER TABLE `league` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaguexdivision`
--

DROP TABLE IF EXISTS `leaguexdivision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaguexdivision` (
  `leagueID` bigint(20) NOT NULL,
  `divisionID` bigint(20) NOT NULL,
  PRIMARY KEY (`leagueID`,`divisionID`),
  KEY `divisionID_idx` (`divisionID`),
  CONSTRAINT `divisionID` FOREIGN KEY (`divisionID`) REFERENCES `division` (`divisionID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `leagueID` FOREIGN KEY (`leagueID`) REFERENCES `league` (`leagueID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaguexdivision`
--

LOCK TABLES `leaguexdivision` WRITE;
/*!40000 ALTER TABLE `leaguexdivision` DISABLE KEYS */;
INSERT INTO `leaguexdivision` VALUES (1,1),(2,2),(3,3),(3,4),(2,8);
/*!40000 ALTER TABLE `leaguexdivision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `newsID` bigint(20) NOT NULL AUTO_INCREMENT,
  `userID` bigint(20) NOT NULL,
  `newsTitle` varchar(250) NOT NULL,
  `newsTitle_fr` varchar(250) NOT NULL,
  `newsTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `newsContent` longtext NOT NULL,
  `newsContent_fr` longtext NOT NULL,
  PRIMARY KEY (`newsID`),
  KEY `NewsToUser` (`userID`),
  CONSTRAINT `NewsToUser` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,1,'Test Post','Test Post','2018-02-26 20:06:54','This is a test of the news system. Hopefully it works and everything goes as planned.\r Do the new lines work in MySQL? We\'re about to find out if it does. I have a feeling that it doesn\'t and we may need to store\r some sort of html code in here. It\'ll have to be super specific tags so that it doesn\'t become an issue. <br> <br>\r I put in some <b>html tags</b> and added a new line. How fancy! I wonder if other stuff will work as well, I will add an image\r to this post for testing purposes. It\'ll be something really stupid though so w/e.<br><br> <img src=\"https://images.pexels.com/photos/114296/pexels-photo-114296.jpeg\"\r style=\"width: 100%; max-width: 560px; height: auto\">','Ceci est un test du système de nouvelles. J\'espère que cela fonctionne et que tout se passe comme prévu.\r  Les nouvelles lignes fonctionnent-elles dans MySQL? Nous sommes sur le point de savoir si c\'est le cas. J\'ai le sentiment que ce n\'est pas le cas et que nous devrons peut-être stocker\r  une sorte de code html ici. Il va falloir que ce soit des tags super spécifiques pour que ça ne devienne pas un problème. <br> <br>\r  J\'ai mis quelques balises <b>html tags</b> et ajouté une nouvelle ligne. Quelle fantaisie! Je me demande si d\'autres choses vont marcher aussi, je vais ajouter une image\r  à ce poste à des fins de test. Ça va être quelque chose de vraiment stupide si w/ e. <br> <br> <img src=\"https://images.pexels.com/photos/114296/pexels-photo-114296.jpeg\"  style=\"width: 100%; max-width: 560px; height: auto\">'),(2,1,'Another test post','Un autre test','2018-03-01 19:40:42','<center><img src=\"https://images.pexels.com/photos/17598/pexels-photo.jpg\" style=\"width: 100%; max-width: 560px; height: auto\"></center> <br><br>\r This is another test post how about that! This time the image is at the start of the post and not at the end! Fancy! So I guess I\'ll put\r some somewhat beliveable content in that the games this week will continue as normal after nothing happened last week. At all. Now\r Stop asking about it please. We are getting pretty close to the end and the playoff spots can still be determined. It\'s anyones game.\r Now if you\'ll excuse me I have a website to create. <br><br> Ok, I\'m not being fully serious here but I think it\'s kinda cool.\r Having to escape the text all the time is getting annoying but what can you do? Well do not do everything from MySQL for starters.\r Hopefully nothing breaks and the spacing between the cards is good and I won\'t have to do some fixing. I doubt it but we shall see.\r Have fun this week everyone! ','<center><img src=\"https://images.pexels.com/photos/17598/pexels-photo.jpg\" style=\"width: 100%; max-width: 560px; height: auto\"></center> <br> <br>\n Ceci est un autre test post que diriez-vous de cela! Cette fois, l\'image est au début de la publication et non à la fin! Fantaisie! Donc je suppose que je vais mettre\n un contenu quelque peu irrésistible en ce que les jeux de cette semaine continueront comme d\'habitude après rien ne s\'est passé la semaine dernière. Du tout. À présent\n Arrêtez de demander à ce sujet s\'il vous plaît. Nous approchons de la fin et les places éliminatoires peuvent encore être déterminées. C\'est un jeu d\'anyones.\n Maintenant, si vous voulez bien m\'excuser, j\'ai un site web à créer. <br> <br> Ok, je ne suis pas complètement sérieux ici mais je pense que c\'est plutôt cool.\n Avoir à échapper au texte tout le temps devient ennuyeux mais que pouvez-vous faire? Eh bien, ne faites pas tout de MySQL pour les débutants.\n J\'espère que rien ne se casse et l\'espacement entre les cartes est bon et je n\'aurai pas à faire quelques réparations. J\'en doute, mais nous verrons.\n Amusez-vous cette semaine tout le monde!');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsxtags`
--

DROP TABLE IF EXISTS `newsxtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newsxtags` (
  `newsID` bigint(20) NOT NULL,
  `tagID` bigint(20) NOT NULL,
  PRIMARY KEY (`newsID`,`tagID`),
  KEY `TagToNews` (`tagID`),
  CONSTRAINT `NewsToTags` FOREIGN KEY (`newsID`) REFERENCES `news` (`newsID`) ON DELETE CASCADE,
  CONSTRAINT `TagToNews` FOREIGN KEY (`tagID`) REFERENCES `tags` (`tagID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsxtags`
--

LOCK TABLES `newsxtags` WRITE;
/*!40000 ALTER TABLE `newsxtags` DISABLE KEYS */;
INSERT INTO `newsxtags` VALUES (1,1),(2,1),(1,2);
/*!40000 ALTER TABLE `newsxtags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `playerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `playerPhoto` varchar(200) DEFAULT NULL,
  `playerFirstName` varchar(100) NOT NULL,
  `playerLastName` varchar(100) NOT NULL,
  `playerEmail` varchar(100) DEFAULT NULL,
  `playerNumber` tinyint(4) DEFAULT NULL,
  `playerPosition` varchar(50) DEFAULT NULL,
  `playerCountry` varchar(50) DEFAULT NULL,
  `playerHeight` float DEFAULT NULL,
  `playerWeight` float DEFAULT NULL,
  `playerHidePage` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`playerID`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,NULL,'Emery','Forrest',NULL,21,'Forward','Canada',176.64,78.44,0),(2,NULL,'Ricki','Saxon',NULL,7,'Goalkeeper',NULL,NULL,NULL,1),(3,NULL,'Trent','Garry',NULL,67,'Forward','United States',187,86.34,0),(4,NULL,'Brand','Derrick',NULL,94,'Goalkeeper',NULL,NULL,NULL,0),(5,NULL,'Hoyt','Braidy',NULL,72,'Goalkeeper',NULL,167.3,NULL,0),(6,NULL,'Jaymes','Tom',NULL,49,'Forward',NULL,NULL,NULL,0),(7,NULL,'Wilmer','Hector',NULL,10,'Forward','Canada',NULL,NULL,0),(8,NULL,'Dawson','Edgar',NULL,53,'Forward',NULL,NULL,NULL,0),(9,NULL,'Kenzie','Orval',NULL,45,'Goalkeeper',NULL,NULL,NULL,0),(10,NULL,'Jed','Leonard',NULL,86,'Forward',NULL,NULL,NULL,0),(11,NULL,'Alva','Darwin',NULL,23,'Forward',NULL,NULL,NULL,0),(12,NULL,'Cecil','Hamnet',NULL,36,'Forward',NULL,NULL,NULL,0),(13,NULL,'Iona','Renae',NULL,25,'Forward',NULL,NULL,NULL,0),(14,NULL,'Medeia','Renita',NULL,66,'Goalkeeper',NULL,NULL,NULL,0),(15,NULL,'Shae','Joy',NULL,3,'Forward',NULL,NULL,NULL,0),(16,NULL,'Lynda','Sharyl',NULL,21,'Goalkeeper',NULL,NULL,NULL,0),(17,NULL,'Paulene','Trixie',NULL,77,'Forward',NULL,NULL,NULL,0),(18,NULL,'Emerson','Ruth',NULL,40,'Goalkeeper',NULL,NULL,NULL,0),(19,NULL,'Deirdre','Dinah',NULL,99,'Forward',NULL,NULL,NULL,0),(20,NULL,'Alexandria','Katheryne',NULL,1,'Forward',NULL,NULL,NULL,0),(21,NULL,'Elfrida','Nicola',NULL,52,'Forward',NULL,NULL,NULL,0),(22,NULL,'Oneida','Corinne',NULL,84,'Forward',NULL,NULL,NULL,0),(23,NULL,'Mattie','Eveline',NULL,13,'Goalkeeper',NULL,NULL,NULL,0),(24,NULL,'Ben','Lana',NULL,4,'Forward',NULL,NULL,NULL,0),(25,NULL,'Juno','Lana',NULL,5,'Forward',NULL,NULL,NULL,0),(26,NULL,'Frank','Byles',NULL,6,'Forward',NULL,NULL,NULL,0),(27,NULL,'Jim','Linko',NULL,7,'Defence',NULL,NULL,NULL,0),(28,NULL,'Bones','Linko',NULL,8,'Defence',NULL,NULL,NULL,0),(29,NULL,'Kirk','Kinko',NULL,9,'Defence',NULL,NULL,NULL,0),(30,NULL,'Kurt','Kinko',NULL,10,'Midfield',NULL,NULL,NULL,0),(31,NULL,'Franky','Lanabean',NULL,11,'Midfield',NULL,NULL,NULL,0),(32,NULL,'Chubo','Lanabean',NULL,12,'Midfield',NULL,NULL,NULL,0),(33,NULL,'Lankin','Dana',NULL,13,'Goalkeeper',NULL,NULL,NULL,0),(34,NULL,'Whachu','Dana',NULL,14,'Forward',NULL,NULL,NULL,0),(35,NULL,'Picu','Terry',NULL,15,'Forward',NULL,NULL,NULL,0),(36,NULL,'Ada','Terry',NULL,16,'Forward',NULL,NULL,NULL,0),(37,NULL,'Naka','Bobanna',NULL,17,'Defence',NULL,NULL,NULL,0),(38,NULL,'Dom','Ajshsa',NULL,18,'Defence',NULL,NULL,NULL,0),(39,NULL,'Harry','Juile',NULL,19,'Defence',NULL,NULL,NULL,0),(40,NULL,'Karry','Zalapski',NULL,20,'Midfield',NULL,NULL,NULL,0),(41,NULL,'Jimmy D','Trueblue',NULL,4,'Midfield',NULL,NULL,NULL,0),(42,NULL,'Aretemus','Waaka',NULL,5,'Midfield',NULL,NULL,NULL,0),(43,NULL,'Langdon','Polo',NULL,6,'Goalkeeper',NULL,NULL,NULL,0),(44,NULL,'Kyle','Oldman',NULL,7,'Forward',NULL,NULL,NULL,0),(45,NULL,'Wes','Guygax',NULL,8,'Forward',NULL,NULL,NULL,0),(46,NULL,'Quebon','Tanner',NULL,9,'Forward',NULL,NULL,NULL,0),(47,NULL,'Lubos','Labelle',NULL,10,'Defence',NULL,NULL,NULL,0),(48,NULL,'Zack','Zachary',NULL,11,'Defence',NULL,NULL,NULL,0),(49,NULL,'Niles','Zalapski',NULL,12,'Defence',NULL,NULL,NULL,0),(50,NULL,'Kurt','Kinko',NULL,10,'Midfield',NULL,NULL,NULL,0),(51,NULL,'Franky','Lanabean',NULL,11,'Midfield',NULL,NULL,NULL,0),(52,NULL,'Chubo','Lanabean',NULL,12,'Midfield',NULL,NULL,NULL,0),(53,NULL,'Lankin','Dana',NULL,13,'Goalkeeper',NULL,NULL,NULL,0),(54,NULL,'Whachu','Dana',NULL,14,'Forward',NULL,NULL,NULL,0),(55,NULL,'Picu','Terry',NULL,15,'Forward',NULL,NULL,NULL,0),(56,NULL,'Ada','Terry',NULL,16,'Forward',NULL,NULL,NULL,0),(57,NULL,'Naka','Bobanna',NULL,17,'Defence',NULL,NULL,NULL,0),(58,NULL,'Dom','Ajshsa',NULL,18,'Defence',NULL,NULL,NULL,0),(59,NULL,'Harry','Juile',NULL,19,'Defence',NULL,NULL,NULL,0),(60,NULL,'Karry','Zalapski',NULL,20,'Midfield',NULL,NULL,NULL,0),(61,NULL,'Jimmy D','Trueblue',NULL,4,'Midfield',NULL,NULL,NULL,0),(62,NULL,'Aretemus','Waaka',NULL,5,'Midfield',NULL,NULL,NULL,0),(63,NULL,'Langdon','Polo',NULL,6,'Goalkeeper',NULL,NULL,NULL,0),(64,NULL,'Kyle','Oldman',NULL,7,'Forward',NULL,NULL,NULL,0),(65,NULL,'Wes','Guygax',NULL,8,'Forward',NULL,NULL,NULL,0),(66,NULL,'Quebon','Tanner',NULL,9,'Forward',NULL,NULL,NULL,0),(67,NULL,'Lubos','Labelle',NULL,10,'Defence',NULL,NULL,NULL,0),(68,NULL,'Zack','Zachary',NULL,11,'Defence',NULL,NULL,NULL,0),(69,NULL,'Niles','Zalapski',NULL,12,'Defence',NULL,NULL,NULL,0),(70,NULL,'Kurt','Kinko',NULL,10,'Midfield',NULL,NULL,NULL,0),(71,NULL,'Franky','Lanabean',NULL,11,'Midfield',NULL,NULL,NULL,0),(72,NULL,'Chubo','Lanabean',NULL,12,'Midfield',NULL,NULL,NULL,0),(73,NULL,'Lankin','Dana',NULL,13,'Goalkeeper',NULL,NULL,NULL,0),(74,NULL,'Whachu','Dana',NULL,14,'Forward',NULL,NULL,NULL,0),(75,NULL,'Picu','Terry',NULL,15,'Forward',NULL,NULL,NULL,0),(76,NULL,'Ada','Terry',NULL,16,'Forward',NULL,NULL,NULL,0),(77,NULL,'Naka','Bobanna',NULL,17,'Defence',NULL,NULL,NULL,0),(78,NULL,'Dom','Ajshsa',NULL,18,'Defence',NULL,NULL,NULL,0),(79,NULL,'Harry','Juile',NULL,19,'Defence',NULL,NULL,NULL,0),(80,NULL,'Karry','Zalapski',NULL,20,'Midfield',NULL,NULL,NULL,0),(81,NULL,'Jimmy D','Trueblue',NULL,4,'Midfield',NULL,NULL,NULL,0),(82,NULL,'Aretemus','Waaka',NULL,5,'Midfield',NULL,NULL,NULL,0),(83,NULL,'Langdon','Polo',NULL,6,'Goalkeeper',NULL,NULL,NULL,0),(84,NULL,'Kyle','Oldman',NULL,7,'Forward',NULL,NULL,NULL,0),(85,NULL,'Wes','Guygax',NULL,8,'Forward',NULL,NULL,NULL,0),(86,NULL,'Quebon','Tanner',NULL,9,'Forward',NULL,NULL,NULL,0),(87,NULL,'Lubos','Labelle',NULL,10,'Defence',NULL,NULL,NULL,0),(88,NULL,'Zack','Zachary',NULL,11,'Defence',NULL,NULL,NULL,0),(89,NULL,'Niles','Zalapski',NULL,12,'Defence',NULL,NULL,NULL,0),(90,NULL,'Kurt','Kinko',NULL,10,'Midfield',NULL,NULL,NULL,0),(91,NULL,'Franky','Lanabean',NULL,11,'Midfield',NULL,NULL,NULL,0),(92,NULL,'Chubo','Lanabean',NULL,12,'Midfield',NULL,NULL,NULL,0),(93,NULL,'Lankin','Dana',NULL,13,'Goalkeeper',NULL,NULL,NULL,0),(94,NULL,'Whachu','Dana',NULL,14,'Forward',NULL,NULL,NULL,0),(95,NULL,'Picu','Terry',NULL,15,'Forward',NULL,NULL,NULL,0),(96,NULL,'Ada','Terry',NULL,16,'Forward',NULL,NULL,NULL,0),(97,NULL,'Naka','Bobanna',NULL,17,'Defence',NULL,NULL,NULL,0),(98,NULL,'Dom','Ajshsa',NULL,18,'Defence',NULL,NULL,NULL,0),(99,NULL,'Harry','Juile',NULL,19,'Defence',NULL,NULL,NULL,0),(100,NULL,'Karry','Zalapski',NULL,20,'Midfield',NULL,NULL,NULL,0),(101,NULL,'Jimmy D','Trueblue',NULL,4,'Midfield',NULL,NULL,NULL,0),(102,NULL,'Aretemus','Waaka',NULL,5,'Midfield',NULL,NULL,NULL,0),(103,NULL,'Langdon','Polo',NULL,6,'Goalkeeper',NULL,NULL,NULL,0),(104,NULL,'Kyle','Oldman',NULL,7,'Forward',NULL,NULL,NULL,0),(105,NULL,'Wes','Guygax',NULL,8,'Forward',NULL,NULL,NULL,0),(106,NULL,'Quebon','Tanner',NULL,9,'Forward',NULL,NULL,NULL,0),(107,NULL,'Lubos','Labelle',NULL,10,'Defence',NULL,NULL,NULL,0),(108,NULL,'Zack','Zachary',NULL,11,'Defence',NULL,NULL,NULL,0),(109,NULL,'Niles','Zalapski',NULL,12,'Defence',NULL,NULL,NULL,0),(110,NULL,'Karry','Zalapski',NULL,20,'Midfield',NULL,NULL,NULL,0),(111,NULL,'Jimmy D','Trueblue',NULL,4,'Midfield',NULL,NULL,NULL,0),(112,NULL,'Aretemus','Waaka',NULL,5,'Midfield',NULL,NULL,NULL,0),(113,NULL,'Langdon','Polo',NULL,6,'Goalkeeper',NULL,NULL,NULL,0),(114,NULL,'Kyle','Oldman',NULL,7,'Forward',NULL,NULL,NULL,0),(115,NULL,'Wes','Guygax',NULL,8,'Forward',NULL,NULL,NULL,0),(116,NULL,'Quebon','Tanner',NULL,9,'Forward',NULL,NULL,NULL,0),(117,NULL,'Lubos','Labelle',NULL,10,'Defence',NULL,NULL,NULL,0),(118,NULL,'Zack','Zachary',NULL,11,'Defence',NULL,NULL,NULL,0),(119,NULL,'Niles','Zalapski',NULL,12,'Defence',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerxteam`
--

DROP TABLE IF EXISTS `playerxteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playerxteam` (
  `playerID` bigint(20) NOT NULL,
  `teamID` bigint(20) NOT NULL,
  PRIMARY KEY (`playerID`,`teamID`),
  KEY `TeamToPlayer` (`teamID`),
  CONSTRAINT `PlayerToTeam` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`),
  CONSTRAINT `TeamToPlayer` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerxteam`
--

LOCK TABLES `playerxteam` WRITE;
/*!40000 ALTER TABLE `playerxteam` DISABLE KEYS */;
INSERT INTO `playerxteam` VALUES (5,1),(6,1),(12,1),(80,1),(81,1),(82,1),(83,1),(84,1),(2,2),(3,2),(10,2),(85,2),(86,2),(87,2),(88,2),(89,2),(4,3),(7,3),(11,3),(90,3),(91,3),(92,3),(93,3),(94,3),(1,4),(8,4),(9,4),(95,4),(96,4),(97,4),(98,4),(99,4),(16,5),(19,5),(22,5),(100,5),(101,5),(102,5),(103,5),(104,5),(14,6),(20,6),(24,6),(105,6),(106,6),(107,6),(108,6),(109,6),(13,7),(17,7),(23,7),(110,7),(111,7),(112,7),(113,7),(114,7),(15,8),(18,8),(21,8),(115,8),(116,8),(117,8),(118,8),(119,8),(25,9),(26,9),(27,9),(28,9),(29,9),(30,10),(31,10),(32,10),(33,10),(34,10),(35,10),(36,10),(37,10),(38,10),(39,10),(40,11),(41,11),(42,11),(43,11),(44,11),(45,11),(46,11),(47,11),(48,11),(49,11),(50,12),(51,12),(52,12),(53,12),(54,12),(55,12),(56,12),(57,12),(58,12),(59,12),(60,13),(61,13),(62,13),(63,13),(64,13),(65,13),(66,13),(67,13),(68,13),(69,13),(70,14),(71,14),(72,14),(73,14),(74,14),(75,14),(76,14),(77,14),(78,14),(79,14);
/*!40000 ALTER TABLE `playerxteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee` (
  `refereeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `refereeFirstName` varchar(100) NOT NULL,
  `refereeLastName` varchar(100) NOT NULL,
  `refereeCountry` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`refereeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
INSERT INTO `referee` VALUES (1,'Opal','Taylor',NULL),(2,'Fitzroy','Hyland',NULL),(3,'Adam','Hale',NULL),(4,'Sapphire','Nonie',NULL),(5,'Kevin','Read',NULL),(6,'Guy','Ferrari',NULL),(7,'Neal','Sengupta',NULL),(8,'Ref','Ref',NULL);
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `gameID` bigint(20) NOT NULL AUTO_INCREMENT,
  `gameDate` date NOT NULL,
  `gameTime` time NOT NULL,
  `homeTeam` bigint(20) NOT NULL,
  `homeScore` tinyint(4) DEFAULT NULL,
  `awayTeam` bigint(20) NOT NULL,
  `awayScore` tinyint(4) DEFAULT NULL,
  `gameStatus` varchar(50) DEFAULT NULL,
  `playoffGame` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`gameID`),
  UNIQUE KEY `UC_Teams` (`gameID`,`homeTeam`,`awayTeam`),
  KEY `HomeTeamToTeam` (`homeTeam`),
  KEY `AwayTeamToTeam` (`awayTeam`),
  CONSTRAINT `AwayTeamToTeam` FOREIGN KEY (`awayTeam`) REFERENCES `team` (`teamID`) ON DELETE CASCADE,
  CONSTRAINT `HomeTeamToTeam` FOREIGN KEY (`homeTeam`) REFERENCES `team` (`teamID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2018-02-10','14:30:00',2,2,3,0,'Final',0),(2,'2018-02-10','16:00:00',1,1,4,1,'Final',0),(3,'2018-02-13','18:30:00',3,3,4,2,'Final',0),(4,'2018-02-15','19:15:00',2,3,1,1,'Final',0),(5,'2018-02-09','18:45:00',7,0,8,3,'Final',0),(6,'2018-02-11','13:30:00',6,1,5,2,'Final',0),(7,'2018-02-15','19:15:00',5,2,8,2,'Final',0),(8,'2018-02-17','14:00:00',6,0,7,0,'Final',0),(9,'2018-02-18','13:45:00',4,5,2,3,'Final',0),(10,'2018-02-20','19:00:00',2,1,3,4,'Final',0),(11,'2018-02-20','20:30:00',1,3,4,2,'Final',0),(12,'2018-02-24','19:15:00',4,0,3,2,'Final',0),(13,'2018-02-26','18:30:00',3,NULL,1,NULL,'Scheduled',0),(14,'2018-02-28','17:15:00',4,NULL,2,NULL,'Scheduled',0),(15,'2018-03-02','18:45:00',2,NULL,1,NULL,'Scheduled',0),(16,'2018-03-03','15:00:00',1,NULL,3,NULL,'Scheduled',0),(17,'2018-03-05','20:45:00',1,NULL,4,NULL,'Scheduled',0),(18,'2018-03-08','21:00:00',2,NULL,1,NULL,'Scheduled',0),(19,'2018-03-10','11:15:00',3,NULL,2,NULL,'Scheduled',0),(20,'2018-03-13','16:30:00',3,NULL,4,NULL,'Scheduled',0),(21,'2018-03-15','19:15:00',6,0,8,0,'Scheduled',0),(22,'2018-03-30','19:30:00',2,NULL,3,NULL,'Scheduled',1),(23,'2018-04-02','16:45:00',3,NULL,2,NULL,'Scheduled',1),(24,'2018-04-04','20:15:00',2,0,3,0,'Scheduled',1),(25,'2018-04-12','19:30:00',15,0,14,0,'Scheduled',0),(27,'2018-04-14','13:15:00',7,0,8,0,'Scheduled',0),(28,'2018-11-19','22:30:00',13,0,12,0,'Scheduled',0);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedulexreferee`
--

DROP TABLE IF EXISTS `schedulexreferee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedulexreferee` (
  `gameID` bigint(20) NOT NULL,
  `refereeID` bigint(20) NOT NULL,
  PRIMARY KEY (`gameID`,`refereeID`),
  KEY `RefereeToSchedule` (`refereeID`),
  CONSTRAINT `RefereeToSchedule` FOREIGN KEY (`refereeID`) REFERENCES `referee` (`refereeID`),
  CONSTRAINT `ScheduleToReferee` FOREIGN KEY (`gameID`) REFERENCES `schedule` (`gameID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedulexreferee`
--

LOCK TABLES `schedulexreferee` WRITE;
/*!40000 ALTER TABLE `schedulexreferee` DISABLE KEYS */;
INSERT INTO `schedulexreferee` VALUES (5,1),(8,1),(23,1),(1,2),(10,2),(13,2),(14,2),(16,2),(17,2),(19,2),(2,3),(3,3),(4,3),(9,3),(11,3),(12,3),(15,3),(18,3),(20,3),(27,3),(6,4),(7,4),(24,4),(22,5),(25,5),(28,5),(21,6);
/*!40000 ALTER TABLE `schedulexreferee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `scorers`
--

DROP TABLE IF EXISTS `scorers`;
/*!50001 DROP VIEW IF EXISTS `scorers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `scorers` AS SELECT
 1 AS `teamName`,
 1 AS `playerName`,
 1 AS `goals`,
 1 AS `yellowCards`,
 1 AS `redCards`,
 1 AS `id`,
 1 AS `playerID`,
 1 AS `playerHidePage`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `standings`
--

DROP TABLE IF EXISTS `standings`;
/*!50001 DROP VIEW IF EXISTS `standings`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `standings` AS SELECT
 1 AS `team`,
 1 AS `GP`,
 1 AS `W`,
 1 AS `D`,
 1 AS `L`,
 1 AS `PTS`,
 1 AS `GF`,
 1 AS `GA`,
 1 AS `GD`,
 1 AS `divisionID`,
 1 AS `logo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!50001 DROP VIEW IF EXISTS `statistics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statistics` AS SELECT
 1 AS `teamName`,
 1 AS `playerName`,
 1 AS `GP`,
 1 AS `goals`,
 1 AS `yellowCards`,
 1 AS `redCards`,
 1 AS `leagueID`,
 1 AS `playerID`,
 1 AS `teamID`,
 1 AS `playoffGame`,
 1 AS `playerHidePage`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tagID` bigint(20) NOT NULL AUTO_INCREMENT,
  `tagDescription` varchar(100) NOT NULL,
  PRIMARY KEY (`tagID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'Mens League'),(2,'Womens League'),(3,'Co-Ed League'),(4,'Site News');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamID` bigint(20) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(100) DEFAULT NULL,
  `teamAbbreviation` char(3) DEFAULT NULL,
  `teamLogo` varchar(200) DEFAULT NULL,
  `teamAbout` text,
  `teamAboutFR` text,
  PRIMARY KEY (`teamID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Nepean Angry Wolves','NAW','http://www.usmedicalsoccerteam.org/wp-content/uploads/2014/08/P1000119-2.jpg','We are Angry Wolves','Nous sommes des loups en colère'),(2,'Manotick Quick Men','MQM','https://img2-placeit-net.s3-accelerate.amazonaws.com/uploads/stage/stage_image/9671/large_thumb_rams.jpg','We Men We Quick','Nous Men We Quick'),(3,'Osgoode Never Reads','ONR','https://i.imgur.com/WSXEUBi.png','This is a team description I have no idea what to write about','Ceci est une description de l\'équipe Je n\'ai aucune idée de quoi écrire'),(4,'Barrhaven Vicious Scorers','BVS','https://i.ytimg.com/vi/j-ghhdCeF74/maxresdefault.jpg','We score a lot of goals. Only sometimes though.','Nous marquons beaucoup de buts. Seulement parfois cependant.'),(5,'Carleton Passive Players','CPP','https://microlancer.lancerassets.com/v2/services/4a/7aa0303c9a11e7b66599334bed589b/large_A.jpg','We can\'t play this soccer game','Nous ne pouvons pas jouer à ce jeu de football'),(6,'Almonte Good Gals','AGG','https://i.pinimg.com/originals/c3/08/bf/c308bfc9f1b6f735fc0d8857a75af651.jpg','We\'re good we think anyways','Nous sommes bons, nous pensons de toute façon'),(7,'Kanata Soccer Team','KST','https://d3kq2xhl2rew87.cloudfront.net/team/bg_image/0/9/9/1140x320/img_1799_sMZBQH8.png','We play soccer and we\'re damn proud','Nous jouons au football et nous sommes sacrément fiers'),(8,'Vars FC','VFC','https://d33wubrfki0l68.cloudfront.net/95fa2a6ddf532a4832ec138864f8a7e709fe73b8/6d114/img/portfolio/goat.png','GO VARS','GO VARS'),(9,'Barrhaven FC','BFC','https://ae01.alicdn.com/kf/HTB175pBKFXXXXaUXXXXq6xXFXXXO/-.jpg_640x640.jpg',NULL,NULL),(10,'Montreal Team','MTL','https://i.pinimg.com/originals/47/86/2d/47862d1e95353cb3a5ba4571e2af5698.png',NULL,NULL),(11,'Ottawa Team','OTT','https://i.pinimg.com/originals/f2/87/29/f28729c260f01bf724443a9dbf964f76.png',NULL,NULL),(12,'Rockland Team','ROC','https://static.vecteezy.com/system/resources/previews/000/199/349/non_2x/fox-basketball-team-badge-mascot-design-logo-concept-vector.jpg',NULL,NULL),(13,'Vanier Team','VAN','https://i.pinimg.com/originals/83/24/50/8324508df5c9381dd31071da773b81db.jpg',NULL,NULL),(14,'Blair Team','BLA','https://i.ytimg.com/vi/IGmQO9sPMXc/maxresdefault.jpg',NULL,NULL),(15,'Kemptville Team','KEM','https://i.ytimg.com/vi/YhA3Pqo786k/maxresdefault.jpg',NULL,NULL),(16,'Kingston Team','KIN','https://i.pinimg.com/originals/a4/6c/5f/a46c5fd0de3b184be02ad800aad1d196.jpg',NULL,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teamxdivision`
--

DROP TABLE IF EXISTS `teamxdivision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teamxdivision` (
  `teamID` bigint(20) NOT NULL,
  `divisionID` bigint(20) NOT NULL,
  PRIMARY KEY (`teamID`,`divisionID`),
  KEY `DivisionToTeam` (`divisionID`),
  CONSTRAINT `DivisionToTeam` FOREIGN KEY (`divisionID`) REFERENCES `division` (`divisionID`),
  CONSTRAINT `TeamToDivision` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamxdivision`
--

LOCK TABLES `teamxdivision` WRITE;
/*!40000 ALTER TABLE `teamxdivision` DISABLE KEYS */;
INSERT INTO `teamxdivision` VALUES (5,1),(6,1),(7,1),(8,1),(1,2),(2,2),(3,2),(4,2),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,4),(16,4);
/*!40000 ALTER TABLE `teamxdivision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT,
  `profilePicture` varchar(200) DEFAULT NULL,
  `userFirstName` varchar(100) NOT NULL,
  `userLastName` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `emailAddress` varchar(100) NOT NULL,
  `emailValidated` tinyint(1) NOT NULL DEFAULT '0',
  `adminActivated` tinyint(1) NOT NULL DEFAULT '0',
  `accountCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accountUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastLogin` timestamp NULL DEFAULT NULL,
  `userType` varchar(100) NOT NULL,
  `refereeID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailAddress` (`emailAddress`),
  UNIQUE KEY `refereeID_UNIQUE` (`refereeID`),
  KEY `UsersToReferee` (`refereeID`),
  CONSTRAINT `UsersToReferee` FOREIGN KEY (`refereeID`) REFERENCES `referee` (`refereeID`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'Fred','Guy','Administrator','$2a$13$Xkb7N2yamdDkNBga.ln7MOJdNyOei05fUGxoUG9PMOrJ7pS3gFQ1i','admin@example.com',1,1,'2018-02-26 23:14:02','2018-04-11 20:19:35','2018-11-19 16:05:52','Administrator',NULL),(2,NULL,'Sapphire','Nonie','referee','$2a$13$rS2zoWEv7UqSr5yxQ4hjquXmkba1O45z5KCsLGOsMjuoUrbQKbJd.','ref@refcorps.org',1,1,'2018-02-26 23:14:02','2018-04-11 20:12:43','2018-04-11 20:12:17','Referee',4),(3,NULL,'Marge','Walters','varsfc','$2a$13$qya3unqqQSDc/oHnE.UooeicrLYV8hdfM/dLJBZVKW6Ot6BPdyk3W','varsfc@varsfc.co.biz',1,1,'2018-02-26 23:14:02','2018-03-21 20:22:28','2018-04-13 16:35:06','Team Owner',NULL),(4,NULL,'Kevin','Read','ref','$2a$13$f7JkZyQo0g9Rtve.4gQxM.K7fVuRvQuo98JuqrQmVcoPFlKQdg4Ci','r@r.com',1,1,'2018-03-14 20:09:12','2018-04-12 21:35:52','2018-04-16 14:34:18','Referee',5),(5,NULL,'Liam','Maloney','team','$2a$13$dqH0xUFLw.biQYKi1JUHJuphSAnN809o0OS9621bVO4ExOnhsVN56','t@t.com',1,1,'2018-03-14 20:09:51','2018-03-21 20:21:37','2018-04-13 16:34:12','Team Owner',NULL),(6,NULL,'Kevin','V','owner','$2a$13$EwBmMAZAf0ynK0scykY12Oucq8luUQWEeZD7pYxdFwNQzzSx3tZvi','a@a.com',1,1,'2018-03-14 20:10:46','2018-03-21 20:21:20','2018-08-21 00:53:47','Administrator',NULL),(8,NULL,'Kevin','Read','kevsummer','$2a$13$42cxm2kVtQHbbZ1i40SeAeNDOaIMBYhAQ/g6X2No1J6HncGj76jrq','123@123.com',1,1,'2018-03-14 21:47:53','2018-03-21 20:22:11','2018-03-14 21:47:53','Team Owner',NULL),(9,NULL,'Bob','Dole','bd','$2a$13$TvZc2GrV.tOPqolBH9HVVeU.VJLItVXNQj2UJnjEmvpoZ/pjKfoLW','bd@bd.net',1,1,'2018-03-21 18:38:52','2018-03-21 18:48:18','2018-03-21 20:13:28','Administrator',NULL),(10,NULL,'123','123','123','$2a$13$ixPauj1xj5A0P8KthVMe8OjGEiaSnMfzcfs6D8J4pIpX.J7iQYLEC','ab@ab.com',1,1,'2018-03-26 19:32:58','2018-03-26 19:32:58','2018-03-26 19:32:58','Team Owner',NULL),(24,NULL,'Kevy','Villy','kvilly','$2a$13$aH7K.eiHsJAHcxETLR6PJ.jOYuXyvON6Rp5ymOLzq6mfM9VhKwJhO','kev@kev.com',1,1,'2018-03-26 21:45:49','2018-03-26 21:45:49','2018-03-26 21:45:48','Administrator',NULL),(25,NULL,'Leo','Malone','LeoMalone','$2a$13$nOAdK1q8nOJI3OzvJDOpmeG4tHyEQsyHp7XeTkML2aBs4BnKTHb9y','leo@malone.org',1,1,'2018-03-26 21:52:05','2018-04-11 20:17:59','2018-05-12 20:07:36','Administrator',NULL),(26,NULL,'asdsad','asdsdf','asdsdf','$2a$13$p6dWTouRadUvn3R5YxLE0uYzVbNX8RiVtSAv9QFSBSGoh3/Wb0ojG','asd@asd.ca',1,0,'2018-03-26 22:39:47','2018-03-26 22:39:47','2018-03-26 22:39:46','Administrator',NULL),(27,NULL,'reCaptcha','IsCool','captchaBois','$2a$13$ho6KHV2UOs9qzII/5xt6o.N1mMDm7lpCUXJw7dIBFzM12bYVuE9yG','captcha@is.awsome',1,0,'2018-03-26 22:40:41','2018-03-26 22:40:41','2018-03-26 22:40:41','Administrator',NULL),(28,NULL,'NotRobot','TotallyHuman','totally_not_robot','$2a$13$F/ITktBO6M5342wA6x81Dudiw9sf6Z14hPNqK/urrLVPp/7HEAhVi','nota@ro.bot',1,0,'2018-03-26 23:16:03','2018-04-05 01:15:25','2018-04-05 01:15:44','Team Owner',NULL),(29,NULL,'John','Rogers','johnrogers','$2a$13$Xq8ez0.xzptREq0K8fkEue4sZmdY0B9SiPiNSc.ACBRSWT8sIURA6','john@rogers.net',1,0,'2018-03-27 15:20:27','2018-03-27 15:20:27','2018-03-27 15:20:26','Team Owner',NULL),(30,NULL,'p','p','p','$2a$13$wcVVQgutzN5kpEzV34x/JuoQKE5tWm4.xER28aLEOOt021x3YnkLO','p@p.com',1,1,'2018-03-29 20:55:25','2018-03-29 20:55:25','2018-03-29 20:55:25','Administrator',NULL),(32,NULL,'Doctor','King','DrKing','$2a$13$.Wq2zsTcHifTae2W9BTsgOK8niu4mNZO0s0bw1Ae.SXgTVdVSIVMW','b@b.b',1,0,'2018-04-11 20:15:14','2018-04-11 20:28:36','2018-04-11 20:41:33','Team Owner',NULL),(33,NULL,'A','A','Sa','$2a$13$igGvm0gToAkKssg85MoRmumkXcI0TuEZKE6s/hUCuStusIubFiv4K','a@a.z',1,0,'2018-04-11 20:39:30','2018-04-11 20:39:57','2018-04-11 20:39:30','Referee',6),(34,NULL,'Neal','Sengupta','NealSenIsCool','$2a$13$sgwW0piour6Pi/knGPAduuxRF6jwqDvnk2j3zohbFxpU.53lPT7Aa','z@z.z',1,0,'2018-04-11 20:46:03','2018-04-11 20:46:17','2018-04-11 20:46:03','Referee',7),(35,NULL,'Ref','Ref','asd','$2a$13$VmE/CGcGhfbXTbBBaN5HQeHhnqhszSdB4972rbrumYlEUDMyUkeQO','asd@asd.com',1,0,'2018-04-11 21:25:49','2018-04-11 21:26:16','2018-04-11 21:25:49','Referee',8),(38,NULL,'Test','Account','testing','$2a$13$AVpaN9lQ6XwQS/5av5jj2O8aa99Lo6s5IpS.2c94sslwHOVerWz1a','test@test.com',1,0,'2018-05-12 18:27:07','2018-05-12 18:27:07','2018-05-12 18:27:35','Team Owner',NULL),(39,NULL,'Zero','Two','zero-two','$2a$13$7MJERMp4ezwbSbtBxCHVUOj0bkKd3cpWS15axXKCT5x1PVcAp2GmO','zero@two.org',1,0,'2018-05-12 18:28:41','2018-05-12 18:28:41','2018-05-12 18:28:39','Referee',NULL),(40,NULL,'a','a','aaaaaaaaaaaaaaaa','$2a$13$cbzxaWs.Pdyl4VA16GLcI.VR6Mn6IV4ZV/oMHh88Pz/2KKPQFleN2','asdkjh@ajkshdajksd.com',1,0,'2018-05-12 18:32:40','2018-05-12 18:32:40','2018-05-12 18:32:37','Referee',NULL),(41,NULL,'a','a','aasdsamdbgzfdsjkhvghsjdkgfkhjg','$2a$13$D7hNgbqC3BsI8Y7uBKR.EOFcMDMg2jP21zhcvopOva2thH4YS.p/m','aksjghdskhdjfgk@ajkshdgsjkhgf',1,0,'2018-05-12 18:33:18','2018-05-12 18:33:18','2018-05-12 18:33:18','Team Owner',NULL),(42,NULL,'b','b','b','$2a$13$b1SjkTUha1iIVcrGPua/CeV9cAuhhMAjNvV8osi6Fnl24RIhm8pV.','b@b',1,0,'2018-05-12 18:36:33','2018-05-12 18:36:33','2018-05-12 18:36:30','Team Owner',NULL),(43,NULL,'Homer','Sampson','homer','$2a$13$jkJVRuIefJYIkfFHPtOuRuEMD118P8Dpa4OWoP983N3zhUUntEDxq','homer@sampson.com',1,0,'2018-07-25 01:45:04','2018-07-25 01:45:04','2018-07-25 01:45:17','Referee',NULL),(44,NULL,'Kev','Read','KevRead123','$2a$13$9V8wGYrx5Wzg7lm1Rta27.jV4a2Vj2LTZGUzmfWItu2RsYynxQ9gy','kev@read.com',1,1,'2018-07-25 12:14:14','2018-07-25 12:14:14','2018-07-25 12:14:25','Team Owner',NULL),(53,NULL,'Bart','Simpson','bartman','$2a$13$sMHz.rrfNzI/gJpVoHSy2ONYdib/3H2ryyaDuW7/wd9/feNuZxDAi','thisisnotanemail45@gmail.com',0,0,'2018-08-03 00:23:58','2018-08-03 00:23:58','2018-08-02 20:23:58','Team Owner',NULL),(54,NULL,'Test','TestTest','testtest','$2a$13$cwd51kmPBnm4LLrJxRU0/.yPy8TbWX3M.ebCOgK5ykHbi24Ls20PW','f3n1x_08@live.com',1,0,'2018-08-03 14:38:57','2018-08-03 10:57:17','2018-09-19 11:17:24','Team Owner',NULL),(55,NULL,'ahhhhhhhhhhhh','ahhhhhhhhhhhhh','ahhhhhh','$2a$13$rebcZO6XztVgBvgReoByx.YtVlIwvqVowN78PX6WtY.yZJwCA0qvy','kvillema8@gmail.com',1,0,'2018-08-03 14:43:03','2018-08-03 10:53:44','2018-08-03 10:42:59','Referee',NULL),(56,NULL,'Joe','Test','joetest','$2a$13$dLZQpUACVjY/pwFoNIO0WuRfRcV11MP28vPcC6dN0GsG2vM9I9gC.','kevinvillemaire@algonquinlive.com',0,0,'2018-08-23 21:14:27','2018-08-23 21:14:27','2018-08-23 17:14:24','Team Owner',NULL),(57,NULL,'Hello','World','helloworld','$2a$13$n/46cF26jELgRfw19kd4wO5QglUNxYlUWWztY2BgxLXcZ/XI7jMry','vill0296@algonquinlive.com',1,0,'2018-08-23 21:17:45','2018-09-19 00:20:15','2018-09-19 11:17:43','Referee',NULL),(58,NULL,'Dylan','Smyth','dsmyth','$2a$13$ld.i1fokA0j5TJm3IBcjZ.k8a4ceAeDTViEFvCSTve7cjQkUT0kE.','graysuller@gmail.com',1,0,'2018-09-19 15:18:56','2018-09-19 21:28:28','2018-09-19 21:51:12','Referee',NULL),(59,NULL,'Bill','Simpson','bills','$2a$13$c7MGFYizmKnNDwPP.k2iX.u2h/Hi4N1d.1T1Q/z5N.Jaz3WzJtkdW','thisisnotanemail45+lastever@gmail.com',1,0,'2018-10-05 01:24:47','2018-10-05 01:33:00','2018-10-05 01:33:14','Team Owner',NULL),(60,NULL,'No','Name','noname','$2a$13$H8zYjOtzpocIbvqBAZLmT.lq7cdCWxzpZ6vfSASjMmY6gV36xTVt6','thisisnotanemail45+noname@gmail.com',0,0,'2018-10-05 01:47:33','2018-10-05 01:47:33','2018-10-04 21:50:51','Team Owner',NULL),(61,NULL,'Homer','Sampson','newacct','$2a$13$10TI8.GttCXHtjP82text.FcZJEqJFHlJcdVp0oFT.UXPOS8uhtyC','thisisnotanemail45+a@gmail.com',1,0,'2018-10-12 23:33:17','2018-10-12 19:35:16','2018-10-12 19:35:43','Team Owner',NULL),(62,NULL,'Async','Test','async','$2a$13$qsZoDrq/SyOD0Ei.Qc//KO5EYeunwExu0jimMfhJwurSVaUdbChZu','lasteversoccer+async@gmail.com',0,0,'2019-01-07 19:40:40','2019-01-07 19:40:40','2019-01-07 14:40:40','Team Owner',NULL),(63,NULL,'Async','Test2','async2','$2a$13$npa3cEsChklKjunPsvI3qe/lEnj/h21AxlkGDjKgsR0I0msUGEZlW','lasteversoccer+async2@gmail.com',0,0,'2019-01-07 19:45:19','2019-01-07 19:45:19','2019-01-07 14:45:19','Team Owner',NULL),(64,NULL,'Async','Test3','async3','$2a$13$D/W/Pav7IaVdgd.wGnr.1.CC/b8TFLU2CK9jjNWI6vJTGyWsPVbFy','lasteversoccer+async3@gmail.com',0,0,'2019-01-07 19:56:44','2019-01-07 19:56:44','2019-01-07 14:56:44','Referee',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersxteam`
--

DROP TABLE IF EXISTS `usersxteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersxteam` (
  `userID` bigint(20) NOT NULL,
  `teamID` bigint(20) NOT NULL,
  PRIMARY KEY (`userID`,`teamID`),
  KEY `TeamToUsers` (`teamID`),
  CONSTRAINT `TeamToUsers` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`),
  CONSTRAINT `UsersToTeam` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersxteam`
--

LOCK TABLES `usersxteam` WRITE;
/*!40000 ALTER TABLE `usersxteam` DISABLE KEYS */;
INSERT INTO `usersxteam` VALUES (28,1),(32,4),(3,8),(8,8),(5,9);
/*!40000 ALTER TABLE `usersxteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venue` (
  `venueID` bigint(20) NOT NULL AUTO_INCREMENT,
  `venueName` varchar(200) NOT NULL,
  `venuePicture` varchar(200) DEFAULT NULL,
  `venueAddress1` varchar(100) NOT NULL,
  `venueAddress2` varchar(100) DEFAULT NULL,
  `venueCity` varchar(100) NOT NULL,
  `venueProvince` varchar(100) NOT NULL,
  `venuePostal` varchar(7) NOT NULL,
  `venueCountry` varchar(100) NOT NULL,
  `venueContactName` varchar(200) DEFAULT NULL,
  `venuePhoneNumber` varchar(14) DEFAULT NULL,
  `venueEmail` varchar(100) DEFAULT NULL,
  `venueAbout` text,
  `venueAboutFR` text,
  PRIMARY KEY (`venueID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (1,'Nepean Sportsplex','https://images.pexels.com/photos/114296/pexels-photo-114296.jpeg?w=1920&h=1080','1701 Woodroffe Ave',NULL,'Nepean','Ontario','K2G 1W2','Canada','Emma Dean','666-666-6666','sportsplex@venue.org','The Nepean Sportsplex where champions play.','Le Sportsplex de Nepean où les champions jouent.');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venuexgame`
--

DROP TABLE IF EXISTS `venuexgame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venuexgame` (
  `venueID` bigint(20) NOT NULL,
  `gameID` bigint(20) NOT NULL,
  PRIMARY KEY (`venueID`,`gameID`),
  KEY `GameToVenue` (`gameID`),
  CONSTRAINT `GameToVenue` FOREIGN KEY (`gameID`) REFERENCES `schedule` (`gameID`) ON DELETE CASCADE,
  CONSTRAINT `VenueToGame` FOREIGN KEY (`venueID`) REFERENCES `venue` (`venueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venuexgame`
--

LOCK TABLES `venuexgame` WRITE;
/*!40000 ALTER TABLE `venuexgame` DISABLE KEYS */;
INSERT INTO `venuexgame` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,27),(1,28);
/*!40000 ALTER TABLE `venuexgame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS `weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weather` (
  `weatherID` bigint(20) NOT NULL AUTO_INCREMENT,
  `weatherCity` varchar(100) NOT NULL,
  `weatherCountry` varchar(2) NOT NULL,
  `weatherTemp` double NOT NULL,
  `weatherIcon` varchar(3) NOT NULL,
  `weatherCode` int(11) NOT NULL,
  `weatherDescription` varchar(100) NOT NULL,
  `weatherPressure` double NOT NULL,
  `weatherHumidity` int(11) NOT NULL,
  `weatherWind` double NOT NULL,
  `weatherGust` double NOT NULL,
  `weatherDay` varchar(5) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`weatherID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather`
--

LOCK TABLES `weather` WRITE;
/*!40000 ALTER TABLE `weather` DISABLE KEYS */;
INSERT INTO `weather` VALUES (1,'Ottawa','CA',-11.13,'09n',521,'shower rain',101.8,43,22.32,33.480000000000004,'night','2019-03-06 23:05:38');
/*!40000 ALTER TABLE `weather` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'lastever'
--

--
-- Dumping routines for database 'lastever'
--
/*!50003 DROP PROCEDURE IF EXISTS `update_div` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`localhost` PROCEDURE `update_div`(In x bigint(20), In y bigint(20))
begin
  IF EXISTS (select divisionID from leaguexdivision where divisionID = x) THEN
    UPDATE leaguexdivision SET leagueID=y WHERE divisionID=x;
  ELSE
    insert into leaguexdivision (divisionID, leagueID) values (x, y);
  END IF;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`localhost` PROCEDURE `update_team`(In x bigint(20), In y bigint(20))
begin
  IF EXISTS (select teamID from teamxdivision where teamID = x) THEN
    UPDATE teamxdivision SET divisionID=y WHERE teamID=x;
  ELSE
    insert into teamxdivision (teamId, divisionID) values (x, y);
  END IF;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `scorers`
--

/*!50001 DROP VIEW IF EXISTS `scorers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `scorers` AS select `sc`.`teamName` AS `teamName`,`sc`.`playerName` AS `playerName`,`sc`.`goals` AS `goals`,`sc`.`yellowCards` AS `yellowCards`,`sc`.`redCards` AS `redCards`,`sc`.`id` AS `id`,`sc`.`playerID` AS `playerID`,`sc`.`playerHidePage` AS `playerHidePage` from (select `t`.`teamName` AS `teamName`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,`g`.`goals` AS `goals`,`g`.`yellowCards` AS `yellowCards`,`g`.`redCards` AS `redCards`,`g`.`gameID` AS `id`,`p`.`playerID` AS `playerID`,`p`.`playerHidePage` AS `playerHidePage` from ((((`lastever`.`schedule` `s` join `lastever`.`team` `t` on((`t`.`teamID` = `s`.`homeTeam`))) join `lastever`.`playerxteam` `pt` on((`pt`.`teamID` = `t`.`teamID`))) join `lastever`.`player` `p` on((`p`.`playerID` = `pt`.`playerID`))) join `lastever`.`gamestatistics` `g` on((`g`.`playerID` = `p`.`playerID`))) where (`s`.`gameStatus` = 'Final') union all select `t`.`teamName` AS `teamName`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,`g`.`goals` AS `goals`,`g`.`yellowCards` AS `yellowCards`,`g`.`redCards` AS `redCards`,`g`.`gameID` AS `id`,`p`.`playerID` AS `playerID`,`p`.`playerHidePage` AS `playerHidePage` from ((((`lastever`.`schedule` `s` join `lastever`.`team` `t` on((`t`.`teamID` = `s`.`awayTeam`))) join `lastever`.`playerxteam` `pt` on((`pt`.`teamID` = `t`.`teamID`))) join `lastever`.`player` `p` on((`p`.`playerID` = `pt`.`playerID`))) join `lastever`.`gamestatistics` `g` on((`g`.`playerID` = `p`.`playerID`))) where (`s`.`gameStatus` = 'Final')) `sc` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `standings`
--

/*!50001 DROP VIEW IF EXISTS `standings`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `standings` AS select `r`.`team` AS `team`,sum((case when ((`r`.`gameStatus` = 'Final') and (`r`.`playoffGame` = 0)) then 1 else 0 end)) AS `GP`,sum((case when (`r`.`homeScore` > `r`.`awayScore`) then 1 else 0 end)) AS `W`,sum((case when (`r`.`homeScore` = `r`.`awayScore`) then 1 else 0 end)) AS `D`,sum((case when (`r`.`homeScore` < `r`.`awayScore`) then 1 else 0 end)) AS `L`,sum(((case when (`r`.`homeScore` > `r`.`awayScore`) then 3 else 0 end) + (case when (`r`.`homeScore` = `r`.`awayScore`) then 1 else 0 end))) AS `PTS`,coalesce(sum(`r`.`homeScore`),0) AS `GF`,coalesce(sum(`r`.`awayScore`),0) AS `GA`,(coalesce(sum(`r`.`homeScore`),0) - coalesce(sum(`r`.`awayScore`),0)) AS `GD`,`r`.`divisionID` AS `divisionID`,`r`.`logo` AS `logo` from (select `t`.`teamName` AS `team`,`s`.`homeScore` AS `homeScore`,`s`.`awayScore` AS `awayScore`,`s`.`gameStatus` AS `gameStatus`,`s`.`playoffGame` AS `playoffGame`,`dt`.`divisionID` AS `divisionID`,`t`.`teamLogo` AS `logo` from (`lastever`.`teamxdivision` `dt` left join (`lastever`.`team` `t` left join `lastever`.`schedule` `s` on((`t`.`teamID` = `s`.`homeTeam`))) on((`t`.`teamID` = `dt`.`teamID`))) union all select `t`.`teamName` AS `away`,`s`.`awayScore` AS `awayScore`,`s`.`homeScore` AS `homeScore`,`s`.`gameStatus` AS `gameStatus`,`s`.`playoffGame` AS `playoffGame`,`dt`.`divisionID` AS `divisionID`,`t`.`teamLogo` AS `logo` from (`lastever`.`teamxdivision` `dt` left join (`lastever`.`team` `t` left join `lastever`.`schedule` `s` on((`t`.`teamID` = `s`.`awayTeam`))) on((`t`.`teamID` = `dt`.`teamID`)))) `r` group by `r`.`team` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statistics`
--

/*!50001 DROP VIEW IF EXISTS `statistics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`admin`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `statistics` AS select `t`.`teamName` AS `teamName`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,sum((case when (`s`.`gameStatus` = 'Final') then 1 else 0 end)) AS `GP`,coalesce(sum(`g`.`goals`),0) AS `goals`,coalesce(sum(`g`.`yellowCards`),0) AS `yellowCards`,coalesce(sum(`g`.`redCards`),0) AS `redCards`,`ld`.`leagueID` AS `leagueID`,`p`.`playerID` AS `playerID`,`t`.`teamID` AS `teamID`,coalesce(`s`.`playoffGame`,0) AS `playoffGame`,`p`.`playerHidePage` AS `playerHidePage` from (((((((`player` `p` left join `gamestatistics` `g` on((`g`.`playerID` = `p`.`playerID`))) left join `schedule` `s` on((`s`.`gameID` = `g`.`gameID`))) join `playerxteam` `pt` on((`pt`.`playerID` = `p`.`playerID`))) join `team` `t` on((`t`.`teamID` = `pt`.`teamID`))) join `teamxdivision` `td` on((`td`.`teamID` = `t`.`teamID`))) join `division` `d` on((`d`.`divisionID` = `td`.`divisionID`))) join `leaguexdivision` `ld` on((`ld`.`divisionID` = `d`.`divisionID`))) group by `playerName`,`t`.`teamName` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-13 14:17:08
