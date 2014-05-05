# Host: localhost  (Version: 5.0.22-community-nt)
# Date: 2014-04-07 16:56:45
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin_info"
#

DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `Id` int(11) NOT NULL auto_increment,
  `user_Name` varchar(50) NOT NULL,
  `user_pw` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "admin_info"
#

INSERT INTO `admin_info` VALUES (1,'admin','123456');

#
# Structure for table "project_info"
#

DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `Id` int(11) NOT NULL auto_increment,
  `project_Name` varchar(255) NOT NULL,
  `project_State` varchar(255) NOT NULL,
  `project_Type` varchar(255) default NULL,
  `project_remark` varchar(800) NOT NULL,
  `createProject_Date` date default '0000-00-00',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "project_info"
#

INSERT INTO `project_info` VALUES (1,'主动维保','3','2','备旺旺44','2013-10-28'),(2,'国客星空','已验收','1','备注9944','2013-10-28'),(3,'临港视频监控系统','研发中','11','备注44766','2013-10-28'),(4,'哈西pis','设计中','1','备注','2013-10-28');

#
# Structure for table "task_particulars"
#

DROP TABLE IF EXISTS `task_particulars`;
CREATE TABLE `task_particulars` (
  `Id` int(11) NOT NULL auto_increment,
  `project_Name` varchar(255) NOT NULL,
  `finish_House` float(5,2) NOT NULL default '0.00',
  `userName` varchar(255) NOT NULL default '',
  `taskDetail` varchar(255) default NULL,
  `commit_Dare` date NOT NULL default '0000-00-00',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "task_particulars"
#

INSERT INTO `task_particulars` VALUES (4,'主动维保',3.00,'周光呈','今日任务详情','2013-10-25'),(5,'主动维保',5.00,'陈浩','今日任务详情','2013-10-09'),(7,'临港视频监控系统',9.00,'陈浩','今日任务详情','2013-10-09'),(8,'国客星空',5.00,'周光呈','今日任务详情','2013-10-10'),(9,'国客星空',5.33,'周光呈','今日任务详情','2013-10-25'),(10,'国客星空',5.00,'张殷豪','今日任务详情','2013-10-12'),(11,'临港视频监控系统2222',2.00,'陈浩','今日任务详情','2013-10-25'),(12,'临港视频监控系统',6.00,'李军','今日任务详情','2013-10-14'),(13,'临港视频监控系统',5.00,'李军','今日任务详情','2013-10-15'),(14,'临港视频监控系统',1.00,'陈浩','今日任务详情','2013-10-25'),(15,'临港视频监控系统',5.00,'张殷豪','今日任务详情','2013-10-17'),(20,'主动维保',5.00,'陈浩','今日任务详情','2013-10-28'),(21,'哈西pis',5.00,'陈浩','今日任务详情','2013-10-28'),(22,'国客星空',5.00,'陈浩','今日任务详情','2013-10-28'),(23,'主动维保',8.00,'陈浩','gg','2013-11-18'),(24,'主动维保',5.00,'张殷豪','今日任务详情','2013-10-04'),(25,'国客星空',4.00,'张殷豪','今日任务详情','2013-10-25'),(26,'国客星空',5.00,'张殷豪','今日任务详情','2013-10-25'),(27,'临港视频监控系统',5.00,'张殷豪','今日任务详情','2013-10-25'),(29,'2',2.00,'44','55','2013-10-25');

#
# Structure for table "user_info"
#

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `Id` int(11) NOT NULL auto_increment,
  `user_Name` varchar(50) NOT NULL,
  `user_account` varchar(50) NOT NULL,
  `user_pw` varchar(50) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_info"
#

INSERT INTO `user_info` VALUES (1,'周光呈','zhouguangcheng','999999','15262516056'),(2,'张殷豪','zhangyinhao','123456',''),(3,'陈浩','chenhao','123456',''),(4,'高吉东','gaojidong','123456',''),(5,'付高庭','fugaoting','123456',''),(6,'张朋','zhangpeng','123456',''),(7,'张斓','zhanglan','123456',''),(8,'程杰','chengjie','123456',''),(9,'李发德','lifade','123456',''),(10,'李娟','lijuan','123456',''),(11,'李军','lijun','123456',''),(12,'陆俊','lujun','123456',''),(13,'石玉奎','shiyukui','123456',''),(14,'薛峰','xuefeng','123456','');

#
# Structure for table "weektask"
#

DROP TABLE IF EXISTS `weektask`;
CREATE TABLE `weektask` (
  `Id` int(11) NOT NULL auto_increment,
  `userAccount` varchar(255) default NULL,
  `projectName` varchar(100) default NULL,
  `WeekTask` varchar(800) default NULL,
  `WeekNo` int(3) default NULL,
  `updateTime` date default '0000-00-00',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "weektask"
#

INSERT INTO `weektask` VALUES (1,'陈浩','国客星空','周计划任务000000000000000',44,'2013-11-04'),(2,'陈浩','国客星空','周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务周计划任务',46,'2013-10-28'),(3,'陈浩','国客星空','周计划任务',46,'2013-11-04'),(4,'陈浩','国客星空','adfs',46,'2013-11-04'),(14,'陈浩','国客星空','adda',44,'2013-11-04'),(15,'陈浩','国客星空','周计划任务',43,'2013-10-28'),(16,'陈浩','国客星空','周计划任务',50,'2013-11-04'),(17,'陈浩','国客星空','adfs',49,'2013-11-04'),(19,'陈浩','国客星空','周计划任务',48,'2013-11-04'),(20,'陈浩','国客星空','adfs',51,'2013-11-04'),(21,'陈浩','国客星空','adda',51,'2013-11-04'),(22,'陈浩','国客星空','adda',51,'2013-11-04'),(23,'陈浩','国客星空','周计划任务',50,'2013-10-28'),(24,'陈浩','国客星空','周计划任务',50,'2013-11-04'),(25,'陈浩','国客星空','adfs',50,'2013-11-04');
