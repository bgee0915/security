/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.3-m13 : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `s_account` */

DROP TABLE IF EXISTS `s_account`;

CREATE TABLE `s_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) DEFAULT NULL,
  `pass` varchar(128) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `headimg` varchar(300) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(128) DEFAULT NULL,
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_by` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_account` */

insert  into `s_account`(`id`,`account`,`pass`,`tel`,`sex`,`headimg`,`status`,`create_date`,`create_by`,`modify_date`,`modify_by`) values (1,'admin','71a4a17a658b90a7f847585721b5a217','13232323232',1,NULL,1,'2018-02-02 14:59:35','you','2018-02-02 14:59:31','you'),(2,'tech','b26e8467a640ef4d8200baaed515f878','13313341334',0,NULL,1,'2018-02-07 11:31:56','me','2018-02-07 11:31:58','mew');

/*Table structure for table `s_account_role` */

DROP TABLE IF EXISTS `s_account_role`;

CREATE TABLE `s_account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_account_role` */

insert  into `s_account_role`(`id`,`account_id`,`role_id`) values (1,1,1),(2,1,2),(3,2,2),(4,2,3),(5,1,3);

/*Table structure for table `s_authz` */

DROP TABLE IF EXISTS `s_authz`;

CREATE TABLE `s_authz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `keys` varchar(64) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keys` (`keys`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_authz` */

/*Table structure for table `s_role` */

DROP TABLE IF EXISTS `s_role`;

CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `keys` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keys` (`keys`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_role` */

insert  into `s_role`(`id`,`name`,`keys`) values (1,'超级管理员','r_super_admin'),(2,'管理员','r_admin'),(3,'技术员','r_tech');

/*Table structure for table `s_role_authz` */

DROP TABLE IF EXISTS `s_role_authz`;

CREATE TABLE `s_role_authz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `s_role_authz` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
