CREATE DATABASE  IF NOT EXISTS `cloudlanes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cloudlanes`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: cloudlanes
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `app_settings`
--

DROP TABLE IF EXISTS `app_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_settings` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `setting_name` varchar(45) DEFAULT NULL,
  `setting_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_settings`
--

LOCK TABLES `app_settings` WRITE;
/*!40000 ALTER TABLE `app_settings` DISABLE KEYS */;
INSERT INTO `app_settings` VALUES (1,'nextId','1'),(2,'maxIdLimit','254'),(3,'naaNextId1','0'),(4,'naaNextId2','0'),(5,'naaNextId3','0'),(6,'nextBarcodeId','1');
/*!40000 ALTER TABLE `app_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drive`
--

DROP TABLE IF EXISTS `drive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drive` (
  `drive_id` int(11) NOT NULL AUTO_INCREMENT,
  `drive_name` varchar(50) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `vtl_id` int(11) DEFAULT NULL,
  `is_empty` tinyint(4) DEFAULT '1',
  `model_id` int(11) DEFAULT NULL,
  `scsi_dev_file_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`drive_id`),
  KEY `fk_drive_vtl_idx` (`vtl_id`),
  KEY `fk_drive_model_idx` (`model_id`),
  KEY `fk_drive_vendor` (`vendor_id`),
  CONSTRAINT `fk_drive_model` FOREIGN KEY (`model_id`) REFERENCES `drive_model` (`model_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_drive_vendor` FOREIGN KEY (`vendor_id`) REFERENCES `drive_vendor` (`vendor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_drive_vtl` FOREIGN KEY (`vtl_id`) REFERENCES `vtl` (`vtl_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drive`
--

LOCK TABLES `drive` WRITE;
/*!40000 ALTER TABLE `drive` DISABLE KEYS */;
/*!40000 ALTER TABLE `drive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drive_model`
--

DROP TABLE IF EXISTS `drive_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drive_model` (
  `model_id` int(11) NOT NULL,
  `model_name` varchar(45) DEFAULT NULL,
  `vtl_brand_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`model_id`),
  KEY `fk_drive_model_vtl_brand_idx` (`vtl_brand_id`),
  CONSTRAINT `fk_drive_model_vtl_brand` FOREIGN KEY (`vtl_brand_id`) REFERENCES `vtl_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drive_model`
--

LOCK TABLES `drive_model` WRITE;
/*!40000 ALTER TABLE `drive_model` DISABLE KEYS */;
INSERT INTO `drive_model` VALUES (1,'T10000A',1),(2,'T10000B',1),(3,'T10000C',1),(4,'9940A',1),(5,'9940B',1),(6,'9840A',1),(7,'9840B',1),(8,'9840C',1),(9,'9840D',1),(10,'Ultrium 3-SCSI',1),(11,'Ultrium 4-SCSI',1),(12,'Ultrium 5-SCSI',1),(13,'Ultrium 6-SCSI',1),(14,'DLT7000',1),(15,'SDLT600',2),(16,'SDX-500C',3),(17,'SDX-700C',3),(18,'SDX-900V',3),(19,'ULT3580-TD1',4),(20,'ULT3580-TD2',4),(21,'ULT3580-TD3',4),(22,'ULT3580-TD4',4),(23,'ULT3580-TD5',4),(24,'ULT3580-TD6',4),(25,'ULT3580-HH4',4),(26,'ULTRIUM-TD1',4),(27,'ULTRIUM-TD2',4),(28,'ULTRIUM-TD3',4),(29,'ULTRIUM-TD4',4),(30,'ULTRIUM-TD5',4),(31,'ULTRIUM-TD6',4),(32,'03592E05',4),(33,'03592J1A',4),(34,'SDLT600',5),(35,'DLT7000',5),(36,'ULTRIUM-TD1',6),(37,'Ultrium 1-SCSI',7),(38,'Ultrium 2-SCSI',7),(39,'Ultrium 3-SCSI',7),(40,'Ultrium 4-SCSI',7),(41,'Ultrium 5-SCSI',7),(42,'Ultrium 6-SCSI',7),(43,'ULT3580-TD1',8),(44,'ULT3580-TD2',8),(45,'ULT3580-TD3',8),(46,'ULT3580-TD4',8),(47,'ULT3580-TD5',8),(48,'Ultrium 1-SCSI',9),(49,'Ultrium 2-SCSI',9),(50,'Ultrium 3-SCSI',9),(51,'Ultrium 4-SCSI',9),(52,'Ultrium 5-SCSI',9),(53,'Ultrium 6-SCSI',9);
/*!40000 ALTER TABLE `drive_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drive_vendor`
--

DROP TABLE IF EXISTS `drive_vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drive_vendor` (
  `vendor_id` int(11) NOT NULL,
  `vendor_name` varchar(45) DEFAULT NULL,
  `vtl_brand_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  KEY `fk_drive_vendor_vtl_brand_idx` (`vtl_brand_id`),
  CONSTRAINT `fk_drive_vendor_vtl_brand` FOREIGN KEY (`vtl_brand_id`) REFERENCES `vtl_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drive_vendor`
--

LOCK TABLES `drive_vendor` WRITE;
/*!40000 ALTER TABLE `drive_vendor` DISABLE KEYS */;
INSERT INTO `drive_vendor` VALUES (1,'STK',1),(2,'HP',1),(3,'Quantum',1),(4,'Spectra',2),(5,'Sony',3),(6,'IBM',4),(7,'Quantum',5),(8,'Dell',6),(9,'HP',7),(10,'ADIC',8),(11,'Overland',9);
/*!40000 ALTER TABLE `drive_vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_id` int(11) NOT NULL,
  `job_name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `tape_id` int(11) DEFAULT NULL,
  `vault_id` int(11) DEFAULT NULL,
  `creation_ts` bigint(20) DEFAULT NULL,
  `start_ts` bigint(20) DEFAULT NULL,
  `completion_ts` bigint(20) DEFAULT NULL,
  `status_message` varchar(45) DEFAULT NULL,
  `hasError` tinyint(4) DEFAULT '0',
  `percentComplete` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  KEY `fk_job_tape_idx` (`tape_id`),
  KEY `fk_job_vault_idx` (`vault_id`),
  CONSTRAINT `fk_job_tape` FOREIGN KEY (`tape_id`) REFERENCES `tape` (`tape_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_vault` FOREIGN KEY (`vault_id`) REFERENCES `vault` (`vault_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_type`
--

DROP TABLE IF EXISTS `media_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(45) DEFAULT NULL,
  `vtl_brand_id` int(11) DEFAULT NULL,
  `raw_capacity_mb` int(11) DEFAULT NULL,
  `barcode_prefix` varchar(2) DEFAULT NULL,
  `barcode_suffix` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  KEY `fk_media_type_vtl_brand_idx` (`vtl_brand_id`),
  CONSTRAINT `fk_media_type_vtl_brand` FOREIGN KEY (`vtl_brand_id`) REFERENCES `vtl_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_type`
--

LOCK TABLES `media_type` WRITE;
/*!40000 ALTER TABLE `media_type` DISABLE KEYS */;
INSERT INTO `media_type` VALUES (1,'T10000A',1,512000,NULL,NULL),(2,'T10000B',1,1024000,NULL,NULL),(3,'T10000C',1,5120000,NULL,NULL),(4,'9940A',1,61440,NULL,NULL),(5,'9940B',1,204800,NULL,NULL),(6,'9840A',1,20480,NULL,NULL),(7,'9840B',1,20480,NULL,NULL),(8,'9840C',1,40960,NULL,NULL),(9,'9840D',1,76800,NULL,NULL),(10,'Ultrium 3-SCSI',1,500,NULL,NULL),(11,'Ultrium 4-SCSI',1,500,NULL,NULL),(12,'Ultrium 5-SCSI',1,500,NULL,NULL),(13,'Ultrium 6-SCSI',1,500,NULL,NULL),(14,'DLT7000',1,35840,NULL,NULL),(15,'SDLT600',2,307200,NULL,NULL),(16,'AIT4',3,204800,NULL,NULL),(17,'AIT3',3,102400,NULL,NULL),(18,'AIT2',3,51200,NULL,NULL),(19,'LTO1',4,102400,NULL,NULL),(20,'LTO2',4,204800,NULL,NULL),(21,'LTO3',4,409600,NULL,NULL),(22,'LTO4',4,819200,NULL,NULL),(23,'LTO5',4,1572864,NULL,NULL),(24,'LTO6',4,2621440,NULL,NULL),(25,'J1A',4,307200,NULL,NULL),(26,'E05',4,512000,NULL,NULL),(27,'SDLT600',5,307200,NULL,NULL),(28,'DLT7000',5,35840,NULL,NULL),(29,'LTO1',6,102400,NULL,NULL),(30,'LTO1',7,102400,NULL,NULL),(31,'LTO2',7,204800,NULL,NULL),(32,'LTO3',7,409600,NULL,NULL),(33,'LTO4',7,819200,NULL,NULL),(34,'LTO5',7,1572864,NULL,NULL),(35,'LTO6',7,2621440,NULL,NULL),(36,'LTO1',8,102400,NULL,NULL),(37,'LTO2',8,204800,NULL,NULL),(38,'LTO3',8,409600,NULL,NULL),(39,'LTO4',8,819200,NULL,NULL),(40,'LTO5',8,1572864,NULL,NULL),(41,'LTO1',9,102400,NULL,NULL),(42,'LTO2',9,204800,NULL,NULL),(43,'LTO3',9,409600,NULL,NULL),(44,'LTO4',9,819200,NULL,NULL),(45,'LTO5',9,1572864,NULL,NULL),(46,'LTO6',9,2621440,NULL,NULL);
/*!40000 ALTER TABLE `media_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy` (
  `policy_id` int(11) NOT NULL AUTO_INCREMENT,
  `policy_key` varchar(45) DEFAULT NULL,
  `policy_name` varchar(45) DEFAULT NULL,
  `policy_value` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`policy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slot`
--

DROP TABLE IF EXISTS `slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slot` (
  `slot_id` int(11) NOT NULL AUTO_INCREMENT,
  `slot_name` varchar(50) DEFAULT NULL,
  `is_empty` tinyint(4) DEFAULT '1',
  `vtl_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`slot_id`),
  KEY `fk_slot_vtl_idx` (`vtl_id`),
  CONSTRAINT `fk_slot_vtl` FOREIGN KEY (`vtl_id`) REFERENCES `vtl` (`vtl_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slot`
--

LOCK TABLES `slot` WRITE;
/*!40000 ALTER TABLE `slot` DISABLE KEYS */;
/*!40000 ALTER TABLE `slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tape`
--

DROP TABLE IF EXISTS `tape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tape` (
  `tape_id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(45) DEFAULT NULL,
  `label` varchar(45) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `vtl_id` int(11) DEFAULT NULL,
  `is_loaded` tinyint(4) DEFAULT '0',
  `drive_id` int(11) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `candidate_ts` bigint(20) DEFAULT NULL,
  `vault_ts` bigint(20) DEFAULT NULL,
  `size_mb` int(11) DEFAULT NULL,
  PRIMARY KEY (`tape_id`),
  KEY `fk_tape_vtl_idx` (`vtl_id`),
  KEY `fk_tape_type_idx` (`type_id`),
  KEY `fk_tape_slot_idx` (`slot_id`),
  KEY `fk_tape_drive_idx` (`drive_id`),
  CONSTRAINT `fk_tape_drive` FOREIGN KEY (`drive_id`) REFERENCES `drive` (`drive_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tape_slot` FOREIGN KEY (`slot_id`) REFERENCES `slot` (`slot_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tape_type` FOREIGN KEY (`type_id`) REFERENCES `media_type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tape_vtl` FOREIGN KEY (`vtl_id`) REFERENCES `vtl` (`vtl_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tape`
--

LOCK TABLES `tape` WRITE;
/*!40000 ALTER TABLE `tape` DISABLE KEYS */;
/*!40000 ALTER TABLE `tape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('CloudLanes','cloudlanes',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vault`
--

DROP TABLE IF EXISTS `vault`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vault` (
  `vault_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `account_name` varchar(45) NOT NULL,
  `access_key` varchar(256) DEFAULT NULL,
  `secret_key` varchar(45) DEFAULT NULL,
  `container` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vault_id`),
  UNIQUE KEY `account_name_UNIQUE` (`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vault`
--

LOCK TABLES `vault` WRITE;
/*!40000 ALTER TABLE `vault` DISABLE KEYS */;
/*!40000 ALTER TABLE `vault` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vtl`
--

DROP TABLE IF EXISTS `vtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vtl` (
  `vtl_id` int(11) NOT NULL AUTO_INCREMENT,
  `vtl_name` varchar(50) DEFAULT NULL,
  `vtl_brand_id` int(11) DEFAULT NULL,
  `vtl_model_id` int(11) DEFAULT NULL,
  `slot_count` int(11) DEFAULT NULL,
  `ie_port_count` int(11) DEFAULT NULL,
  `drive_count` int(11) DEFAULT NULL,
  `compression_enabled` tinyint(4) DEFAULT '0',
  `empty_slot_count` int(11) DEFAULT NULL,
  `vault_id` int(11) DEFAULT NULL,
  `picker_count` int(11) DEFAULT NULL,
  `compression_factor` int(11) DEFAULT NULL,
  `scsi_dev_file_path` varchar(50) DEFAULT NULL,
  `vtl_lib_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`vtl_id`),
  KEY `fk_vtl_brand_idx` (`vtl_brand_id`),
  KEY `fk_vtl_model` (`vtl_model_id`),
  KEY `fk_vtl_vault_idx` (`vault_id`),
  CONSTRAINT `fk_vtl_brand` FOREIGN KEY (`vtl_brand_id`) REFERENCES `vtl_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vtl_model` FOREIGN KEY (`vtl_model_id`) REFERENCES `vtl_model` (`model_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vtl_vault` FOREIGN KEY (`vault_id`) REFERENCES `vault` (`vault_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vtl`
--

LOCK TABLES `vtl` WRITE;
/*!40000 ALTER TABLE `vtl` DISABLE KEYS */;
/*!40000 ALTER TABLE `vtl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vtl_brand`
--

DROP TABLE IF EXISTS `vtl_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vtl_brand` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vtl_brand`
--

LOCK TABLES `vtl_brand` WRITE;
/*!40000 ALTER TABLE `vtl_brand` DISABLE KEYS */;
INSERT INTO `vtl_brand` VALUES (1,'STK'),(2,'Spectra'),(3,'Sony'),(4,'IBM'),(5,'Quantum'),(6,'Dell'),(7,'HP'),(8,'ADIC'),(9,'Overland');
/*!40000 ALTER TABLE `vtl_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vtl_model`
--

DROP TABLE IF EXISTS `vtl_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vtl_model` (
  `model_id` int(11) NOT NULL,
  `model_name` varchar(45) DEFAULT NULL,
  `vtl_brand_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`model_id`),
  KEY `fk_vtl_model_brand_idx` (`vtl_brand_id`),
  CONSTRAINT `fk_vtl_model_brand` FOREIGN KEY (`vtl_brand_id`) REFERENCES `vtl_brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vtl_model`
--

LOCK TABLES `vtl_model` WRITE;
/*!40000 ALTER TABLE `vtl_model` DISABLE KEYS */;
INSERT INTO `vtl_model` VALUES (1,'SL150',1),(2,'SL500',1),(3,'SL3000',1),(4,'L700',1),(5,'L180',1),(6,'L120',1),(7,'PYTHON',2),(8,'GECKO',2),(9,'LIB-302',3),(10,'LIB-152',3),(11,'LIB-152E',3),(12,'03584L22',4),(13,'03584L32',4),(14,'ULT-3582-TL',4),(15,'3577-TL',4),(16,'3573-TL',4),(17,'03590H11',4),(18,'Dxi6700',5),(19,'Scalar i6000',5),(20,'Scalar i500',5),(21,'PX720',5),(22,'DX5000',5),(23,'PV-136T',6),(24,'EML E-Series',7),(25,'MSL G3 Series',7),(26,'MSL6000 Series',7),(27,'ESL E-Series',7),(28,'ThinStor AutoLdr',7),(29,'1*8 autoloader',7),(30,'VLS',7),(31,'Scalar i2000',8),(32,'Scalar 1000',8),(33,'NEO Series',9),(34,'REO Series',9);
/*!40000 ALTER TABLE `vtl_model` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-16 17:57:28
