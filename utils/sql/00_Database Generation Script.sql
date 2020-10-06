-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema demographql
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema demographql
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demographql` DEFAULT CHARACTER SET latin1 ;
USE `demographql` ;

-- -----------------------------------------------------
-- Table `demographql`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demographql`.`vehicle` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `model_code` VARCHAR(45) NULL DEFAULT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `launch_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
