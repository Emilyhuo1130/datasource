# Host: localhost  (Version: 5.0.22-community-nt)
# Date: 2014-04-09 16:20:06
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "power"
#

DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `Id` int(11) NOT NULL auto_increment,
  `power_name` varchar(255) default NULL,
  `resource_ids` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "power"
#

INSERT INTO `power` VALUES (1,'查看报表','1,2,'),(2,'管理系统','3,4,');

#
# Structure for table "resource"
#

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `Id` int(11) NOT NULL auto_increment,
  `resource_name` varchar(255) default NULL,
  `resource_url` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "resource"
#

INSERT INTO `resource` VALUES (1,'查看最近收入','/jsp/getinput.do'),(2,'查看最近支出','/jsp/geoutput.do'),(3,'添加报表管理员','/jsp/addreport_admin.do'),(4,'删除报表管理员','/jsp/deletereport_admin.do');

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `Id` int(11) NOT NULL auto_increment,
  `role_name` varchar(255) default NULL,
  `role_type` varchar(255) default NULL,
  `power_ids` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'系统管理员','ROLE_ADMIN','1,2,'),(2,'报表管理员','ROLE_USER','1,');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL auto_increment,
  `logname` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `role_ids` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','123456','ROLE_USER,ROLE_ADMIN'),(3,'zhang','123','ROLE_USER');
