# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: ssm
# Generation Time: 2018-04-15 15:46:05 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table auth_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `is_expired` bit(1) NOT NULL COMMENT '是否过期',
  `is_locked` bit(1) NOT NULL COMMENT '是否被锁定',
  `is_enabled` bit(1) NOT NULL COMMENT '是否启用',
  `is_del` bit(1) NOT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;

INSERT INTO `auth_user` (`id`, `create_date`, `modify_date`, `username`, `password`, `name`, `is_expired`, `is_locked`, `is_enabled`, `is_del`)
VALUES
	(1,'2017-10-19 17:46:01','2018-04-15 22:58:32','admin','123456','超级管理员',b'0',b'0',b'1',b'0'),
	(2,'2018-04-15 22:13:24','2018-04-15 22:58:47','test','1234','测试用户',b'0',b'0',b'1',b'0');

/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '馆藏数量',
  `is_del` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书表';

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`id`, `create_date`, `modify_date`, `name`, `number`, `is_del`)
VALUES
	(1,'2018-04-09 01:19:34','2018-04-09 01:19:34','鲁滨逊漂流记',6,b'0'),
	(1000,'2018-04-09 15:56:14','2018-04-09 21:27:05','aaa',12,b'1'),
	(1001,'2018-04-09 15:56:34','2018-04-09 21:28:06','v',100,b'1'),
	(1002,'2018-04-09 17:38:24','2018-04-09 17:38:24','bbb',12,b'0'),
	(1003,'2018-04-09 17:58:08','2018-04-09 17:58:08','aaad',12,b'0'),
	(1004,'2018-04-09 20:30:35','2018-04-09 20:30:35','你好，世界',19,b'0');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_conf
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_conf`;

CREATE TABLE `sys_conf` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company` varchar(20) DEFAULT NULL COMMENT '公司名',
  `theme` varchar(64) DEFAULT NULL COMMENT '主题',
  `insite_messager` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用站内信',
  `insite_email` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用站内邮件',
  `alert` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_conf` WRITE;
/*!40000 ALTER TABLE `sys_conf` DISABLE KEYS */;

INSERT INTO `sys_conf` (`id`, `company`, `theme`, `insite_messager`, `insite_email`, `alert`)
VALUES
	(1,'XX','TURQUOISE',b'0',b'0',b'0');

/*!40000 ALTER TABLE `sys_conf` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_id
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_id`;

CREATE TABLE `sys_id` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统唯一编号',
  `stub` char(1) NOT NULL DEFAULT '' COMMENT '系统标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '菜单标题',
  `parent` bigint(32) DEFAULT NULL COMMENT '父菜单',
  `spel` text COMMENT '授权判断表达式',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `url` varchar(256) DEFAULT NULL COMMENT '菜单地址',
  `is_del` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`id`, `create_date`, `modify_date`, `title`, `parent`, `spel`, `icon`, `url`, `is_del`)
VALUES
	(1,'2018-04-08 20:59:59','2018-04-08 20:59:59','样例',0,'hasRole(\'ROLE_DEMO\')',NULL,NULL,b'0'),
	(2,'2018-04-08 20:59:59','2018-04-08 20:59:59','通用组件',1,'hasRole(\'ROLE_DEMO\')',NULL,'/demo/example',b'0'),
	(3,'2018-04-08 20:59:59','2018-04-08 20:59:59','图书管理',1,'hasRole(\'ROLE_DEMO\')',NULL,'/demo/books/view',b'0'),
	(4,'2018-04-15 20:55:14','2018-04-15 20:55:14','系统管理',0,'hasRole(\'ROLE_MANAGER\')',NULL,'',b'0'),
	(5,'2018-04-15 20:55:14','2018-04-15 20:55:14','用户管理',4,'hasRole(\'ROLE_MANAGER\')',NULL,'/system/users/view',b'0');

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '权限编号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '权限名称',
  `category` varchar(64) NOT NULL DEFAULT '' COMMENT '权限分类',
  `remark` text COMMENT '备注',
  `is_del` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;

INSERT INTO `sys_permission` (`id`, `create_date`, `modify_date`, `code`, `name`, `category`, `remark`, `is_del`)
VALUES
	(951364680838295552,'2018-01-11 16:06:29','2018-01-11 16:06:29','admin:permissions:view','查看权限','权限管理',NULL,b'0'),
	(951377683656744960,'2018-01-11 16:58:08','2018-01-11 16:58:08','admin:permission:view','权限查看','权限管理',NULL,b'0'),
	(951701856443944960,'2018-01-12 14:26:18','2018-01-25 13:12:23','admin:permissions:create','创建权限','权限管理',NULL,b'0'),
	(956777798790860800,'2018-01-26 14:36:13','2018-01-26 14:36:13','admin:permission:update','权限修改','权限管理',NULL,b'0');

/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime NOT NULL COMMENT '修改日期',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` text COMMENT '备注',
  `is_del` bit(1) NOT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `create_date`, `modify_date`, `code`, `name`, `remark`, `is_del`)
VALUES
	(1,'2018-01-24 18:06:24','0018-01-24 18:06:24','ADMIN','超级管理员角色',NULL,b'0'),
	(2,'2018-04-08 21:18:07','2018-04-08 21:18:07','DEMO','样例角色',NULL,b'0'),
	(3,'2018-04-15 20:55:14','2018-04-15 20:55:14','MANAGER','系统管理员',NULL,b'0');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_roles_permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_roles_permissions`;

CREATE TABLE `sys_roles_permissions` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(32) NOT NULL COMMENT '角色编号',
  `permission_id` bigint(32) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_users_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_users_roles`;

CREATE TABLE `sys_users_roles` (
  `id` bigint(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(32) NOT NULL COMMENT '用户编号',
  `role_id` bigint(32) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_users_roles` WRITE;
/*!40000 ALTER TABLE `sys_users_roles` DISABLE KEYS */;

INSERT INTO `sys_users_roles` (`id`, `user_id`, `role_id`)
VALUES
	(1,1,1),
	(2,1,2),
	(3,1,3);

/*!40000 ALTER TABLE `sys_users_roles` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
