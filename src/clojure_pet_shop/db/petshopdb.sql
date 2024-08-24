/*
MySQL - 5.7.14 : Database - petshopdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`petshopdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `petshopdb`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `clientId` int(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `lastName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `korisnik` */

insert  into `client`(`clientId`,`firstName`,`lastName`,`username`,`password`) values 
(30,'Kristina','Stanisavljevic','kris@gmail.com','kris123'),
(56,'Milica','Suknovic','milicasukn@gmail.com','milica123'),
(57,'Aleksa','Ilic','ilke@gmail.com','ilke123'),
(58,'Emilija','Pajkovic','ema@gmail.com','ema123'),
(59,'Dunja','Tikvicki','dunja@gmail.com','dunja123');

/*Table structure for table `proizvodjac` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `manufacturerId` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`manufacturerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `proizvodjac` */

insert  into `manufacturer`(`manufacturerId`,`name`,`country`,`address`) values 
(1,'ACANA','Canada','12871 Bowling Green Road'),
(2,'PREMIL','Serbia','Blok 116., Kovin 26220'),
(3,'CAMON','Italy','37041 Albaredo DAdige (VR) IT');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `petProduct` (
  `productId` int(20) NOT NULL AUTO_INCREMENT,
  `productName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `price` double DEFAULT NULL,
  `type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `manufacturerId` int(20) DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `product` */

insert  into `proizvod`(`productId`,`productName`,`price`,`type`,`manufacturerId`) values 
(65,'Premil sunrise 3 kg',990,'Superpremium suva hrana za pse',2),
(67,'Acana puppy small breed 6kg',7.452,'Vrhunska suva hrana za pse',1),
(68,'CAMON Ogrlica za pse',539,'Ogrlica za pse',3),
(92,'CAMON Zatezna ogrlica za pse',409,'Ogrlica za pse',3);

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `invoiceId` int(20) NOT NULL AUTO_INCREMENT,
  `dateOfShopping` datetime DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `clientId` int(20) DEFAULT NULL,
  PRIMARY KEY (`invoiceId`),
  KEY `clientId_fk` (`clientId`),
  CONSTRAINT `clientId_fk` FOREIGN KEY (`clientId`) REFERENCES `client` (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `invoice` */

insert  into `invoice`(`invoiceId`,`dateOfShopping`,`totalPrice`,`clientId`) values 
(50,'2024-02-20 12:00:00',112640,30),
(51,'2024-01-20 12:00:00',69560,56),
(52,'2024-05-20 12:00:00',27680,57),
(53,'2024-06-20 12:00:00',14590,58),
(54,'2024-02-20 12:00:00',42980,30);

/*Table structure for table `invoiceItem` */

DROP TABLE IF EXISTS `invoiceItem`;

CREATE TABLE `invoiceItem` (
  `sequenceNumber` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceId` int(11) NOT NULL,
  `itemPrice` double DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`sequenceNumber`,`invoiceId`),
  KEY `sequenceNumber` (`sequenceNumber`),
  KEY `id_invoice_fk` (`invoiceId`),
  KEY `product_fk` (`productId`),
  CONSTRAINT `id_inovice_fk` FOREIGN KEY (`invoiceId`) REFERENCES `inovice` (`invoiceId`),
  CONSTRAINT `product_fk` FOREIGN KEY (`productId`) REFERENCES `product` (`proizvodId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`sequenceNumber`,`invoiceId`,`itemPrice`,`productId`,`quantity`) values 
(1,71,29180,65,2),
(1,72,33800,67,2),
(1,74,25980,68,2),
(1,76,33980,69,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;