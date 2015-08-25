CREATE DATABASE  IF NOT EXISTS `jbf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jbf`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: jbf
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '关系树ID',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `identification` varchar(100) DEFAULT NULL COMMENT '标识',
  `url` varchar(200) DEFAULT NULL COMMENT '资源地址',
  `icon` varchar(200) DEFAULT NULL COMMENT '资源图标',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  `is_show` tinyint(4) DEFAULT NULL COMMENT '是否显示',
  `create_by` bigint(20) DEFAULT NULL COMMENT '谁创建',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_by` bigint(20) NOT NULL COMMENT '被谁修改',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除。0表示没有，1表示删除。不删除数据。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` (`id`, `parent_id`, `parent_ids`, `name`, `identification`, `url`, `icon`, `weight`, `is_show`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `deleted`) VALUES (1,0,'0/','资源','','',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(2,1,'0/1/','示例管理','showcase','',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(3,2,'0/1/2/','示例列表','sample','/showcase/sample',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(4,2,'0/1/2/','逻辑删除列表','deleted','/showcase/deleted',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(5,2,'0/1/2/','可移动列表','move','/showcase/move',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(6,2,'0/1/2/','文件上传列表','upload','/showcase/upload',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(7,2,'0/1/2/','树列表','tree','/showcase/tree',NULL,5,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(8,2,'0/1/2/','编辑器列表','editor','/showcase/editor',NULL,6,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(9,2,'0/1/2/','父子表（小数据量）','parentchild','/showcase/parentchild/parent',NULL,7,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(10,2,'0/1/2/','父子表（大数据量）管理','','',NULL,8,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(11,10,'0/1/2/10/','类别列表','productCategory','/showcase/product/category',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(12,10,'0/1/2/10/','产品列表','product','/showcase/product/product',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(13,2,'0/1/2/','状态管理','','',NULL,9,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(14,13,'0/1/2/13/','审核状态列表','statusAudit','/showcase/status/audit',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(15,13,'0/1/2/13/','显示状态列表','statusShow','/showcase/status/show',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(16,1,'0/1/','系统管理','sys','',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(17,16,'0/1/16/','用户管理','','',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(18,17,'0/1/16/17/','用户列表','user','/admin/sys/user/main',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(19,17,'0/1/16/17/','在线用户列表','userOnline','/admin/sys/user/online',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(20,17,'0/1/16/17/','状态变更历史列表','userStatusHistory','/admin/sys/user/statusHistory',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(21,17,'0/1/16/17/','用户最后在线历史列表','userLastOnline','/admin/sys/user/lastOnline',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(22,16,'0/1/16/','组织机构管理','','',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(23,22,'0/1/16/22/','组织机构列表','organization','/admin/sys/organization/organization',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(24,22,'0/1/16/22/','工作职务列表','job','/admin/sys/organization/job',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(25,16,'0/1/16/','资源列表','resource','/admin/sys/resource',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(26,16,'0/1/16/','权限管理','','',NULL,5,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(27,26,'0/1/16/26/','权限列表','permission','/admin/sys/permission/permission',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(28,26,'0/1/16/26/','授权权限给角色','role','/admin/sys/permission/role',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(29,16,'0/1/16/','分组列表','group','/admin/sys/group',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(30,26,'0/1/16/26/','授权角色给实体','auth','/admin/sys/auth',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(31,1,'0/1/','个人中心','','',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(32,31,'0/1/31/','我的消息','','/admin/personal/message',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(33,1,'0/1/','开发维护','maintain','',NULL,5,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(34,33,'0/1/33/','图标管理','icon','/admin/maintain/icon',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(35,33,'0/1/33/','键值对','keyvalue','/admin/maintain/keyvalue',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(37,33,'0/1/33/','静态资源版本控制','staticResource','/admin/maintain/staticResource',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(38,33,'0/1/33/','在线编辑','onlineEditor','/admin/maintain/editor',NULL,5,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(39,1,'0/1/','系统监控','monitor','',NULL,6,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(40,39,'0/1/39/','在线用户列表','userOnline','/admin/sys/user/online',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(41,39,'0/1/39/','数据库监控','db','/admin/monitor/druid/index.html',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(42,39,'0/1/39/','hibernate监控','hibernate','/admin/monitor/hibernate',NULL,3,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(43,39,'0/1/39/','执行SQL/JPA QL','ql','/admin/monitor/db/sql',NULL,4,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(44,39,'0/1/39/','ehcache监控','ehcache','/admin/monitor/ehcache',NULL,5,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(45,39,'0/1/39/','jvm监控','jvm','/admin/monitor/jvm',NULL,6,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(46,2,'0/1/2/','Excel导入/导出','excel','/showcase/excel',NULL,10,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(47,31,'0/1/31/','我的通知','','/admin/personal/notification',NULL,2,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(48,33,'0/1/33/','通知模板','notificationTemplate','/admin/maintain/notification/template',NULL,1,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0),(49,33,'0/1/33/','任务调度','dynamicTask','/admin/maintain/dynamicTask',NULL,6,1,NULL,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00','',0);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(100) NOT NULL COMMENT '登录用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `salt` varchar(10) DEFAULT NULL COMMENT '密码盐',
  `name` varchar(100) NOT NULL COMMENT '真实姓名',
  `work_number` varchar(100) NOT NULL COMMENT '员工工号',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别。0女、1男',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `email` varchar(200) DEFAULT NULL COMMENT '电子邮件',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  `photo` varchar(1000) DEFAULT NULL COMMENT '照片',
  `create_by` bigint(20) NOT NULL COMMENT '谁创建',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `update_by` bigint(20) NOT NULL COMMENT '被谁修改',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除。0表示没有，1表示删除。不删除数据。',
  `status` tinyint(4) DEFAULT '0' COMMENT '用户状态。0 正常、1 禁止登录',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `work_number_UNIQUE` (`work_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `name`, `work_number`, `sex`, `birthday`, `email`, `mobile`, `photo`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `deleted`, `status`) VALUES (1,'admin','admin',NULL,'管理员','0001',1,'1988-09-09 00:00:00','adb','123456789','',0,'0000-00-00 00:00:00',0,'0000-00-00 00:00:00',NULL,0,0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-25  9:49:02
