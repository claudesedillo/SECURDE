CREATE DATABASE  IF NOT EXISTS `indigo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `indigo`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: indigo
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `email` varchar(45) NOT NULL,
  `hashedpassword` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `authorid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`authorid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Briane Samson'),(2,'Nathalie Rose Lim-Cheng'),(3,'Courtney Ngo'),(4,'Adolf Hitler'),(5,'Alex Agno'),(6,'Stephen Hawking'),(7,'Jess Gano'),(8,'Claude Sedillo'),(9,'Gary Non'),(10,'Luigi Acorda'),(11,'Patrick Tobias'),(12,'Elon Musk'),(13,'Stephen Hawking');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(60) DEFAULT NULL,
  `ISBN` varchar(13) DEFAULT NULL,
  `Genre` varchar(45) DEFAULT NULL,
  `Format` varchar(45) DEFAULT NULL,
  `Published` date DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `stocklevel` int(11) DEFAULT NULL,
  `authorid` int(11) DEFAULT NULL,
  `publisherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  KEY `author_name_idx` (`authorid`),
  KEY `publisher_id_idx` (`publisherid`),
  CONSTRAINT `author_id` FOREIGN KEY (`authorid`) REFERENCES `authors` (`authorid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `publisher_id` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Software Design Patterns','0000000011451','Computers','Paperback','2016-05-16',200,20,1,1),(2,'COMPRO2 For Dummies','0000000001454','Computers','Paperback','2014-03-12',250,30,2,1),(3,'HTML, CSS, JS and Servlets','0000000018896','Computers','Paperback','2015-05-11',300,30,3,1),(4,'Mein Kampf','000000001135','Biography and Memoir','Hardbound','1925-11-22',700,20,4,2),(5,'Hunting Jews','0000001251421','Education','Hardbound','1930-11-30',500,50,4,2),(6,'Algorithms for Dummies','1111221454215','Computers','Hardbound','1997-03-09',420,50,5,1),(7,'Biology for dummies','5581425947114','Science and Nature','Hardbound','1994-02-14',600,50,6,3),(8,'The Science of Black Holes','0000001121421','Science and Nature','Hardbound','1948-02-19',700,50,6,3),(9,'How to get to mars in 30 days','0000011215214','Science and Nature','Paperback','2016-05-29',700,50,12,4),(10,'Why (and how) I launched a car to space','1100000885471','Science and Nature','Paperback','2018-01-29',800,50,12,4),(11,'A tourist\'s guide to Alberta','0000011251426','Travel','Hardbound','2011-03-25',755,50,7,5),(12,'A tourists guide to Ontario','0002258474156','Travel','Hardbound','2014-03-19',855,50,8,5),(13,'How to survive in Afghanistan','1332598851001','Travel','Paperback','2009-11-14',500,50,9,6),(14,'Courting for dummies','1000584466952','Fiction and literature','Paperback','2008-04-27',250,50,10,2),(15,'Womens rights','1000044539587','Fiction and literature','Paperback','1940-08-19',600,50,7,2),(16,'Naruto','1000365125899','Graphic Novels and Manga','Paperback','1998-01-21',350,50,10,2),(17,'Womens rights in the future','1000447852582','SciFi and Fantasy','Paperback','2018-01-20',400,50,7,2),(18,'How to be a god','1255563300001','Business and Finance','Hardbound','2017-11-23',450,50,11,7),(19,'How to get rich using bitcoin','1000025964832','Business and Finance','Hardbound','2018-02-01',700,50,11,7);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concerns`
--

DROP TABLE IF EXISTS `concerns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concerns` (
  `ticketnumber` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `concern` varchar(45) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ticketnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concerns`
--

LOCK TABLES `concerns` WRITE;
/*!40000 ALTER TABLE `concerns` DISABLE KEYS */;
/*!40000 ALTER TABLE `concerns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `email` varchar(45) NOT NULL,
  `hashedpassword` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `securityquestion` varchar(100) DEFAULT NULL,
  `securityanswer` varchar(100) DEFAULT NULL,
  `streetaddress` varchar(45) DEFAULT NULL,
  `postalcode` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('claudesedillo@gmail.com','claude',NULL,NULL,'what subject did you enjoy the most in college?','SOFENGG',NULL,0,NULL,NULL,NULL),('garynon@gmail.com','gary',NULL,NULL,'what subject did you enjoy the most in college?','CSETHIC',NULL,0,NULL,NULL,NULL),('jessgano@gmail.com','jess',NULL,NULL,'what\'s your dream travel destination?','Korea',NULL,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlist`
--

DROP TABLE IF EXISTS `orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderlist` (
  `orderid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`bookid`),
  KEY `list_bookid_idx` (`bookid`),
  CONSTRAINT `list_bookid` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `list_orderid` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlist`
--

LOCK TABLES `orderlist` WRITE;
/*!40000 ALTER TABLE `orderlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `orderdate` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  `datecompleted` timestamp NULL DEFAULT NULL,
  `streetaddress` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `postalcode` int(11) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderstatus`
--

DROP TABLE IF EXISTS `orderstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderstatus` (
  `orderid` int(11) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  CONSTRAINT `status_orderid` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderstatus`
--

LOCK TABLES `orderstatus` WRITE;
/*!40000 ALTER TABLE `orderstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `publisherid` int(11) NOT NULL AUTO_INCREMENT,
  `publishername` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`publisherid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'College of Computer Studies'),(2,'Penguin Publishing'),(3,'Rational People Inc'),(4,'SpaceX'),(5,'Indigo Chapters'),(6,'Al Quaeda Inc'),(7,'Gary V Publishing');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `bookid` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  KEY `cart_email_idx` (`email`),
  CONSTRAINT `cart_email` FOREIGN KEY (`email`) REFERENCES `customers` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'indigo'
--

--
-- Dumping routines for database 'indigo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-24 13:17:31
