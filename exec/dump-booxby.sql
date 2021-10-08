-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: j5b203.p.ssafy.io    Database: booxby
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `review_score` int DEFAULT '0',
  `review_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `review_like` tinyint(1) DEFAULT '0',
  `review_idea` tinyint(1) DEFAULT '0',
  `review_useful` tinyint(1) DEFAULT '0',
  `review_read` tinyint(1) DEFAULT '0',
  `isbn` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `img_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `review_FK_1` (`user_id`),
  CONSTRAINT `review_FK_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'2021-09-27 09:47:21',0,'stringtest',0,1,1,1,'string','test1111','string'),(2,5,'2021-09-28 13:02:29',4,'굿굿굿',0,0,1,1,'9788932918389','잠 :베르나르 베르베르 장편소설','http://image.aladin.co.kr/product/10971/7/cover/8932918384_3.jpg'),(3,5,'2021-09-28 13:02:57',4,'좋아요',0,1,0,1,'9788932918389','잠 :베르나르 베르베르 장편소설','http://image.aladin.co.kr/product/10971/7/cover/8932918384_3.jpg'),(4,5,'2021-09-28 13:03:36',1,'책이재밌네요 !',0,1,0,1,'9788932918389','잠 :베르나르 베르베르 장편소설','http://image.aladin.co.kr/product/10971/7/cover/8932918384_3.jpg'),(5,8,'2021-10-01 16:08:16',4,'test',0,0,0,0,'9788965401742','수학의 참견 :방정식으로 바라본 건강, 돈, 사랑의 해법','http://image.aladin.co.kr/product/11020/66/cover/8965401747_1.jpg'),(6,8,'2021-10-01 16:11:38',4,'주식투자',0,0,0,0,'9791191347180','매매의 기술 - 박병창의 돈을 부르는','https://image.aladin.co.kr/product/27135/25/cover/k652731246_1.jpg'),(7,8,'2021-10-01 17:24:13',3,'리뷰리뷰',1,1,0,0,'9791191347180','매매의 기술 - 박병창의 돈을 부르는','https://image.aladin.co.kr/product/27135/25/cover/k652731246_1.jpg'),(8,9,'2021-10-05 12:42:27',4,'올ㅋ',0,1,0,0,'9788935703111','리얼리티 트랜서핑 =Reality transurfing','http://image.aladin.co.kr/product/347/89/cover/8935703117_1.jpg'),(9,17,'2021-10-05 14:37:20',5,'2',0,0,1,0,'9788935703975','왓칭 =시야를 넓힐수록 마법처럼 이루어진다.Watching','http://image.aladin.co.kr/product/7541/76/cover/8935703974_1.jpg'),(10,8,'2021-10-06 10:17:48',4,'다이어트에 딱좋아요',1,1,0,1,'9788928106516','(날씬튼튼 몸매짱짱) 다이어트','https://image.aladin.co.kr/product/22268/2/cover/8928106516_1.jpg'),(11,8,'2021-10-06 11:03:02',4,'눈꽃 재밌어요@@',1,0,1,1,'9788963711690','눈꽃 :홍수연 장편소설','http://image.aladin.co.kr/product/4480/35/cover/8963711692_1.jpg'),(12,8,'2021-10-06 11:03:16',4,'아무말 대잔치네 진짜',1,0,1,0,'9788928315949','아무 말 대잔치','https://image.aladin.co.kr/product/15225/20/cover/8928315948_1.jpg'),(13,8,'2021-10-07 01:47:50',5,'코딩 테스트 준비할 때, 많은 도움이 되었어요!! 추천합니다!',1,0,0,1,'9791162243077','이것이 취업을 위한 코딩 테스트다 with 파이썬 :취업과 이직을 결정하는 알고리즘 인터뷰 완벽 가이드','https://image.aladin.co.kr/product/24788/21/cover/k342631735_1.jpg'),(14,14,'2021-10-07 01:49:14',4,'취업을 준비하고 있다면 필수로 봐야하는 책!',0,0,0,0,'9791162243077','이것이 취업을 위한 코딩 테스트다 with 파이썬 :취업과 이직을 결정하는 알고리즘 인터뷰 완벽 가이드','https://image.aladin.co.kr/product/24788/21/cover/k342631735_1.jpg'),(15,8,'2021-10-07 09:39:41',5,'죽음, 또다른 세계, 유머러스한 인물들, 딱 베르베르다운 소설이다. 베르베르의 상상력은 어디까지 뻗어갈까. 벌써 베르베르의 다음 작품이 기대된다!!',0,1,0,0,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(16,16,'2021-10-07 09:41:43',4,'2편을 구매해서 빨리 읽어보고싶다!!',0,0,1,1,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(17,18,'2021-10-07 09:43:06',4,'‘죽음‘과 사후의 전개라는 소재로 시작하였지만, 이야기의 이면으로 들어가보면 각 존재(사람, 영매, 영혼)들 사이의 에피소드를 통해 ‘소통‘의 중요성에 대한 작가의 진정성을 볼 수 있었어요.',0,1,0,1,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(18,17,'2021-10-07 10:22:05',4,'흥미로웠어요!',0,1,0,0,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(20,29,'2021-10-07 16:00:33',1,'저한텐 어려운 책이었습니다.... 별로입니다....ㅠㅠ',0,1,0,0,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(21,24,'2021-10-07 17:28:00',4,'재미있었습니다~',0,1,1,1,'9788932919676','죽음 :베르나르 베르베르 장편소설','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg'),(22,30,'2021-10-07 17:32:39',5,'너무 좋은 책!',0,0,1,1,'9791156751069','달님의 산책','http://image.aladin.co.kr/product/9007/19/cover/k222535901_1.jpg');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_count`
