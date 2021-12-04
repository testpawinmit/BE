-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: pawin-db
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergy`
--

DROP TABLE IF EXISTS `allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergy` (
  `ALLERGY_ID` int(11) NOT NULL,
  `ALLERGY_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `ALLERGY_NAME` varchar(45) DEFAULT NULL,
  `ALLERGY_TREATMENT` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ALLERGY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergy`
--

LOCK TABLES `allergy` WRITE;
/*!40000 ALTER TABLE `allergy` DISABLE KEYS */;
INSERT INTO `allergy` VALUES (1,'ALR-1','PET-1','rash','cream','super','2021-09-25 05:26:58',NULL,NULL);
/*!40000 ALTER TABLE `allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_type`
--

DROP TABLE IF EXISTS `app_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_type` (
  `APP_TYPE_ID` int(11) NOT NULL,
  `APP_TYPE_CODE` varchar(30) NOT NULL,
  `APP_TYPE_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`APP_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_type`
--

LOCK TABLES `app_type` WRITE;
/*!40000 ALTER TABLE `app_type` DISABLE KEYS */;
INSERT INTO `app_type` VALUES (1,'APP-1','WEB');
/*!40000 ALTER TABLE `app_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `APP_ID` int(11) NOT NULL,
  `APP_CODE` varchar(45) NOT NULL,
  `SERVICE_CAT_CODE` varchar(45) NOT NULL,
  `SERVICE_CODE` varchar(45) NOT NULL,
  `CHECK_IN_DATE` date DEFAULT NULL,
  `CHECK_IN_TIME` varchar(45) DEFAULT NULL,
  `CHECK_OUT_DATE` date DEFAULT NULL,
  `CHECK_OUT_TIME` varchar(45) DEFAULT NULL,
  `ROOM_CODE` varchar(45) DEFAULT NULL,
  `EMP_CODE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `TOTAL_PRICE` varchar(45) DEFAULT NULL,
  `PAYMENT_TYPE` varchar(45) DEFAULT NULL,
  `PORTAL_STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'APPO-1','Boarding','SE-1','2021-09-25','21:00','2021-09-28',NULL,'Winter',NULL,'super','2021-09-25 05:31:31',NULL,NULL,'369.0','Cash','CHECK_OUT'),(2,'APPO-2','Daycare','SE-5','2021-09-25','10:00','2021-09-25',NULL,'Winter',NULL,'super','2021-09-25 09:49:26',NULL,NULL,'125.0','Cash','CHECK_IN');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breed`
--

DROP TABLE IF EXISTS `breed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `breed` (
  `BREED_ID` int(11) NOT NULL,
  `BREED_CODE` varchar(45) NOT NULL,
  `BREED_NAME` varchar(45) NOT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`BREED_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breed`
--

LOCK TABLES `breed` WRITE;
/*!40000 ALTER TABLE `breed` DISABLE KEYS */;
INSERT INTO `breed` VALUES (1,'BR-1','BullDog','super','2021-06-20 12:11:01','super','2021-09-20 11:52:35'),(2,'BR-2','Labrador Retriever ','super','2021-06-20 12:11:14',NULL,NULL),(3,'BR-3','Husky','super','2021-06-24 14:31:17',NULL,NULL),(4,'BR-4','German Shepherd ','super','2021-06-24 14:44:19',NULL,NULL),(5,'BR-5','Cat','super','2021-06-24 14:44:19',NULL,NULL),(6,'BR-6','Siberian Husky','super','2021-06-24 14:44:19',NULL,NULL),(7,'BR-7','Shiba Inu','super','2021-06-20 12:11:14',NULL,NULL),(8,'BR-8','Shitzu','super','2021-09-25 05:42:14','super','2021-09-25 05:42:42');
/*!40000 ALTER TABLE `breed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `COLOR_ID` int(11) NOT NULL,
  `COLOR_CODE` varchar(45) NOT NULL,
  `COLOR_NAME` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`COLOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'CO-1','Orange','super','2021-06-21 22:19:54','super','2021-09-20 11:52:59'),(2,'CO-2','Blue','super','2021-06-21 22:19:57',NULL,NULL),(3,'CO-3','White','super','2021-06-21 22:19:57',NULL,NULL),(4,'CO-4','Black','super','2021-06-21 22:19:57',NULL,NULL),(5,'CO-5','Black & White','super','2021-06-21 22:19:57',NULL,NULL),(6,'CO-6','Brown & Black','super','2021-09-25 01:18:09','super','2021-09-25 01:18:26');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CUST_ID` int(11) NOT NULL,
  `CUST_CODE` varchar(45) NOT NULL,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `NIC` varchar(45) DEFAULT NULL,
  `PHONE_NO` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(45) DEFAULT NULL,
  `LOCATION` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`CUST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (7,'CUS-7','Nethmini','Senanayake','test@test.com','912345655V','0712342728','4033  Leroy Lane','Kegalle','super','2021-06-11 21:00:18',NULL,NULL),(23,'CUS-23','Nethmini','Senanayake','nethminisenanayake@gmail.com','915293255V','0702491107','Main Street','Colombo','super','2021-06-12 13:47:54',NULL,NULL),(26,'CUS-26','Nethmini','Senanayake','nethmini@gmail.com','912345466V','0702491107','Main Street','Colombo','super','2021-06-12 18:16:56',NULL,NULL),(60,'CUS-60','NethminiS','Senanayake','nethminisenanayake@gmail.com','915293255V','0701237789','Main Street','Kegalle','super','2021-06-15 22:42:17','super','2021-09-24 12:52:41'),(61,'CUS-61','Nike12','Stock','nethminisenanayake@gmail.com','913334312V','0345676123','No 25, Main Street,Kegalle','Kegalle','super','2021-09-23 22:27:38','super','2021-09-25 10:22:09');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_retail`
--

DROP TABLE IF EXISTS `customer_retail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_retail` (
  `CUST_RETAIL_ID` int(11) NOT NULL,
  `CUST_RETAIL_CODE` varchar(45) NOT NULL,
  `CUST_CODE` varchar(45) DEFAULT NULL,
  `RETAIL_CODE` varchar(45) DEFAULT NULL,
  `QUANTITY` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `TOTAL_PRICE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CUST_RETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_retail`
--

LOCK TABLES `customer_retail` WRITE;
/*!40000 ALTER TABLE `customer_retail` DISABLE KEYS */;
INSERT INTO `customer_retail` VALUES (1,'CUSTRETAIL-1','CUS-61','RE-8','1','super','2021-09-25 05:36:13',NULL,NULL,'450'),(2,'CUSTRETAIL-2','CUS-61','RE-14','1','super','2021-09-25 05:36:59',NULL,NULL,'100'),(3,'CUSTRETAIL-3','CUS-61','RE-8','1','super','2021-09-25 05:37:10',NULL,NULL,'450'),(4,'CUSTRETAIL-4','CUS-61','RE-1','1','super','2021-09-25 09:54:23',NULL,NULL,'1000'),(5,'CUSTRETAIL-5','CUS-61','RE-3','1','super','2021-09-25 09:54:28',NULL,NULL,'56');
/*!40000 ALTER TABLE `customer_retail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dietary`
--

DROP TABLE IF EXISTS `dietary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dietary` (
  `DIET_ID` int(11) NOT NULL,
  `DIET_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `OWN_FOOD` varchar(45) DEFAULT NULL,
  `FEED_TIME_MORNING` varchar(45) DEFAULT NULL,
  `FEED_ALONE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `FEED_TIME_AFTERNOON` varchar(45) DEFAULT NULL,
  `FEED_TIME_EVENING` varchar(45) DEFAULT NULL,
  `FOOD_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`DIET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietary`
--

LOCK TABLES `dietary` WRITE;
/*!40000 ALTER TABLE `dietary` DISABLE KEYS */;
INSERT INTO `dietary` VALUES (1,'DIET-1','PET-1','Yes','Morning','Yes','super','2021-09-25 05:28:56',NULL,NULL,'Afternoon','Evening','Milk');
/*!40000 ALTER TABLE `dietary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email` (
  `EMAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PET_CODE` varchar(45) DEFAULT NULL,
  `EMAIL_TYPE` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  PRIMARY KEY (`EMAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'PET-1','DOB','2021-09-20 22:48:28','System'),(2,'PET-21','DOB','2021-09-20 22:48:33','System'),(3,'PET-33','DOB','2021-09-20 22:48:37','System'),(4,'PET-34','DOB','2021-09-20 22:48:42','System'),(5,'PET-30','VACC_2021-09-18 00:00:00.0','2021-09-20 23:22:59','System'),(6,'PET-33','VACC_2021-09-22 00:00:00.0','2021-09-20 23:23:04','System'),(7,'PET-30','VACC_2021-09-23 00:00:00.0','2021-09-21 00:00:20','System'),(8,'PET-33','VACC_2021-09-26 00:00:00.0','2021-09-24 11:55:17','System'),(9,'PET-40','DOB','2021-09-24 12:11:33','System'),(10,'PET-29','VACC_2021-09-26 00:00:00.0','2021-09-24 12:13:39','System'),(11,'PET-41','DOB','2021-09-24 12:17:50','System'),(12,'PET-39','VACC_2021-09-27 00:00:00.0','2021-09-25 01:06:50','System'),(13,'PET-43','DOB','2021-09-25 01:07:56','System'),(14,'PET-42','VACC_2021-09-27 00:00:00.0','2021-09-25 05:02:04','System'),(15,'PET-45','DOB','2021-09-25 05:04:11','System'),(16,'PET-1','VACC_2021-09-27 00:00:00.0','2021-09-25 05:29:23','System'),(17,'PET-2','DOB','2021-09-25 09:51:38','System'),(18,'PET-2','VACC_2021-09-27 00:00:00.0','2021-09-25 09:52:44','System');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMP_ID` int(11) NOT NULL,
  `EMP_CODE` varchar(30) NOT NULL,
  `EMP_FIRST_NAME` varchar(30) DEFAULT NULL,
  `EMP_LAST_NAME` varchar(30) DEFAULT NULL,
  `EMP_FULL_NAME` varchar(100) DEFAULT NULL,
  `EMP_EMAIL` varchar(50) DEFAULT NULL,
  `EMP_NO` varchar(30) DEFAULT NULL,
  `IS_ACTIVE` int(11) DEFAULT NULL,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(30) DEFAULT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `UPDATED_BY` varchar(30) DEFAULT NULL,
  `EMP_SEX` varchar(30) DEFAULT NULL,
  `EMP_CIVIL_STATUS` varchar(30) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `EMP_NIC` varchar(30) DEFAULT NULL,
  `EPF_NO` varchar(30) DEFAULT NULL,
  `DATE_OF_JOIN` date DEFAULT NULL,
  `MACHINE_STATUS` varchar(30) DEFAULT NULL,
  `MACHINE_CODE` varchar(30) DEFAULT NULL,
  `CONFIRM_DATE` date DEFAULT NULL,
  `EMP_STATUS` varchar(30) DEFAULT NULL,
  `DATE_LEFT` date DEFAULT NULL,
  `ADDR_LINE_1` varchar(100) DEFAULT NULL,
  `ADDR_LINE_2` varchar(100) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `POSTAL_CODE` varchar(30) DEFAULT NULL,
  `TEL_RESIDENCE` varchar(15) DEFAULT NULL,
  `TEL_MOBILE` varchar(15) DEFAULT NULL,
  `BANK_ACNT_NO` varchar(30) DEFAULT NULL,
  `EMP_COMPANY` varchar(50) DEFAULT NULL,
  `EMP_BRANCH` varchar(30) DEFAULT NULL,
  `EMP_DEPARTMENT` varchar(30) DEFAULT NULL,
  `EMP_SECTION` varchar(30) DEFAULT NULL,
  `EMP_GRADE` varchar(30) DEFAULT NULL,
  `EMP_GROUP` varchar(30) DEFAULT NULL,
  `EMP_JOB_TITLE` varchar(30) DEFAULT NULL,
  `EMP_NATIONALITY` varchar(30) DEFAULT NULL,
  `EMP_RELIGION` varchar(30) DEFAULT NULL,
  `FIXED_SHIFT` varchar(30) DEFAULT NULL,
  `SHIFT_GROUP` varchar(30) DEFAULT NULL,
  `CALENDAR_TYPE_CODE` varchar(30) DEFAULT NULL,
  `OT_TYPE` varchar(30) DEFAULT NULL,
  `LEAVE_TYPE` varchar(30) DEFAULT NULL,
  `PROFILE_IMG_NAME` varchar(50) DEFAULT NULL,
  `TITLE` varchar(5) DEFAULT NULL,
  `EMP_CONFIRMATION` int(11) DEFAULT NULL,
  `EMP_FUTURE_CONFIRM_DATE` date DEFAULT NULL,
  `IMMEDIATE_BOSS_CODE` varchar(30) DEFAULT NULL,
  `COMPNY_CODE` varchar(30) DEFAULT NULL,
  `BRANCH_CODE` varchar(30) DEFAULT NULL,
  `DEPT_CODE` varchar(30) DEFAULT NULL,
  `SECTION_CODE` varchar(30) DEFAULT NULL,
  `GRADE_CODE` varchar(30) DEFAULT NULL,
  `E_GROUP_CODE` varchar(30) DEFAULT NULL,
  `JOB_TITLE_CODE` varchar(30) DEFAULT NULL,
  `NATION_CODE` varchar(30) DEFAULT NULL,
  `REL_CODE` varchar(30) DEFAULT NULL,
  `EMP_NAME` varchar(45) DEFAULT NULL,
  `EMP_PHONE` varchar(45) DEFAULT NULL,
  `EMP_ADDRESS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'EMP-1','GG','GTG','GTG','v3fbg12q@gmail.com','EMP001',1,'2021-01-13 14:30:20.144000','super','2021-09-20 11:45:54.739000','super','GE','GGGGGV','2020-02-15','871630169V','3001','2020-12-30','DFDF','fffffffff','2020-02-15','0','2020-12-30','DCJD','VFJN','FVKN','fknv','0771238534','0771238543','v1fbg14117','BGB','GB','BG','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','ANCALTYPE-1','bgb','bg',NULL,'Ms',0,'2020-02-15','','COMPNY-2',NULL,NULL,NULL,NULL,'EGRP-1',NULL,NULL,NULL,'Andrew','0771255234','Galle'),(2,'EMP-2','GG','GTG','GTG','v3fbg12q@gmail.com','EMP002',1,'2021-01-13 14:31:10.125000','buddhika','2021-09-19 10:46:50.554000','super','GE','GGGGGV','2020-02-14','993447733V','3002','2020-12-30','DFDF','fffffffff','2020-12-30','0','2020-12-30','DCJD','VFJN','FVKN','fknv','0771238534','0771238543','v1fbg14117','BGB','GB','BG','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','bg','bgb','bg','PrfImg_EMP-2.jpg','Mr',0,'2020-02-15','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Johny','0702487728','Kegalle'),(3,'EMP-3','BUDDHIKA','GTG','GTG','v3fbg@gmail.com','EMP018',1,'2021-01-15 23:05:02.905000','super','2021-09-19 11:01:57.656000','super','GE','GGGGGV','2020-02-16','992347644V','3003','2020-12-30','DFDF','fffffffff',NULL,'0','2020-12-30','DCJD','VFJN','FVKN','fknv','0771238534','0771238543','vfbg12','BGB','GB','BG','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','ANCALTYPE-1','bgb','bg','PrfImg_EMP-3.jpg','Ms',0,'2020-02-15','',NULL,NULL,NULL,NULL,NULL,'EGRP-1',NULL,NULL,NULL,'Nipuni','0712345654','Gampaha'),(4,'EMP-4','GG','GTG','GTG','buddhikaucsc2010@gmail.com','EMP004',1,'2021-01-16 23:30:57.308000','super','2021-03-02 15:05:56.865000','super','GE','GGGGGV','2020-12-30','880630160V','3004','2020-12-30','DFDF','fffffffff','2020-12-30','0','2020-12-30','DCJD','VFJN','FVKN','fknv','0771238534','0771238543','v1fbg141147','BGB','GB','BG','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','ANCALTYPE-1','bgb','bg',NULL,'Miss',0,'2020-02-15','',NULL,NULL,NULL,NULL,NULL,'EGRP-1',NULL,NULL,NULL,'Chathu',NULL,NULL),(5,'EMP-5','BUDDHIKA','GTG','GTG','EMP001','EMP005',1,'2021-01-20 12:22:11.448000','buddhika','2021-02-15 11:10:00.918000','super','GE','GGGGGV','2020-12-30','960630161V','c2225','2020-12-30','DFDF','fffffffff','2020-12-30','0',NULL,'DCJD','VFJN','FVKN','fknv','0771238534','0771238543','14141414','BGB','GB','BG','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','bg','bgb','bg',NULL,'Mr',0,'2020-02-15','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Buddhika',NULL,NULL),(6,'EMP-6','BUDDHIKA','BANDARA','SETUNGA MIDIYANSELAGE BUDDHIKA CHULA BANDARA','','EMP006',1,'2021-01-26 15:07:43.903000','super','2021-01-26 15:24:01.168000','buddhika5','MALE','MARRIED','1989-02-15','900630162V','1225','2019-07-01','DFDF','fffffffff','2020-07-01','0',NULL,'DATHAWA, PAHALAGAMA','DAMBADENIYA','KURUNEGALA','6300','0771238534','0771238543','1234678','CEYLON BUSINESS APPLIANCES (PVT) LTD','DEHIWALA','IT','GBG','GB','GBG','BG','BGB','BGB','BGB','BG','bg','bgb','bg','PrfImg_EMP-6.jpg','Mr',0,'2020-02-15','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Viraj',NULL,NULL),(7,'EMP-7',NULL,NULL,NULL,NULL,'EMP010',NULL,'2021-07-07 13:45:56.262000','super',NULL,NULL,NULL,NULL,NULL,'941234567V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nick','0702348867','Kandy'),(8,'EMP-8',NULL,NULL,NULL,NULL,'EMP008',NULL,'2021-08-29 21:24:15.785000','super',NULL,NULL,NULL,NULL,NULL,'901234566V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Ben','0702347821','Kegalle'),(9,'EMP-9',NULL,NULL,NULL,NULL,'EMP028',NULL,'2021-09-10 11:24:37.958000','super',NULL,NULL,NULL,NULL,NULL,'975293256V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Jick','0771234587','Kurunegala'),(10,'EMP-10',NULL,NULL,NULL,NULL,NULL,NULL,'2021-09-23 17:10:00.786000','super',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'EMP-11',NULL,NULL,NULL,NULL,'EMP012',NULL,'2021-09-23 18:19:56.465000','super',NULL,NULL,NULL,NULL,NULL,'892233333V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'White','0723232222','Kandy'),(12,'EMP-12',NULL,NULL,NULL,NULL,'EMP030',NULL,'2021-09-25 01:15:22.656000','super','2021-09-25 01:15:59.234000','super',NULL,NULL,NULL,'977678967V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Witey','0714567764','Colombo');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_h`
--

DROP TABLE IF EXISTS `employee_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_h` (
  `EMP_H_ID` int(11) NOT NULL,
  `EMP_CODE` varchar(30) DEFAULT NULL,
  `EMP_FIRST_NAME` varchar(30) DEFAULT NULL,
  `EMP_LAST_NAME` varchar(30) DEFAULT NULL,
  `EMP_FULL_NAME` varchar(100) DEFAULT NULL,
  `EMP_EMAIL` varchar(50) DEFAULT NULL,
  `EMP_NO` varchar(30) DEFAULT NULL,
  `IS_ACTIVE` int(11) DEFAULT NULL,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(30) DEFAULT NULL,
  `EMP_SEX` varchar(30) DEFAULT NULL,
  `EMP_CIVIL_STATUS` varchar(30) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `EMP_NIC` varchar(30) DEFAULT NULL,
  `EPF_NO` varchar(30) DEFAULT NULL,
  `DATE_OF_JOIN` date DEFAULT NULL,
  `MACHINE_STATUS` varchar(30) DEFAULT NULL,
  `MACHINE_CODE` varchar(30) DEFAULT NULL,
  `CONFIRM_DATE` date DEFAULT NULL,
  `EMP_STATUS` varchar(30) DEFAULT NULL,
  `DATE_LEFT` date DEFAULT NULL,
  `ADDR_LINE_1` varchar(100) DEFAULT NULL,
  `ADDR_LINE_2` varchar(100) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `POSTAL_CODE` varchar(30) DEFAULT NULL,
  `TEL_RESIDENCE` varchar(15) DEFAULT NULL,
  `TEL_MOBILE` varchar(15) DEFAULT NULL,
  `BANK_ACNT_NO` varchar(30) DEFAULT NULL,
  `EMP_COMPANY` varchar(50) DEFAULT NULL,
  `EMP_BRANCH` varchar(30) DEFAULT NULL,
  `EMP_DEPARTMENT` varchar(30) DEFAULT NULL,
  `EMP_SECTION` varchar(30) DEFAULT NULL,
  `EMP_GRADE` varchar(30) DEFAULT NULL,
  `EMP_GROUP` varchar(30) DEFAULT NULL,
  `EMP_JOB_TITLE` varchar(30) DEFAULT NULL,
  `EMP_NATIONALITY` varchar(30) DEFAULT NULL,
  `EMP_RELIGION` varchar(30) DEFAULT NULL,
  `FIXED_SHIFT` varchar(30) DEFAULT NULL,
  `SHIFT_GROUP` varchar(30) DEFAULT NULL,
  `CALENDAR_TYPE` varchar(30) DEFAULT NULL,
  `OT_TYPE` varchar(30) DEFAULT NULL,
  `LEAVE_TYPE` varchar(30) DEFAULT NULL,
  `PROFILE_IMG_NAME` varchar(50) DEFAULT NULL,
  `TITLE` varchar(5) DEFAULT NULL,
  `EMP_CONFIRMATION` int(11) DEFAULT NULL,
  `EMP_FUTURE_CONFIRM_DATE` date DEFAULT NULL,
  `IMMEDIATE_BOSS_CODE` varchar(30) DEFAULT NULL,
  `COMPNY_CODE` varchar(30) DEFAULT NULL,
  `BRANCH_CODE` varchar(30) DEFAULT NULL,
  `DEPT_CODE` varchar(30) DEFAULT NULL,
  `SECTION_CODE` varchar(30) DEFAULT NULL,
  `GRADE_CODE` varchar(30) DEFAULT NULL,
  `E_GROUP_CODE` varchar(30) DEFAULT NULL,
  `JOB_TITLE_CODE` varchar(30) DEFAULT NULL,
  `NATION_CODE` varchar(30) DEFAULT NULL,
  `REL_CODE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`EMP_H_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_h`
--

LOCK TABLES `employee_h` WRITE;
/*!40000 ALTER TABLE `employee_h` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_schedule`
--

DROP TABLE IF EXISTS `employee_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_schedule` (
  `EMP_SCHEDULE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMP_CODE` varchar(45) DEFAULT NULL,
  `DAY` varchar(45) DEFAULT NULL,
  `START_TIME` varchar(45) DEFAULT NULL,
  `END_TIME` varchar(45) DEFAULT NULL,
  `CREATED_AT` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` varchar(45) DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EMP_SCHEDULE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_schedule`
--

LOCK TABLES `employee_schedule` WRITE;
/*!40000 ALTER TABLE `employee_schedule` DISABLE KEYS */;
INSERT INTO `employee_schedule` VALUES (1,'EMP-8','SUNDAY','09:00','18:00','2021-08-30 22:04:17.173','super',NULL,NULL),(2,'EMP-8','MONDAY','07:00','18:00','2021-08-30 22:12:00.915','super',NULL,NULL),(3,'EMP-8','TUESDAY','10:00','19:00','2021-08-30 22:12:01.048','super',NULL,NULL),(4,'EMP-8','THURSDAY','09:00','17:00','2021-09-05 17:04:52.217','super',NULL,NULL),(5,'EMP-8','FRIDAY','09:00','17:00','2021-09-05 17:04:52.36','super',NULL,NULL),(6,'EMP-5','SUNDAY','09:00','16:00','2021-09-20 11:58:01.319','super','2021-09-20 15:17:24.457','super'),(7,'EMP-12','SUNDAY','08:00','17:00','2021-09-25 01:16:47.262','super','2021-09-25 01:16:59.737','super'),(8,'EMP-8','SATURDAY','09:00','12:00','2021-09-25 05:40:10.187','super',NULL,NULL);
/*!40000 ALTER TABLE `employee_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `injury`
--

DROP TABLE IF EXISTS `injury`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `injury` (
  `INJURY_ID` int(11) NOT NULL,
  `INJURY_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) DEFAULT NULL,
  `INJURY_DES` varchar(45) DEFAULT NULL,
  `INJURY_OCCURRED` date DEFAULT NULL,
  `INJURY_IMPACT` varchar(45) DEFAULT NULL,
  `INJURY_UNTIL` date DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`INJURY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `injury`
--

LOCK TABLES `injury` WRITE;
/*!40000 ALTER TABLE `injury` DISABLE KEYS */;
INSERT INTO `injury` VALUES (1,'INJ-1','PET-1','Right Leg','2021-09-03','Difficult to run','2021-09-24','2021-09-25 05:28:11','super',NULL,NULL);
/*!40000 ALTER TABLE `injury` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_physical_disabilities`
--

DROP TABLE IF EXISTS `medical_physical_disabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_physical_disabilities` (
  `DISABLE_ID` int(11) NOT NULL,
  `DISABLE_CODE` varchar(45) DEFAULT NULL,
  `PET_CODE` varchar(45) DEFAULT NULL,
  `DISABLE_NAME` varchar(45) NOT NULL,
  `DISABLE_TREATMENT` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`DISABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_physical_disabilities`
--

LOCK TABLES `medical_physical_disabilities` WRITE;
/*!40000 ALTER TABLE `medical_physical_disabilities` DISABLE KEYS */;
INSERT INTO `medical_physical_disabilities` VALUES (1,'DIS-1','PET-1','right arm disabled','exercises','2021-09-25 05:27:36','super',NULL,NULL);
/*!40000 ALTER TABLE `medical_physical_disabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medication` (
  `MED_ID` int(11) NOT NULL,
  `MED_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `MED_DOSAGE` varchar(45) DEFAULT NULL,
  `MED_NAME` varchar(45) DEFAULT NULL,
  `MED_FREQUENCY` varchar(45) DEFAULT NULL,
  `MED_AM` varchar(45) DEFAULT NULL,
  `MED_MID` varchar(45) DEFAULT NULL,
  `MED_PM` varchar(45) DEFAULT NULL,
  `MED_UNTIL` date DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`MED_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'MED-1','PET-1','2','Vitamin C','As Needed','Yes','Yes','Yes','2021-10-08','super','2021-09-25 05:28:36',NULL,NULL);
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `PET_ID` int(11) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `CUST_CODE` varchar(45) NOT NULL,
  `PET_NAME` varchar(45) NOT NULL,
  `DOB` date DEFAULT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  `WEIGHT` varchar(45) DEFAULT NULL,
  `GENDER` varchar(45) DEFAULT NULL,
  `BREED_CODE` varchar(45) DEFAULT NULL,
  `COLOR_CODE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`PET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (1,'PET-1','CUS-61','Timmy','2017-09-25','Dog','50','Male','BR-2','CO-3','super','2021-09-25 05:26:37',NULL,NULL),(2,'PET-2','CUS-61','Black','2020-09-25','Dog','30','Male','BR-4','CO-4','super','2021-09-25 09:51:22',NULL,NULL);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_appointment`
--

DROP TABLE IF EXISTS `pet_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_appointment` (
  `PET_APP_ID` int(11) NOT NULL,
  `PET_APP_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `APP_CODE` varchar(45) NOT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`PET_APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_appointment`
--

LOCK TABLES `pet_appointment` WRITE;
/*!40000 ALTER TABLE `pet_appointment` DISABLE KEYS */;
INSERT INTO `pet_appointment` VALUES (1,'PETAPPO-1','PET-1','APPO-1','super','2021-09-25 05:31:31',NULL,NULL),(2,'PETAPPO-2','PET-1','APPO-2','super','2021-09-25 09:49:26',NULL,NULL);
/*!40000 ALTER TABLE `pet_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_vaccination`
--

DROP TABLE IF EXISTS `pet_vaccination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_vaccination` (
  `PET_VACC_ID` int(11) NOT NULL,
  `PET_VACC_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) NOT NULL,
  `VACC_CODE` varchar(45) NOT NULL,
  `EXPIRATION_DATE` date DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PET_VACC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_vaccination`
--

LOCK TABLES `pet_vaccination` WRITE;
/*!40000 ALTER TABLE `pet_vaccination` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet_vaccination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
  `ROOM_ID` int(11) NOT NULL,
  `ROOM_CODE` varchar(45) NOT NULL,
  `ROOM_NAME` varchar(45) DEFAULT NULL,
  `ROOM_TYPE` varchar(45) DEFAULT NULL,
  `MAX_WEIGHT` varchar(45) DEFAULT NULL,
  `CLEAN_NEEDED` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'RE-1','Superior',NULL,'600','Yes','super','2021-06-22 22:18:11','super','2021-09-20 11:51:07'),(2,'RE-2','Dulax',NULL,'150','Yes','super','2021-06-22 22:18:26',NULL,NULL),(3,'RE-3','Winter',NULL,'100','No','super','2021-06-26 12:48:53',NULL,NULL),(4,'RE-4','Premium',NULL,'500','No','super','2021-07-01 21:18:41',NULL,NULL),(5,'RE-5','Otum',NULL,'1000','Yes','super','2021-07-01 22:02:56',NULL,NULL),(6,'RE-6','Deak',NULL,'300','No','super','2021-07-02 23:39:12',NULL,NULL),(7,'RE-7','A12',NULL,'1200','No','super','2021-09-10 11:18:07',NULL,NULL),(8,'RE-8','A3',NULL,'100','Yes','super','2021-09-23 18:14:10',NULL,NULL),(9,'RE-9','A6',NULL,'400','No','super','2021-09-25 01:17:32','super','2021-09-25 01:17:48');
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retail`
--

DROP TABLE IF EXISTS `retail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retail` (
  `RETAIL_ID` int(11) NOT NULL,
  `RETAIL_CODE` varchar(45) NOT NULL,
  `RETAIL_CAT_CODE` varchar(45) DEFAULT NULL,
  `RETAIL_NAME` varchar(45) DEFAULT NULL,
  `RETAIL_PRICE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`RETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retail`
--

LOCK TABLES `retail` WRITE;
/*!40000 ALTER TABLE `retail` DISABLE KEYS */;
INSERT INTO `retail` VALUES (1,'RE-1','Foods','Chicken','1000','super','2021-07-04 10:33:10','super','2021-09-20 11:24:34'),(3,'RE-3','Foods','Milk','56','super','2021-07-04 13:01:20','super','2021-09-19 19:20:53'),(4,'RE-4','Toys','Bear','400','super','2021-07-05 22:24:42',NULL,NULL),(8,'RE-8','Collar','Cat Collar','450','super','2021-09-25 01:22:46','super','2021-09-25 05:35:57'),(14,'RE-14','Foods','Milk Pack','100','super','2021-09-25 01:31:59',NULL,NULL),(15,'RE-15','Foods','Dog Collar','200','super','2021-09-25 09:58:48',NULL,NULL);
/*!40000 ALTER TABLE `retail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retail_category`
--

DROP TABLE IF EXISTS `retail_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retail_category` (
  `RETAIL_CAT_ID` int(11) NOT NULL,
  `RETAIL_CAT_CODE` varchar(45) NOT NULL,
  `RETAIL_CAT_NAME` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`RETAIL_CAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retail_category`
--

LOCK TABLES `retail_category` WRITE;
/*!40000 ALTER TABLE `retail_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `retail_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `SERVICE_ID` int(11) NOT NULL,
  `SERVICE_CODE` varchar(45) NOT NULL,
  `SERVICE_CAT_CODE` varchar(45) NOT NULL,
  `SERVICE_NAME` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `SERVICE_PRICE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'SE-1','Boarding','Boarding-1','super','2021-06-20 18:22:30','super','2021-09-20 00:08:25','123'),(2,'SE-2','Training','Training-1','super','2021-06-24 23:22:15','super','2021-09-20 00:08:35','1000'),(3,'SE-3','Grooming','Saloon','super','2021-06-24 23:22:35','super','2021-09-20 11:53:40','300'),(4,'SE-4','Training','Private Lessons','super','2021-06-24 23:23:26','super','2021-09-20 11:53:51','2000'),(5,'SE-5','Daycare','Full day','super','2021-09-19 23:03:41',NULL,NULL,'125'),(6,'SE-6','Daycare','Half day','super','2021-09-25 01:18:53','super','2021-09-25 01:19:12','700');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_category`
--

DROP TABLE IF EXISTS `service_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_category` (
  `SERVICE_CAT_ID` int(11) NOT NULL,
  `SERVICE_CAT_CODE` varchar(45) NOT NULL,
  `SERVICE_CAT_NAME` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`SERVICE_CAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_category`
--

LOCK TABLES `service_category` WRITE;
/*!40000 ALTER TABLE `service_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_dog`
--

DROP TABLE IF EXISTS `service_dog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_dog` (
  `SERVICE_DOG_ID` int(11) NOT NULL,
  `SERVICE_DOG_CODE` varchar(45) NOT NULL,
  `SERVICE_DOG_NAME` varchar(45) DEFAULT NULL,
  `SERVICE_DOG_DOB` date DEFAULT NULL,
  `BREED_CODE` varchar(45) DEFAULT NULL,
  `COLOR_CODE` varchar(45) DEFAULT NULL,
  `NOTE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`SERVICE_DOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_dog`
--

LOCK TABLES `service_dog` WRITE;
/*!40000 ALTER TABLE `service_dog` DISABLE KEYS */;
INSERT INTO `service_dog` VALUES (1,'SD-1','Nuke','2021-02-15','BR-7','CO-3','Clever','super','2021-06-10 21:08:43','super','2021-09-20 11:41:25'),(2,'SD-2','Rexy','2020-09-17','BR-4','CO-1','Inteligent','super','2021-07-02 22:32:59',NULL,NULL),(3,'SD-3','Laxie','2019-10-16','BR-2','CO-1','Friendly','super','2021-07-02 23:04:29',NULL,NULL),(4,'SD-4','KOBE','2019-02-18','BR-4','CO-4','Easy to guide','super','2021-07-02 23:08:30',NULL,NULL),(5,'SD-5','Rudy','2016-05-16','BR-1','CO-5','Clever','super','2021-07-02 23:09:44',NULL,NULL),(6,'SD-6','Nicky','2020-07-06','BR-6','CO-3','Clever','super','2021-07-03 14:38:24',NULL,NULL),(7,'SD-7','JASPER','2018-05-30','BR-6','CO-5','Good health','super','2021-07-06 22:17:19',NULL,NULL),(8,'SD-8','Quick','2021-06-09','BR-1','CO-1','test','super','2021-09-23 18:21:28',NULL,NULL),(9,'SD-9','Lilly','2019-02-13','BR-1','CO-1','Good','super','2021-09-25 01:19:48','super','2021-09-25 01:20:07');
/*!40000 ALTER TABLE `service_dog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SUPP_ID` int(11) NOT NULL,
  `SUPP_CODE` varchar(45) NOT NULL,
  `SUPP_NIC` varchar(45) DEFAULT NULL,
  `SUPP_ADDRESS` varchar(45) DEFAULT NULL,
  `SUPP_PHONE` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  PRIMARY KEY (`SUPP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'SUP-1',NULL,NULL,NULL,'super','2021-09-24 23:29:55',NULL,NULL),(2,'SUP-2',NULL,NULL,NULL,'super','2021-09-25 04:30:08',NULL,NULL),(3,'SUP-3',NULL,NULL,NULL,'super','2021-09-25 06:55:25',NULL,NULL);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_retail`
--

DROP TABLE IF EXISTS `supplier_retail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_retail` (
  `SUPP_RETAIL_ID` int(11) NOT NULL,
  `SUPP_CODE` varchar(45) NOT NULL,
  `RETAIL_CODE` varchar(45) NOT NULL,
  `RETAIL_NAME` varchar(45) DEFAULT NULL,
  `MAX_COUNT` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `WHOLE_SALE_PRICE` varchar(45) DEFAULT NULL,
  `IS_APPROVED` int(11) DEFAULT NULL,
  PRIMARY KEY (`SUPP_RETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_retail`
--

LOCK TABLES `supplier_retail` WRITE;
/*!40000 ALTER TABLE `supplier_retail` DISABLE KEYS */;
INSERT INTO `supplier_retail` VALUES (1,'SR-1','RE-8','Cat Collar','100','supplier1','2021-09-25 05:33:36',NULL,NULL,'350',0),(6,'SR-6','RE-1','Chicken','2','supplier1','2021-09-24 23:53:35',NULL,NULL,'100',NULL),(7,'SR-7','RE-3','Milk','2','supplier1','2021-09-25 00:28:51',NULL,NULL,'453',NULL),(8,'SR-8','RE-4','Bear','10','supplier1','2021-09-25 01:10:14',NULL,NULL,'300',NULL),(9,'SR-9','RE-14','Milk Pack','10','supplier1','2021-09-25 09:53:22',NULL,NULL,'100',0);
/*!40000 ALTER TABLE `supplier_retail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `SYS_USR_ID` int(11) NOT NULL,
  `SYS_USR_CODE` varchar(30) NOT NULL,
  `APP_CODE` varchar(30) DEFAULT NULL,
  `EMP_CODE` varchar(30) DEFAULT NULL,
  `IS_ACTIVE` int(11) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `PASS_EXPIR` datetime(6) DEFAULT NULL,
  `USER_ROLE_CODE` varchar(30) DEFAULT NULL,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(30) DEFAULT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `UPDATED_BY` varchar(30) DEFAULT NULL,
  `USER_LOGIN_COUNT` int(11) DEFAULT NULL,
  `PASS_EXPIR_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`SYS_USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,'SYSUSR-1','APP-1','EMP-4',1,'super','$2a$10$PR66vbwFJTcl8/0q6uVN7OxVWnfSmT/N/iwsnS7m7JqHmdu3Vak3i','2022-01-22 14:28:54.269000','USROLE-1','2021-01-22 14:28:54.269000','super','2021-01-23 00:41:23.335000','super',2,0),(2,'SYSUSR-2','APP-1','EMP-4',1,'mytest2','$2a$10$ajFEC2uopiBK1VPvdUYShupzhfBALMIJv1aC1vbjeTzBcuPRmMgP.','2021-01-24 14:32:50.150000','USROLE-1','2021-01-22 14:31:19.862000','super','2021-01-24 14:42:57.856000','hris-system',0,1),(3,'SYSUSR-3','APP-1','EMP-5',1,'buddhika1','$2a$10$fEZskLeX2IgzzPqXQqNm1ObuPrmrOjsDAulUydqpvY9LO2M30RaA.','2021-01-24 14:52:09.167000','USROLE-12','2021-01-22 14:52:09.209000','super','2021-01-24 14:42:58.192000','hris-system',0,1),(4,'SYSUSR-4','APP-1','EMP-5',1,'buddhika2','$2a$10$lVdMqvFGwjDeXwjbBobOTOhl3G4IFxm7Uux4NJyyUtyaUmLW48RUK','2021-01-24 14:53:41.723000','USROLE-12','2021-01-22 14:53:41.750000','super','2021-01-24 14:42:58.634000','hris-system',0,1),(5,'SYSUSR-5','APP-1','EMP-5',1,'buddhika456','$2a$10$2d5HnqEv1wvU.cJIANOjTuN.1em7CtekJ3Y36h56fAiTQgBCB1Fki','2021-01-25 11:22:06.995000','USROLE-12','2021-01-22 14:58:45.903000','super','2021-01-24 14:42:59.310000','hris-system',0,1),(6,'SYSUSR-6','APP-1','EMP-5',1,'buddhika4','$2a$10$02GYsA77OtV7B9cibXo1f.WBf3R2TyelOc1lmReXU6s7JT76jorR.','2021-01-24 15:00:58.749000','USROLE-12','2021-01-22 15:00:58.749000','super','2021-01-24 14:47:39.272000','hris-system',0,1),(7,'SYSUSR-7','APP-1','EMP-6',0,'buddhika5','$2a$10$s9yVDkkafMbGKMt9X.wckOyar0jdnS6DEr/WWPNde0GhkWOxvUZTW','2021-01-28 15:16:46.315000','USROLE-13','2021-01-26 15:16:46.357000','super','2021-01-26 15:48:52.570000','hris-system',0,1),(8,'SYSUSR-8','APP-1','EMP-4',1,'qqq','$2a$10$PR66vbwFJTcl8/0q6uVN7OxVWnfSmT/N/iwsnS7m7JqHmdu3Vak3i','2021-06-15 03:19:26.743000','USROLE-3','2021-06-13 03:19:28.044000','super','2021-06-13 10:28:21.109000','qqq',-97,0),(9,'SYSUSR-9','APP-1','CUS-18',1,'aaa','$2a$10$1zuVvCCnYvrvS1w0YKyP0u3t8GDkykmIOH2/gQ21r6ChcDgJpjnU2','2021-06-15 11:50:11.324000','USROLE-3','2021-06-13 11:50:11.352000','super',NULL,NULL,-97,0),(10,'SYSUSR-10','APP-1','CUS-41',1,'bbb','$2a$10$2uzNljPud34YgGY0cKxiAutqamB7eC.jPL1SBUmQkMfG1cFxLMXLO','2021-06-15 20:15:55.929000','USROLE-3','2021-06-13 20:15:56.003000','super',NULL,NULL,0,0),(11,'SYSUSR-11','APP-1','CUS-42',1,'ccc','$2a$10$CZLY3mD5JcTBlED263VOLefKgwxfbqKrtHb.VPpPI.smMnMsd9wWK','2021-06-15 20:40:32.820000','USROLE-3','2021-06-13 20:40:32.856000','super',NULL,NULL,0,0),(12,'SYSUSR-12','APP-1','CUS-43',1,'ddd','$2a$10$6RkBhhxrHDS9v3fJu/i4OO4bvmTelhajx1.SwITrnm9JmsrEqe4oy','2021-06-15 21:00:45.378000','USROLE-3','2021-06-13 21:00:45.409000','super',NULL,NULL,0,0),(13,'SYSUSR-13','APP-1','CUS-44',1,'eee','$2a$10$XEQEA6XVVPcAm3OP9AahEekoH./Bp/zh1ZwJphILvgX5HoYMLFhFi','2021-06-15 21:13:39.792000','USROLE-3','2021-06-13 21:13:39.891000','super',NULL,NULL,0,0),(14,'SYSUSR-14','APP-1','CUS-45',1,'fff','$2a$10$84zKVT5lQCQUOV5h/Kemnu3G9Z.44HoA/v7ksFlocg8ynmWLzMzd6','2021-06-15 21:15:17.573000','USROLE-3','2021-06-13 21:15:17.672000','super',NULL,NULL,0,0),(15,'SYSUSR-15','APP-1','CUS-46',1,'buddhika','$2a$10$BunZJryD9V69wHCWGMDY1.iOEM4Vhvsro24jrovUrpa..mD70Puda','2021-06-15 22:17:11.652000','USROLE-3','2021-06-13 22:17:11.679000','super',NULL,NULL,1,0),(16,'SYSUSR-16','APP-1','CUS-47',1,'zxc','$2a$10$BwPKUGWJUEtn5JjKtoew7uraEhWPoeWhZxPXppUsLLqgY7brlSnza','2021-06-15 22:22:32.467000','USROLE-3','2021-06-13 22:22:32.474000','super',NULL,NULL,0,0),(17,'SYSUSR-17','APP-1','CUS-48',1,'qaz','$2a$10$rZA17gYwKIHkpN5DgDWjL.x8ayV7h5SDPryorD.CDh74r..spF8dq','2021-06-15 22:25:26.868000','USROLE-3','2021-06-13 22:25:26.899000','super',NULL,NULL,0,0),(18,'SYSUSR-18','APP-1','CUS-49',1,'qwer','$2a$10$WrY2ZbzxIN5NdKjgNbRr..LpQ62AEz00vcqd4WQVpvDOjlgsErGMC','2021-06-15 22:27:23.357000','USROLE-3','2021-06-13 22:27:23.371000','super',NULL,NULL,0,0),(19,'SYSUSR-19','APP-1','CUS-50',1,'nethmini','$2a$10$VRa1.3Xo05IOWqA3JxQoQus2sJUQClh5mQ0AlmKhOQ03KpC46/i4W','2021-06-15 23:38:33.663000','USROLE-3','2021-06-13 23:38:33.689000','super',NULL,NULL,0,0),(20,'SYSUSR-20','APP-1','CUS-51',1,'asd','$2a$10$zPaS0FKGuQ9MVebHB8bAy.BDWTbxA93RCxhy9JW1lGMxvs7Vrhyte','2021-06-15 23:44:12.251000','USROLE-3','2021-06-13 23:44:12.277000','super',NULL,NULL,0,0),(21,'SYSUSR-21','APP-1','CUS-52',1,'mlp','$2a$10$F0RZSK38M/UHXlne7gUEq.UckFEBqmf/ZmeKhVmy09FEBgo.jaZka','2021-06-15 23:45:02.525000','USROLE-3','2021-06-13 23:45:02.547000','super',NULL,NULL,0,0),(22,'SYSUSR-22','APP-1','CUS-53',1,'op','$2a$10$sXjuWDkSKAnSV.BMdpkDney1c5QYUKMc8a.dNOVY.oIPyfvC4u0sG','2021-06-15 23:56:43.936000','USROLE-3','2021-06-13 23:56:43.959000','super',NULL,NULL,0,0),(23,'SYSUSR-23','APP-1','CUS-54',1,'ggg','$2a$10$mp7RjdjiqEfD3qMsMPGlQOnYwSX24e4TnJN3lU4CBEPdu0Sxcaq7y','2021-06-16 15:42:11.699000','USROLE-3','2021-06-14 15:42:11.718000','super',NULL,NULL,0,0),(24,'SYSUSR-24','APP-1','CUS-55',1,'hhh','$2a$10$Ndt1Pqa3MBTbP8fracddG.mNlITkecaIACTSDs9CiyrLlz4qwknTi','2021-06-16 15:45:04.509000','USROLE-3','2021-06-14 15:45:04.527000','super',NULL,NULL,0,0),(25,'SYSUSR-25','APP-1','CUS-56',1,'iii','$2a$10$faFtBReqILpkoh4ghV5BjOrGWX8fUauGKJgtlGbOH3gCP3LGlIh7q','2021-06-16 15:45:45.122000','USROLE-3','2021-06-14 15:45:45.140000','super',NULL,NULL,0,0),(26,'SYSUSR-26','APP-1','CUS-57',1,'jjj','$2a$10$T6mArdlwQXd9boj54cxCaeMxMN2jeqWgD95nqB4QeX0afK2CehzQS','2021-06-16 16:03:08.318000','USROLE-3','2021-06-14 16:03:08.597000','super',NULL,NULL,0,0),(27,'SYSUSR-27','APP-1','CUS-59',1,'mytest','$2a$10$BH6zqyRqpGZsGt.2PZitdenDXDz83AjlN/4.iVxNhsQENu.YAl0rC','2021-06-16 16:27:04.968000','USROLE-3','2021-06-14 16:27:04.995000','super',NULL,NULL,1,0),(28,'SYSUSR-28','APP-1','CUS-60',1,'NethminiS','$2a$10$.X.Dlqvs2A.1GvNZKAvgnuA3sSCGQErbNRpt4YDUtrlDs9gi0L/sS','2021-06-17 22:42:18.576000','USROLE-2','2021-06-15 22:42:18.654000','super','2021-09-25 05:58:43.013000','NethminiS',-10,0),(29,'SYSUSR-29','APP-1','CUS-61',1,'Nike','$2a$10$OvhdWOKasaIg3ItOPzGXNOXsCOzUfgyhePfBKVsktfS3u28QjMpyO','2021-09-25 22:27:38.814000','USROLE-3','2021-09-23 22:27:38.838000','super',NULL,NULL,0,0),(30,'SYSUSR-30','APP-1','CUS-71',1,'customer1','$2a$10$1vqvCrIzkY5eO4iyD27AoOkO1ECfFHMC5kIFvClQCgPyegtRvb9Ii','2021-09-26 23:11:49.691000','USROLE-3','2021-09-24 23:11:49.705000','super',NULL,NULL,0,0),(31,'SYSUSR-31','APP-1','SUP-1',1,'supplier1','$2a$10$B/zbDCUAFy.NGfODvq/fu.w2wHzlV5A5m8K3cAMw8hEFIWA769Dam','2021-09-26 23:29:55.281000','USROLE-4','2021-09-24 23:29:55.298000','super',NULL,NULL,0,0),(32,'SYSUSR-32','APP-1','CUS-72',1,'mycustomer','$2a$10$YZjSseYU./pt3ziHQMzN5Ox8lflBp1H4R78ENYGBAL4Ma455bd1Ni','2021-09-27 04:29:22.439000','USROLE-3','2021-09-25 04:29:22.463000','super',NULL,NULL,0,0),(33,'SYSUSR-33','APP-1','SUP-2',1,'mysupplier','$2a$10$m2KujsFugk7Kgta3INCbfOSqzZbpoMGw3/Usbt7S7NTr/vKSajcmG','2021-09-27 04:30:08.783000','USROLE-4','2021-09-25 04:30:08.799000','super',NULL,NULL,0,0),(34,'SYSUSR-34','APP-1','CUS-63',1,'zxcvbnm','$2a$10$kHKLQv4puSODPJyPBRV/tuPcNGAzBwMfAgYmQYLxcZJCy8MbckHUe','2021-09-27 06:54:54.690000','USROLE-3','2021-09-25 06:54:54.721000','super',NULL,NULL,0,0),(35,'SYSUSR-35','APP-1','SUP-3',1,'asdfghjkl','$2a$10$Hv3rTyBmRc6391Aa1KtsUOUhEMb46em.AUHzlXC8JT.iXyBldERhe','2021-09-27 06:55:25.006000','USROLE-4','2021-09-25 06:55:25.018000','super',NULL,NULL,0,0);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user_h`
--

DROP TABLE IF EXISTS `system_user_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_h` (
  `SYS_USR_H_ID` int(11) NOT NULL,
  `SYS_USR_CODE` varchar(30) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `EMP_CODE` varchar(30) NOT NULL,
  `APP_CODE` varchar(30) NOT NULL,
  `IS_ACTIVE` int(11) DEFAULT NULL,
  `USER_ROLE_CODE` varchar(30) NOT NULL,
  `CREATED_AT` datetime(6) NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `USER_LOGIN_COUNT` int(11) NOT NULL,
  `PASS_EXPIR_STATUS` int(11) NOT NULL,
  PRIMARY KEY (`SYS_USR_H_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user_h`
--

LOCK TABLES `system_user_h` WRITE;
/*!40000 ALTER TABLE `system_user_h` DISABLE KEYS */;
INSERT INTO `system_user_h` VALUES (1,'SYSUSR-2','buddhika','EMP-4','APP-1',1,'USROLE-12','2021-01-22 14:32:26.913000','super',0,0),(2,'SYSUSR-2','mytest1','EMP-4','APP-1',1,'USROLE-1','2021-01-22 14:32:41.021000','super',0,0),(3,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-22 14:32:50.224000','super',0,0),(4,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:05:15.274000','super',0,0),(5,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:05:43.425000','super',0,0),(6,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:06:19.043000','super',0,0),(7,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:07:30.530000','super',0,0),(8,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:08:54.619000','super',0,0),(9,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:10:19.956000','super',10,0),(10,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:10:40.088000','super',0,0),(11,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:10:48.439000','super',1,0),(12,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:11:00.058000','super',2,0),(13,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:11:00.610000','super',3,0),(14,'SYSUSR-1','super','EMP-4','APP-1',0,'USROLE-1','2021-01-22 16:11:22.601000','super',3,0),(15,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:22:37.094000','super',4,0),(16,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:29:50.007000','super',5,0),(17,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:29:50.279000','super',6,0),(18,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:41:16.225000','super',6,0),(19,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:41:16.654000','super',7,0),(20,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:46:40.451000','super',7,0),(21,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:46:40.808000','super',8,0),(22,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:49:16.244000','super',8,0),(23,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-22 16:49:17.315000','super',9,0),(24,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:30:04.273000','super',9,0),(25,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:30:04.827000','super',10,0),(26,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:34:28.029000','super',10,0),(27,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:34:28.954000','super',11,0),(28,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:35:21.315000','super',11,0),(29,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:35:21.717000','super',12,0),(30,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:36:57.401000','super',12,0),(31,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:36:58.125000','super',13,0),(32,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:38:31.353000','super',13,0),(33,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:38:31.727000','super',14,0),(34,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-01-23 00:41:23.223000','super',14,0),(35,'SYSUSR-5','buddhika3','EMP-5','APP-1',1,'USROLE-12','2021-01-23 00:41:57.361000','super',0,0),(36,'SYSUSR-5','buddhika12','EMP-5','APP-1',1,'USROLE-12','2021-01-23 11:00:04.775000','super',0,0),(37,'SYSUSR-5','buddhika12','EMP-5','APP-1',1,'USROLE-12','2021-01-23 11:22:06.674000','super',0,0),(38,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 12:59:04.025000','hris-system',0,0),(39,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:26:36.125000','hris-system',0,0),(40,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:28:29.435000','hris-system',0,0),(41,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:29:17.694000','hris-system',0,0),(42,'SYSUSR-3','buddhika1','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:29:18.141000','hris-system',0,0),(43,'SYSUSR-4','buddhika2','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:29:18.422000','hris-system',0,0),(44,'SYSUSR-5','buddhika456','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:29:18.995000','hris-system',0,0),(45,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:29:19.561000','hris-system',0,0),(46,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:30:15.156000','hris-system',0,0),(47,'SYSUSR-3','buddhika1','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:30:15.419000','hris-system',0,0),(48,'SYSUSR-4','buddhika2','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:30:15.586000','hris-system',0,0),(49,'SYSUSR-5','buddhika456','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:30:15.792000','hris-system',0,0),(50,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:30:16.026000','hris-system',0,0),(51,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:32:31.374000','hris-system',0,0),(52,'SYSUSR-3','buddhika1','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:32:31.753000','hris-system',0,0),(53,'SYSUSR-4','buddhika2','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:32:32.001000','hris-system',0,0),(54,'SYSUSR-5','buddhika456','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:32:32.343000','hris-system',0,0),(55,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:32:32.540000','hris-system',0,0),(56,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:34:01.694000','hris-system',0,0),(57,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:34:59.017000','hris-system',0,0),(58,'SYSUSR-3','buddhika1','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:34:59.533000','hris-system',0,0),(59,'SYSUSR-4','buddhika2','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:34:59.818000','hris-system',0,0),(60,'SYSUSR-5','buddhika456','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:35:00.299000','hris-system',0,0),(61,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:35:00.634000','hris-system',0,0),(62,'SYSUSR-2','mytest2','EMP-4','APP-1',1,'USROLE-1','2021-01-24 14:42:57.648000','hris-system',0,0),(63,'SYSUSR-3','buddhika1','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:42:58.044000','hris-system',0,0),(64,'SYSUSR-4','buddhika2','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:42:58.357000','hris-system',0,0),(65,'SYSUSR-5','buddhika456','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:42:58.988000','hris-system',0,0),(66,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:42:59.524000','hris-system',0,0),(67,'SYSUSR-6','buddhika4','EMP-5','APP-1',1,'USROLE-12','2021-01-24 14:47:39.099000','hris-system',0,0),(68,'SYSUSR-7','buddhika5','EMP-6','APP-1',1,'USROLE-13','2021-01-26 15:48:52.345000','hris-system',0,0),(69,'SYSUSR-8','qqq','CUS-38','APP-1',1,'USROLE-13','2021-06-13 10:22:35.317000','qqq',0,0),(70,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:24:15.792000','qqq',1,0),(71,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:25:27.473000','qqq',2,0),(72,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:25:29.013000','qqq',3,0),(73,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:26:04.087000','qqq',3,0),(74,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:26:04.340000','qqq',4,0),(75,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:28:20.727000','qqq',4,0),(76,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:28:21.065000','qqq',5,0),(77,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:31:51.023000','qqq',-100,0),(78,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-13','2021-06-13 10:32:24.589000','qqq',-100,0),(79,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-3','2021-06-13 13:48:10.102000','qqq',-99,0),(80,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-06-13 15:20:11.886000','super',0,0),(81,'SYSUSR-8','qqq','EMP-4','APP-1',1,'USROLE-3','2021-06-13 22:10:46.475000','qqq',-98,0),(82,'SYSUSR-15','buddhika','CUS-46','APP-1',1,'USROLE-3','2021-06-14 16:33:37.650000','buddhika',0,0),(83,'SYSUSR-27','mytest','CUS-59','APP-1',1,'USROLE-3','2021-06-15 20:02:32.188000','mytest',0,0),(84,'SYSUSR-1','super','EMP-4','APP-1',1,'USROLE-1','2021-06-15 23:29:43.148000','super',1,0),(85,'SYSUSR-9','aaa','CUS-18','APP-1',1,'USROLE-3','2021-06-15 23:53:19.527000','aaa',-99,0),(86,'SYSUSR-9','aaa','CUS-18','APP-1',1,'USROLE-3','2021-06-16 00:28:08.423000','aaa',-98,0),(87,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-06-18 17:43:02.115000','NethminiS',0,0),(88,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-03 11:53:50.749000','NethminiS',1,0),(89,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-04 12:15:52.404000','NethminiS',2,0),(90,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-04 12:15:53.141000','NethminiS',3,0),(91,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-07 13:37:56.759000','NethminiS',0,0),(92,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-11 09:40:44.893000','NethminiS',1,0),(93,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-11 12:47:51.778000','NethminiS',2,0),(94,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-11 12:47:52.409000','NethminiS',3,0),(95,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-07-11 21:08:53.151000','NethminiS',0,0),(96,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-08-04 23:52:24.054000','NethminiS',1,0),(97,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-08-21 11:31:49.833000','NethminiS',0,0),(98,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-08-30 15:23:12.121000','NethminiS',1,0),(99,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-09-03 22:32:23.745000','NethminiS',2,0),(100,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-09-03 22:32:24.073000','NethminiS',3,0),(101,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-09-09 21:50:24.355000','NethminiS',0,0),(102,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-09-09 21:50:32.256000','NethminiS',1,0),(103,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-3','2021-09-24 15:12:11.986000','NethminiS',2,0),(104,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-2','2021-09-25 05:58:42.673000','NethminiS',3,0),(105,'SYSUSR-28','NethminiS','CUS-60','APP-1',1,'USROLE-2','2021-09-25 05:58:42.971000','NethminiS',4,0);
/*!40000 ALTER TABLE `system_user_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tran_log_name_mapping`
--

DROP TABLE IF EXISTS `tran_log_name_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tran_log_name_mapping` (
  `NAME_MAP_ID` int(11) NOT NULL,
  `NAME_MAP_CODE` varchar(30) NOT NULL,
  `FILE_NAME` varchar(45) NOT NULL,
  `ENCRYPTED_NAME` varchar(45) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  PRIMARY KEY (`NAME_MAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tran_log_name_mapping`
--

LOCK TABLES `tran_log_name_mapping` WRITE;
/*!40000 ALTER TABLE `tran_log_name_mapping` DISABLE KEYS */;
INSERT INTO `tran_log_name_mapping` VALUES (1,'TransNameMap-1','trans_log_2021-02-09','kDPT6aX9yzw5u4w6D0JfEJyv31NPVe9XmPDCKO2K7Y=','2021-02-09 14:18:03','System'),(2,'TransNameMap-2','trans_log_2021-02-08','kDPT6aX9yzw5u4w6D0JfEEtRWsVjGQKxY6OQZxE5XwI=','2021-02-08 14:44:59','System'),(3,'TransNameMap-3','trans_log_2021-02-10','kDPT6aX9yzw5u4w6D0JfEIIc8Z53nLODn44X+BXcrPo=','2021-02-10 09:34:08','System'),(4,'TransNameMap-4','trans_log_2021-02-11','kDPT6aX9yzw5u4w6D0JfEMndClTyo8EHV2yfPFadvA=','2021-02-11 13:47:06','System'),(5,'TransNameMap-5','trans_log_2021-02-12','kDPT6aX9yzw5u4w6D0JfEAdwq7JpPyF8tV1Jl76Ncgs=','2021-02-12 08:52:04','System'),(6,'TransNameMap-6','trans_log_2021-02-13','kDPT6aX9yzw5u4w6D0JfEHk7Z66qk8+r3qxmo+894no=','2021-02-13 09:36:53','System'),(7,'TransNameMap-7','trans_log_2021-02-15','kDPT6aX9yzw5u4w6D0JfEN4msyPr6bkz67+P1001xE=','2021-02-15 08:54:43','System'),(8,'TransNameMap-8','trans_log_2021-02-16','kDPT6aX9yzw5u4w6D0JfEONjr3DJ1+5UvS06enDIXs=','2021-02-16 10:36:17','System'),(9,'TransNameMap-9','trans_log_2021-02-17','kDPT6aX9yzw5u4w6D0JfEMwMDqsUUQ4NVyrYC3zuR5A=','2021-02-17 10:13:23','System'),(10,'TransNameMap-10','trans_log_2021-02-18','kDPT6aX9yzw5u4w6D0JfEDvWMOUglELSkCXLbXwGdeA=','2021-02-18 00:00:43','System'),(11,'TransNameMap-11','trans_log_2021-02-19','kDPT6aX9yzw5u4w6D0JfEKScNoqLGS6YNOE8EC0t+L0=','2021-02-19 00:03:01','System'),(12,'TransNameMap-12','trans_log_2021-02-22','kDPT6aX9yzw5u4w6D0JfEKwJVGVCqMkUQQKwWwHBx1o=','2021-02-22 07:39:21','System'),(13,'TransNameMap-13','trans_log_2021-02-23','kDPT6aX9yzw5u4w6D0JfEJbbp4d4vYopOTP5jIB+RSM=','2021-02-23 08:58:49','System'),(14,'TransNameMap-14','trans_log_2021-02-24','kDPT6aX9yzw5u4w6D0JfEJ1YnnE7eYl9GNuEeADfH40=','2021-02-24 08:32:31','System'),(15,'TransNameMap-15','trans_log_2021-02-25','kDPT6aX9yzw5u4w6D0JfEMcNkTn+Nx0ag7DNiAh03r0=','2021-02-25 08:42:52','System'),(16,'TransNameMap-16','trans_log_2021-03-01','kDPT6aX9yzw5u4w6D0JfEPrcE707n4xayJXTwW05N5Y=','2021-03-01 08:43:36','System'),(17,'TransNameMap-17','trans_log_2021-03-02','kDPT6aX9yzw5u4w6D0JfEI8n9ppbJnlz5WNvjuJ22Jw=','2021-03-02 09:22:44','System'),(18,'TransNameMap-18','trans_log_2021-03-03','kDPT6aX9yzw5u4w6D0JfEPaOCzkKUKitaZoHqjKXG5o=','2021-03-03 09:15:57','System'),(19,'TransNameMap-19','trans_log_2021-03-04','kDPT6aX9yzw5u4w6D0JfEAoqNKV3d+JcG1c2DOjI=','2021-03-04 09:17:12','System'),(20,'TransNameMap-20','trans_log_2021-03-05','kDPT6aX9yzw5u4w6D0JfEDlRKPHtJqIReyzXfrIEQE0=','2021-03-05 09:27:33','System'),(21,'TransNameMap-21','trans_log_2021-03-07','kDPT6aX9yzw5u4w6D0JfEOGUujOkwNH75vd635+cDo=','2021-03-07 11:43:49','System'),(22,'TransNameMap-22','trans_log_2021-03-08','kDPT6aX9yzw5u4w6D0JfEKw0AY4RI5xbfHQHd0HcyiQ=','2021-03-08 00:00:53','System'),(23,'TransNameMap-23','trans_log_2021-03-09','kDPT6aX9yzw5u4w6D0JfEGn9kVvjy6oY4otv9FaSRI=','2021-03-09 08:27:21','System'),(24,'TransNameMap-24','trans_log_2021-03-10','kDPT6aX9yzw5u4w6D0JfENTZX0F94gV0vsPmKC4Smog=','2021-03-10 00:00:27','System'),(25,'TransNameMap-25','trans_log_2021-03-11','kDPT6aX9yzw5u4w6D0JfEEhnEPxNTdpq6MCObwmJpU=','2021-03-11 08:46:47','System'),(26,'TransNameMap-26','trans_log_2021-03-12','kDPT6aX9yzw5u4w6D0JfECrz4z+a5uI8fToNr5onTww=','2021-03-12 08:59:07','System'),(27,'TransNameMap-27','trans_log_2021-03-15','kDPT6aX9yzw5u4w6D0JfEMR1MsYnX4YE3ddzlbj9PpU=','2021-03-15 08:54:32','System'),(28,'TransNameMap-28','trans_log_2021-03-16','kDPT6aX9yzw5u4w6D0JfEEDsPoyu2ojOcs4mXU8vPrI=','2021-03-16 08:36:17','System'),(29,'TransNameMap-29','trans_log_2021-03-17','kDPT6aX9yzw5u4w6D0JfEE6pUlUfhPDXR2wMmyXvFuc=','2021-03-17 08:42:41','System'),(30,'TransNameMap-30','trans_log_2021-03-18','kDPT6aX9yzw5u4w6D0JfEEgh5RLTg2DUjJIdFrOxOkY=','2021-03-18 11:59:07','System'),(31,'TransNameMap-31','trans_log_2021-03-19','kDPT6aX9yzw5u4w6D0JfELFoSH83Xfwhwd5EPATrqWM=','2021-03-19 08:51:16','System'),(32,'TransNameMap-32','trans_log_2021-03-30','kDPT6aX9yzw5u4w6D0JfEFuKryI5iyLlcwv18ag3Ha4=','2021-03-30 14:27:21','System'),(33,'TransNameMap-33','trans_log_2021-04-16','kDPT6aX9yzw5u4w6D0JfECy0rm74B0ubSLKiMx4e18=','2021-04-16 08:50:58','System'),(34,'TransNameMap-34','trans_log_2021-04-19','kDPT6aX9yzw5u4w6D0JfEH2nuCmUyibDCL16mTT91M=','2021-04-19 08:36:09','System'),(35,'TransNameMap-35','trans_log_2021-04-20','kDPT6aX9yzw5u4w6D0JfEPoihUQHFHjbB0WMCL8B2m8=','2021-04-20 08:51:11','System'),(36,'TransNameMap-36','transLog_2021-04-21','PWbjtI91THL1r7KBN2bcB1C2JizziSrG5R4BOm2rrM=','2021-04-21 08:48:40','System');
/*!40000 ALTER TABLE `tran_log_name_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_log_count`
--

DROP TABLE IF EXISTS `transaction_log_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_log_count` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOG_DATE` varchar(10) DEFAULT NULL,
  `FILE_NAME` varchar(200) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  `RECORD_COUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_log_count`
--

LOCK TABLES `transaction_log_count` WRITE;
/*!40000 ALTER TABLE `transaction_log_count` DISABLE KEYS */;
INSERT INTO `transaction_log_count` VALUES (1,'2021-04-21','L3OpUzzZ2Ge7BgfYR44kyfXjgEsijRCUh7CXsqE6ko=.cba','System',3),(2,'2021-04-21','ZyDCckM5k174Uj5rtf9xkvYoIHjQzYwhNtQRaUPWN5k=.cba','super',82),(3,'2021-04-22','L3OpUzzZ2Ge7BgfYR44k6OIcUl4wjF4w5XRqw6Omsw=.cba','System',259),(4,'2021-04-22','ZyDCckM5k174Uj5rtf9xksTzzxUJfBsQRWpPSxsltxw=.cba','super',2253),(5,'2021-04-22','uROkGxXi81mfl2NBd6Ju7VNT2mLxkUQpq7jqgENi30=.cba','null',9),(6,'2021-05-03','L3OpUzzZ2Ge7BgfYR44kz+KEC5kAbbef05MCg23lts=.cba','System',27),(7,'2021-05-31','L3OpUzzZ2Ge7BgfYR44k8DRlxePtnFEJ77HSjAfFFk=.cba','System',175),(8,'2021-05-31','uROkGxXi81mfl2NBd6Ju7XbkSa0lCqIPyX+igFmP2Wk=.cba','null',9),(9,'2021-05-31','ZyDCckM5k174Uj5rtf9xklOYwurWfUxyBcu88uE6oM=.cba','super',23),(10,'2021-06-07','uROkGxXi81mfl2NBd6Ju7Qk0EtrzQ4knDP8+tABX7es=.cba','null',27),(11,'2021-06-07','L3OpUzzZ2Ge7BgfYR44k8Z1y04nuHzibtbF9ZsZY=.cba','System',38),(12,'2021-06-08','L3OpUzzZ2Ge7BgfYR44k6pBPzP01+7q3tzEV90gc14=.cba','System',3),(13,'2021-06-08','ZyDCckM5k174Uj5rtf9xkliSZxOWCXGL8GszajUF18=.cba','super',159),(14,'2021-06-08','UPLOMNMEg7KaRSBV7Cu60F4xyQ08npLuyFVe3p2kVpw=.cba','',12),(15,'2021-06-08','rhjWRinHjsTGCLXPQhUFtXnr3pkmf9BE83ooCEr9jw=.cba','bbb',12),(16,'2021-06-09','ZyDCckM5k174Uj5rtf9xko8fMROxzyBQGVSQ3mJDY=.cba','super',20),(17,'2021-06-09','ZyDCckM5k174Uj5rtf9xko8fMROxzyBQGVSQ3mJDY=.cba','super',1),(18,'2021-06-10','uROkGxXi81mfl2NBd6Ju7VrRTW44o3elATsuDSvqwbs=.cba','null',59),(19,'2021-06-10','ZyDCckM5k174Uj5rtf9xkoxqk3dei3Xa9yydXm4nXok=.cba','super',104),(20,'2021-06-10','ZyDCckM5k174Uj5rtf9xkoxqk3dei3Xa9yydXm4nXok=.cba','super',1),(21,'2021-06-11','ZyDCckM5k174Uj5rtf9xksNymhJWv2nIcIrrynWT7BY=.cba','super',282),(22,'2021-06-12','ZyDCckM5k174Uj5rtf9xkp01NijoK1U+sWPoxVVGl8=.cba','super',1331),(23,'2021-06-12','ZyDCckM5k174Uj5rtf9xkp01NijoK1U+sWPoxVVGl8=.cba','super',1),(24,'2021-06-12','ZyDCckM5k174Uj5rtf9xkp01NijoK1U+sWPoxVVGl8=.cba','super',1),(25,'2021-06-12','ZyDCckM5k174Uj5rtf9xkp01NijoK1U+sWPoxVVGl8=.cba','super',1),(26,'2021-06-12','uROkGxXi81mfl2NBd6Ju7bpdWmc0q50CdGikI0LIXTA=.cba','null',59),(27,'2021-06-13','uROkGxXi81mfl2NBd6Ju7aj+Hc6MsctxgstkFuILLEA=.cba','null',279),(28,'2021-06-13','QJ8BOc1mQZis6uOyOajydbVgIdpb5KpbqcDObm3Ykc=.cba','qqq',379),(29,'2021-06-13','QJ8BOc1mQZis6uOyOajydbVgIdpb5KpbqcDObm3Ykc=.cba','qqq',1),(30,'2021-06-13','ZyDCckM5k174Uj5rtf9xktwY60KcLpR24OV7CZ534WQ=.cba','super',547),(31,'2021-06-13','ZyDCckM5k174Uj5rtf9xktwY60KcLpR24OV7CZ534WQ=.cba','super',1),(32,'2021-06-13','l4HNlmwGu7vU8L7eo6l5n7VgIdpb5KpbqcDObm3Ykc=.cba','aaa',763),(33,'2021-06-13','l4HNlmwGu7vU8L7eo6l5n7VgIdpb5KpbqcDObm3Ykc=.cba','aaa',1),(34,'2021-06-13','rhjWRinHjsTGCLXPQhUFrVgIdpb5KpbqcDObm3Ykc=.cba','bbb',88),(35,'2021-06-13','rhjWRinHjsTGCLXPQhUFrVgIdpb5KpbqcDObm3Ykc=.cba','bbb',1),(36,'2021-06-13','1cP3iEGg8C1bFjWQAUgpDLVgIdpb5KpbqcDObm3Ykc=.cba','ccc',40),(37,'2021-06-13','1cP3iEGg8C1bFjWQAUgpDLVgIdpb5KpbqcDObm3Ykc=.cba','ccc',1),(38,'2021-06-13','MTA5cqqsIATtUO5FnxmcLLVgIdpb5KpbqcDObm3Ykc=.cba','eee',41),(39,'2021-06-13','MTA5cqqsIATtUO5FnxmcLLVgIdpb5KpbqcDObm3Ykc=.cba','eee',1),(40,'2021-06-13','npVIgaASAuZrk4jCit+SObVgIdpb5KpbqcDObm3Ykc=.cba','fff',125),(41,'2021-06-13','npVIgaASAuZrk4jCit+SObVgIdpb5KpbqcDObm3Ykc=.cba','fff',1),(42,'2021-06-13','n4X26klZJ6kibvh0CfOedT3hxV1quQeyu69ZeUJKMrE=.cba','buddhika',86),(43,'2021-06-13','n4X26klZJ6kibvh0CfOedT3hxV1quQeyu69ZeUJKMrE=.cba','buddhika',1),(44,'2021-06-13','i5nyhvKsYGevXl41fRk6dbVgIdpb5KpbqcDObm3Ykc=.cba','zxc',20),(45,'2021-06-13','i5nyhvKsYGevXl41fRk6dbVgIdpb5KpbqcDObm3Ykc=.cba','zxc',1),(46,'2021-06-13','Ont+zgTyBdW6eITUPC0garVgIdpb5KpbqcDObm3Ykc=.cba','qaz',44),(47,'2021-06-13','thbvLaNrCysIih1GDkuG6j+Hc6MsctxgstkFuILLEA=.cba','qwer',20),(48,'2021-06-13','thbvLaNrCysIih1GDkuG6j+Hc6MsctxgstkFuILLEA=.cba','qwer',1),(49,'2021-06-13','j5wISU+TfSQ8Hvqv1s78GJHV+bzMkBe1eANbuG9O0=.cba','nethmini',40),(50,'2021-06-13','j5wISU+TfSQ8Hvqv1s78GJHV+bzMkBe1eANbuG9O0=.cba','nethmini',1),(51,'2021-06-13','MsJ6BVRCZn8gdIcAP6Y0rVgIdpb5KpbqcDObm3Ykc=.cba','asd',41),(52,'2021-06-13','MsJ6BVRCZn8gdIcAP6Y0rVgIdpb5KpbqcDObm3Ykc=.cba','asd',1),(53,'2021-06-13','a6mzuphBmOH726KDkTe857VgIdpb5KpbqcDObm3Ykc=.cba','mlp',20),(54,'2021-06-13','a6mzuphBmOH726KDkTe857VgIdpb5KpbqcDObm3Ykc=.cba','mlp',1),(55,'2021-06-13','y2Ru1PcbHP+5Q70hi9fCEWFI8yFBP0EhZSQDacidJ4=.cba','op',22),(56,'2021-06-14','l4HNlmwGu7vU8L7eo6l5n8PwnMBGizBC8dQhr22tV2U=.cba','aaa',330),(57,'2021-06-14','l4HNlmwGu7vU8L7eo6l5n8PwnMBGizBC8dQhr22tV2U=.cba','aaa',1),(58,'2021-06-14','RonZ0qikW7oGh+T07BhdakdHJARhmGJewYPmlCF7Vpc=.cba','ggg',41),(59,'2021-06-14','RonZ0qikW7oGh+T07BhdakdHJARhmGJewYPmlCF7Vpc=.cba','ggg',1),(60,'2021-06-14','fOK2MHUDLG5dvb8qM4Yu8PwnMBGizBC8dQhr22tV2U=.cba','hhh',41),(61,'2021-06-14','fOK2MHUDLG5dvb8qM4Yu8PwnMBGizBC8dQhr22tV2U=.cba','hhh',1),(62,'2021-06-14','fPZGz5i2R2v06VQYEuYNsPwnMBGizBC8dQhr22tV2U=.cba','iii',20),(63,'2021-06-14','fPZGz5i2R2v06VQYEuYNsPwnMBGizBC8dQhr22tV2U=.cba','iii',1),(64,'2021-06-14','ZyDCckM5k174Uj5rtf9xkmOhT5gmQ5L1Bf7AoGxnog=.cba','super',19),(65,'2021-06-14','npVIgaASAuZrk4jCit+SOcPwnMBGizBC8dQhr22tV2U=.cba','fff',66),(66,'2021-06-14','npVIgaASAuZrk4jCit+SOcPwnMBGizBC8dQhr22tV2U=.cba','fff',1),(67,'2021-06-14','GOYBCtbIhENHCpwP6jHt7MPwnMBGizBC8dQhr22tV2U=.cba','jjj',26),(68,'2021-06-14','j5wISU+TfSQ8Hvqv1s72pYslYNB+ns4QTsRh8reMk=.cba','nethmini',20),(69,'2021-06-14','j5wISU+TfSQ8Hvqv1s72pYslYNB+ns4QTsRh8reMk=.cba','nethmini',1),(70,'2021-06-14','uROkGxXi81mfl2NBd6Ju7eKxUOsu776Lqf+WnU1KY=.cba','null',21),(71,'2021-06-14','CBzDwlABxDdxxkKKZ5QjG+HxeIbVNFj91ZIcw5wHsJk=.cba','mytest',680),(72,'2021-06-14','CBzDwlABxDdxxkKKZ5QjG+HxeIbVNFj91ZIcw5wHsJk=.cba','mytest',1),(73,'2021-06-14','n4X26klZJ6kibvh0CfOedWPhKoyIPEYvlxs8AiA6QY=.cba','buddhika',20),(74,'2021-06-14','n4X26klZJ6kibvh0CfOedWPhKoyIPEYvlxs8AiA6QY=.cba','buddhika',1),(75,'2021-06-15','N1iVBxCWVyPxEQZtnic1q1h4UL7Dw2XL9doYhvrp9o=.cba','aaa',378),(76,'2021-06-15','N1iVBxCWVyPxEQZtnic1q1h4UL7Dw2XL9doYhvrp9o=.cba','aaa',1),(77,'2021-06-15','CBzDwlABxDdxxkKKZ5QjGyUBRB3pISrVup8OU3DDCvM=.cba','mytest',1618),(78,'2021-06-15','CBzDwlABxDdxxkKKZ5QjGyUBRB3pISrVup8OU3DDCvM=.cba','mytest',1),(79,'2021-06-15','Jd3+3Tave9tvnkgp14mUtczB1o7fJaKVIrAbsjCU8U=.cba','NethminiS',636),(80,'2021-06-15','Jd3+3Tave9tvnkgp14mUtczB1o7fJaKVIrAbsjCU8U=.cba','NethminiS',1),(81,'2021-06-15','ZyDCckM5k174Uj5rtf9xkmGXKXcUXStEgI0IDzW58ms=.cba','super',23),(82,'2021-06-15','ZyDCckM5k174Uj5rtf9xkmGXKXcUXStEgI0IDzW58ms=.cba','super',1),(83,'2021-06-16','Jd3+3Tave9tvnkgp14mUoTiUUVtK8YmwbSGHCuzPp8=.cba','NethminiS',881),(84,'2021-06-16','Jd3+3Tave9tvnkgp14mUoTiUUVtK8YmwbSGHCuzPp8=.cba','NethminiS',1),(85,'2021-06-16','l4HNlmwGu7vU8L7eo6l5n7Q+7ZrxeBqNSTYxlu7T6SA=.cba','aaa',396),(86,'2021-06-16','l4HNlmwGu7vU8L7eo6l5n7Q+7ZrxeBqNSTYxlu7T6SA=.cba','aaa',1),(87,'2021-06-16','CBzDwlABxDdxxkKKZ5QjG7P4MKfC37fSOUG7cBwLwg=.cba','mytest',566),(88,'2021-06-16','CBzDwlABxDdxxkKKZ5QjG7P4MKfC37fSOUG7cBwLwg=.cba','mytest',1),(89,'2021-06-17','Jd3+3Tave9tvnkgp14mUo5GKf5E+d6T5sWsipcuSFA=.cba','NethminiS',253),(90,'2021-06-17','Jd3+3Tave9tvnkgp14mUo5GKf5E+d6T5sWsipcuSFA=.cba','NethminiS',1),(91,'2021-06-18','Jd3+3Tave9tvnkgp14mUi1+uDy2qBz2d1K1peYTRsU=.cba','NethminiS',172),(92,'2021-06-18','Jd3+3Tave9tvnkgp14mUi1+uDy2qBz2d1K1peYTRsU=.cba','NethminiS',1),(93,'2021-06-19','Jd3+3Tave9tvnkgp14mUsFweQopNLDMfMB7u8DhqI0=.cba','NethminiS',804),(94,'2021-06-19','Jd3+3Tave9tvnkgp14mUsFweQopNLDMfMB7u8DhqI0=.cba','NethminiS',1),(95,'2021-06-20','cIvRqScs76ZWpo2UfftF48LbHz9yS5jnVsV6K0lefRw=.cba','NethminiS',190),(96,'2021-06-20','cIvRqScs76ZWpo2UfftF48LbHz9yS5jnVsV6K0lefRw=.cba','NethminiS',1),(97,'2021-06-20','UPLOMNMEg7KaRSBV7Cu60B9t2daPWNrnIlWSSxHQEg=.cba','',6),(98,'2021-06-20','UPLOMNMEg7KaRSBV7Cu60B9t2daPWNrnIlWSSxHQEg=.cba','',1),(99,'2021-06-21','Jd3+3Tave9tvnkgp14mUkOWfv5Vb9X0RUta79Vpm0A=.cba','NethminiS',191),(100,'2021-06-22','Jd3+3Tave9tvnkgp14mUh4hCsP5syi6p9MaREWHKM=.cba','NethminiS',35),(101,'2021-06-22','Jd3+3Tave9tvnkgp14mUh4hCsP5syi6p9MaREWHKM=.cba','NethminiS',1),(102,'2021-06-24','AbjRx+ZmQHYOiFW3NAyjKc+Oi7QT2y3N2v4Sw1xUR0=.cba','NethminiS',441),(103,'2021-06-24','AbjRx+ZmQHYOiFW3NAyjKc+Oi7QT2y3N2v4Sw1xUR0=.cba','NethminiS',1),(104,'2021-06-26','Jd3+3Tave9tvnkgp14mUhoeqfpC0yKc8KnRW03oIL4=.cba','NethminiS',260),(105,'2021-06-26','Jd3+3Tave9tvnkgp14mUhoeqfpC0yKc8KnRW03oIL4=.cba','NethminiS',1),(106,'2021-06-27','AbjRx+ZmQHYOiFW3NAyjKO43ertYzsOm6ZkOUhfVss=.cba','NethminiS',145),(107,'2021-06-27','AbjRx+ZmQHYOiFW3NAyjKO43ertYzsOm6ZkOUhfVss=.cba','NethminiS',1),(108,'2021-06-28','Jd3+3Tave9tvnkgp14mUilJv9FWSJL+L4IKURMTnM=.cba','NethminiS',44),(109,'2021-06-28','Jd3+3Tave9tvnkgp14mUilJv9FWSJL+L4IKURMTnM=.cba','NethminiS',1),(110,'2021-06-29','Jd3+3Tave9tvnkgp14mUtJ8AsDUb5dZQEY5pXWhgE=.cba','NethminiS',353),(111,'2021-06-29','Jd3+3Tave9tvnkgp14mUtJ8AsDUb5dZQEY5pXWhgE=.cba','NethminiS',1),(112,'2021-06-30','Jd3+3Tave9tvnkgp14mUgSDzaLW0Hxt+ULAiY+VXdk=.cba','NethminiS',471),(113,'2021-06-30','Jd3+3Tave9tvnkgp14mUgSDzaLW0Hxt+ULAiY+VXdk=.cba','NethminiS',1),(114,'2021-07-01','Jd3+3Tave9tvnkgp14mUh7FDkPEhOllCDmUcpIEyZY=.cba','NethminiS',458),(115,'2021-07-01','Jd3+3Tave9tvnkgp14mUh7FDkPEhOllCDmUcpIEyZY=.cba','NethminiS',1),(116,'2021-07-02','Jd3+3Tave9tvnkgp14mUhGltWYvowEr7UV2vn95cX8=.cba','NethminiS',1332),(117,'2021-07-02','Jd3+3Tave9tvnkgp14mUhGltWYvowEr7UV2vn95cX8=.cba','NethminiS',1),(118,'2021-07-03','Jd3+3Tave9tvnkgp14mUpb9Ub3lr1ZpkQl51STZUo=.cba','NethminiS',458),(119,'2021-07-03','Jd3+3Tave9tvnkgp14mUpb9Ub3lr1ZpkQl51STZUo=.cba','NethminiS',1),(120,'2021-07-04','Jd3+3Tave9tvnkgp14mUrVBeF8vlYq9NFBXghB33gM=.cba','NethminiS',21636),(121,'2021-07-04','Jd3+3Tave9tvnkgp14mUrVBeF8vlYq9NFBXghB33gM=.cba','NethminiS',1),(122,'2021-07-05','Jd3+3Tave9tvnkgp14mUphpB80BI+V550N0GAkGpfo=.cba','NethminiS',201),(123,'2021-07-05','Jd3+3Tave9tvnkgp14mUphpB80BI+V550N0GAkGpfo=.cba','NethminiS',1),(124,'2021-07-06','Jd3+3Tave9tvnkgp14mUr7LxHf+Lto1w2oI7mRxy4c=.cba','NethminiS',552),(125,'2021-07-06','Jd3+3Tave9tvnkgp14mUr7LxHf+Lto1w2oI7mRxy4c=.cba','NethminiS',1),(126,'2021-07-07','Jd3+3Tave9tvnkgp14mUvt7CVS4gV18Uly5Pz63USA=.cba','NethminiS',441),(127,'2021-07-07','Jd3+3Tave9tvnkgp14mUvt7CVS4gV18Uly5Pz63USA=.cba','NethminiS',1),(128,'2021-07-09','Jd3+3Tave9tvnkgp14mUqFhPyrpJYlrigi+m7HyDQ=.cba','NethminiS',351),(129,'2021-07-09','Jd3+3Tave9tvnkgp14mUqFhPyrpJYlrigi+m7HyDQ=.cba','NethminiS',1),(130,'2021-07-10','Jd3+3Tave9tvnkgp14mUs7LnNsPoUSspjZelewtMw=.cba','NethminiS',209),(131,'2021-07-10','Jd3+3Tave9tvnkgp14mUs7LnNsPoUSspjZelewtMw=.cba','NethminiS',1),(132,'2021-07-11','Jd3+3Tave9tvnkgp14mUvBfwl+LjliX0H3eAlV9K00=.cba','NethminiS',1489),(133,'2021-07-11','Jd3+3Tave9tvnkgp14mUvBfwl+LjliX0H3eAlV9K00=.cba','NethminiS',1),(134,'2021-07-12','Jd3+3Tave9tvnkgp14mUjHCv0OK5BLxI8bksToZE1w=.cba','NethminiS',202),(135,'2021-07-12','Jd3+3Tave9tvnkgp14mUjHCv0OK5BLxI8bksToZE1w=.cba','NethminiS',1),(136,'2021-07-17','AbjRx+ZmQHYOiFW3NAyjIdbAN2334UiGYapwGKkPFg=.cba','NethminiS',86),(137,'2021-07-17','AbjRx+ZmQHYOiFW3NAyjIdbAN2334UiGYapwGKkPFg=.cba','NethminiS',1),(138,'2021-08-01','Jd3+3Tave9tvnkgp14mUuJgcyidzO4qQjUY4Hplit4=.cba','NethminiS',105),(139,'2021-08-01','Jd3+3Tave9tvnkgp14mUuJgcyidzO4qQjUY4Hplit4=.cba','NethminiS',1),(140,'2021-08-04','Jd3+3Tave9tvnkgp14mUp48eOnBo07EoFmvIVt5qRQ=.cba','NethminiS',102),(141,'2021-08-04','Jd3+3Tave9tvnkgp14mUp48eOnBo07EoFmvIVt5qRQ=.cba','NethminiS',1),(142,'2021-08-05','Jd3+3Tave9tvnkgp14mUjiweXarwBcf53bb1t3O2jE=.cba','NethminiS',29),(143,'2021-08-05','Jd3+3Tave9tvnkgp14mUjiweXarwBcf53bb1t3O2jE=.cba','NethminiS',1),(144,'2021-08-15','uROkGxXi81mfl2NBd6Ju7T3SO2FZQFuj+dyFkUrQzyc=.cba','null',59),(145,'2021-08-15','Jd3+3Tave9tvnkgp14mUp81zOM6VZ30xro6uyhqce4=.cba','NethminiS',109),(146,'2021-08-15','Jd3+3Tave9tvnkgp14mUp81zOM6VZ30xro6uyhqce4=.cba','NethminiS',1),(147,'2021-08-21','Jd3+3Tave9tvnkgp14mUmNuXY17X1NYAJylGPPYwc=.cba','NethminiS',543),(148,'2021-08-21','Jd3+3Tave9tvnkgp14mUmNuXY17X1NYAJylGPPYwc=.cba','NethminiS',1),(149,'2021-08-22','Jd3+3Tave9tvnkgp14mUmv6mNWPmTprvs2F5oin+1s=.cba','NethminiS',210),(150,'2021-08-22','Jd3+3Tave9tvnkgp14mUmv6mNWPmTprvs2F5oin+1s=.cba','NethminiS',1),(151,'2021-08-23','Jd3+3Tave9tvnkgp14mUhlC3yRQbTU86FeSert1qM=.cba','NethminiS',386),(152,'2021-08-23','Jd3+3Tave9tvnkgp14mUhlC3yRQbTU86FeSert1qM=.cba','NethminiS',1),(153,'2021-08-23','Jd3+3Tave9tvnkgp14mUhlC3yRQbTU86FeSert1qM=.cba','NethminiS',1),(154,'2021-08-27','AbjRx+ZmQHYOiFW3NAyjIJtkjLK+eMFQPdDTurcBw=.cba','NethminiS',206),(155,'2021-08-27','AbjRx+ZmQHYOiFW3NAyjIJtkjLK+eMFQPdDTurcBw=.cba','NethminiS',1),(156,'2021-08-28','Jd3+3Tave9tvnkgp14mUj9srZb2MZ3XZ8pXL1Pd9lA=.cba','NethminiS',2307),(157,'2021-08-29','Jd3+3Tave9tvnkgp14mUkfPSlKqap6N2gzwzhrQIyY=.cba','NethminiS',75),(158,'2021-08-29','Jd3+3Tave9tvnkgp14mUkfPSlKqap6N2gzwzhrQIyY=.cba','NethminiS',1),(159,'2021-08-30','Jd3+3Tave9tvnkgp14mUqoJmxv2gCLVTjMj1jo19AY=.cba','NethminiS',890),(160,'2021-08-30','Jd3+3Tave9tvnkgp14mUqoJmxv2gCLVTjMj1jo19AY=.cba','NethminiS',1),(161,'2021-08-31','Jd3+3Tave9tvnkgp14mUrQaUX+XmzK8OJtUBxBpJA=.cba','NethminiS',217),(162,'2021-08-31','Jd3+3Tave9tvnkgp14mUrQaUX+XmzK8OJtUBxBpJA=.cba','NethminiS',1),(163,'2021-08-31','Jd3+3Tave9tvnkgp14mUrQaUX+XmzK8OJtUBxBpJA=.cba','NethminiS',1),(164,'2021-09-01','Jd3+3Tave9tvnkgp14mUjTZZB9wTbKtVeD69isIu98=.cba','NethminiS',168),(165,'2021-09-01','Jd3+3Tave9tvnkgp14mUjTZZB9wTbKtVeD69isIu98=.cba','NethminiS',1),(166,'2021-09-03','Jd3+3Tave9tvnkgp14mUoofNvADcn4H6xcDL2rLeLk=.cba','NethminiS',382),(167,'2021-09-03','Jd3+3Tave9tvnkgp14mUoofNvADcn4H6xcDL2rLeLk=.cba','NethminiS',1),(168,'2021-09-04','Jd3+3Tave9tvnkgp14mUuurirH7IrNZyEnbCsEtlVQ=.cba','NethminiS',1991),(169,'2021-09-05','Jd3+3Tave9tvnkgp14mUkH1VM83Ok+APdj5tPgZXwU=.cba','NethminiS',1674),(170,'2021-09-06','Jd3+3Tave9tvnkgp14mUqiDfRFOjKRodlL8vbrpr+s=.cba','NethminiS',237),(171,'2021-09-06','Jd3+3Tave9tvnkgp14mUqiDfRFOjKRodlL8vbrpr+s=.cba','NethminiS',1),(172,'2021-09-07','Jd3+3Tave9tvnkgp14mUrt4yJ0JCEO3QxKMuiyaEo=.cba','NethminiS',703),(173,'2021-09-07','Jd3+3Tave9tvnkgp14mUrt4yJ0JCEO3QxKMuiyaEo=.cba','NethminiS',1),(174,'2021-09-08','cIvRqScs76ZWpo2UfftF42+2dSjcXUSNH5mFqqXAKQ=.cba','NethminiS',214),(175,'2021-09-08','cIvRqScs76ZWpo2UfftF42+2dSjcXUSNH5mFqqXAKQ=.cba','NethminiS',1),(176,'2021-09-09','Jd3+3Tave9tvnkgp14mUllcCc58UqA5ewX8dxKcIY=.cba','NethminiS',365),(177,'2021-09-09','Jd3+3Tave9tvnkgp14mUllcCc58UqA5ewX8dxKcIY=.cba','NethminiS',1),(178,'2021-09-10','cIvRqScs76ZWpo2UfftF4ziCxILBLxHa1PLCBrHOPL0=.cba','NethminiS',802),(179,'2021-09-10','cIvRqScs76ZWpo2UfftF4ziCxILBLxHa1PLCBrHOPL0=.cba','NethminiS',1),(180,'2021-09-11','Jd3+3Tave9tvnkgp14mUhyHtsov7JOz4iFW3aIttQs=.cba','NethminiS',554),(181,'2021-09-16','Jd3+3Tave9tvnkgp14mUtDAclV78NGzxpHQPjFl4o4=.cba','NethminiS',55),(182,'2021-09-16','Jd3+3Tave9tvnkgp14mUtDAclV78NGzxpHQPjFl4o4=.cba','NethminiS',1),(183,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',595),(184,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(185,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(186,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(187,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(188,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(189,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(190,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(191,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(192,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(193,'2021-09-18','Jd3+3Tave9tvnkgp14mUttmRhufWPTiAz2aqySNyws=.cba','NethminiS',1),(194,'2021-09-19','Jd3+3Tave9tvnkgp14mUuzyIkpPI2b59nweM4na3ew=.cba','NethminiS',1925),(195,'2021-09-19','Jd3+3Tave9tvnkgp14mUuzyIkpPI2b59nweM4na3ew=.cba','NethminiS',1),(196,'2021-09-20','Jd3+3Tave9tvnkgp14mUhHTAQtgaOP0D0MULmPlbHw=.cba','NethminiS',1614),(197,'2021-09-20','Jd3+3Tave9tvnkgp14mUhHTAQtgaOP0D0MULmPlbHw=.cba','NethminiS',1),(198,'2021-09-20','Jd3+3Tave9tvnkgp14mUhHTAQtgaOP0D0MULmPlbHw=.cba','NethminiS',1),(199,'2021-09-20','uROkGxXi81mfl2NBd6Ju7W1QBO6y6lEIy41D9gpVrAk=.cba','null',1695),(200,'2021-09-21','uROkGxXi81mfl2NBd6Ju7azc1DEKGENPfg8ntI+7yRg=.cba','null',1293),(201,'2021-09-21','Jd3+3Tave9tvnkgp14mUpO37stNP6U80OX4PXdfoTY=.cba','NethminiS',8668),(202,'2021-09-21','Jd3+3Tave9tvnkgp14mUpO37stNP6U80OX4PXdfoTY=.cba','NethminiS',1),(203,'2021-09-22','Jd3+3Tave9tvnkgp14mUmtTfO4sNO4UEGn3F35YMe8=.cba','NethminiS',6134),(204,'2021-09-22','uROkGxXi81mfl2NBd6Ju7RYqNxEu+rtTsNKNdCTDpUM=.cba','null',150),(205,'2021-09-23','Jd3+3Tave9tvnkgp14mUskn1TyB27iAPKsrdi1SrQo=.cba','NethminiS',9529),(206,'2021-09-23','67krnlrR66BnlGeyS7Q9PhU7smW3hsB33UG6R1oFW5A=.cba','Nike',527),(207,'2021-09-24','67krnlrR66BnlGeyS7Q9Plx+PP2gkUPRug+rlaTjLs=.cba','Nike',2866),(208,'2021-09-24','Jd3+3Tave9tvnkgp14mUhYmNpaT8IaDyLGQaGrb8=.cba','NethminiS',14884),(209,'2021-09-24','Jd3+3Tave9tvnkgp14mUhYmNpaT8IaDyLGQaGrb8=.cba','NethminiS',1),(210,'2021-09-24','uROkGxXi81mfl2NBd6Ju7Vx+PP2gkUPRug+rlaTjLs=.cba','null',1584),(211,'2021-09-24','pYBPpvbGHss7TxQ8kxLhHc7UAxQKQe9iDo1S15iRo=.cba','customer1',67),(212,'2021-09-24','pYBPpvbGHss7TxQ8kxLhHc7UAxQKQe9iDo1S15iRo=.cba','customer1',1),(213,'2021-09-24','XqUSrUCFHT2fIBdGSzX0nc7UAxQKQe9iDo1S15iRo=.cba','supplier1',641),(214,'2021-09-24','XqUSrUCFHT2fIBdGSzX0nc7UAxQKQe9iDo1S15iRo=.cba','supplier1',1),(215,'2021-09-25','XqUSrUCFHT2fIBdGSzX0qtdUXecvRzDh9Dr8aqERFk=.cba','supplier1',2433),(216,'2021-09-25','YC6lcI5n7fWuRp1hZNghTdOesZv1ROzBR0KKAS0CKrw=.cba','null',723),(217,'2021-09-25','XcCSsXWgN82zd5jQ6+ZnxtBcmVBdbiDQo16kVIRX5L8=.cba','NethminiS',22882),(218,'2021-09-25','XcCSsXWgN82zd5jQ6+ZnxtBcmVBdbiDQo16kVIRX5L8=.cba','NethminiS',1),(219,'2021-09-25','I8SXBYzZohrDep3vps4YSdOesZv1ROzBR0KKAS0CKrw=.cba','Nike',3251),(220,'2021-09-25','I8SXBYzZohrDep3vps4YSdOesZv1ROzBR0KKAS0CKrw=.cba','Nike',1),(221,'2021-09-25','7VuYKO3CleoNT9XMJGcim7+Pzq2gNupD6lC8SoY9NLM=.cba','mycustomer',67),(222,'2021-09-25','7VuYKO3CleoNT9XMJGcim7+Pzq2gNupD6lC8SoY9NLM=.cba','mycustomer',1),(223,'2021-09-25','q8DVJ1FydaAG25YMdYehHH73S+F2AEtBF4HN7fIPs=.cba','mysupplier',125),(224,'2021-09-25','q8DVJ1FydaAG25YMdYehHH73S+F2AEtBF4HN7fIPs=.cba','mysupplier',1),(225,'2021-09-25','Fxfa61yQG6qrmuUpFW0DeSMuWEwuBC41IOdxufioC+U=.cba','zxcvbnm',41),(226,'2021-09-25','Fxfa61yQG6qrmuUpFW0DeSMuWEwuBC41IOdxufioC+U=.cba','zxcvbnm',1),(227,'2021-09-25','6ECGc1zLDtz1OavnaTH0Iwgi1b65nArS1a+x19vyoI=.cba','asdfghjkl',22),(228,'2021-09-25','6ECGc1zLDtz1OavnaTH0Iwgi1b65nArS1a+x19vyoI=.cba','asdfghjkl',1),(229,'2021-09-26','XcCSsXWgN82zd5jQ6+ZnxvMczj5itKYeGh8dF6xbfjw=.cba','NethminiS',12),(230,'2021-12-02','YC6lcI5n7fWuRp1hZNghTd3iDvtkaUZp8y5whWn0uxQ=.cba','null',144),(231,'2021-12-03','YC6lcI5n7fWuRp1hZNghTbu9NpkoKRujaMnnnzMcvk=.cba','null',270),(232,'2021-12-03','XcCSsXWgN82zd5jQ6+ZnxnpvbzT9kF7MB6lR9102o=.cba','NethminiS',422),(233,'2021-12-03','XcCSsXWgN82zd5jQ6+ZnxnpvbzT9kF7MB6lR9102o=.cba','NethminiS',1),(234,'2021-12-03','I8SXBYzZohrDep3vps4YSbu9NpkoKRujaMnnnzMcvk=.cba','Nike',37),(235,'2021-12-03','I8SXBYzZohrDep3vps4YSbu9NpkoKRujaMnnnzMcvk=.cba','Nike',1),(236,'2021-12-03','XqUSrUCFHT2fIBdGSzX0oJdyqqKTZfY19G8m3iC86E=.cba','supplier1',57),(237,'2021-12-03','XqUSrUCFHT2fIBdGSzX0oJdyqqKTZfY19G8m3iC86E=.cba','supplier1',1);
/*!40000 ALTER TABLE `transaction_log_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_privilege`
--

DROP TABLE IF EXISTS `user_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_privilege` (
  `USER_PRIVILEGE_ID` int(11) NOT NULL,
  `USER_PRIVILEGE_CODE` varchar(30) NOT NULL,
  `USER_PRIV_NAME` varchar(50) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(30) DEFAULT NULL,
  `IS_ACTIVE` int(11) NOT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `PARENT_LEVEL` int(11) DEFAULT NULL,
  `IS_ASSIGNED` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_PRIVILEGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_privilege`
--

LOCK TABLES `user_privilege` WRITE;
/*!40000 ALTER TABLE `user_privilege` DISABLE KEYS */;
INSERT INTO `user_privilege` VALUES (1,'USRPRIV-1','ROOT','2021-01-11 17:04:39','super','2021-01-26 16:44:20','buddhika5',1,0,-1,1),(2,'USRPRIV-2','Global Settings','2021-01-11 17:04:39','super',NULL,NULL,1,1,0,1),(3,'USRPRIV-3','Role And Permissions','2021-01-11 17:04:39','super',NULL,NULL,1,2,1,1),(4,'USRPRIV-4','View','2021-01-11 17:04:39','super',NULL,NULL,1,3,2,1),(5,'USRPRIV-5','Add','2021-01-11 17:04:39','super',NULL,NULL,1,4,2,1),(6,'USRPRIV-6','Edit','2021-01-11 17:04:39','super',NULL,NULL,1,5,2,1),(7,'USRPRIV-7','Delete','2021-01-11 17:04:39','super',NULL,NULL,1,6,2,1),(8,'USRPRIV-8','User','2021-01-11 17:04:39','super',NULL,NULL,1,7,0,1),(9,'USRPRIV-9','View','2021-01-11 17:04:39','super',NULL,NULL,1,8,7,1),(10,'USRPRIV-10','Add','2021-01-11 17:04:39','super',NULL,NULL,1,9,7,1),(11,'USRPRIV-11','Edit','2021-01-11 17:04:39','super',NULL,NULL,1,10,7,1),(12,'USRPRIV-12','Delete','2021-01-11 17:04:39','super',NULL,NULL,1,11,7,1),(13,'USRPRIV-13','Change Password','2021-01-11 17:04:39','super',NULL,NULL,1,12,7,1);
/*!40000 ALTER TABLE `user_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_privilege_h`
--

DROP TABLE IF EXISTS `user_privilege_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_privilege_h` (
  `USER_PRIVILEGE_H_ID` int(11) NOT NULL,
  `USER_PRIVILEGE_CODE` varchar(30) NOT NULL,
  `USER_PRIV_NAME` varchar(50) NOT NULL,
  `HIERARCHY_STATUS` varchar(10) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `IS_ACTIVE` int(11) NOT NULL,
  PRIMARY KEY (`USER_PRIVILEGE_H_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_privilege_h`
--

LOCK TABLES `user_privilege_h` WRITE;
/*!40000 ALTER TABLE `user_privilege_h` DISABLE KEYS */;
INSERT INTO `user_privilege_h` VALUES (1,'USRPRIV-1','ADD EMPLOYEE','000','2021-01-11 18:22:42','super',1),(2,'USRPRIV-1','aaa','001','2021-01-11 18:23:58','super',1),(3,'USRPRIV-1','aaa','001','2021-01-11 18:34:31','super',1),(4,'USRPRIV-1','null','null','2021-01-11 18:36:43','super',1),(5,'USRPRIV-1','null','null','2021-01-11 20:14:05','super',0),(6,'USRPRIV-1','null','null','2021-01-15 16:48:50','super',0),(7,'USRPRIV-1','asas','null','2021-01-15 16:49:11','super',0),(8,'USRPRIV-1','asas','null','2021-01-15 16:49:59','super',0),(9,'USRPRIV-1','asas','null','2021-01-15 16:50:35','super',1),(10,'USRPRIV-1','ASAS','null','2021-01-15 16:53:01','super',1),(11,'USRPRIV-1','ASAS','null','2021-01-17 13:47:11','super',0),(12,'USRPRIV-1','ASAS','null','2021-01-26 09:03:20','super',0),(13,'USRPRIV-1','ASAS','null','2021-01-26 09:03:22','super',0),(14,'USRPRIV-1','ASAS','null','2021-01-26 09:03:57','super',0),(15,'USRPRIV-1','ASAS','null','2021-01-26 09:12:05','super',0),(16,'USRPRIV-1','ASAS','null','2021-01-26 09:13:02','super',0),(17,'USRPRIV-1','ASAS','null','2021-01-26 09:13:39','super',0),(18,'USRPRIV-1','ASAS','null','2021-01-26 09:14:34','super',0),(19,'USRPRIV-1','ASAS','null','2021-01-26 09:19:45','super',0),(20,'USRPRIV-1','ASAS','null','2021-01-26 09:20:09','super',0),(21,'USRPRIV-1','ASAS','null','2021-01-26 09:20:11','super',0),(22,'USRPRIV-11','VIEW EMPLOEE1','04','2021-01-26 15:29:27','buddhika5',1),(23,'USRPRIV-11','UPDATE-1','04','2021-01-26 15:38:24','buddhika5',1),(24,'USRPRIV-1','ASAS','null','2021-01-26 16:44:05','buddhika5',0),(25,'USRPRIV-1','ASAS','null','2021-01-26 16:44:20','buddhika5',0);
/*!40000 ALTER TABLE `user_privilege_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `USER_ROLE_ID` int(11) NOT NULL,
  `USER_ROLE_CODE` varchar(30) NOT NULL,
  `USER_ROLE_NAME` varchar(50) NOT NULL,
  `IS_ACTIVE` int(11) DEFAULT NULL,
  `CREATED_AT` datetime(6) NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `UPDATED_BY` varchar(30) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `PARENT_LEVEL` int(11) DEFAULT NULL,
  `IS_ASSIGNED` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'USROLE-1','SUPER ADMIN',1,'2020-12-30 14:55:55.541000','super','2021-03-10 10:58:57.091000','super',0,-1,1),(2,'USROLE-2','ADMIN 1',1,'2021-03-10 14:05:49.112000','super','2021-03-10 15:45:45.121000','super',1,0,0),(3,'USROLE-3','CUSTOMER',1,'2021-03-10 14:06:57.015000','super','2021-03-10 15:45:45.596000','super',2,1,0),(4,'USROLE-4','SUPPLIER',1,'2021-03-10 14:08:08.891000','super','2021-03-10 15:45:45.914000','super',3,2,0),(5,'USROLE-5','TEST1',1,'2021-03-10 14:08:54.133000','super','2021-03-10 15:45:46.287000','super',4,3,1),(6,'USROLE-6','TEST2',1,'2021-03-10 14:09:01.113000','super','2021-03-10 15:58:30.442000','super',7,3,0),(7,'USROLE-7','SERVICE MANAGER',1,'2021-03-10 14:09:53.135000','super','2021-03-10 15:58:30.696000','super',8,1,0),(8,'USROLE-8','ADMIN 2',1,'2021-03-10 14:11:08.072000','super','2021-03-10 15:58:31.047000','super',9,0,0),(9,'USROLE-9','QAZ',0,'2021-03-10 14:13:40.293000','super','2021-03-10 15:57:33.424000','super',5,4,0),(10,'USROLE-10','ACCOUNTANT2',1,'2021-03-10 15:58:31.262000','super','2021-03-10 16:01:14.090000','super',6,4,NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_h`
--

DROP TABLE IF EXISTS `user_role_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_h` (
  `USER_ROLE_H_ID` int(11) NOT NULL,
  `USER_ROLE_CODE` varchar(30) NOT NULL,
  `USER_ROLE_NAME` varchar(50) NOT NULL,
  `IS_ACTIVE` int(11) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `PARENT_LEVEL` int(11) DEFAULT NULL,
  `IS_ASSIGNED` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ROLE_H_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_h`
--

LOCK TABLES `user_role_h` WRITE;
/*!40000 ALTER TABLE `user_role_h` DISABLE KEYS */;
INSERT INTO `user_role_h` VALUES (1,'USROLE-2','ADMIN 1',1,'2021-03-09 09:33:32','super',1,0,0),(2,'USROLE-8','ADMIN 2',1,'2021-03-10 15:36:36','super',8,0,0),(3,'USROLE-8','ADMIN 2',1,'2021-03-10 15:38:25','super',8,0,0),(4,'USROLE-2','ADMIN 1',1,'2021-03-10 15:45:10','super',1,0,0),(5,'USROLE-3','MANAGER',1,'2021-03-10 15:45:11','super',2,1,0),(6,'USROLE-4','SUPERVISOR',1,'2021-03-10 15:45:12','super',3,2,0),(7,'USROLE-5','TEST1',1,'2021-03-10 15:45:12','super',4,3,0),(8,'USROLE-6','TEST2',1,'2021-03-10 15:45:13','super',6,3,0),(9,'USROLE-7','SERVICE MANAGER',1,'2021-03-10 15:45:13','super',7,1,0),(10,'USROLE-9','QAZ',1,'2021-03-10 15:45:14','super',5,4,0),(11,'USROLE-2','ADMIN 1',0,'2021-03-10 15:45:45','super',1,0,0),(12,'USROLE-3','MANAGER',0,'2021-03-10 15:45:45','super',2,1,0),(13,'USROLE-4','SUPERVISOR',0,'2021-03-10 15:45:46','super',3,2,0),(14,'USROLE-5','TEST1',0,'2021-03-10 15:45:46','super',4,3,0),(15,'USROLE-6','TEST2',0,'2021-03-10 15:45:46','super',6,3,0),(16,'USROLE-7','SERVICE MANAGER',0,'2021-03-10 15:45:47','super',7,1,0),(17,'USROLE-9','QAZ',0,'2021-03-10 15:45:47','super',5,4,0),(18,'USROLE-9','QAZ',1,'2021-03-10 15:57:33','super',5,4,0),(19,'USROLE-10','QAZLK',1,'2021-03-10 16:01:14','super',6,4,NULL);
/*!40000 ALTER TABLE `user_role_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_map`
--

DROP TABLE IF EXISTS `user_role_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_map` (
  `URM_ID` int(11) NOT NULL,
  `PARENT_USR_ROLE_CODE` varchar(30) DEFAULT NULL,
  `CHILD_USR_ROLE_CODE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`URM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_map`
--

LOCK TABLES `user_role_map` WRITE;
/*!40000 ALTER TABLE `user_role_map` DISABLE KEYS */;
INSERT INTO `user_role_map` VALUES (1,'USROLE-1','USROLE-2'),(2,'USROLE-2','USROLE-3'),(3,'USROLE-1','USROLE-3'),(4,'USROLE-3','USROLE-4'),(5,'USROLE-2','USROLE-4'),(6,'USROLE-1','USROLE-4'),(7,'USROLE-4','USROLE-5'),(8,'USROLE-3','USROLE-5'),(9,'USROLE-2','USROLE-5'),(10,'USROLE-1','USROLE-5'),(11,'USROLE-4','USROLE-6'),(12,'USROLE-3','USROLE-6'),(13,'USROLE-2','USROLE-6'),(14,'USROLE-1','USROLE-6'),(15,'USROLE-2','USROLE-7'),(16,'USROLE-1','USROLE-7'),(17,'USROLE-1','USROLE-8'),(18,'USROLE-5','USROLE-9'),(19,'USROLE-4','USROLE-9'),(20,'USROLE-3','USROLE-9'),(21,'USROLE-2','USROLE-9'),(22,'USROLE-1','USROLE-9'),(23,'USROLE-5','USROLE-10'),(24,'USROLE-4','USROLE-10'),(25,'USROLE-3','USROLE-10'),(26,'USROLE-2','USROLE-10'),(27,'USROLE-1','USROLE-10');
/*!40000 ALTER TABLE `user_role_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_with_privilege`
--

DROP TABLE IF EXISTS `user_with_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_with_privilege` (
  `USR_WITH_PRIV_ID` int(11) NOT NULL,
  `USR_WITH_PRIV_CODE` varchar(30) NOT NULL,
  `USER_ROLE_CODE` varchar(30) NOT NULL,
  `USER_PRIVILEGE_CODE` varchar(30) NOT NULL,
  `IS_ADDED` int(11) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`USR_WITH_PRIV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_with_privilege`
--

LOCK TABLES `user_with_privilege` WRITE;
/*!40000 ALTER TABLE `user_with_privilege` DISABLE KEYS */;
INSERT INTO `user_with_privilege` VALUES (1,'USRWITHPRIV-1','USROLE-1','USRPRIV-1',0,'2021-01-11 21:34:17','super','2021-01-12 11:32:52','super'),(2,'USRWITHPRIV-2','USROLE-9','USRPRIV-3',1,'2021-01-15 11:22:48','super',NULL,NULL),(3,'USRWITHPRIV-3','USROLE-9','USRPRIV-4',1,'2021-01-15 11:22:48','super',NULL,NULL),(4,'USRWITHPRIV-4','USROLE-9','USRPRIV-5',1,'2021-01-15 11:22:48','super',NULL,NULL),(5,'USRWITHPRIV-5','USROLE-9','USRPRIV-6',1,'2021-01-15 11:22:48','super',NULL,NULL),(6,'USRWITHPRIV-6','USROLE-9','USRPRIV-7',0,'2021-01-15 11:22:48','super','2021-01-15 16:54:59','super'),(7,'USRWITHPRIV-7','USROLE-9','USRPRIV-8',1,'2021-01-15 11:22:48','super',NULL,NULL),(8,'USRWITHPRIV-8','USROLE-13','USRPRIV-10',1,'2021-01-26 14:58:37','super',NULL,NULL),(9,'USRWITHPRIV-9','USROLE-13','USRPRIV-11',1,'2021-01-26 14:58:38','super','2021-01-26 16:31:13','buddhika5'),(10,'USRWITHPRIV-10','USROLE-13','USRPRIV-9',0,'2021-01-26 16:32:09','buddhika5','2021-01-26 16:34:00','buddhika5');
/*!40000 ALTER TABLE `user_with_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_with_privilege_h`
--

DROP TABLE IF EXISTS `user_with_privilege_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_with_privilege_h` (
  `USR_WITH_PRIV_H_ID` int(11) NOT NULL,
  `USR_WITH_PRIV_CODE` varchar(30) NOT NULL,
  `USER_ROLE_CODE` varchar(30) NOT NULL,
  `USER_PRIVILEGE_CODE` varchar(30) NOT NULL,
  `IS_ADDED` int(11) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  PRIMARY KEY (`USR_WITH_PRIV_H_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_with_privilege_h`
--

LOCK TABLES `user_with_privilege_h` WRITE;
/*!40000 ALTER TABLE `user_with_privilege_h` DISABLE KEYS */;
INSERT INTO `user_with_privilege_h` VALUES (1,'USRWITHPRIV-1','USROLE-1','USRPRIV-1',0,'2021-01-12 11:28:17','super'),(2,'USRWITHPRIV-1','USROLE-1','USRPRIV-1',0,'2021-01-12 11:28:19','super'),(3,'USRWITHPRIV-1','USROLE-1','USRPRIV-1',0,'2021-01-12 11:28:20','super'),(4,'USRWITHPRIV-1','USROLE-1','USRPRIV-1',0,'2021-01-12 11:32:52','super'),(5,'USRWITHPRIV-6','USROLE-9','USRPRIV-7',1,'2021-01-15 16:54:59','super'),(6,'USRWITHPRIV-9','USROLE-13','USRPRIV-11',1,'2021-01-26 15:57:57','buddhika5'),(7,'USRWITHPRIV-9','USROLE-13','USRPRIV-11',1,'2021-01-26 16:18:38','buddhika5'),(8,'USRWITHPRIV-9','USROLE-13','USRPRIV-11',0,'2021-01-26 16:18:45','buddhika5'),(9,'USRWITHPRIV-9','USROLE-13','USRPRIV-11',1,'2021-01-26 16:31:12','buddhika5'),(10,'USRWITHPRIV-10','USROLE-13','USRPRIV-9',1,'2021-01-26 16:34:00','buddhika5');
/*!40000 ALTER TABLE `user_with_privilege_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccination`
--

DROP TABLE IF EXISTS `vaccination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccination` (
  `VACC_ID` int(11) NOT NULL,
  `VACC_CODE` varchar(45) NOT NULL,
  `PET_CODE` varchar(45) DEFAULT NULL,
  `VACC_NAME` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `EXPIRATION_DATE` date DEFAULT NULL,
  PRIMARY KEY (`VACC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccination`
--

LOCK TABLES `vaccination` WRITE;
/*!40000 ALTER TABLE `vaccination` DISABLE KEYS */;
INSERT INTO `vaccination` VALUES (1,'VACC-1','PET-1','Rabies','super','2021-09-25 05:29:07',NULL,NULL,'2021-09-25'),(2,'VACC-2','PET-1','Bordetella','super','2021-09-25 05:30:07',NULL,NULL,'2021-09-27'),(3,'VACC-3','PET-2','Rabies','super','2021-09-25 09:51:51',NULL,NULL,'2021-09-27');
/*!40000 ALTER TABLE `vaccination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinary`
--

DROP TABLE IF EXISTS `veterinary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinary` (
  `VET_ID` int(11) NOT NULL,
  `VET_CODE` varchar(45) NOT NULL,
  `VET_PHONE` varchar(45) DEFAULT NULL,
  `VET_ADDRESS` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `VET_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`VET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinary`
--

LOCK TABLES `veterinary` WRITE;
/*!40000 ALTER TABLE `veterinary` DISABLE KEYS */;
INSERT INTO `veterinary` VALUES (1,'VET-1','0223222342','Colombo','super','2021-06-24 10:26:35','super','2021-09-18 23:17:47','Mark Nick'),(2,'VET-2','0712491107','Main Street,Kegalle','super','2021-07-06 10:28:45','super','2021-09-20 11:32:06','Daya  Senanayake'),(4,'VET-4','0872244551','Colombo','super','2021-09-08 15:13:33',NULL,NULL,'John'),(6,'VET-6','0787878997','Colombo','super','2021-09-23 18:22:32',NULL,NULL,'Emmy Stock'),(7,'VET-7','0897657567','Colombo','super','2021-09-25 01:20:54','super','2021-09-25 01:21:05','Watson');
/*!40000 ALTER TABLE `veterinary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04  9:58:38
