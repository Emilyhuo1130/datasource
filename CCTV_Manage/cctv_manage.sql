# Host: localhost  (Version: 5.0.22-community-nt)
# Date: 2014-04-07 16:57:04
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin_info"
#

DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `Id` int(11) NOT NULL auto_increment,
  `admin_Account` varchar(50) default NULL,
  `admin_Pw` varchar(50) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "admin_info"
#

INSERT INTO `admin_info` VALUES (1,'admin','123456');

#
# Structure for table "camera_group"
#

DROP TABLE IF EXISTS `camera_group`;
CREATE TABLE `camera_group` (
  `group_id` int(11) NOT NULL default '0',
  `camera_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`camera_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "camera_group"
#

INSERT INTO `camera_group` VALUES (4,1);

#
# Structure for table "camera_info"
#

DROP TABLE IF EXISTS `camera_info`;
CREATE TABLE `camera_info` (
  `camera_id` int(11) NOT NULL auto_increment,
  `camera_Name` varchar(50) default NULL,
  `camera_Model` varchar(20) default NULL,
  `camera_IP` varchar(20) default NULL,
  `camera_Mac` varchar(50) default NULL,
  `capacity` int(11) default NULL,
  `section` varchar(50) default NULL,
  `camera_Type` varchar(20) default NULL,
  PRIMARY KEY  (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "camera_info"
#

INSERT INTO `camera_info` VALUES (1,'摄像机1','','192.168.2.125','',NULL,'广场','半球'),(2,'摄像机7','canon','192.168.2.122','68-94-23-36-9F-19',30,'办公室','全球'),(3,'摄像机6','canon','192.168.2.123','68-94-23-36-9F-19',30,'机房','半球');

#
# Structure for table "monitor_group"
#

DROP TABLE IF EXISTS `monitor_group`;
CREATE TABLE `monitor_group` (
  `group_id` int(11) NOT NULL auto_increment,
  `group_Name` varchar(50) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "monitor_group"
#

INSERT INTO `monitor_group` VALUES (4,'黑机房监控组3',''),(13,'白房监控组','监控白天机房摄像机');

#
# Structure for table "operate_log"
#

DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `log_id` int(11) NOT NULL auto_increment,
  `operator_Account` varchar(50) default NULL,
  `operate_Time` datetime default '0000-00-00 00:00:00',
  `operate_Type` varchar(50) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "operate_log"
#

INSERT INTO `operate_log` VALUES (15,'zhouguangcheng','2013-11-22 15:37:34','添加','添加新摄像机'),(16,'chenhao','2013-11-25 09:19:11','删除','删除新摄像机'),(17,'zhangyinhao','2013-11-25 09:20:01','修改','修改新摄像机'),(18,'admin','2013-12-18 10:24:46','登录','admin登录了系统'),(19,'admin','2013-12-18 14:11:11','登录','admin登录了系统'),(20,'admin','2013-12-18 14:25:44','登录','admin登录了系统'),(21,'admin','2013-12-18 14:26:51','登录','admin登录了系统'),(22,'admin','2013-12-26 13:55:49','登录','admin登录了系统'),(23,'admin','2013-12-26 13:56:08','登录','admin登录了系统'),(24,'admin','2013-12-26 14:06:08','登录','admin登录了系统'),(25,'admin','2013-12-26 17:10:29','登录','admin登录了系统'),(26,'admin','2014-01-08 11:08:50','登录','admin登录了系统'),(27,'admin','2014-01-08 11:14:00','登录','admin登录了系统'),(28,'admin','2014-01-08 12:34:12','登录','admin登录了系统'),(29,'admin','2014-01-09 10:47:16','登录','admin登录了系统'),(30,'admin','2014-01-09 10:57:01','登录','admin登录了系统'),(31,'admin','2014-01-15 16:22:41','登录','admin登录了系统'),(32,'admin','2014-01-15 16:26:00','添加','admin添加了新的操作员'),(33,'admin','2014-01-15 16:26:27','更新','admin更新了id为25的操作员信息'),(34,'admin','2014-01-15 16:27:03','更新','admin更新了id为25的操作员信息'),(35,'admin','2014-01-15 16:38:30','添加','admin添加了新的操作员'),(36,'admin','2014-01-15 17:11:09','添加','admin添加了新的操作员'),(37,'admin','2014-01-15 17:11:21','更新','admin更新了id为27的操作员信息'),(38,'admin','2014-01-16 08:56:42','登录','admin登录了系统'),(39,'admin','2014-01-16 08:57:12','更新','admin更新了id为27的操作员信息'),(40,'admin','2014-01-16 08:57:26','更新','admin更新了id为27的操作员信息'),(41,'admin','2014-01-16 09:14:29','更新','admin修改了id为3的摄像机'),(42,'admin','2014-01-16 09:19:19','登录','admin登录了系统'),(43,'admin','2014-01-16 09:19:28','更新','admin修改了id为1的摄像机'),(44,'admin','2014-01-16 09:22:03','更新','admin修改了id为1的摄像机'),(45,'admin','2014-01-16 09:22:15','更新','admin修改了id为1的摄像机'),(46,'admin','2014-01-16 09:28:51','删除','admin删除了id为1的录像机'),(47,'admin','2014-01-16 09:31:37','添加','admin添加了一台录像机'),(48,'admin','2014-01-16 09:32:03','删除','admin删除了id为2的录像机'),(49,'admin','2014-01-16 09:32:09','删除','admin删除了id为9的录像机'),(50,'admin','2014-01-16 09:33:03','添加','admin添加了一台录像机'),(51,'admin','2014-01-16 09:33:16','删除','admin删除了id为10的录像机'),(52,'admin','2014-01-16 09:34:00','添加','admin添加了一台录像机'),(53,'admin','2014-01-16 09:34:35','删除','admin删除了id为11的录像机'),(54,'admin','2014-01-16 09:36:31','添加','admin添加了一条区位信息'),(55,'admin','2014-01-16 09:36:40','删除','admin删除了id为8的摄像机'),(56,'admin','2014-01-16 09:38:01','添加','admin添加了一条区位信息'),(57,'admin','2014-01-16 09:38:37','添加','admin添加了一条区位信息'),(58,'admin','2014-01-16 09:39:34','添加','admin添加了新的操作员'),(59,'admin','2014-01-16 10:43:00','登录','admin登录了系统'),(60,'admin','2014-01-16 10:45:12','删除','admin删除了id为9的摄像机'),(61,'admin','2014-01-16 10:45:22','删除','admin删除了id为10的摄像机'),(62,'admin','2014-01-16 10:47:19','添加','admin修改了id为的监控组'),(63,'admin','2014-01-16 10:47:27','删除','admin删除了id为14的监控组'),(64,'admin','2014-01-16 10:57:10','登录','admin登录了系统'),(65,'admin','2014-01-16 10:57:27','更新','admin更新了id为23的操作员信息'),(66,'admin','2014-01-16 10:58:38','更新','admin修改了id为4的监控组'),(67,'admin','2014-01-16 10:58:50','添加','admin修改了id为的监控组'),(68,'admin','2014-01-16 10:58:55','删除','admin删除了id为15的监控组'),(69,'admin','2014-01-16 10:59:37','更新','admin更新了系统指标参数'),(70,'admin','2014-01-16 10:59:47','更新','admin更新了系统指标参数'),(71,'admin','2014-01-16 11:00:08','更新','admin更新了系统指标参数'),(72,'admin','2014-01-16 11:00:46','更新','admin更新了磁盘信息'),(73,'admin','2014-01-16 11:01:08','更新','admin更新了磁盘信息'),(74,'admin','2014-01-16 11:02:16','更新','admin更新了磁盘信息'),(75,'admin','2014-01-16 11:17:50','登录','admin登录了系统'),(76,'admin','2014-01-16 11:28:48','更新','admin更新了id为23的操作员信息'),(77,'admin','2014-01-16 11:35:38','更新','admin更新了id为23的操作员信息'),(78,'admin','2014-01-16 11:35:55','更新','admin更新了id为23的操作员信息'),(79,'admin','2014-01-16 11:37:18','添加','admin添加了新的操作员'),(80,'admin','2014-01-16 11:37:24','删除','admin删除了id为25的操作员信息'),(81,'admin','2014-01-16 11:37:29','删除','admin删除了id为26的操作员信息'),(82,'admin','2014-01-16 11:37:33','删除','admin删除了id为27的操作员信息'),(83,'admin','2014-01-16 11:37:36','删除','admin删除了id为28的操作员信息'),(84,'admin','2014-01-16 11:37:41','删除','admin删除了id为29的操作员信息'),(85,'admin','2014-01-16 12:48:23','登录','admin登录了系统'),(86,'admin','2014-01-16 12:50:26','更新','admin修改了id为3的摄像机'),(87,'admin','2014-01-16 12:53:56','登录','admin登录了系统'),(88,'admin','2014-01-16 12:55:51','登录','admin登录了系统'),(89,'admin','2014-01-16 13:00:18','添加','admin添加了一台摄像机'),(90,'admin','2014-01-16 13:00:33','更新','admin修改了id为4的摄像机'),(91,'admin','2014-01-16 13:11:45','添加','admin添加了一台摄像机'),(92,'admin','2014-01-16 13:11:51','删除','admin删除了cameraId为5的摄像机'),(93,'admin','2014-01-16 13:12:00','删除','admin删除了cameraId为4的摄像机'),(94,'admin','2014-01-16 13:23:07','更新','admin修改了id为3的摄像机'),(95,'admin','2014-01-16 13:25:28','更新','admin修改了id为3的摄像机'),(96,'admin','2014-01-16 13:49:18','添加','admin添加了一台录像机'),(97,'admin','2014-01-16 13:49:24','删除','admin删除了id为12的录像机'),(98,'admin','2014-01-16 13:55:37','登录','admin登录了系统'),(99,'admin','2014-01-16 13:55:50','更新','admin更新了一台录像机'),(100,'admin','2014-01-16 13:58:55','删除','admin删除了id为8的录像机'),(101,'admin','2014-01-16 14:00:43','更新','admin更新了一台录像机'),(102,'admin','2014-01-16 14:04:26','添加','admin添加了一条区位信息'),(103,'admin','2014-01-16 14:04:34','删除','admin删除了id为11的摄像机'),(104,'admin','2014-01-16 14:12:16','添加','admin添加了一条区位信息'),(105,'admin','2014-01-16 14:12:21','删除','admin删除了id为12的摄像机'),(106,'admin','2014-01-16 14:13:39','添加','admin添加了一条区位信息'),(107,'admin','2014-01-16 14:14:19','删除','admin删除了id为13的摄像机'),(108,'admin','2014-01-16 14:14:28','添加','admin添加了一条区位信息'),(109,'admin','2014-01-16 14:17:01','更新','admin更新了id为7的区位信息'),(110,'admin','2014-01-16 14:17:13','添加','admin添加了一条区位信息'),(111,'admin','2014-01-16 14:17:19','删除','admin删除了id为15的摄像机'),(112,'admin','2014-01-16 14:37:06','删除','admin删除了id为7的摄像机'),(113,'admin','2014-01-16 14:37:21','删除','admin删除了id为2的摄像机'),(114,'admin','2014-01-16 14:37:35','添加','admin添加了一条区位信息'),(115,'admin','2014-01-16 14:42:20','添加','admin修改了id为的监控组'),(116,'admin','2014-01-16 14:42:27','删除','admin删除了id为16的监控组'),(117,'admin','2014-01-16 14:57:32','登录','admin登录了系统'),(118,'admin','2014-01-16 14:57:42','添加','admin修改了id为的监控组'),(119,'admin','2014-01-16 14:57:48','删除','admin删除了id为17的监控组'),(120,'admin','2014-01-16 14:59:32','更新','admin修改了id为4的监控组'),(121,'admin','2014-01-16 15:00:15','更新','admin更新了系统指标参数'),(122,'admin','2014-01-16 15:02:18','更新','admin更新了系统指标参数'),(123,'admin','2014-01-16 15:02:31','更新','admin更新了系统指标参数'),(124,'admin','2014-01-16 15:02:38','更新','admin更新了系统指标参数'),(125,'admin','2014-01-16 15:04:13','更新','admin更新了系统指标参数'),(126,'admin','2014-01-16 15:04:26','更新','admin更新了磁盘信息'),(127,'admin','2014-01-16 15:05:37','更新','admin更新了系统指标参数'),(128,'admin','2014-01-16 15:06:02','更新','admin更新了系统指标参数'),(129,'admin','2014-01-16 15:07:23','更新','admin更新了系统指标参数'),(130,'admin','2014-01-16 15:09:38','更新','admin更新了磁盘信息'),(131,'admin','2014-01-16 15:24:01','更新','admin更新了磁盘信息'),(132,'admin','2014-01-16 15:24:29','更新','admin更新了磁盘信息'),(133,'admin','2014-01-16 15:26:41','更新','admin更新了磁盘信息'),(134,'admin','2014-01-16 15:26:53','更新','admin更新了id为23的操作员信息'),(135,'admin','2014-01-16 15:32:47','添加','admin添加了一条区位信息'),(136,'admin','2014-01-16 15:32:53','删除','admin删除了id为17的摄像机'),(137,'admin','2014-01-17 08:51:09','登录','admin登录了系统'),(138,'admin','2014-01-17 08:51:28','添加','admin修改了id为的监控组'),(139,'admin','2014-01-17 08:54:41','添加','admin修改了id为的监控组'),(140,'admin','2014-01-17 08:58:33','删除','admin删除了id为15的监控组'),(141,'admin','2014-01-17 09:01:16','添加','admin修改了id为的监控组'),(142,'admin','2014-01-17 09:08:25','添加','admin修改了id为的监控组'),(143,'admin','2014-01-17 09:09:38','添加','admin修改了id为的监控组'),(144,'admin','2014-01-17 09:12:37','添加','admin修改了id为的监控组'),(145,'admin','2014-01-17 09:28:04','登录','admin登录了系统'),(146,'admin','2014-01-17 09:28:18','添加','admin修改了id为的监控组'),(147,'admin','2014-01-17 09:28:29','更新','admin修改了id为20的监控组'),(148,'admin','2014-01-17 09:55:40','登录','admin登录了系统'),(149,'admin','2014-01-17 09:55:56','添加','admin修改了id为的监控组'),(150,'admin','2014-01-20 14:12:11','登录','admin登录了系统'),(151,'admin','2014-02-10 15:34:05','登录','admin登录了系统'),(152,'admin','2014-02-10 15:34:29','删除','admin删除了id为14的监控组'),(153,'admin','2014-02-10 15:34:36','删除','admin删除了id为16的监控组'),(154,'admin','2014-02-10 15:34:40','删除','admin删除了id为17的监控组'),(155,'admin','2014-02-10 15:34:44','删除','admin删除了id为18的监控组'),(156,'admin','2014-02-10 15:34:47','删除','admin删除了id为19的监控组'),(157,'admin','2014-02-10 15:34:50','删除','admin删除了id为20的监控组'),(158,'admin','2014-02-10 15:34:54','删除','admin删除了id为21的监控组'),(159,'admin','2014-02-10 16:55:18','登录','admin登录了系统'),(160,'admin','2014-03-11 14:00:21','登录','admin登录了系统');

#
# Structure for table "operator_group"
#

DROP TABLE IF EXISTS `operator_group`;
CREATE TABLE `operator_group` (
  `operator_id` int(11) NOT NULL default '0',
  `group_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`operator_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "operator_group"
#

INSERT INTO `operator_group` VALUES (23,13);

#
# Structure for table "operator_info"
#

DROP TABLE IF EXISTS `operator_info`;
CREATE TABLE `operator_info` (
  `operator_id` int(11) NOT NULL auto_increment,
  `operator_Name` varchar(50) default NULL,
  `phone_Number` varchar(50) default NULL,
  `operator_Level` varchar(50) default NULL,
  `remark` varchar(255) default NULL,
  `operator_Account` varchar(50) NOT NULL default '',
  `operator_Passwd` varchar(50) default NULL,
  PRIMARY KEY  (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "operator_info"
#

INSERT INTO `operator_info` VALUES (23,'陈浩4','13921219812','普通操作员','eee3','chenhao','66666'),(24,'陈浩','13921219802','超级操作员','备注','chenhao','123');

#
# Structure for table "recorder_info"
#

DROP TABLE IF EXISTS `recorder_info`;
CREATE TABLE `recorder_info` (
  `recorder_id` int(11) NOT NULL auto_increment,
  `recorder_Name` varchar(50) default NULL,
  `recorder_Model` varchar(20) default NULL,
  `recorder_IP` varchar(50) default NULL,
  `recorder_Mac` varchar(50) default NULL,
  `capacity` int(11) default NULL,
  `recorder_Type` varchar(50) default NULL,
  PRIMARY KEY  (`recorder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "recorder_info"
#

INSERT INTO `recorder_info` VALUES (3,'录像机3','DELL','192.168.1.103','68-94-23-36-9F-19',300,'全球'),(4,'录像机1','DELL','192.168.1.105','68-94-23-36-9F-19',300,'枪机'),(5,'录像机1','DELL','192.168.1.106','68-94-23-36-9F-19',300,'枪机');

#
# Structure for table "section_info"
#

DROP TABLE IF EXISTS `section_info`;
CREATE TABLE `section_info` (
  `section_id` int(11) NOT NULL auto_increment,
  `section_Name` varchar(255) default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "section_info"
#

INSERT INTO `section_info` VALUES (3,'办公室','5555'),(14,'机房',''),(16,'广场','');
