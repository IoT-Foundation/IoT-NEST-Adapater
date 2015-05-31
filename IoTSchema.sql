CREATE DATABASE  IF NOT EXISTS `iot` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `iot`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: iot
-- ------------------------------------------------------
-- Server version	5.5.38

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
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `id` mediumint(9) NOT NULL,
  `name` varchar(254) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `valueName` varchar(256) NOT NULL,
  `valueType` varchar(45) NOT NULL DEFAULT 'String',
  `deviceId` mediumint(9) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actions`
--

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;
INSERT INTO `actions` VALUES (1,'Set Away','Sets the Nest thermostat to Away setting.','123-123','String',2),(2,'Set Temprature','Sets the temprature of the thermostat.','target_temprature_f','Float',2),(3,'Set ETA','Sets the Estimated time of Arival.  Time in milliseconds after January 1, 1970, 0:00:00 GMT.','estimated_arrival_window_end','Float',2),(4,'Get Temprature','Gets the current temprature of the thermostat','123-123','String',2);
/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adapterConfig`
--

DROP TABLE IF EXISTS `adapterConfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adapterConfig` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `token` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adapterConfig`
--

LOCK TABLES `adapterConfig` WRITE;
/*!40000 ALTER TABLE `adapterConfig` DISABLE KEYS */;
INSERT INTO `adapterConfig` VALUES (1,'Nest Adapter','123456');
/*!40000 ALTER TABLE `adapterConfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devices` (
  `deviceType` varchar(128) NOT NULL,
  `misc` varchar(256) DEFAULT NULL,
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES ('smoke_co_alarms',NULL,1),('thermostats',NULL,2);
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endpointInfo`
--

DROP TABLE IF EXISTS `endpointInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endpointInfo` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) NOT NULL,
  `value` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endpointInfo`
--

LOCK TABLES `endpointInfo` WRITE;
/*!40000 ALTER TABLE `endpointInfo` DISABLE KEYS */;
INSERT INTO `endpointInfo` VALUES (1,'name','Nest Devices'),(2,'description','Meet the Nest Learning Thermostat. The Nest thermostat learns the temps you like, turns itself down when you\'re away, and has remote control through Wi-Fi.'),(3,'hrefUrl','http://localhost:8080/IoT/console/nest/login?client_id=*&state=*&redirect_uri=*'),(4,'hrefImageUrl','http://blueprintbuilders.com/wp-content/uploads/2014/08/nest-stock-image-1.png');
/*!40000 ALTER TABLE `endpointInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessionTokenMapper`
--

DROP TABLE IF EXISTS `sessionTokenMapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessionTokenMapper` (
  `sessionId` varchar(254) NOT NULL,
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `returnURL` varchar(512) NOT NULL,
  `state` varchar(254) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `sessionId_UNIQUE` (`sessionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionTokenMapper`
--

LOCK TABLES `sessionTokenMapper` WRITE;
/*!40000 ALTER TABLE `sessionTokenMapper` DISABLE KEYS */;
INSERT INTO `sessionTokenMapper` VALUES ('123',3,'http://www.google.com','234');
/*!40000 ALTER TABLE `sessionTokenMapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokenMapper`
--

DROP TABLE IF EXISTS `tokenMapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tokenMapper` (
  `tempToken` varchar(128) NOT NULL,
  `token` varchar(128) NOT NULL,
  PRIMARY KEY (`tempToken`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokenMapper`
--

LOCK TABLES `tokenMapper` WRITE;
/*!40000 ALTER TABLE `tokenMapper` DISABLE KEYS */;
INSERT INTO `tokenMapper` VALUES ('e33631d8-dc34-44ce-be66-03ae6d6354f5','d7a218fd-e1b6-49fc-93ed-2aec9ddefb35');
/*!40000 ALTER TABLE `tokenMapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userDevices`
--

DROP TABLE IF EXISTS `userDevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userDevices` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `deviceId` mediumint(9) NOT NULL,
  `userDeviceId` varchar(128) NOT NULL,
  `deviceName` varchar(256) DEFAULT NULL,
  `structureId` varchar(256) DEFAULT NULL,
  `deviceLongName` varchar(256) DEFAULT NULL,
  `token` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userToDeviceMapping_idx` (`token`),
  CONSTRAINT `userToDeviceMapping` FOREIGN KEY (`token`) REFERENCES `users` (`token`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userDevices`
--

LOCK TABLES `userDevices` WRITE;
/*!40000 ALTER TABLE `userDevices` DISABLE KEYS */;
INSERT INTO `userDevices` VALUES (2,2,'12348765','MyAC','#2132A','My First Floor AC','123-123');
/*!40000 ALTER TABLE `userDevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `token` varchar(128) NOT NULL,
  `oauthToken` varchar(128) NOT NULL,
  `insertedTimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedTimestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`token`),
  UNIQUE KEY `id_UNIQUE` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('123-123','Sean B','2015-03-04 19:41:32','2015-03-04 19:41:32'),('321-321','Fred M','2015-03-04 19:41:32','2015-03-04 19:41:32'),('d7a218fd-e1b6-49fc-93ed-2aec9ddefb35','12345','2015-04-05 20:24:30','2015-04-05 20:24:29');
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

-- Dump completed on 2015-05-31 13:41:28
