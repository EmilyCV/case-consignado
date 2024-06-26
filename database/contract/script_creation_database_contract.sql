-- -----------------------------------------------------
-- Schema consigned_contract
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS consigned_contract;
USE consigned_contract;

-- -----------------------------------------------------
-- Table TCON_CONTRACT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TCON_CONTRACT (
  ID_CONTR INT NOT NULL AUTO_INCREMENT,
  DT_CONTR DATETIME NOT NULL,
  ID_SIMULATION INT NOT NULL,
  ACTIVE_CONTR TINYINT NOT NULL,
  PRIMARY KEY (ID_CONTR, ID_SIMULATION))
  ADD UNIQUE INDEX `ID_SIMULATION_UNIQUE` (ID_SIMULATION ASC) VISIBLE,
  ADD UNIQUE INDEX `ID_CONTR_UNIQUE` (ID_CONTR ASC) VISIBLE;
ENGINE = InnoDB;