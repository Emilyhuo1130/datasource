# Host: localhost  (Version: 5.0.22-community-nt)
# Date: 2014-05-05 09:01:15
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "abillity"
#

DROP TABLE IF EXISTS `abillity`;
CREATE TABLE `abillity` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `age` int(11) default NULL,
  `info` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "abillity"
#

INSERT INTO `abillity` VALUES (1,'zhang',22,'---9uhahakjldsljakhdlu'),(2,'zhang',22,'---9uhahakjldsljakhdlu'),(3,'zhang',22,'---9uhahakjldsljakhdlu'),(4,'zhang',22,'---9uhahakjldsljakhdlu'),(5,'zhang',22,'---9uhahakjldsljakhdlu');

#
# Structure for table "article"
#

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `Id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `title` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "article"
#

INSERT INTO `article` VALUES (1,1,'title1'),(2,1,'title2'),(3,1,'title3');

#
# Structure for table "auth_group"
#

DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_group"
#


#
# Structure for table "auth_user"
#

DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(128) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `last_login` datetime NOT NULL,
  `date_joined` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_user"
#

INSERT INTO `auth_user` VALUES (1,'admin','','','1234@163.com','sha1$208c1$47a98f3953a4d8d1d0b1bb14fd89815c134e1c0e',1,1,1,'2014-04-27 20:18:11','2014-04-27 20:14:23');

#
# Structure for table "auth_message"
#

DROP TABLE IF EXISTS `auth_message`;
CREATE TABLE `auth_message` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `message` longtext NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `auth_message_403f60f` (`user_id`),
  CONSTRAINT `user_id_refs_id_650f49a6` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_message"
#


#
# Structure for table "auth_user_groups"
#

DROP TABLE IF EXISTS `auth_user_groups`;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `user_id` (`user_id`,`group_id`),
  KEY `auth_user_groups_403f60f` (`user_id`),
  KEY `auth_user_groups_425ae3c4` (`group_id`),
  CONSTRAINT `group_id_refs_id_f116770` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `user_id_refs_id_7ceef80f` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_user_groups"
#


#
# Structure for table "django_content_type"
#

DROP TABLE IF EXISTS `django_content_type`;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `app_label` (`app_label`,`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "django_content_type"
#

INSERT INTO `django_content_type` VALUES (1,'permission','auth','permission'),(2,'group','auth','group'),(3,'user','auth','user'),(4,'message','auth','message'),(5,'content type','contenttypes','contenttype'),(6,'session','sessions','session'),(7,'site','sites','site'),(8,'log entry','admin','logentry');

#
# Structure for table "django_admin_log"
#

DROP TABLE IF EXISTS `django_admin_log`;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL auto_increment,
  `action_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `content_type_id` int(11) default NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `django_admin_log_403f60f` (`user_id`),
  KEY `django_admin_log_1bb8f392` (`content_type_id`),
  CONSTRAINT `content_type_id_refs_id_288599e6` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `user_id_refs_id_c8665aa` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "django_admin_log"
#


#
# Structure for table "auth_permission"
#

DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `content_type_id` (`content_type_id`,`codename`),
  KEY `auth_permission_1bb8f392` (`content_type_id`),
  CONSTRAINT `content_type_id_refs_id_728de91f` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_permission"
#

INSERT INTO `auth_permission` VALUES (1,'Can add permission',1,'add_permission'),(2,'Can change permission',1,'change_permission'),(3,'Can delete permission',1,'delete_permission'),(4,'Can add group',2,'add_group'),(5,'Can change group',2,'change_group'),(6,'Can delete group',2,'delete_group'),(7,'Can add user',3,'add_user'),(8,'Can change user',3,'change_user'),(9,'Can delete user',3,'delete_user'),(10,'Can add message',4,'add_message'),(11,'Can change message',4,'change_message'),(12,'Can delete message',4,'delete_message'),(13,'Can add content type',5,'add_contenttype'),(14,'Can change content type',5,'change_contenttype'),(15,'Can delete content type',5,'delete_contenttype'),(16,'Can add session',6,'add_session'),(17,'Can change session',6,'change_session'),(18,'Can delete session',6,'delete_session'),(19,'Can add site',7,'add_site'),(20,'Can change site',7,'change_site'),(21,'Can delete site',7,'delete_site'),(22,'Can add log entry',8,'add_logentry'),(23,'Can change log entry',8,'change_logentry'),(24,'Can delete log entry',8,'delete_logentry');

#
# Structure for table "auth_user_user_permissions"
#

DROP TABLE IF EXISTS `auth_user_user_permissions`;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `user_id` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_403f60f` (`user_id`),
  KEY `auth_user_user_permissions_1e014c8f` (`permission_id`),
  CONSTRAINT `permission_id_refs_id_67e79cb` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `user_id_refs_id_dfbab7d` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_user_user_permissions"
