-- MySQL Script generated by MySQL Workbench
-- Sun Jun 27 15:38:06 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema demodb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema demodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demodb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `demodb` ;

-- -----------------------------------------------------
-- Table `demodb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demodb`.`user` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` VARCHAR(16) NOT NULL,
  `nickname` VARCHAR(45) NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  `id_type` VARCHAR(45) NULL COMMENT '证件类型',
  `id_number` VARCHAR(45) NULL COMMENT '证件号码',
  `adress` VARCHAR(155) NULL COMMENT '地址',
  `tel` VARCHAR(45) NULL,
  `tel_pre` VARCHAR(45) NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `demodb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demodb`.`product` (
  `id` BIGINT(10) NOT NULL COMMENT '商品编号',
  `name` VARCHAR(100) NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `demodb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demodb`.`order` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order0` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order1` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order2` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order3` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order4` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `demodb`.`order5` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order6` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order7` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order8` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order9` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order10` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order11` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order12` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `demodb`.`order13` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `demodb`.`order14` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `demodb`.`order15` (
  `id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL COMMENT '购买的用户id\n',
  `product_id` BIGINT(10) NULL COMMENT '购买的产品id',
  `name` VARCHAR(45) NULL,
  `amount` INT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
