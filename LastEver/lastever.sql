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
-- Temporary table structure for view `awayscorers`
--

DROP TABLE IF EXISTS `awayscorers`;
/*!50001 DROP VIEW IF EXISTS `awayscorers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `awayscorers` AS SELECT 
 1 AS `teamName`,
 1 AS `awayScore`,
 1 AS `playerName`,
 1 AS `goals`,
 1 AS `yellowCards`,
 1 AS `redCards`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `divisionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `divsionName` varchar(100) NOT NULL,
  `divisionLogo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`divisionID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (1,'Womens',NULL),(2,'Mens',NULL),(3,'Co-ed',NULL);
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
  CONSTRAINT `GameStaticticsToSchedule` FOREIGN KEY (`gameID`) REFERENCES `schedule` (`gameID`),
  CONSTRAINT `GameStatisticsToPlayer` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamestatistics`
--

LOCK TABLES `gamestatistics` WRITE;
/*!40000 ALTER TABLE `gamestatistics` DISABLE KEYS */;
INSERT INTO `gamestatistics` VALUES (1,1,2,0,0,0),(2,1,3,1,1,0),(3,1,10,1,0,0),(4,1,4,0,0,0),(5,1,7,0,2,1),(6,1,11,0,0,0),(7,2,5,0,0,0),(8,2,6,0,1,0),(9,2,12,1,0,0),(10,2,1,0,0,0),(11,2,8,1,0,0),(12,2,9,0,0,0),(13,5,13,0,0,0),(14,5,17,0,1,0),(15,5,23,0,1,0),(16,5,15,2,1,0),(17,5,18,0,0,0),(18,5,21,0,0,0),(19,6,14,0,0,0),(20,6,20,1,0,0),(21,6,24,0,0,0),(22,6,16,0,0,0),(23,6,19,1,1,0),(24,6,22,1,0,1),(25,7,16,0,0,0),(26,7,19,0,0,0),(27,7,22,2,0,0),(28,5,15,1,1,0),(29,5,18,0,1,0),(30,5,21,1,0,0),(31,3,4,0,0,0),(32,3,7,2,0,0),(33,3,11,1,1,0),(34,3,1,1,1,0),(35,3,8,1,0,0),(36,3,9,0,1,0);
/*!40000 ALTER TABLE `gamestatistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `homescorers`
--

DROP TABLE IF EXISTS `homescorers`;
/*!50001 DROP VIEW IF EXISTS `homescorers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `homescorers` AS SELECT 
 1 AS `teamName`,
 1 AS `homeScore`,
 1 AS `playerName`,
 1 AS `goals`,
 1 AS `yellowCards`,
 1 AS `redCards`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `playerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `playerFirstName` varchar(100) NOT NULL,
  `playerLastName` varchar(100) NOT NULL,
  `playerNumber` tinyint(4) DEFAULT NULL,
  `playerPosition` varchar(50) DEFAULT NULL,
  `playerCountry` varchar(50) DEFAULT NULL,
  `playerHeight` float DEFAULT NULL,
  `playerWeight` float DEFAULT NULL,
  PRIMARY KEY (`playerID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'Emery','Forrest',21,'Forward',NULL,NULL,NULL),(2,'Ricki','Saxon',7,'Goalkeeper',NULL,NULL,NULL),(3,'Trent','Garry',67,'Forward',NULL,NULL,NULL),(4,'Brand','Derrick',94,'Goalkeeper',NULL,NULL,NULL),(5,'Hoyt','Braidy',72,'Goalkeeper',NULL,NULL,NULL),(6,'Jaymes','Tom',49,'Forward',NULL,NULL,NULL),(7,'Wilmer','Hector',10,'Forward',NULL,NULL,NULL),(8,'Dawson','Edgar',53,'Forward',NULL,NULL,NULL),(9,'Kenzie','Orval',45,'Goalkeeper',NULL,NULL,NULL),(10,'Jed','Leonard',86,'Forward',NULL,NULL,NULL),(11,'Alva','Darwin',23,'Forward',NULL,NULL,NULL),(12,'Cecil','Hamnet',36,'Forward',NULL,NULL,NULL),(13,'Iona','Renae',25,'Forward',NULL,NULL,NULL),(14,'Medeia','Renita',66,'Goalkeeper',NULL,NULL,NULL),(15,'Shae','Joy',3,'Forward',NULL,NULL,NULL),(16,'Lynda','Sharyl',21,'Goalkeeper',NULL,NULL,NULL),(17,'Paulene','Trixie',77,'Forward',NULL,NULL,NULL),(18,'Emerson','Ruth',40,'Goalkeeper',NULL,NULL,NULL),(19,'Deirdre','Dinah',99,'Forward',NULL,NULL,NULL),(20,'Alexandria','Katheryne',1,'Forward',NULL,NULL,NULL),(21,'Elfrida','Nicola',52,'Forward',NULL,NULL,NULL),(22,'Oneida','Corinne',84,'Forward',NULL,NULL,NULL),(23,'Mattie','Eveline',13,'Goalkeeper',NULL,NULL,NULL),(24,'Christi','Lana',4,'Forward',NULL,NULL,NULL);
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
  CONSTRAINT `TeamToPlayer` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerxteam`
--

LOCK TABLES `playerxteam` WRITE;
/*!40000 ALTER TABLE `playerxteam` DISABLE KEYS */;
INSERT INTO `playerxteam` VALUES (5,1),(6,1),(12,1),(2,2),(3,2),(10,2),(4,3),(7,3),(11,3),(1,4),(8,4),(9,4),(16,5),(19,5),(22,5),(14,6),(20,6),(24,6),(13,7),(17,7),(23,7),(15,8),(18,8),(21,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
INSERT INTO `referee` VALUES (1,'Opal','Taylor',NULL),(2,'Fitzroy','Hyland',NULL),(3,'Adam','Hale',NULL),(4,'Sapphire','Nonie',NULL);
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refereexdivision`
--

DROP TABLE IF EXISTS `refereexdivision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refereexdivision` (
  `refereeID` bigint(20) NOT NULL,
  `divisionID` bigint(20) NOT NULL,
  PRIMARY KEY (`refereeID`,`divisionID`),
  KEY `DivisionToReferee` (`divisionID`),
  CONSTRAINT `DivisionToReferee` FOREIGN KEY (`divisionID`) REFERENCES `division` (`divisionID`),
  CONSTRAINT `RefereeToDivision` FOREIGN KEY (`refereeID`) REFERENCES `referee` (`refereeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refereexdivision`
--

LOCK TABLES `refereexdivision` WRITE;
/*!40000 ALTER TABLE `refereexdivision` DISABLE KEYS */;
INSERT INTO `refereexdivision` VALUES (1,1),(4,1),(2,2),(3,2);
/*!40000 ALTER TABLE `refereexdivision` ENABLE KEYS */;
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
  PRIMARY KEY (`gameID`),
  KEY `HomeTeamToTeam` (`homeTeam`),
  KEY `AwayTeamToTeam` (`awayTeam`),
  CONSTRAINT `AwayTeamToTeam` FOREIGN KEY (`awayTeam`) REFERENCES `team` (`teamID`),
  CONSTRAINT `HomeTeamToTeam` FOREIGN KEY (`homeTeam`) REFERENCES `team` (`teamID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'2018-02-10','14:30:00',2,2,3,0,'Final'),(2,'2018-02-10','16:00:00',1,1,4,1,'Final'),(3,'2018-02-13','18:30:00',3,3,4,2,'Final'),(4,'2018-02-15','19:15:00',2,NULL,1,NULL,'Scheduled'),(5,'2018-02-09','18:45:00',7,0,8,3,'Final'),(6,'2018-02-11','13:30:00',6,1,5,2,'Final'),(7,'2018-02-15','19:15:00',5,2,8,2,'Final'),(8,'2018-02-17','14:00:00',6,NULL,7,NULL,'Scheduled'),(9,'2018-02-18','13:45:00',4,NULL,2,NULL,'Scheduled'),(10,'2018-02-20','19:00:00',2,NULL,3,NULL,'Scheduled'),(11,'2018-02-20','20:30:00',1,NULL,4,NULL,'Scheduled'),(12,'2018-02-24','19:15:00',4,NULL,3,NULL,'Scheduled'),(13,'2018-02-26','18:30:00',3,NULL,1,NULL,'Scheduled'),(14,'2018-02-28','17:15:00',4,NULL,2,NULL,'Scheduled'),(15,'2018-03-02','18:45:00',2,NULL,1,NULL,'Scheduled'),(16,'2018-03-03','15:00:00',1,NULL,3,NULL,'Scheduled'),(17,'2018-03-05','20:45:00',1,NULL,4,NULL,'Scheduled'),(18,'2018-03-08','21:00:00',2,NULL,1,NULL,'Scheduled'),(19,'2018-03-10','11:15:00',3,NULL,2,NULL,'Scheduled'),(20,'2018-03-13','16:30:00',3,NULL,4,NULL,'Scheduled');
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
  CONSTRAINT `ScheduleToReferee` FOREIGN KEY (`gameID`) REFERENCES `schedule` (`gameID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedulexreferee`
--

LOCK TABLES `schedulexreferee` WRITE;
/*!40000 ALTER TABLE `schedulexreferee` DISABLE KEYS */;
INSERT INTO `schedulexreferee` VALUES (5,1),(8,1),(1,2),(2,3),(3,3),(4,3),(6,4),(7,4);
/*!40000 ALTER TABLE `schedulexreferee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `standings`
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
 1 AS `divisionID`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `statistics`
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
 1 AS `divisionID`*/;
SET character_set_client = @saved_cs_client;

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
  PRIMARY KEY (`teamID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Nepean Angry Wolves','NAW',NULL),(2,'Manotick Quick Men','MQM',NULL),(3,'Osgoode Never Reads','ONR',NULL),(4,'Barrhaven Vicious Scorers','BVS',NULL),(5,'Carleton Passive Players','CPP',NULL),(6,'Almonte Good Gals','AGG',NULL),(7,'Kanata Soccer Team','KST',NULL),(8,'Vars FC','VFC',NULL);
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
  CONSTRAINT `TeamToDivision` FOREIGN KEY (`teamID`) REFERENCES `team` (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamxdivision`
--

LOCK TABLES `teamxdivision` WRITE;
/*!40000 ALTER TABLE `teamxdivision` DISABLE KEYS */;
INSERT INTO `teamxdivision` VALUES (5,1),(6,1),(7,1),(8,1),(1,2),(2,2),(3,2),(4,2);
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
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `emailAddress` varchar(100) NOT NULL,
  `emailValidated` tinyint(1) NOT NULL,
  `userType` varchar(100) NOT NULL,
  `refereeID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `UsersToReferee` (`refereeID`),
  CONSTRAINT `UsersToReferee` FOREIGN KEY (`refereeID`) REFERENCES `referee` (`refereeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'admin','superPassword','admin@example.com',1,'Administrator',NULL),(2,NULL,'referee','ezpass','ref@refcorps.org',1,'Referee',4),(3,NULL,'varsfc','teamOwner','varsfc@varsfc.co.biz',0,'Team Owner',NULL);
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
INSERT INTO `usersxteam` VALUES (3,8);
/*!40000 ALTER TABLE `usersxteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `awayscorers`
--

/*!50001 DROP VIEW IF EXISTS `awayscorers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `awayscorers` AS select `a`.`teamName` AS `teamName`,`s`.`awayScore` AS `awayScore`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,`g`.`goals` AS `goals`,`g`.`yellowCards` AS `yellowCards`,`g`.`redCards` AS `redCards` from ((((`schedule` `s` join `team` `a` on((`a`.`teamID` = `s`.`awayTeam`))) join `playerxteam` `pt` on((`pt`.`teamID` = `a`.`teamID`))) join `player` `p` on((`p`.`playerID` = `pt`.`playerID`))) join `gamestatistics` `g` on((`g`.`playerID` = `p`.`playerID`))) where (`s`.`gameStatus` = 'Final') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `homescorers`
--

/*!50001 DROP VIEW IF EXISTS `homescorers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `homescorers` AS select `h`.`teamName` AS `teamName`,`s`.`homeScore` AS `homeScore`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,`g`.`goals` AS `goals`,`g`.`yellowCards` AS `yellowCards`,`g`.`redCards` AS `redCards` from ((((`schedule` `s` join `team` `h` on((`h`.`teamID` = `s`.`homeTeam`))) join `playerxteam` `pt` on((`pt`.`teamID` = `h`.`teamID`))) join `player` `p` on((`p`.`playerID` = `pt`.`playerID`))) join `gamestatistics` `g` on((`g`.`playerID` = `p`.`playerID`))) where (`s`.`gameStatus` = 'Final') */;
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
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `standings` AS select `r`.`team` AS `team`,sum((case when (`r`.`gameStatus` = 'Final') then 1 else 0 end)) AS `GP`,sum((case when (`r`.`homeScore` > `r`.`awayScore`) then 1 else 0 end)) AS `W`,sum((case when (`r`.`homeScore` = `r`.`awayScore`) then 1 else 0 end)) AS `D`,sum((case when (`r`.`homeScore` < `r`.`awayScore`) then 1 else 0 end)) AS `L`,sum(((case when (`r`.`homeScore` > `r`.`awayScore`) then 3 else 0 end) + (case when (`r`.`homeScore` = `r`.`awayScore`) then 1 else 0 end))) AS `PTS`,coalesce(sum(`r`.`homeScore`),0) AS `GF`,coalesce(sum(`r`.`awayScore`),0) AS `GA`,(coalesce(sum(`r`.`homeScore`),0) - coalesce(sum(`r`.`awayScore`),0)) AS `GD`,`r`.`divisionID` AS `divisionID` from (select `t`.`teamName` AS `team`,`s`.`homeScore` AS `homeScore`,`s`.`awayScore` AS `awayScore`,`s`.`gameStatus` AS `gameStatus`,`dt`.`divisionID` AS `divisionID` from ((`lastever`.`schedule` `s` join `lastever`.`team` `t` on((`t`.`teamID` = `s`.`homeTeam`))) join `lastever`.`teamxdivision` `dt` on((`t`.`teamID` = `dt`.`teamID`))) union all select `t`.`teamName` AS `away`,`s`.`awayScore` AS `awayScore`,`s`.`homeScore` AS `homeScore`,`s`.`gameStatus` AS `gameStatus`,`dt`.`divisionID` AS `divisionID` from ((`lastever`.`schedule` `s` join `lastever`.`team` `t` on((`t`.`teamID` = `s`.`awayTeam`))) join `lastever`.`teamxdivision` `dt` on((`t`.`teamID` = `dt`.`teamID`)))) `r` group by `r`.`team` */;
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
/*!50001 VIEW `statistics` AS select `t`.`teamName` AS `teamName`,concat_ws(' ',`p`.`playerFirstName`,`p`.`playerLastName`) AS `playerName`,count(`p`.`playerID`) AS `GP`,sum(`g`.`goals`) AS `goals`,sum(`g`.`yellowCards`) AS `yellowCards`,sum(`g`.`redCards`) AS `redCards`,`td`.`divisionID` AS `divisionID` from ((((`gamestatistics` `g` join `player` `p` on((`p`.`playerID` = `g`.`playerID`))) join `playerxteam` `pt` on((`pt`.`playerID` = `p`.`playerID`))) join `team` `t` on((`t`.`teamID` = `pt`.`teamID`))) join `teamxdivision` `td` on((`td`.`teamID` = `t`.`teamID`))) group by `playerName` */;
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

-- Dump completed on 2018-02-15 17:23:47
