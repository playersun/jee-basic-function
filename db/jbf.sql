CREATE DATABASE  IF NOT EXISTS `jbf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jbf`;
-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: jbf
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

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
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS sys_users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE sys_users (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  username varchar(100) NOT NULL COMMENT '登录用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  salt varchar(10) DEFAULT NULL COMMENT '密码盐',
  `name` varchar(100) NOT NULL COMMENT '真实姓名',
  work_number varchar(100) NOT NULL COMMENT '员工工号',
  sex tinyint(4) DEFAULT NULL COMMENT '性别。0女、1男',
  birthday datetime DEFAULT NULL COMMENT '出生年月',
  email varchar(200) DEFAULT NULL COMMENT '电子邮件',
  mobile varchar(30) DEFAULT NULL COMMENT '手机号',
  photo varchar(1000) DEFAULT NULL COMMENT '照片',
  create_date datetime NOT NULL COMMENT '创建日期',
  update_by bigint(20) DEFAULT NULL COMMENT '被谁修改',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  remarks varchar(255) DEFAULT NULL COMMENT '备注',
  deleted tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除。0表示没有，1表示删除。不删除数据。',
  `status` tinyint(4) DEFAULT '0' COMMENT '用户状态。0 正常、1 禁止登录',
  PRIMARY KEY (id),
  UNIQUE KEY username_UNIQUE (username),
  UNIQUE KEY work_number_UNIQUE (work_number)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_users`
--

LOCK TABLES sys_users WRITE;
/*!40000 ALTER TABLE sys_users DISABLE KEYS */;
INSERT INTO sys_users (id, username, password, salt, name, work_number, sex, birthday, email, mobile, photo, create_date, update_by, update_date, remarks, deleted, status) VALUES (1,'admin','admin',NULL,'管理员','0001',1,'1988-09-09 00:00:00','adb','123456789','','0000-00-00 00:00:00',NULL,NULL,NULL,0,0);
/*!40000 ALTER TABLE sys_users ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-27 16:42:16
