-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `farmacia` DEFAULT CHARACTER SET utf8mb4 ;
USE `farmacia` ;

-- -----------------------------------------------------
-- Table `farmacia`.`farmaceutic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`farmaceutic` (
  `DNI` VARCHAR(20) NOT NULL,
  `Nom` VARCHAR(50) NOT NULL,
  `Cognoms` VARCHAR(50) NOT NULL,
  `AnyLlicenciatura` DATE NOT NULL,
  PRIMARY KEY (`DNI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`farmacia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`farmacia` (
  `CIF` VARCHAR(20) NOT NULL,
  `Adreça` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`CIF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`empleabilitat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`empleabilitat` (
  `DNIFarmaceutic` VARCHAR(20) NOT NULL,
  `CIFFarmacia` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`DNIFarmaceutic`, `CIFFarmacia`),
  INDEX `CIFFarmacia` (`CIFFarmacia` ASC) VISIBLE,
  CONSTRAINT `empleabilitat_ibfk_1`
    FOREIGN KEY (`DNIFarmaceutic`)
    REFERENCES `farmacia`.`farmaceutic` (`DNI`),
  CONSTRAINT `empleabilitat_ibfk_2`
    FOREIGN KEY (`CIFFarmacia`)
    REFERENCES `farmacia`.`farmacia` (`CIF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`medicament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`medicament` (
  `NomComercial` VARCHAR(50) NOT NULL,
  `Formula` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`NomComercial`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`metge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`metge` (
  `NumColegiat` INT(11) NOT NULL,
  `Especialitat` VARCHAR(75) NOT NULL,
  `Nom` VARCHAR(50) NOT NULL,
  `Cognoms` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`NumColegiat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`pacient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`pacient` (
  `DNI` VARCHAR(20) NOT NULL,
  `Nom` VARCHAR(50) NOT NULL,
  `Cognoms` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`DNI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`prescripcio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`prescripcio` (
  `NumColegiatMetge` INT(11) NOT NULL,
  `DNIPacient` VARCHAR(20) NOT NULL,
  `NomComercialMedicament` VARCHAR(50) NOT NULL,
  `Data` DATETIME NOT NULL,
  `Quantitat` INT(11) NOT NULL,
  PRIMARY KEY (`NumColegiatMetge`, `DNIPacient`, `NomComercialMedicament`),
  INDEX `DNIPacient` (`DNIPacient` ASC) VISIBLE,
  INDEX `NomComercialMedicament` (`NomComercialMedicament` ASC) VISIBLE,
  CONSTRAINT `prescripcio_ibfk_1`
    FOREIGN KEY (`NumColegiatMetge`)
    REFERENCES `farmacia`.`metge` (`NumColegiat`),
  CONSTRAINT `prescripcio_ibfk_2`
    FOREIGN KEY (`DNIPacient`)
    REFERENCES `farmacia`.`pacient` (`DNI`),
  CONSTRAINT `prescripcio_ibfk_3`
    FOREIGN KEY (`NomComercialMedicament`)
    REFERENCES `farmacia`.`medicament` (`NomComercial`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`tractament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`tractament` (
  `NumColegiatMetge` INT(11) NOT NULL,
  `DNIPacient` VARCHAR(20) NOT NULL,
  `Data` DATETIME NOT NULL,
  PRIMARY KEY (`NumColegiatMetge`, `DNIPacient`),
  INDEX `DNIPacient` (`DNIPacient` ASC) VISIBLE,
  CONSTRAINT `tractament_ibfk_1`
    FOREIGN KEY (`NumColegiatMetge`)
    REFERENCES `farmacia`.`metge` (`NumColegiat`),
  CONSTRAINT `tractament_ibfk_2`
    FOREIGN KEY (`DNIPacient`)
    REFERENCES `farmacia`.`pacient` (`DNI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `farmacia`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`venta` (
  `NomComercialMedicament` VARCHAR(50) NOT NULL,
  `CIFFarmacia` VARCHAR(20) NOT NULL,
  `Data` DATETIME NOT NULL,
  `Preu` DECIMAL(2,0) NOT NULL,
  PRIMARY KEY (`NomComercialMedicament`, `CIFFarmacia`),
  INDEX `CIFFarmacia` (`CIFFarmacia` ASC) VISIBLE,
  CONSTRAINT `venta_ibfk_1`
    FOREIGN KEY (`NomComercialMedicament`)
    REFERENCES `farmacia`.`medicament` (`NomComercial`),
  CONSTRAINT `venta_ibfk_2`
    FOREIGN KEY (`CIFFarmacia`)
    REFERENCES `farmacia`.`farmacia` (`CIF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