--

DROP TABLE IF EXISTS `review_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_count` (
  `review_count_id` int NOT NULL AUTO_INCREMENT,
  `review_like_cnt` int DEFAULT '0',
  `review_idea_cnt` int DEFAULT '0',
  `review_useful_cnt` int DEFAULT '0',
  `review_read_cnt` int DEFAULT '0',
  `isbn` varchar(500) NOT NULL,
  PRIMARY KEY (`review_count_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_count`
--

LOCK TABLES `review_count` WRITE;
/*!40000 ALTER TABLE `review_count` DISABLE KEYS */;
INSERT INTO `review_count` VALUES (1,1,1,1,1,'string'),(2,0,2,1,3,'9788932918389'),(3,0,0,0,0,'9788965401742'),(4,1,1,0,0,'9791191347180'),(5,0,1,0,0,'9788935703111'),(6,0,0,1,0,'9788935703975'),(7,1,1,0,1,'9788928106516'),(8,1,0,1,1,'9788963711690'),(9,1,0,1,0,'9788928315949'),(10,1,0,0,1,'9791162243077'),(11,0,6,3,4,'9788932919676'),(12,0,0,1,1,'9791156751069');
/*!40000 ALTER TABLE `review_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrap`
--

DROP TABLE IF EXISTS `scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scrap` (
  `scrap_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `isbn` varchar(500) NOT NULL,
  `img_url` varchar(500) DEFAULT NULL,
  `title` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`scrap_id`),
  KEY `scrap_FK` (`user_id`),
  CONSTRAINT `scrap_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrap`
--

LOCK TABLES `scrap` WRITE;
/*!40000 ALTER TABLE `scrap` DISABLE KEYS */;
INSERT INTO `scrap` VALUES (1,5,'9788932918389','http://image.aladin.co.kr/product/10971/7/cover/8932918384_3.jpg','잠 :베르나르 베르베르 장편소설'),(3,8,'9788965401742','http://image.aladin.co.kr/product/11020/66/cover/8965401747_1.jpg','수학의 참견 :방정식으로 바라본 건강, 돈, 사랑의 해법'),(4,8,'9791188754328','https://image.aladin.co.kr/product/25400/6/cover/k392633011_1.jpg','(1타 7피) 주식 초보 최고 계략'),(5,8,'9791191347180','https://image.aladin.co.kr/product/27135/25/cover/k652731246_1.jpg','매매의 기술 - 박병창의 돈을 부르는'),(6,8,'9788937461033','https://bookthumb-phinf.pstatic.net/cover/001/510/00151055.jpg?type=m1&udate=20130129','인간 실격'),(7,8,'9791165210731','https://image.aladin.co.kr/product/23465/54/cover/k752638301_1.jpg','라미의 잘 빠진 다이어트 레시피'),(8,8,'9788901239767','https://image.aladin.co.kr/product/23406/73/cover/8901239760_2.jpg','설득의 논리학 =말과 글을 단련하는 10가지 논리 도구 /Logic of persuasion'),(9,8,'9791190242400','https://image.aladin.co.kr/product/23994/31/cover/k862639116_1.jpg','(잠든 사이 월급 버는) 미국 주식 투자 :불황을 이기는 가장 확실한 비법'),(11,8,'9788926396605','http://image.aladin.co.kr/product/3743/86/cover/8926396606_1.jpg','뽑히는 이공계 취업 :이공계생을 위한 맞춤 취업 전략'),(12,8,'9788950988241','https://image.aladin.co.kr/product/25310/6/cover/8950988240_1.jpg','어른은 아니고, 서른입니다 :그때는 몰랐고, 이제야 알 것 같은 서른의 마음'),(17,8,'9788980712656','http://image.aladin.co.kr/product/797/60/cover/8980712650_2.jpg','가족의 가족을 뭐라고 부르지? :바르게 부르는 가족 호칭책'),(18,8,'9788995640623','https://bookthumb-phinf.pstatic.net/cover/061/995/06199557.jpg?type=m1&udate=20180103','밥한그릇 뚝딱!'),(19,9,'9788984373853','https://image.aladin.co.kr/product/21672/22/cover/8984373850_1.jpg','작가들의 비밀스러운 삶 :기욤 뮈소 장편소설'),(20,10,'9788963710051','https://bookthumb-phinf.pstatic.net/cover/060/481/06048111.jpg?type=m1&udate=20100913','성균관 유생들의 나날 :정은궐 장편소설'),(21,10,'9791160075199','https://image.aladin.co.kr/product/24924/69/cover/k962632892_1.jpg','4차 산업 미국 주식에 투자하라 :해외 주식투자로 부를 축적하는 가장 확실한 방법'),(22,12,'9788962860290','https://bookthumb-phinf.pstatic.net/cover/062/857/06285778.jpg?type=m1&udate=20100609','쉿! 신데렐라는 시계를 못 본대 :길이 재기와 시계 보기'),(23,6,'9788936455477','https://image.aladin.co.kr/product/23820/96/cover/8936455478_1.jpg','개미 요정의 선물 :신선미 그림책'),(24,3,'9788954440042','https://image.aladin.co.kr/product/20780/51/cover/8954440045_1.jpg','시간을 파는 상점 :김선영 장편소설'),(25,3,'9788959762439','https://image.aladin.co.kr/product/25701/39/cover/8959762431_1.jpg','이카보그'),(26,8,'9788961770576','http://image.aladin.co.kr/product/1932/18/cover/8961770578_1.jpg','앗, 깜깜해'),(27,16,'9791160402650','https://image.aladin.co.kr/product/19599/9/cover/k392635423_1.jpg','공부의 미래 :10년 후 통하는 새로운 공부법'),(28,16,'9791186940099','http://image.aladin.co.kr/product/8155/28/cover/k152434346_1.jpg','조선왕조실톡 3 - 조선백성실톡'),(30,6,'9788965483717','http://image.aladin.co.kr/product/10951/56/cover/8965483719_1.jpg','미카엘라 :달빛 드레스 도난 사건'),(32,26,'9791190382267','https://image.aladin.co.kr/product/25108/7/cover/k842633074_1.jpg','마음챙김의 시'),(50,18,'9788972215882','https://image.aladin.co.kr/product/19357/18/cover/8972215880_1.jpg','맛있는 계란 요리'),(51,18,'9788954675581','https://image.aladin.co.kr/product/25520/63/cover/8954675581_1.jpg','미자언니네 집밥 :매일 먹는 국과 반찬 레시피'),(52,18,'9791164136896','https://image.aladin.co.kr/product/25592/69/cover/k252735581_1.jpg','(설민석의) 역사 고민 상담소 :역사 이야기로 고민을 해결하는 스토리텔링 한국사'),(53,27,'9791189909178','https://image.aladin.co.kr/product/24549/58/cover/k822631873_1.jpg','파이썬 알고리즘 인터뷰 :95가지 알고리즘 문제 풀이로 완성하는 코딩 테스트'),(54,27,'9788958205159','http://image.aladin.co.kr/product/13830/23/cover/8958205156_1.jpg','(코딩책과 함께 보는) 코딩 개념 사전'),(55,27,'9791162243077','https://image.aladin.co.kr/product/24788/21/cover/k342631735_1.jpg','이것이 취업을 위한 코딩 테스트다 with 파이썬 :취업과 이직을 결정하는 알고리즘 인터뷰 완벽 가이드'),(56,27,'9788966263080','https://image.aladin.co.kr/product/11511/65/cover/8966263089_1.jpg','코딩 인터뷰 완전 분석 :189가지 프로그래밍 문제와 해법'),(57,27,'9788748131297','https://image.aladin.co.kr/product/26351/98/cover/k152738023_1.jpg','코딩 진로 - IT 진로를 꿈꾸는 이들을 위한 지침서'),(58,27,'9791158512064','https://image.aladin.co.kr/product/25967/84/cover/k902738422_1.jpg','오늘의 주식 :이슈를 읽으면 종목이 보인다'),(59,27,'9791188279890','https://image.aladin.co.kr/product/25180/59/cover/k722633698_1.jpg','주식시장의 승부사들 :대한민국 최고의 트레이더들이 전하는 주식투자의 비밀'),(60,27,'9791164454266','https://image.aladin.co.kr/product/26355/1/cover/k792738026_1.jpg','주가조작 모르면 주식투자 절대로 하지 마라! :어디에서도 가르쳐주지 않은 작전 세력에 속지 않는 주식투자 노하우'),(61,27,'9788997924820','https://image.aladin.co.kr/product/26892/10/cover/8997924826_1.jpg','죽을 때까지 코딩하며 사는 법'),(62,27,'9791162242988','https://image.aladin.co.kr/product/23728/45/cover/k322638441_1.jpg','학교에서 알려주지 않는 17가지 실무 개발 기술 :문자열 인코딩부터 웹 필수 지식까지'),(63,27,'9791163030911','https://image.aladin.co.kr/product/19483/1/cover/k582635822_1.jpg','(Do it!) 점프 투 파이썬'),(64,27,'9791162241882','https://image.aladin.co.kr/product/19393/14/cover/k432635210_1.jpg','혼자 공부하는 파이썬 :1:1 과외하듯 배우는 프로그래밍 자습서'),(65,27,'9791160505016','https://image.aladin.co.kr/product/15079/52/cover/k782533163_1.jpg','하루 만에 수익 내는 실전 주식투자'),(67,29,'9788947546997','https://image.aladin.co.kr/product/26665/93/cover/8947546992_1.jpg','좋은 주식 나쁜 주식 =부의 추월차선에 오르기 위한 진짜 주식 공부 /Good stock bad stock'),(68,29,'9791190931083','https://image.aladin.co.kr/product/25039/70/cover/k122632927_1.jpg','(하루 만 원으로 시작하는) 주식 부자 프로젝트'),(69,29,'9791160022902','https://image.aladin.co.kr/product/24354/1/cover/k042630803_1.jpg','내 인생의 첫 주식 공부 :주식투자 왕초보가 꼭 알고 싶은 것들'),(70,29,'9791160505016','https://image.aladin.co.kr/product/15079/52/cover/k782533163_1.jpg','하루 만에 수익 내는 실전 주식투자'),(71,29,'9788790835711','https://image.aladin.co.kr/product/24276/7/cover/k522630788_1.jpg','(허영만의) 주식 타짜 :대한민국 주식 고수 7인의 투자 전략'),(72,24,'9788932919676','https://image.aladin.co.kr/product/19183/62/cover/8932919674_1.jpg','죽음 :베르나르 베르베르 장편소설'),(73,30,'9788966351176','https://image.aladin.co.kr/product/24430/95/cover/8966351174_1.jpg','멋지다!');
/*!40000 ALTER TABLE `scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `age` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `hashtag` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `worldcup_score` int DEFAULT '0',
  `hash_score` int DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test@test.com','test','a1!',NULL,NULL,'20',1,'援�#諛곕뱶',0,0),(2,'test2@test.com','test2','1111',NULL,'2021-09-14 04:38:02','20',1,'援�#諛곕뱶',0,0),(3,'a@aaa.com','aaaa','aaaa1111','','2021-09-16 02:02:27','20��',1,'#test2',200,100),(4,'test3@test.com','수정','1234abcd!','수정2','2021-09-27 10:30:09','20대',0,'string',123,0),(5,'aaa@aaa.com','훈훈훈','aaa111@@',NULL,'2021-09-28 13:01:21','60대 이상',0,'#대담한#호기심많은#신중한#표현력있는#절제된',272,609),(6,'a@a.com','고양이','pa$$w0rd',NULL,'2021-09-28 18:31:40','30대',0,'#공격적#고집센#표현력있는#활발한#호기심많은#분석적인#친절한#느긋한#변덕스러운#희생적인#귀찮은',0,812),(7,'ssafy@ssafy.com','싸피1','ssafy!1234',NULL,'2021-09-29 09:35:11','20대',0,'#차분한#친절한#느긋한',75,8),(8,'test@naver.com','선주','q1w2e3r4!',NULL,'2021-10-01 12:51:57','20대',1,'#변덕스러운#이해심많은#느긋한#신중한#친절한#온화한',0,0),(9,'last9500@naver.com','이집잘하네','dkdl1532!',NULL,'2021-10-05 12:41:20','20대',0,'#공격적#고집센#변덕스러운#완벽주의#까다로운',-273,535),(10,'jinok@ssafy.com','진옥','jinok2021!',NULL,'2021-10-05 14:35:44','20대',1,'#공격적#고집센#까다로운#주도적인#분석적인#체계적인',-361,0),(11,'safy@safy.com','사피','a1234567!',NULL,'2021-10-05 17:00:21','60대 이상',0,'#귀요미',-915,13),(12,'wscho94@gmail.com','안녕하세용','ssafy1234!',NULL,'2021-10-05 17:01:49','20대',0,'#공격적#낙천적#온화한#희생적인',-205,9),(13,'b@bbb.com','비비','bbbb1111!',NULL,'2021-10-06 09:51:24','20대',1,'#공격적#열혈',293,207),(14,'rhrh@rhrh.com','고고','rhrhrhrh123!',NULL,'2021-10-06 10:14:31','30대',0,'#친절한#느긋한',-1379,-2),(15,'test2@test2.com','테스트','1234qwer!',NULL,'2021-10-06 13:46:02','30대',1,'#활발한#표현력있는#호기심많은',1296,812),(16,'rogoboh568@xeiex.com','로고','rogo568!',NULL,'2021-10-06 14:25:47','20대',0,'#온화한#참신한#신중한',167,-118),(17,'hajung@naver.com','하종','hajung1!',NULL,'2021-10-06 14:45:38','20대',1,'#낙천적#성실한#용감한',1188,1500),(18,'sss@sss.com','황싸피','sss111@@',NULL,'2021-10-06 15:53:43','20대',0,'#성실한#활발한#잘생긴',728,64),(19,'efefefe@naver.com','레드빈','rkskek123!',NULL,'2021-10-06 23:20:39','20대',0,'#낙천적#성실한#조심스러운#인심좋은#포용력#차분한',3000,0),(20,'readbean2@naver.com','레드빈','1234qwer!',NULL,'2021-10-07 00:19:13','청소년',1,'#활발한#호기심많은#표현력있는',2000,3000),(21,'readbean3@naver.com','레드빈','1234qwer!',NULL,'2021-10-07 00:22:16','10대',1,'#호기심많은#활발한#표현력있는#인심좋은#친절한#이해심많은',829,814),(22,'readbean4@naver.com','레드빈','1234qwer!',NULL,'2021-10-07 00:23:55','10대',1,'#호기심많은#낙천적#온화한#활발한#표현력있는#성실한#용감한#이해심많은#완벽주의#친절한#참신한#차분한',1066,1005),(23,'readbean5@naver.com','레드빈','1234qwer!',NULL,'2021-10-07 00:25:36','청소년',1,'#호기심많은#활발한#신중한#표현력있는#친절한#이해심많은#차분한#포용력#인심좋은',2000,3000),(24,'readbean@naver.com','레드빈','1234qwer!',NULL,'2021-10-07 00:27:25','10대',1,'#호기심많은#활발한#표현력있는#포용력#이해심많은#영향력#차분한',2800,1500),(25,'asd@asd.asd','ㅁㄴㅇ','asdasdasd1!',NULL,'2021-10-07 01:31:35','초등학생',0,'#온화한#친절한#참신한#느긋한#변덕스러운',0,-102),(26,'dkssud@naver.com','우와','dkssud00@@',NULL,'2021-10-07 04:19:57','20대',0,'#느긋한#희생적인#친절한#온화한#영향력#귀요미#개방적인#이해심많은#분석적인#절제된#태평한#까다로운',-157,-32),(27,'mmm@mmm.com','황지훈','mmm111@@',NULL,'2021-10-07 15:19:33','20대',0,'#성실한#친절한#잘생긴',750,45),(28,'ttest@naver.com','테테스트','AAaa0502@@',NULL,'2021-10-07 15:55:18','20대',0,'#성실한#친절한#잘생긴',678,1042),(29,'skjo666@gmail.com','찌훈','AAaa0502@@',NULL,'2021-10-07 15:56:17','20대',0,'#까다로운#귀찮은#공격적#대담한#고집센',741,-556),(30,'hajung2@naver.com','하종2','hajung1!',NULL,'2021-10-07 17:23:39','20대',1,'#성실한#낙천적#활발한',777,643);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'booxby'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-07 17:36:07
