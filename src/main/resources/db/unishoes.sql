CREATE DATABASE  IF NOT EXISTS `marketplace_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `marketplace_db`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: marketplace_db
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Corrida'),(2,'Casual'),(12,'Chuteira'),(13,'Chinelo');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `papel`
--

DROP TABLE IF EXISTS `papel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `papel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr0myf68c69dgjedxpdnbpdrhb` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `papel`
--

LOCK TABLES `papel` WRITE;
/*!40000 ALTER TABLE `papel` DISABLE KEYS */;
INSERT INTO `papel` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `papel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(38,2) DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  `imagem_url` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8rqw0ljwdaom34jr2t46bjtrn` (`categoria_id`),
  CONSTRAINT `FK8rqw0ljwdaom34jr2t46bjtrn` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (2,'Nike Dunk High Retro Midnight Navy','Nike Dunk High Retro Midnight Navy',700.00,2,'/uploads/1761937959317_dunk high.webp'),(3,'Pra você que pisa mas não pisa. ','Oakley - Flack Low',950.00,2,'/uploads/1761938296452_Oakley - Flack Low.webp'),(16,'Tenis Nike Af1 Shadow Off','Tenis Nike Af1 Shadow Off',1000.00,2,'/uploads/1761938200742_tenis_nike_af1_shadow_off_rosa_31713_1_a70d91c4fee9c132b29889fd352314ec.webp'),(17,'Air Jordan 4 Red Thunder','Air Jordan 4 Red Thunder',1000.00,2,'/uploads/1761938061960_Jordan 4.webp'),(29,'Tênis Adidas Originals Campus 00s | Unissex | Adidas | Cinza','Tênis Adidas Originals Campus 00s',400.00,2,'/uploads/1761938145555_tenis-adidas-campus-794830.jpg'),(30,'Você acha que pisa mas não pisa.','Pisa pé',690.00,13,'/uploads/1761364822236_br-11134201-7r98o-lni02ecxfqhbb1.jfif');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'admin@teste.com','admin','$2a$12$4oJontmFjhLAGO3/emG3/eXlTmhr4pEi/qTJDw1oZhoZiGGRKJPP6',_binary '',NULL),(3,'admin@exemplo.com',NULL,'$2a$10$UDf1vo2vMg.Up2mOhT58Iexyuwviy86V/OTnrNYf9xZM/wzm1nGuW',_binary '',NULL),(4,'user@exemplo.com',NULL,'$2a$10$pDkAPHfQ4t/tYavqPrx5deBCE7BU2ZNJfFHxD0xzjNfGZdzTWSjBy',_binary '',NULL),(6,'admin@unishoes.com','Admin Unishoes','$2a$10$PA.zZuIPj7n7GRjsuQHwX.eoSIjK0i9p0pGIABWDZpvEqNb39VFNa',_binary '',NULL),(7,'usuarioteste@unishoes.com','Usuario Unishoes','$2a$10$CW8X84zxqx59NeVWuEFi1u6plFFVhGyGYQylwfQ0vOg1mCFyBQlma',_binary '',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_papel`
--

DROP TABLE IF EXISTS `usuario_papel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_papel` (
  `usuario_id` bigint NOT NULL,
  `papel_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`papel_id`),
  KEY `FK75psa5ndkgp28gqowe8em3n6q` (`papel_id`),
  CONSTRAINT `FK75psa5ndkgp28gqowe8em3n6q` FOREIGN KEY (`papel_id`) REFERENCES `papel` (`id`),
  CONSTRAINT `FKpwds94wb43d01jx8l62ukbd67` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_papel`
--

LOCK TABLES `usuario_papel` WRITE;
/*!40000 ALTER TABLE `usuario_papel` DISABLE KEYS */;
INSERT INTO `usuario_papel` VALUES (2,1),(3,1),(6,1),(4,2),(7,2);
/*!40000 ALTER TABLE `usuario_papel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-01 20:58:53
