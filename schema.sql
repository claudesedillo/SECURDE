-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema indigo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema indigo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `indigo` DEFAULT CHARACTER SET utf8 ;
USE `indigo` ;

-- -----------------------------------------------------
-- Table `indigo`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`address` (
  `addressid` INT(11) NOT NULL AUTO_INCREMENT,
  `streetaddress` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `postalcode` VARCHAR(45) NULL DEFAULT NULL,
  `province` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`addressid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`admin` (
  `email` VARCHAR(45) NOT NULL,
  `hashedpassword` VARCHAR(100) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`authors` (
  `authorid` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`authorid`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`book` (
  `bookid` INT(11) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(60) NULL DEFAULT NULL,
  `ISBN` VARCHAR(13) NULL DEFAULT NULL,
  `Genre` VARCHAR(45) NULL DEFAULT NULL,
  `Format` VARCHAR(45) NULL DEFAULT NULL,
  `Published` DATE NULL DEFAULT NULL,
  `Price` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`bookid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`bookauthorrelation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`bookauthorrelation` (
  `book_id` INT(11) NOT NULL,
  `authorid` INT(11) NOT NULL,
  PRIMARY KEY (`book_id`, `authorid`),
  INDEX `ba_relation_authorid_idx` (`authorid` ASC),
  CONSTRAINT `ba_relation_authorid`
    FOREIGN KEY (`authorid`)
    REFERENCES `indigo`.`authors` (`authorid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ba_relation_bookid`
    FOREIGN KEY (`book_id`)
    REFERENCES `indigo`.`book` (`bookid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`concerns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`concerns` (
  `ticketnumber` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `concern` VARCHAR(45) NULL DEFAULT NULL,
  `date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`ticketnumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`customers` (
  `email` VARCHAR(45) NOT NULL,
  `hashedpassword` VARCHAR(100) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`customeraddressrelation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`customeraddressrelation` (
  `email` VARCHAR(45) NOT NULL,
  `addressid` INT(11) NOT NULL,
  PRIMARY KEY (`email`, `addressid`),
  INDEX `customer_addressid_idx` (`addressid` ASC),
  CONSTRAINT `customer_addressid`
    FOREIGN KEY (`addressid`)
    REFERENCES `indigo`.`address` (`addressid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_email`
    FOREIGN KEY (`email`)
    REFERENCES `indigo`.`customers` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`inventory` (
  `bookid` INT(11) NOT NULL,
  `stocklevel` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  CONSTRAINT `book_id`
    FOREIGN KEY (`bookid`)
    REFERENCES `indigo`.`book` (`bookid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`orders` (
  `orderid` INT(11) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `orderdate` DATE NULL DEFAULT NULL,
  `total` FLOAT NULL DEFAULT NULL,
  `datecompleted` TIMESTAMP NULL DEFAULT NULL,
  `streetaddress` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `province` VARCHAR(45) NULL DEFAULT NULL,
  `postalcode` INT(11) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`orderlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`orderlist` (
  `orderid` INT(11) NOT NULL,
  `bookid` INT(11) NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`, `bookid`),
  INDEX `list_bookid_idx` (`bookid` ASC),
  CONSTRAINT `list_bookid`
    FOREIGN KEY (`bookid`)
    REFERENCES `indigo`.`book` (`bookid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `list_orderid`
    FOREIGN KEY (`orderid`)
    REFERENCES `indigo`.`orders` (`orderid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`orderstatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`orderstatus` (
  `orderid` INT(11) NOT NULL,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(200) NULL DEFAULT NULL,
  `date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  CONSTRAINT `status_orderid`
    FOREIGN KEY (`orderid`)
    REFERENCES `indigo`.`orders` (`orderid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`publisher` (
  `publisherid` INT(11) NOT NULL AUTO_INCREMENT,
  `publishername` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`publisherid`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`publisherbookrelation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`publisherbookrelation` (
  `publisherid` INT(11) NOT NULL,
  `bookid` INT(11) NOT NULL,
  PRIMARY KEY (`publisherid`, `bookid`),
  INDEX `pb_relation_bookid_idx` (`bookid` ASC),
  CONSTRAINT `pb_relation_bookid`
    FOREIGN KEY (`bookid`)
    REFERENCES `indigo`.`book` (`bookid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pb_relation_publisherid`
    FOREIGN KEY (`publisherid`)
    REFERENCES `indigo`.`publisher` (`publisherid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `indigo`.`shoppingcart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `indigo`.`shoppingcart` (
  `bookid` INT(11) NOT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `quantity` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`bookid`),
  INDEX `cart_email_idx` (`email` ASC),
  CONSTRAINT `cart_email`
    FOREIGN KEY (`email`)
    REFERENCES `indigo`.`customers` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
