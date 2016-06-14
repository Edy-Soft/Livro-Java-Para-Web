SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `comercio` ;
CREATE SCHEMA IF NOT EXISTS `comercio` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `comercio` ;

-- -----------------------------------------------------
-- Table `comercio`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comercio`.`categoria` ;

CREATE  TABLE IF NOT EXISTS `comercio`.`categoria` (
  `cod_categoria` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(50) NULL ,
  PRIMARY KEY (`cod_categoria`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comercio`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comercio`.`produto` ;

CREATE  TABLE IF NOT EXISTS `comercio`.`produto` (
  `cod_produto` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(50) NULL ,
  `preco` DECIMAL(8,2) NULL ,
  PRIMARY KEY (`cod_produto`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comercio`.`categoria_produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comercio`.`categoria_produto` ;

CREATE  TABLE IF NOT EXISTS `comercio`.`categoria_produto` (
  `cod_categoria` INT NOT NULL ,
  `cod_produto` INT NOT NULL ,
  PRIMARY KEY (`cod_categoria`, `cod_produto`) ,
  INDEX `fk_categoria_produto_cod_categoria` (`cod_categoria` ASC) ,
  INDEX `fk_categoria_produto_cod_produto` (`cod_produto` ASC) ,
  CONSTRAINT `fk_categoria_produto_cod_categoria`
    FOREIGN KEY (`cod_categoria` )
    REFERENCES `comercio`.`categoria` (`cod_categoria` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categoria_produto_cod_produto`
    FOREIGN KEY (`cod_produto` )
    REFERENCES `comercio`.`produto` (`cod_produto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
