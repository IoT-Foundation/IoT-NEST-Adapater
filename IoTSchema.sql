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
  `valueType` varchar(256) NOT NULL DEFAULT 'String',
  `deviceId` mediumint(9) NOT NULL,
  `partialURL` varchar(245) DEFAULT NULL,
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
INSERT INTO `actions` VALUES (1,'Set Away','Sets the Nest thermostat to Away setting.','away','String (home, away, auto-away, unknown)',0,'/structures/{0}'),(2,'Set Temprature','Sets the temprature of the thermostat.','target_temperature_f','double (63-88)',0,'/devices/thermostats/{0}'),(3,'Set ETA','Sets the Estimated time of Arival.  Time in milliseconds after January 1, 1970, 0:00:00 GMT.','estimated_arrival_window_begin, estimated_arrival_window_end','String (YYYY-MM-DDTHh:Mm:Ss.mmmZ), String (YYYY-MM-DDTHh:Mm:Ss.mmmZ)',0,'/structures/{0}/eta'),(4,'Set HVAC Mode','Sets the thermostat mode','hvac_mode','String (heat, cool, heat-cool, off)',0,'/devices/thermostats/{0}');
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
  `id` mediumint(9) unsigned NOT NULL,
  `hrefImageUrl` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES ('thermostats',NULL,0,NULL),('smoke_co_alarms',NULL,1,NULL);
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
  `state` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `sessionId_UNIQUE` (`sessionId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessionTokenMapper`
--

LOCK TABLES `sessionTokenMapper` WRITE;
/*!40000 ALTER TABLE `sessionTokenMapper` DISABLE KEYS */;
INSERT INTO `sessionTokenMapper` VALUES ('Starbucks123',46,'https://www.starbucks.com','ON'),('71cd1cc5-b811-4338-a5e3-a5ebc54b99f5',47,'http://localhost:8079/DeviceCloud/serviceadaptors/accesstoken','eyJhZGFwdG9ySUQiOiJuZXN0IiwidXVpZCI6IjE0NDAwNjUxOTEiLCJkZXZpY2VJbmZvIjp7ImJpbmRpbmdfdXJsIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwODBcL0lvVFwvYXBpXC8iLCJjcmVkZW50aWFsIjoiMTIzNDU2IiwiYWRhcHRvcklEIjoibmVzdCIsIm5hbWUiOiJuZXN0IiwiZGV2aWNlSUQiOiJkYWM4Y2JiNi0wMzAxLTQyOTktYWM1Ni1iYWI3YzNjM2Q4M2QifX0='),('71cd1cc5-b811-4338-a5e3-a5ebc54b99f4',49,'http://localhost:8079/DeviceCloud/serviceadaptors/accesstoken','eyJhZGFwdG9ySUQiOiJuZXN0IiwidXVpZCI6IjE0NDAwNjUxOTEiLCJkZXZpY2VJbmZvIjp7ImJpbmRpbmdfdXJsIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwODBcL0lvVFwvYXBpXC8iLCJjcmVkZW50aWFsIjoiMTIzNDU2IiwiYWRhcHRvcklEIjoibmVzdCIsIm5hbWUiOiJuZXN0IiwiZGV2aWNlSUQiOiJkYWM4Y2JiNi0wMzAxLTQyOTktYWM1Ni1iYWI3YzNjM2Q4M2QifX0='),('71cd1cc5-b811-4338-a5e3-a5ebc54b99f1',50,'http://localhost:8079/DeviceCloud/serviceadaptors/accesstoken','eyJhZGFwdG9ySUQiOiJuZXN0IiwidXVpZCI6IjE0NDAwNjUxOTEiLCJkZXZpY2VJbmZvIjp7ImJpbmRpbmdfdXJsIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwODBcL0lvVFwvYXBpXC8iLCJjcmVkZW50aWFsIjoiMTIzNDU2IiwiYWRhcHRvcklEIjoibmVzdCIsIm5hbWUiOiJuZXN0IiwiZGV2aWNlSUQiOiJkYWM4Y2JiNi0wMzAxLTQyOTktYWM1Ni1iYWI3YzNjM2Q4M2QifX0=');
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
INSERT INTO `tokenMapper` VALUES ('12b3d5b4-38f3-4487-bc44-e286c5e17335','3596e4d9-f380-4119-819c-b41bd0b41745'),('932dcecd-fed8-45b8-9070-cba296afd48f','ac964718-1fca-4398-8805-a797ca9ea2c4'),('9cfd97a0-c9fa-447e-a8f4-f40d620f7714','e4709dd6-d7fe-4880-ad92-5c651793a2bc'),('db95808e-2f35-4ceb-bd9e-1b68b88e6572','ad4502d9-6727-4ff9-9b5d-18312c56d3d6');
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
  `deviceId` varchar(128) NOT NULL,
  `userDeviceId` mediumint(9) NOT NULL,
  `deviceName` varchar(256) DEFAULT NULL,
  `structureId` varchar(256) DEFAULT NULL,
  `deviceLongName` varchar(256) DEFAULT NULL,
  `token` varchar(128) NOT NULL,
  `structureName` varchar(256) DEFAULT NULL,
  `postalCode` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `structureIdAndDeviceIdMapping` (`deviceId`,`structureId`),
  KEY `userToDeviceMapping_idx` (`token`),
  CONSTRAINT `userToDeviceMapping` FOREIGN KEY (`token`) REFERENCES `users` (`token`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userDevices`
--

LOCK TABLES `userDevices` WRITE;
/*!40000 ALTER TABLE `userDevices` DISABLE KEYS */;
INSERT INTO `userDevices` VALUES (2,'asbecdiefag',1,'MyAC','#2132A','My First Floor AC','123-123','Home','78703'),(10,'cAJvZQ5lhBxrPsSGdaYhEl2jVGzz3wgS',1,'Office','wD2JoyPtoodpRKt5kZ_i08294COoewkQ02LxcCiQhJeZLn-1rtWcFQ','Office Nest Protect','ac964718-1fca-4398-8805-a797ca9ea2c4','Home','78727'),(11,'_fEhAd9g8CaC0BtoVa-kWV2jVGzz3wgS',0,'Kitchen','wD2JoyPtoodpRKt5kZ_i08294COoewkQ02LxcCiQhJeZLn-1rtWcFQ','Kitchen Thermostat','ac964718-1fca-4398-8805-a797ca9ea2c4','Home','78727');
/*!40000 ALTER TABLE `userDevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `token` varchar(256) NOT NULL,
  `oauthToken` varchar(256) NOT NULL,
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
INSERT INTO `users` VALUES ('123-123','Sean_B','2015-03-04 19:41:32','2015-03-04 19:41:32'),('321-321','Fred_M','2015-03-04 19:41:32','2015-03-04 19:41:32'),('3596e4d9-f380-4119-819c-b41bd0b41745','c.JYpWX9tVA8QWko84T1TGD4VT4OIIaRyPS6GVV7TJNjK384IMmb4Z6lGS9Vq3SZ2Av7DPimcZ0ONAWCrSZe9KjCapT5D6yiBrI2Ydl2Aop68TWjQUFzTwYabkKRAjYyn2rXy1KMaURg2wjSYl','2015-12-22 22:19:34','2015-12-22 22:19:33'),('ac964718-1fca-4398-8805-a797ca9ea2c4','c.PJdbzIaxq1wj83IclRHxEsT3MmR2Rsmyhdya3xYeCDHZIEtFzYExMiK5mWC5clWmHOApDmKAVnk1pch9SRiRyRXiMo7cES772QTDhGjy7ek3d0kMto3BVJkLozadSTnLLk2PXLKiB3KYenVa','2015-09-07 17:36:15','2015-09-07 17:36:15'),('ad4502d9-6727-4ff9-9b5d-18312c56d3d6','c.3wGSqZReV5yAoRWyylkfZWnc9J7gaOzO2Oz1bSi2l8UO2cizCvcAOCOt9LiIDb3GomzI0tdINN3wXov310y9oDi284T8BdiH4wKBULU5FTSfditz1PTUweM3w98SY68zkirnxuhU2XK9KLHg','2015-12-22 22:12:05','2015-12-22 22:12:05'),('e4709dd6-d7fe-4880-ad92-5c651793a2bc','c.HeusiFAos4EbCg7EU9gSyq88tZHCRjYurCsg3NdN7t1VwSjNjI6k3DSeuk9i8HseRtGZuJypPhPx3Z1ElGFzgg13aBoTBGDW9lPoC2nS7kaclulPyh10n97YkfXiEzCtUMVjMxGZCx1lyeuf','2015-12-22 22:14:10','2015-12-22 22:14:10');
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

-- Dump completed on 2016-05-17  9:38:18
