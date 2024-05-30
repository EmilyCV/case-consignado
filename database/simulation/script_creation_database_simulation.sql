-- -----------------------------------------------------
-- Schema consigned_simulation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS consigned_simulation;
USE consigned_simulation;


-- -----------------------------------------------------
-- Table tsimu_agreement_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_agreement_type (
  ID_AGRM INT NOT NULL AUTO_INCREMENT,
  AGMR_TYPE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID_AGRM),
  UNIQUE INDEX AGMR_TYPE_UNIQUE (AGMR_TYPE ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_segment_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_segment_type (
  ID_SEGM INT NOT NULL AUTO_INCREMENT,
  SEGM_TYPE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID_SEGM),
  UNIQUE INDEX SEGM_TYPE (SEGM_TYPE ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_installment_details
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_installment_details (
  ID_INSTALLM INT NOT NULL,
  ACCOUNT_HOLDER TINYINT NOT NULL,
  ID_SEGMENT INT NOT NULL,
  QTD_INSTALLM_MAX INT NOT NULL,
  PRIMARY KEY (ID_INSTALLM),
  UNIQUE INDEX ACCOUNT_HOLDER_UNIQUE (ACCOUNT_HOLDER ASC) VISIBLE,
  UNIQUE INDEX ID_SEGMENT_UNIQUE (ID_SEGMENT ASC) VISIBLE,
  UNIQUE INDEX QTD_INSTALL_MAX_UNIQUE (QTD_INSTALLM_MAX ASC) VISIBLE,
  INDEX fk_tsimu_installment_details_tsimu_segment_type_idx (ID_SEGMENT ASC) VISIBLE,
  CONSTRAINT fk_tsimu_installment_details_tsimu_segment_type
    FOREIGN KEY (ID_SEGMENT)
    REFERENCES tsimu_segment_type (ID_SEGM))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_simulation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_simulation (
  ID_SIMU INT NOT NULL,
  DT_SIMU DATETIME NOT NULL,
  DOC_CLIENT INT NOT NULL,
  ID_AGREEMENT INT NOT NULL,
  REQUEST_AMOUNT DECIMAL(15,2) NOT NULL,
  RATE_APPLIED VARCHAR(45) NOT NULL,
  QTD_INSTALLM INT NOT NULL,
  TOTAL_AMOUNT_PAY DECIMAL(15,2) NOT NULL,
  INSTALLM_VALUE DECIMAL(15,2) NOT NULL,
  ACTIVE_SIMULATION TINYINT NOT NULL,
  PRIMARY KEY (ID_SIMU),
  INDEX fk_tsimu_simulation_tsimu_agreement_type1_idx (ID_AGREEMENT ASC) VISIBLE,
  CONSTRAINT fk_tsimu_simulation_tsimu_agreement_type1
    FOREIGN KEY (ID_AGREEMENT)
    REFERENCES tsimu_agreement_type (ID_AGRM)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table tsimu_agreement_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_agreement_type (
  ID_AGRM INT NOT NULL AUTO_INCREMENT,
  AGMR_TYPE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID_AGRM),
  UNIQUE INDEX AGMR_TYPE_UNIQUE (AGMR_TYPE ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_segment_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_segment_type (
  ID_SEGM INT NOT NULL AUTO_INCREMENT,
  SEGM_TYPE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID_SEGM),
  UNIQUE INDEX SEGM_TYPE (SEGM_TYPE ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_installment_details
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_installment_details (
  ID_INSTALLM INT NOT NULL,
  ACCOUNT_HOLDER TINYINT NOT NULL,
  ID_SEGMENT INT NOT NULL,
  QTD_INSTALLM_MAX INT NOT NULL,
  PRIMARY KEY (ID_INSTALLM),
  UNIQUE INDEX ACCOUNT_HOLDER_UNIQUE (ACCOUNT_HOLDER ASC) VISIBLE,
  UNIQUE INDEX ID_SEGMENT_UNIQUE (ID_SEGMENT ASC) VISIBLE,
  UNIQUE INDEX QTD_INSTALL_MAX_UNIQUE (QTD_INSTALLM_MAX ASC) VISIBLE,
  INDEX fk_tsimu_installment_details_tsimu_segment_type_idx (ID_SEGMENT ASC) VISIBLE,
  CONSTRAINT fk_tsimu_installment_details_tsimu_segment_type
    FOREIGN KEY (ID_SEGMENT)
    REFERENCES tsimu_segment_type (ID_SEGM))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table tsimu_simulation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tsimu_simulation (
  ID_SIMU INT NOT NULL,
  DT_SIMU DATETIME NOT NULL,
  DOC_CLIENT INT NOT NULL,
  ID_AGREEMENT INT NOT NULL,
  REQUEST_AMOUNT DECIMAL(15,2) NOT NULL,
  RATE_APPLIED VARCHAR(45) NOT NULL,
  QTD_INSTALLM INT NOT NULL,
  TOTAL_AMOUNT_PAY DECIMAL(15,2) NOT NULL,
  INSTALLM_VALUE DECIMAL(15,2) NOT NULL,
  ACTIVE_SIMULATION TINYINT NOT NULL,
  PRIMARY KEY (ID_SIMU),
  INDEX fk_tsimu_simulation_tsimu_agreement_type1_idx (ID_AGREEMENT ASC) VISIBLE,
  CONSTRAINT fk_tsimu_simulation_tsimu_agreement_type1
    FOREIGN KEY (ID_AGREEMENT)
    REFERENCES tsimu_agreement_type (ID_AGRM)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