#


#
# Structure for table "auth_group_permissions"
#

DROP TABLE IF EXISTS `auth_group_permissions`;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL auto_increment,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `group_id` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_425ae3c4` (`group_id`),
  KEY `auth_group_permissions_1e014c8f` (`permission_id`),
  CONSTRAINT `group_id_refs_id_3cea63fe` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `permission_id_refs_id_5886d21f` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "auth_group_permissions"
#


#
# Structure for table "django_session"
#

DROP TABLE IF EXISTS `django_session`;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY  (`session_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "django_session"
#

INSERT INTO `django_session` VALUES ('703bebf0de218178674c92db2b98df5a','gAJ9cQEoVRJfYXV0aF91c2VyX2JhY2tlbmRxAlUpZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5k\ncy5Nb2RlbEJhY2tlbmRxA1UNX2F1dGhfdXNlcl9pZHEEigEBdS4wODcyYTUyZDk4OGUzNjcwMTY5\nZGVmNmYwZTA4YjFmNA==\n','2014-05-11 20:18:11');

#
# Structure for table "django_site"
#

DROP TABLE IF EXISTS `django_site`;
CREATE TABLE `django_site` (
  `id` int(11) NOT NULL auto_increment,
  `domain` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "django_site"
#

INSERT INTO `django_site` VALUES (1,'example.com','example.com');

#
# Structure for table "loginfo"
#

DROP TABLE IF EXISTS `loginfo`;
CREATE TABLE `loginfo` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "loginfo"
#

INSERT INTO `loginfo` VALUES (1,'admin','2'),(2,'user','33');

#
# Structure for table "test2"
#

DROP TABLE IF EXISTS `test2`;
CREATE TABLE `test2` (
  `Id` varchar(80) NOT NULL default '',
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "test2"
#

INSERT INTO `test2` VALUES ('81e6abb7-aef9-1031-908a-92250093e1af','22'),('a2e73232-aef9-1031-908a-92250093e1af','22'),('aab311d1-aef9-1031-908a-92250093e1af','22'),('ab4479a7-aef9-1031-908a-92250093e1af','22'),('ac1069ae-aef9-1031-908a-92250093e1af','22'),('ac3778e1-aef9-1031-908a-92250093e1af','22'),('ac55822b-aef9-1031-908a-92250093e1af','22'),('ac72e0da-aef9-1031-908a-92250093e1af','22'),('ac909025-aef9-1031-908a-92250093e1af','22'),('aca93821-aef9-1031-908a-92250093e1af','22'),('e08fc79baef91031908a92250093e1af','22');

#
# Structure for table "userinfo"
#

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `age` int(11) default NULL,
  `infoid` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "userinfo"
#

INSERT INTO `userinfo` VALUES (1,'test',42,2),(4,'zhang',33,99),(6,'zhangsan',42,2),(8,'zhangsan',22,2),(9,'wangwu张',42,1),(17,'new',22,2),(21,'test',33,555555);

#
# Procedure "hello"
#

DROP PROCEDURE IF EXISTS `hello`;
CREATE PROCEDURE `hello`()
begin
     select * from test2;
end;

#
# Procedure "proc1"
#

DROP PROCEDURE IF EXISTS `proc1`;
CREATE PROCEDURE `proc1`(out s int)
begin
      select count(*) into s from uerinfo;
 end;
