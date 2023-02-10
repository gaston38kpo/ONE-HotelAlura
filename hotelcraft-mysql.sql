CREATE DATABASE  IF NOT EXISTS `hotel_alura`;

USE `hotel_alura`;

CREATE TABLE IF NOT EXISTS `guest` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(1100) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `lastname` varchar(1100) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `nationality` varchar(60) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `phone` varchar(150) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `reservation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reservation_id` (`reservation_id`),
  CONSTRAINT `guest_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entry_date` date DEFAULT NULL,
  `exit_date` date DEFAULT NULL,
  `value` decimal(20,2) DEFAULT NULL,
  `payment_method` varchar(150) COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

CREATE TABLE IF NOT EXISTS `hotel_alura`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(150) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC) VISIBLE);

INSERT INTO user (user, password) VALUES ('admin', 'admin');
