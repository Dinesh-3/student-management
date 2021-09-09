DROP DATABASE IF EXISTS `sql_test`;
CREATE DATABASE `sql_test`;
USE `sql_test`;

CREATE TABLE `test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `test` VALUES (DEFAULT, 'Dinesh', "I");