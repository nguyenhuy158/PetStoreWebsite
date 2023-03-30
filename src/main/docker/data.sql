-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: PetStore
-- ------------------------------------------------------
-- Server version	8.0.32

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
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Food',NULL),(2,'Accessories',NULL),(3,'Bag',NULL),(4,'Toys',NULL),(5,'Clothes',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_thumbnail`
--

DROP TABLE IF EXISTS `list_thumbnail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_thumbnail` (
  `id` bigint NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  KEY `FKg2v3y4bfg68md05asgi3mpvid` (`id`),
  CONSTRAINT `FKg2v3y4bfg68md05asgi3mpvid` FOREIGN KEY (`id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_thumbnail`
--

LOCK TABLES `list_thumbnail` WRITE;
/*!40000 ALTER TABLE `list_thumbnail` DISABLE KEYS */;
INSERT INTO `list_thumbnail` VALUES (1,'https://product.hstatic.net/200000264739/product/yem_dat_kem_tui_zi_chen_cho_cho_meo_3_90ac87660f1548dfb5db1e5bdd077b84_master.jpg'),(1,'https://product.hstatic.net/200000264739/product/yem_dat_kem_tui_zi_chen_cho_cho_meo_4_d4fbcc64e09d4abc96bdbcf0491cd7c7_master.jpg'),(1,'https://product.hstatic.net/200000264739/product/yem_dat_kem_tui_zi_chen_cho_cho_meo_5_cbd83c2132784145afcb13b8e75249af_master.jpg'),(1,'https://product.hstatic.net/200000264739/product/yem_dat_kem_tui_zi_chen_cho_cho_meo_6_12cb5b073ffb4a1b97818084d5484de4_master.jpg'),(2,'https://product.hstatic.net/200000264739/product/yem_dat_phan_quang_2_f2f4b79efe414262aaad6f2af74139fb_master.jpg'),(2,'https://product.hstatic.net/200000264739/product/yem_dat_phan_quang_1_245246dc50874303b6d4c01068a9d3f8_master.jpg'),(2,'https://product.hstatic.net/200000264739/product/yem_dat_phan_quang_50de666037844aedb45d450625189280_master.jpg'),(2,'https://product.hstatic.net/200000264739/product/yem_dat_phan_quang_3_f3bd318186414fdfa9fee37ba6b51bc3_master.jpg'),(3,'https://product.hstatic.net/200000264739/product/snack_inaba_juicy_bites_11.3g_cho_meo_b48d2f316a094b8a881d15ea3c404fce_master.jpg'),(3,'https://product.hstatic.net/200000264739/product/snack_inaba_juicy_bites_11.3g_cho_meo_b48d2f316a094b8a881d15ea3c404fce_master.jpg'),(3,'https://product.hstatic.net/200000264739/product/juicybite3_fa9ee4c25f184335a198c03168d6c2fb_master.jpg'),(3,'https://product.hstatic.net/200000264739/product/juicybite2_a6f4d4f5baf94529a7333cdb8e912363_master.jpg'),(4,'https://product.hstatic.net/200000264739/product/soi_ca_ngu_149951cc7f7a4f3d8f322717d772dbef_master.jpg'),(4,'https://product.hstatic.net/200000264739/product/soi_ca_ngu_1_c31a524bdf9345d59e30adfd89a90a57_master.jpg'),(5,'https://product.hstatic.net/200000264739/product/thit_tuoi_kitcat_fresh_fillet_cho_meo_813f32b274fc41838ab78e26a0fc15c7_master.jpg'),(5,'https://product.hstatic.net/200000264739/product/thit_tuoi_kitcat_fresh_fillet_cho_meo_2_52c0a7ed87734374b9e40d4fa83c2421_master.jpg'),(5,'https://product.hstatic.net/200000264739/product/thit_tuoi_kitcat_fresh_fillet_cho_meo_5_4ac384aadf0044028f8fae5387d2dee4_master.jpg'),(5,'https://product.hstatic.net/200000264739/product/thit_tuoi_kitcat_fresh_fillet_cho_meo_3_5ee31a187f3b4d028cb5f0743f5fc301_master.jpg'),(6,'https://product.hstatic.net/200000264739/product/balo_moorpet_e656ba3b83cf4d46bbfea2de910a0857_master.jpg'),(6,'https://product.hstatic.net/200000264739/product/balo_moorpet_4_633f8d2130014662a2af69f060e2aef5_master.jpg'),(6,'https://product.hstatic.net/200000264739/product/balo_moorpet_5_6b974bc3993b4cab9bcf52c1371ef84b_master.jpg'),(6,'https://product.hstatic.net/200000264739/product/balo_moorpet_10_49b4e6f33792488792ab45e0e9abc996_master.jpg'),(7,'https://product.hstatic.net/200000264739/product/vali_keo_ebda998dea544ed4a24769db67b223d1_master.jpg'),(7,'https://product.hstatic.net/200000264739/product/vali_keo_2_3e1c7e7e844d4ad4907aee6ee0435a33_master.jpg'),(7,'https://product.hstatic.net/200000264739/product/vali_keo_3_22c46bc1a2b1452188da271e6a84ecc4_master.jpg'),(7,'https://product.hstatic.net/200000264739/product/vali_keo_1_e7eaa6e09a52417ebbf2afc8c4b4a73b_master.jpg'),(8,'https://product.hstatic.net/200000264739/product/lon_trong_co_meo_sunsun_2b7f71927efb43a28eebb84cfcf0dfca_master.jpg'),(8,'https://product.hstatic.net/200000264739/product/lon_trong_co_meo_sunsun_2_630c52a3cb1644eea70a8c609672d868_master.jpg'),(8,'https://product.hstatic.net/200000264739/product/lon_trong_co_meo_sunsun_3_0880d527f98747af83e3fbe9dd619bdf_master.jpg'),(8,'https://product.hstatic.net/200000264739/product/lon_trong_co_meo_sunsun_1_8a9bf59c55c14ee4ba808971738f9342_master.jpg'),(9,'https://product.hstatic.net/200000264739/product/catnip_hoat_hinh_f5bb57487e7748ebb828668e4f10f67b_master.jpg'),(9,'https://product.hstatic.net/200000264739/product/catnip_hoat_hinh_3_3dd419e2bd5344da865db06f552cadd3_master.jpg'),(9,'https://product.hstatic.net/200000264739/product/catnip_hoat_hinh_1_146e34e2295947d58185ff6b10666011_master.jpg'),(9,'https://product.hstatic.net/200000264739/product/catnip_hoat_hinh_4_bfec4deb0bed4a6baa83c17caa97b145_master.jpg'),(10,'https://product.hstatic.net/200000264739/product/ao_dua_hau__ao_con_cua_1_4faec12ffb00437a989ba4eb8f6c2ce7_master.jpg'),(10,'https://product.hstatic.net/200000264739/product/ao_dua_hau__ao_con_cua_2_93e0d510b2d64e668bd78d5e066348ef_master.jpg'),(10,'https://product.hstatic.net/200000264739/product/ao_dua_hau__ao_con_cua_3_da3dc3aac98c4386a8586d77deb6c4db_master.jpg'),(10,'https://product.hstatic.net/200000264739/product/ao_dua_hau__ao_con_cua_6_59f1a92457dd4391bdf8c2f00335878f_master.jpg'),(11,'https://product.hstatic.net/200000264739/product/ao_gau_70ccb9be83aa42bfb61f53da6e499585_master.jpg'),(11,'https://product.hstatic.net/200000264739/product/ao_gau_2_d676399413d843ffbff117596b6cb29a_master.jpg'),(11,'https://product.hstatic.net/200000264739/product/ao_gau_1_da998fe0f17346f5bca20efbc75c7f5a_master.jpg'),(11,'https://product.hstatic.net/200000264739/product/ao_gau_3_293bd364e23a4dbb8899e94237156a43_master.jpg');
/*!40000 ALTER TABLE `list_thumbnail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `sale_date` datetime(6) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('gear',1,123,'Yếm dắt kèm túi Zi Chen cho chó mèo','Yếm dắt thú cưng ',150000,4,NULL,NULL,NULL,NULL,NULL,2),('gear',2,123,'Yếm dắt phản quang cho chó mèo','Yếm dắt phản quang ',150000,4,NULL,NULL,NULL,NULL,NULL,2),('gear ',3,99,'Khối lượng tịnh: 33,9g/túi','Snack mềm Inaba Juicy Bites',16000,4,NULL,NULL,NULL,NULL,NULL,1),('gear ',4,60,'Khối lượng tịnh: 15g/túi','Sợi cá ngừ sấy 15g',25000,4,NULL,NULL,NULL,NULL,NULL,1),('gear ',5,70,'Đối tượng: mèo mọi lứa tuổi','Thịt tươi Kitcat Fresh Fillet',30000,4,NULL,NULL,NULL,NULL,NULL,1),('gear',6,69,'Balo phi thuyền Moorpet cho chó mèo','Balo phi thuyền Moorpet',620000,4,NULL,NULL,NULL,NULL,NULL,3),('gear',7,36,'Vali kéo vận chuyển chó mèo','Vali kéo vận chuyển',1450000,4,NULL,NULL,NULL,NULL,NULL,3),('gear',8,80,'Lon trồng cỏ mèo tươi SUN SUN chống búi lông, giúp bé tiêu hóa tốt, thơm miệng, giảm stress','Lon trồng cỏ mèo tươi Sun Sun',35000,4,NULL,NULL,NULL,NULL,NULL,4),('gear',9,50,'Kẹo dán tường Catnip hoạt hình cho mèo','Kẹo dán tường Catnip hoạt hình',42000,4,NULL,NULL,NULL,NULL,NULL,4),('gear',10,40,'Chất liệu: Vải Do cài đặt màn hình và điều kiện ánh sáng khác nhau, màu sắc thực tế của sản phẩm có thể hơi khác so với hình ảnh','Áo dưa hấu, áo con cua',45000,4,NULL,NULL,NULL,NULL,NULL,5),('gear',11,30,'Áo sát nách hình gấu cho chó mèo','Áo sát nách hình gấu',50000,4,NULL,NULL,NULL,NULL,NULL,5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsg7jp0aj6qipr50856wf6vbw1` (`user_id`),
  CONSTRAINT `FKsg7jp0aj6qipr50856wf6vbw1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_detail`
--

DROP TABLE IF EXISTS `transaction_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `transaction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0202i7eat7u1q5i9sxm1442k` (`product_id`),
  KEY `FK2nh7hmi2mfurimsk0viq4a127` (`transaction_id`),
  CONSTRAINT `FK2nh7hmi2mfurimsk0viq4a127` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKp0202i7eat7u1q5i9sxm1442k` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_detail`
--

LOCK TABLES `transaction_detail` WRITE;
/*!40000 ALTER TABLE `transaction_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123 Trần Hưng Đạo ','nguyenvana@gmail.com','Nam','Nguyễn Văn A',NULL,'0903285186 ','nguyenvana'),(2,'678 Trần Phú','nguyenvanb@gmail.com','Nữ','Nguyễn Văn B',NULL,'0123856236','nguyenvanb'),(3,'357 Lê Lợi ','nguyenvanc@gmail.com','Nam','Nguyễn Văn C',NULL,'0189236742','nguyenvanc');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'PetStore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-29 17:00:18

INSERT INTO PetStore.`user` (address,email,gender,name,password,phone,username) VALUES
	 ('Quan 7, HCM','ntqhuy2k2@gmail.com',NULL,'Nguyen Huy','$2a$10$Lk6xnMgxHnY3mTiLnxapS.XcFnKsqpFFW9Tdb6LqxJRHRwqGGJtl6',"0837377855",'1');
