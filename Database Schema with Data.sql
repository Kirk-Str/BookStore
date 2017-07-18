CREATE DATABASE  IF NOT EXISTS `ibook` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ibook`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ibook
-- ------------------------------------------------------
-- Server version	5.5.8

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ShortTitle` varchar(500) DEFAULT NULL,
  `Title` varchar(500) DEFAULT NULL,
  `ISBN` varchar(100) DEFAULT NULL,
  `CategoryId` int(11) DEFAULT NULL,
  `Author` varchar(100) DEFAULT NULL,
  `Pages` varchar(45) DEFAULT NULL,
  `Publisher` varchar(100) DEFAULT NULL,
  `PublishedDate` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CatId_idx` (`CategoryId`),
  CONSTRAINT `CatId` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (108,'Every Girl is the End of the World for Me ','Every Girl is the End of the World for Me ','978-1-891830-77-8',3,'Jeffrey Brown','104',NULL,'1999'),(109,'The God Delusion','The God Delusion','0-618-68000-4',4,'Richard Dawkins','500',NULL,'2008'),(110,'Elon Musk','Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future','978-0062301239',1,'Ashlee Vance','400',NULL,'2015'),(111,'The Magnolia Story','The Magnolia Story','9780718079185',3,'Chip and Joanna','208',NULL,'2016'),(112,'Born to Run','Born to Run','9781501141515',1,'Bruce Springsteen','528',NULL,'2016'),(113,'Our Revolution','Our Revolution: A Future to Believe In','9781250132925',1,'Bernie Sanders','464',NULL,'2016'),(114,'Five Presidents','Five Presidents: My Extraordinary Journey with Eisenhower, Kennedy, Johnson, Nixon, and Ford','9781476794143',1,'Clint Hill, Lisa McCubbin','464',NULL,'2017'),(115,'Prince Charles','Prince Charles: The Passions and Paradoxes of an Improbable Life','9781400067909',1,'Sally Bedell Smith','640',NULL,'2017'),(116,'Endurance','Endurance: My Year in Space','9781524731595',1,'Scott Kelly','320',NULL,'2017'),(120,'When All The Girls Have Gone','When All The Girls Have Gone','9780399174490',2,'Jayne Ann Krentz','510',NULL,'2016'),(121,'I’ll Take You There','I’ll Take You There: A Novel','9780062656285',2,'Harper','240',NULL,'2016'),(122,'Victoria','Victoria: The Queen: An Intimate Biography of the Woman Who Ruled an Empire','9781400069880',9,'Julia Baird','300',NULL,'2016'),(123,'A Little Life','A Little Life','9780804172707',15,'Hanya Yanagihara','410','','2016'),(124,'Honky Tonk Samurai','Honky Tonk Samurai','9780316329408',15,'Joe R. Lansdale','350','','2016'),(125,'Morning Star','Morning Star','9780345539847',15,'Pierce Brown','240',NULL,'2016'),(126,'Just Another Jihadi Jane','Just Another Jihadi Jane','9781566560672',3,'Tabish Khair','176',NULL,'2016'),(127,'Rasputin','Rasputin - Faith, Power, and the Twilight of the Romanovs','9780374240844',16,'Douglas Smith','848',NULL,'2016'),(128,'Heartless','Heartless','9781250044655',1,'Marissa Meyer','464',NULL,'2016'),(129,'Valiant Gentlemen','Valiant Gentlemen','9780802125453',16,'Sabina Murray','496',NULL,'2016'),(130,'Thus Bad Begins','Thus Bad Begins','9781101946084',2,'Javier Marias (Author), Margaret Jull Costa (Translator)','464',NULL,'2016'),(142,'Clean Code','A Handbook of Agile Software Craftsmanship','9780132350884',19,'Robert C. Martin','431','Prentice Hall PTR','Aug. 11th, 2008'),(143,'On Killing','The Psychological Cost of Learning to Kill in War and Society - See more at: http://www.betterworldbooks.com/On-Killing--The-Psychological-Cost-of-Learning-to-Kill-in-War-and-Society-id-9780316040938.aspx#sthash.PLkA8g1e.dpuf','9780316040938',19,'Dave Grossman','377','Back Bay Books','Jun. 1st, 2009');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Sci-Fi'),(2,'Novel'),(3,'Religious'),(4,'Good Story'),(9,'Biography'),(10,'Romantic'),(11,'Children'),(12,'Night Time Story'),(13,'Religious'),(14,'Biology'),(15,'Fiction'),(16,'History'),(17,'Science'),(18,'Current Affairs'),(19,'Technology');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BookId` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `UpdateType` bit(1) DEFAULT NULL,
  `UpdatedDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (24,142,90,'','2016-12-02'),(25,123,50,'','2016-12-02'),(26,112,80,'','2016-12-02'),(27,110,150,'','2016-12-02'),(28,116,200,'','2016-12-02'),(29,108,500,'','2016-12-02'),(30,114,400,'','2016-12-02'),(31,128,200,'','2016-12-02'),(32,124,250,'','2016-12-02'),(33,121,40,'','2016-12-02'),(34,126,60,'','2016-12-02'),(35,125,200,'','2016-12-02'),(36,120,100,'','2016-12-02'),(37,108,20,'','2016-12-02'),(38,108,300,'\0','2016-12-09'),(39,142,10,'\0','2016-12-09'),(40,110,40,'\0','2016-12-09'),(41,110,1,'\0','2016-12-09'),(42,123,2,'\0','2016-12-09'),(43,110,1,'\0','2016-12-09');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Firstname` varchar(100) DEFAULT NULL,
  `Lastname` varchar(100) DEFAULT NULL,
  `UserType` int(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'Admin','[a, d, m, i, n]','Admin','User',1),(14,'Stock','[s, t, o, c, k]','Stock Controller','User',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-09 18:43:12
