-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema AIPOS_5
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AIPOS_5
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AIPOS_5` DEFAULT CHARACTER SET utf8 ;
USE `AIPOS_5` ;

-- -----------------------------------------------------
-- Table `AIPOS_5`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AIPOS_5`.`students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `group_name` INT NOT NULL,
  `department` VARCHAR(45) NOT NULL,
  `average_mark` DECIMAL UNSIGNED NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
