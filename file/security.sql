/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.3-m13 : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `security`;

/*Table structure for table `s_account` */

DROP TABLE IF EXISTS `s_account`;

CREATE TABLE `s_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) DEFAULT NULL,
  `pass` varchar(64) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `status` int(2) DEFAULT '1' COMMENT '1：可用  0：不可用',
  `create_date` timestamp NULL DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tel` (`tel`),
  UNIQUE KEY `unique_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_account` */

insert  into `s_account`(`id`,`account`,`pass`,`tel`,`sex`,`status`,`create_date`,`create_by`,`modify_date`,`modify_by`) values (1,'admin','71a4a17a658b90a7f847585721b5a217','13312341234',1,1,'2018-01-30 00:09:54','admin','2018-01-30 00:09:59','admin'),(2,'test','217df19d942a4a990ebeed63a983292f','134123412345',0,1,'2018-02-10 16:14:13','test','2018-02-10 16:14:15','test'),(3,'ceshi','ceshi','13412311231',0,0,'2018-02-23 17:25:20','admin','2018-02-23 17:25:20','admin'),(4,'yiya','yiya','13612341234',0,0,'2018-02-23 17:28:20','admin','2018-02-23 17:28:20','admin'),(5,'mm','mm','13712341234',0,0,'2018-02-23 17:31:15','admin','2018-02-23 17:31:15','admin'),(6,'rrrr','rrrr','13878947894',0,0,'2018-02-23 17:32:17','admin','2018-02-23 17:32:17','admin'),(7,'13412331233','9e7075e456bcff55781bf59ff490da6d','13412331233',0,0,'2018-02-23 17:40:19','admin','2018-02-23 17:40:19','admin'),(8,'xxxxx','f31b715aa05de9396fbc396953d7380d','13522221111',1,0,'2018-02-23 23:30:30','admin','2018-02-23 23:30:30','admin'),(12,'abc','52052a04825fde7700a2194234f7fb2c','13599007722',0,1,'2018-02-26 16:12:03','test','2018-02-26 16:12:03','test');

/*Table structure for table `s_account_role` */

DROP TABLE IF EXISTS `s_account_role`;

CREATE TABLE `s_account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_account_role` */

insert  into `s_account_role`(`id`,`account_id`,`role_id`) values (32,12,15),(33,1,13),(34,1,14);

/*Table structure for table `s_authz` */

DROP TABLE IF EXISTS `s_authz`;

CREATE TABLE `s_authz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `keys` varchar(64) DEFAULT NULL,
  `status` int(2) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `KEY_UNIQUE` (`keys`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_authz` */

insert  into `s_authz`(`id`,`pid`,`name`,`keys`,`status`) values (1,0,'首页',NULL,1),(2,0,'账号',NULL,1),(3,0,'角色',NULL,1),(4,0,'权限',NULL,1),(5,0,'菜单',NULL,1),(6,1,'首页列表','a_home',1),(7,2,'账号列表','a_account_list',1),(8,2,'账号添加','a_account_add',1),(9,2,'账号修改','a_account_edit',1),(10,2,'账号删除','a_account_del',1),(15,3,'角色添加','a_role_add',1),(16,3,'角色列表','a_role_list',1),(17,3,'角色编辑','a_role_edit',1),(18,3,'角色删除','a_role_del',1),(19,4,'权限列表','a_authz_list',1),(20,4,'权限添加','a_authz_add',1),(21,4,'权限编辑','a_authz_edit',1),(22,4,'权限删除','a_authz_del',1);

/*Table structure for table `s_menu` */

DROP TABLE IF EXISTS `s_menu`;

CREATE TABLE `s_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `perm` varchar(256) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '类型， 0：目录，1：菜单，2：按钮',
  `icon` varchar(256) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(2) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL,
  `modify_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_menu` */

/*Table structure for table `s_role` */

DROP TABLE IF EXISTS `s_role`;

CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `keys` varchar(128) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`keys`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_role` */

insert  into `s_role`(`id`,`name`,`keys`,`status`) values (1,'超级管理员','r_super_admin',0),(2,'管理员','r_admin',0),(3,'技术员','r_tech',0),(9,'123','321',0),(10,'fff','eee',0),(11,'测试的角色','test_role',0),(12,'测试啊','13_23',0),(13,'超级管理员','s_super_admin',1),(14,'普通管理员','s_normal_admin',1),(15,'技术员','s_tech',1);

/*Table structure for table `s_role_authz` */

DROP TABLE IF EXISTS `s_role_authz`;

CREATE TABLE `s_role_authz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_role_authz` */

insert  into `s_role_authz`(`id`,`role_id`,`authz_id`) values (8,13,6),(9,13,7),(10,13,8),(11,13,9),(12,13,10),(13,13,15),(14,13,16),(15,13,17),(16,13,18),(17,13,19),(18,13,20),(19,13,21),(20,13,22),(21,14,6),(22,14,7),(23,14,8),(24,14,9),(25,14,16),(26,14,19),(30,15,6),(31,15,7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
