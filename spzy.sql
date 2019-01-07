-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: video
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(255) NOT NULL,
  `activityDate` datetime NOT NULL,
  `upUser` varchar(20) NOT NULL,
  `videoSize` varchar(45) DEFAULT NULL,
  `videoFormat` varchar(45) DEFAULT NULL,
  `videoUrl` varchar(255) DEFAULT NULL,
  `videoName` varchar(255) DEFAULT NULL,
  `activityClass` varchar(45) NOT NULL,
  PRIMARY KEY (`aId`),
  KEY `name_idx` (`upUser`),
  CONSTRAINT `name` FOREIGN KEY (`upUser`) REFERENCES `user` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'qqq','2018-10-01 00:00:00','admin','20','mp4','/ssss','tt',''),(2,'www','2014-05-22 00:00:00','admin','20','mp4','/ssss','rr','');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键自增',
  `userName` varchar(20) NOT NULL COMMENT '用户名',
  `userPass` varchar(100) NOT NULL COMMENT '用户密码',
  `userPhone` varchar(20) DEFAULT NULL COMMENT '用户联系电话',
  `userPower` int(2) NOT NULL COMMENT '用户权限 0普通用户 1管理员',
  `userDepartment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `username_idx` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','202cb962ac59075b964b07152d234b70','18716006635',1,'管理员'),(2,'陈宁','ceec869b5c3a42fb0768b85223719d1f','13241006465',0,'视频拓展部'),(3,'白伟','fd80ee3bac692860613a5a30a87de5ba','13716067527',0,'艺术新闻部'),(4,'李世宇','88539e20706fe68e58083581067212c9','18716006635',0,'技术管理处');
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

-- Dump completed on 2019-01-07 10:29:14
