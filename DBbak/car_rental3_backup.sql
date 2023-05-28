-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: car_rental3
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `Admin_id` int DEFAULT NULL,
  `Admin_name` varchar(30) NOT NULL,
  `Admin_password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'lkr','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(30) NOT NULL,
  `customer_password` varchar(50) DEFAULT NULL,
  `customer_Email` varchar(60) DEFAULT NULL,
  `customer_Phone` varchar(40) DEFAULT NULL,
  `customer_money` double DEFAULT NULL,
  PRIMARY KEY (`customer_name`),
  UNIQUE KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (29,'cshi2','123456','79862235@qq.com','12345678902',1241562),(2,'cshi3','123456','77885621@qq.com','12345690873',6671504),(14,'ffl','123456','132456807@163.com','59632487520',0),(9,'htb','123456','123465@qq.com','10259863251',999999),(28,'htbb','123456','165146@qq.com','12345678902',100000),(7,'lkr','123456','735690757@qq.com','18643079329',8895384),(26,'lkrrr','456789','987@qq.ccom','18686535808',0),(13,'lqr','123456','79562591@gmail.com','96385214736',200),(19,'lqrr','123456','12345678901@lqr.com','12345678901',100000000000000),(25,'ttkx','456789','AASDA@qq.com','13245678990',0),(27,'wc','456789','1043999401@qq.com','18686535808',1108),(30,'zls','123456','789@qq.com','12312312310',12356489),(15,'zs','456789','9786457312@qq.com','46132659105',0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) DEFAULT NULL,
  `message_date` date DEFAULT NULL,
  `message_content` text,
  `message_state` tinyint DEFAULT '1',
  PRIMARY KEY (`message_id`),
  KEY `message_customers_customer_name_fk` (`customer_name`),
  CONSTRAINT `message_customers_customer_name_fk` FOREIGN KEY (`customer_name`) REFERENCES `customers` (`customer_name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'lkr','2023-05-04','四只草履虫冲冲冲！！',1),(4,'ffl','2023-05-05','啊对对对 冲冲冲！！',1),(5,'lqr','2023-05-05','啊！冲冲冲！！',1),(6,'htb','2023-05-05','我饿了....',1),(8,'lqrr','2023-05-10','我是这个系统里最有钱的man！！！！',0),(12,'lkr','2023-05-14','发呆!!!',1),(19,'lkr','2023-05-25','123',0),(20,'lkr','2023-05-25','789789',0),(21,'cshi2','2023-05-26','今天天气不错',1),(22,'lkr','2023-05-26','天气不错',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(30) DEFAULT NULL,
  `Vehicle_license` varchar(30) DEFAULT NULL,
  `order_borrowdate` date DEFAULT NULL,
  `order_returndate` date DEFAULT NULL,
  `order_fee` double DEFAULT NULL,
  `order_return` tinyint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_customers_customer_name_fk` (`customer_name`),
  KEY `orders_vehicle_Vehicle_license_fk` (`Vehicle_license`),
  CONSTRAINT `orders_customers_customer_name_fk` FOREIGN KEY (`customer_name`) REFERENCES `customers` (`customer_name`) ON UPDATE CASCADE,
  CONSTRAINT `orders_vehicle_Vehicle_license_fk` FOREIGN KEY (`Vehicle_license`) REFERENCES `vehicle` (`Vehicle_license`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'lkr','A8001','2023-05-13','2023-05-13',1500,1),(5,'lkr','A8001','2023-05-14','2023-05-16',1500,1),(6,'cshi3','A9002','2023-05-14',NULL,9000,0),(7,'lkr','A9030','2023-05-14','2023-05-14',7800,1),(8,'lkr','A9030','2023-05-14','2023-05-14',17400,1),(9,'lkr','A9003','2023-05-16','2023-05-16',38500,1),(10,'lkr','A9004','2023-05-16','2023-05-16',19000,1),(11,'lkr','A9005','2023-05-16','2023-05-16',22000,1),(12,'lkr','A9006','2023-05-16','2023-05-16',43000,1),(13,'lkr','A9007','2023-05-16','2023-05-16',46000,1),(14,'lkr','A9008','2023-05-16','2023-05-16',22000,1),(15,'lkr','A9003','2023-05-16','2023-05-16',17500,1),(16,'lkr','A9004','2023-05-16','2023-05-16',19000,1),(17,'lkr','A9005','2023-05-16','2023-05-16',22000,1),(18,'lkr','A9006','2023-05-16','2023-05-16',43000,1),(19,'lkr','A9003','2023-05-17','2023-05-17',17500,1),(20,'lkr','A9004','2023-05-17','2023-05-17',19000,1),(21,'lkr','A9005','2023-05-17','2023-05-17',22000,1),(22,'lkr','A9043','2023-05-17','2023-05-20',25000,1),(23,'lkr','A9030','2023-05-20',NULL,22600,0),(24,'lkr','A9020','2023-05-23',NULL,20500,0),(25,'lkr','A9021','2023-05-25',NULL,37000,0),(26,'cshi2','A9013','2023-05-26',NULL,52000,0),(27,'cshi2','A9006','2023-05-26','2023-05-26',67000,1),(28,'lkr','A9027','2023-05-26',NULL,19500,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `readable_messages`
--

DROP TABLE IF EXISTS `readable_messages`;
/*!50001 DROP VIEW IF EXISTS `readable_messages`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `readable_messages` AS SELECT 
 1 AS `message_id`,
 1 AS `customer_name`,
 1 AS `message_date`,
 1 AS `message_content`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `rented_vehicles`
--

DROP TABLE IF EXISTS `rented_vehicles`;
/*!50001 DROP VIEW IF EXISTS `rented_vehicles`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `rented_vehicles` AS SELECT 
 1 AS `Vehicle_license`,
 1 AS `Vehicle_name`,
 1 AS `order_borrowdate`,
 1 AS `order_returndate`,
 1 AS `order_fee`,
 1 AS `order_return`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `Vehicle_id` int NOT NULL AUTO_INCREMENT,
  `Vehicle_license` varchar(30) NOT NULL,
  `Vehicle_name` varchar(40) DEFAULT NULL,
  `Vehicle_rent` double DEFAULT NULL,
  `Vehicle_state` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`Vehicle_license`),
  UNIQUE KEY `vehicle_pk` (`Vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'A8001','大众2',600,0),(7,'A9002','奔驰 A级',3000,1),(8,'A9003','奔驰 B级',3500,0),(9,'A9004','奔驰 C级',4000,0),(10,'A9005','奔驰 E级',5000,0),(11,'A9006','奔驰 G级',12000,0),(12,'A9007','奔驰 S级',13000,0),(13,'A9008','宝马 X4',5000,0),(14,'A9009','宝马 X5',8000,0),(15,'A9010','宝马 X6',9000,0),(16,'A9011','宝马 X7',10000,0),(17,'A9012','林肯 领航员',7500,0),(18,'A9013','林肯 航海家',9000,1),(19,'A9014','林肯 冒险家',9000,0),(20,'A9015','别克 君越',2000,0),(21,'A9016','别克 君威',2500,0),(22,'A9017','别克 威朗',3000,0),(23,'A9018','别克 英朗',3500,0),(24,'A9019','别克 凯越',4000,0),(25,'A9020','沃尔沃 S60',4500,1),(26,'A9021','沃尔沃 S90',6000,1),(27,'A9022','大众 凌度',2500,0),(28,'A9023','大众 朗逸',2600,0),(29,'A9024','本田 缤智',4100,0),(30,'A9025','本田 致在',4200,0),(31,'A9026','本田 冠道',4200,0),(32,'A9027','欧拉 好猫',2500,1),(33,'A9028','小鹏 G6',2400,0),(34,'A9029','小鹏 G9',2500,0),(35,'A9030','小鹏 P7',2600,1),(36,'A9031','问界 M5',2500,0),(37,'A9032','问界 M7',2800,0),(38,'A9033','丰田 皇冠',2600,0),(39,'A9034','丰田 雷凌',2900,0),(40,'A9035','丰田 致享',3500,0),(41,'A9036','比亚迪 汉',3000,0),(42,'A9037','比亚迪 唐',3200,0),(43,'A9038','比亚迪 秦',3400,0),(44,'A9039','比亚迪 宋',3800,0),(45,'A9040','比亚迪 元',4000,0),(47,'A9041','新来的小车',1100,0),(48,'A9042','新来的小车2号',600,0),(49,'A9043','新来的小车4号',6000,0),(50,'A9044','新来的3号',800,0),(51,'A9045','欣欣小车',823,0);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `readable_messages`
--

/*!50001 DROP VIEW IF EXISTS `readable_messages`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `readable_messages` AS select `m`.`message_id` AS `message_id`,`m`.`customer_name` AS `customer_name`,`m`.`message_date` AS `message_date`,`m`.`message_content` AS `message_content` from `message` `m` where (`m`.`message_state` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rented_vehicles`
--

/*!50001 DROP VIEW IF EXISTS `rented_vehicles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rented_vehicles` AS select `v`.`Vehicle_license` AS `Vehicle_license`,`v`.`Vehicle_name` AS `Vehicle_name`,`o`.`order_borrowdate` AS `order_borrowdate`,`o`.`order_returndate` AS `order_returndate`,`o`.`order_fee` AS `order_fee`,`o`.`order_return` AS `order_return` from (`vehicle` `v` join `orders` `o` on((`v`.`Vehicle_license` = `o`.`Vehicle_license`))) where (`o`.`order_return` = 0) */;
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

-- Dump completed on 2023-05-28 19:01:31
