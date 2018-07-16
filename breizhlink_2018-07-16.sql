# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Hôte: 127.0.0.1 (MySQL 5.7.21)
# Base de données: breizhlink
# Temps de génération: 2018-07-16 14:23:53 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table complexe_urls
# ------------------------------------------------------------

DROP TABLE IF EXISTS `complexe_urls`;

CREATE TABLE `complexe_urls` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_url` int(11) DEFAULT NULL,
  `captcha` tinyint(1) DEFAULT NULL,
  `max_date` int(50) DEFAULT NULL,
  `date_start` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `complexe_urls` WRITE;
/*!40000 ALTER TABLE `complexe_urls` DISABLE KEYS */;

INSERT INTO `complexe_urls` (`id`, `id_url`, `captcha`, `max_date`, `date_start`, `date_end`, `email`)
VALUES
	(1,37,0,1531743793,NULL,NULL,NULL),
	(2,38,0,1531743888,NULL,NULL,NULL),
	(3,39,0,1531744198,NULL,NULL,NULL),
	(4,40,0,1531744272,NULL,NULL,NULL),
	(5,41,0,1531744342,NULL,NULL,NULL),
	(6,42,0,1531744441,NULL,NULL,NULL),
	(7,43,0,1531744589,NULL,NULL,NULL),
	(8,44,0,1531745055,NULL,NULL,NULL),
	(9,45,0,1531745138,NULL,NULL,NULL),
	(10,46,0,1531745212,NULL,NULL,NULL),
	(11,47,0,1531745283,NULL,NULL,NULL),
	(12,48,0,1531745530,NULL,NULL,NULL),
	(13,0,0,0,NULL,NULL,NULL),
	(14,0,0,0,NULL,NULL,NULL),
	(15,0,0,0,NULL,NULL,NULL),
	(16,0,0,0,NULL,NULL,NULL),
	(17,0,0,0,NULL,NULL,NULL),
	(18,54,0,1531749783,NULL,NULL,NULL),
	(19,0,0,0,NULL,NULL,NULL);

/*!40000 ALTER TABLE `complexe_urls` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table simple_urls
# ------------------------------------------------------------

DROP TABLE IF EXISTS `simple_urls`;

CREATE TABLE `simple_urls` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `destination_url` varchar(255) DEFAULT NULL,
  `generated_urls` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `simple_urls` WRITE;
/*!40000 ALTER TABLE `simple_urls` DISABLE KEYS */;

INSERT INTO `simple_urls` (`id`, `destination_url`, `generated_urls`, `user_id`)
VALUES
	(25,'http://esgi.fr','http://localhost:8282/s/eY85LI',4),
	(52,'http://tutu.com','http://localhost:8282/s/S2pVru',4),
	(53,'http://perdu.com','http://localhost:8282/s/a1eYQO',5),
	(54,'http://decathlon.com','http://localhost:8282/s/XYsrTL',6),
	(55,'http://google.com','http://localhost:8282/s/LAP5YE',6);

/*!40000 ALTER TABLE `simple_urls` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table statistics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `statistics`;

CREATE TABLE `statistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url_id` int(11) DEFAULT NULL,
  `visites` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;

INSERT INTO `statistics` (`id`, `url_id`, `visites`, `date`)
VALUES
	(3,22,1,'2018-07-14'),
	(5,13,1,'2018-07-14'),
	(6,25,5,'2018-07-14'),
	(7,25,7,'2018-07-13'),
	(8,25,5,'2018-07-11'),
	(9,25,3,'2018-07-15'),
	(10,25,2,'2018-07-16'),
	(11,34,1,'2018-07-16'),
	(12,38,2,'2018-07-16'),
	(13,39,1,'2018-07-16'),
	(14,42,2,'2018-07-16'),
	(15,43,1,'2018-07-16'),
	(16,44,1,'2018-07-16'),
	(17,45,1,'2018-07-16'),
	(18,46,1,'2018-07-16'),
	(19,47,2,'2018-07-16'),
	(20,48,1,'2018-07-16'),
	(21,49,1,'2018-07-16'),
	(22,52,1,'2018-07-16'),
	(23,54,3,'2018-07-16'),
	(24,55,1,'2018-07-16');

/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table urls_passwords
# ------------------------------------------------------------

DROP TABLE IF EXISTS `urls_passwords`;

CREATE TABLE `urls_passwords` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url_id` int(11) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `urls_passwords` WRITE;
/*!40000 ALTER TABLE `urls_passwords` DISABLE KEYS */;

INSERT INTO `urls_passwords` (`id`, `url_id`, `password`)
VALUES
	(5,13,'password'),
	(6,49,'toto'),
	(7,55,'tutu');

/*!40000 ALTER TABLE `urls_passwords` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table user_types
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_types`;

CREATE TABLE `user_types` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user_types` WRITE;
/*!40000 ALTER TABLE `user_types` DISABLE KEYS */;

INSERT INTO `user_types` (`id`, `name`)
VALUES
	(1,'Particulier'),
	(2,'Entreprise'),
	(3,'Association');

/*!40000 ALTER TABLE `user_types` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `email`, `password`, `type_id`, `enabled`)
VALUES
	(4,'jeremy-haudry@hotmail.fr','password',1,1),
	(5,'virgil@moreau.fr','azerty',2,1),
	(6,'virgil@esgi.fr','azerty',1,1);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
