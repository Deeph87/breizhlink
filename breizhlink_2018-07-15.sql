# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Hôte: 127.0.0.1 (MySQL 5.7.21)
# Base de données: breizhlink
# Temps de génération: 2018-07-14 22:45:16 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


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
	(13,'http://perdu.com','http://localhost:8282/s/psrVkd',NULL),
	(15,'http://toto.com','http://localhost:8282/s/bSBrgV',NULL),
	(16,'http://titi.com','http://localhost:8282/s/gryxTL',NULL),
	(17,'http://tutu.com','http://localhost:8282/s/AKU3ym',NULL),
	(18,'http://mich.com','http://localhost:8282/s/jIltyr',NULL),
	(19,'http://blabla.com','http://localhost:8282/s/aGayHV',NULL),
	(20,'http://blublu.com','http://localhost:8282/s/LDbFOW',0),
	(21,'http://tuti.com','http://localhost:8282/s/fNm27G',0),
	(22,'http://lolo.com','http://localhost:8282/s/XTQkIk',0),
	(23,'http://lulu.com','http://localhost:8282/s/E5xi1C',0),
	(24,'http://esgi.com','http://localhost:8282/s/Dd3cLn',-1),
	(25,'http://esgi.com','http://localhost:8282/s/eY85LI',0),
	(26,'http://esgi.com','http://localhost:8282/s/bDBg43',-1);

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
	(6,25,4,'2018-07-14'),
	(7,25,6,'2018-07-13'),
	(8,25,4,'2018-07-11'),
	(9,25,2,'2018-07-15');

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
	(5,13,'password');

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
	(4,'jeremy-haudry@hotmail.fr','password',1,1);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
