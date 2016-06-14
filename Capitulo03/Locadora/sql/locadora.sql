SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `locadora` ;
CREATE SCHEMA IF NOT EXISTS `locadora` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;
USE `locadora` ;

-- -----------------------------------------------------
-- Table `locadora`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`categoria` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`categoria` (
  `cod_categoria` INT(11) NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(50) NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_categoria`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `locadora`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`cliente` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`cliente` (
  `cod_cliente` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NULL DEFAULT NULL ,
  `telefone` VARCHAR(50) NULL DEFAULT NULL ,
  `celular` VARCHAR(50) NULL DEFAULT NULL ,
  `email` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_cliente`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `locadora`.`endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`endereco` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`endereco` (
  `cod_cliente` INT(11) NOT NULL ,
  `rua` VARCHAR(100) NULL DEFAULT NULL ,
  `numero` INT(11) NULL DEFAULT NULL ,
  `bairro` VARCHAR(100) NULL DEFAULT NULL ,
  `cidade` VARCHAR(100) NULL DEFAULT NULL ,
  `estado` CHAR(2) NULL DEFAULT NULL ,
  `cep` VARCHAR(10) NULL DEFAULT NULL ,
  `complemento` TEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_cliente`) ,
  INDEX `fk_endereco_cod_cliente` (`cod_cliente` ASC) ,
  CONSTRAINT `fk_endereco_cod_cliente`
    FOREIGN KEY (`cod_cliente` )
    REFERENCES `locadora`.`cliente` (`cod_cliente` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `locadora`.`filme`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`filme` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`filme` (
  `cod_filme` INT(11) NOT NULL AUTO_INCREMENT ,
  `cod_categoria` INT(11) NOT NULL ,
  `descricao` VARCHAR(50) NULL DEFAULT NULL ,
  `ano` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_filme`) ,
  INDEX `fk_filme_cod_categoria` (`cod_categoria` ASC) ,
  CONSTRAINT `fk_filme_cod_categoria`
    FOREIGN KEY (`cod_categoria` )
    REFERENCES `locadora`.`categoria` (`cod_categoria` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `locadora`.`midia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`midia` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`midia` (
  `cod_midia` INT(11) NOT NULL AUTO_INCREMENT ,
  `cod_filme` INT(11) NOT NULL ,
  `inutilizada` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_midia`) ,
  INDEX `fk_midia_cod_filme` (`cod_filme` ASC) ,
  CONSTRAINT `fk_midia_cod_filme`
    FOREIGN KEY (`cod_filme` )
    REFERENCES `locadora`.`filme` (`cod_filme` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `locadora`.`locacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora`.`locacao` ;

CREATE  TABLE IF NOT EXISTS `locadora`.`locacao` (
  `cod_locacao` INT(11) NOT NULL AUTO_INCREMENT ,
  `cod_cliente` INT(11) NOT NULL ,
  `cod_midia` INT(11) NOT NULL ,
  `data_emprestimo` DATE NULL DEFAULT NULL ,
  `hora_emprestimo` TIME NULL DEFAULT NULL ,
  `data_devolucao` DATE NULL DEFAULT NULL ,
  `obs` TEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`cod_locacao`, `cod_cliente`, `cod_midia`) ,
  INDEX `fk_locacao_cod_cliente` (`cod_cliente` ASC) ,
  INDEX `fk_locacao_cod_midia` (`cod_midia` ASC) ,
  CONSTRAINT `fk_locacao_cod_cliente`
    FOREIGN KEY (`cod_cliente` )
    REFERENCES `locadora`.`cliente` (`cod_cliente` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_locacao_cod_midia`
    FOREIGN KEY (`cod_midia` )
    REFERENCES `locadora`.`midia` (`cod_midia` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
