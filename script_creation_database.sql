CREATE DATABASE case_consigned;
USE case_consigned;

CREATE TABLE TCON_CLIENT (
    doc_client VARCHAR(32) PRIMARY KEY,
    name_client VARCHAR(100) NOT NULL,
    account_holder BOOLEAN NOT NULL,
    segment VARCHAR(50),
    agreement VARCHAR(50) NOT NULL,
    inclusion_date DATETIME NOT NULL
);

CREATE INDEX idx_agreement ON TCON_CLIENT (agreement);

CREATE TABLE TCON_SIMULATION_CONSIGNED (
    id_simulation INT AUTO_INCREMENT PRIMARY KEY,
    simulation_date DATETIME NOT NULL,
    doc_client VARCHAR(32) NOT NULL,
    agreement_client VARCHAR(100) NOT NULL,
    request_amount DECIMAL(15, 2) NOT NULL,
    rate_applied DECIMAL(10, 2) NOT NULL,
    quantity_installments INT NOT NULL,
    total_amount DECIMAL(15, 2) NOT NULL,
    installment_amount DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (doc_client) REFERENCES TCON_CLIENT (doc_client),
    CONSTRAINT FK_agreement FOREIGN KEY (agreement_client) REFERENCES TCON_CLIENT (agreement)
);

CREATE TABLE TCON_CUSTODY (
    id_contract INT AUTO_INCREMENT PRIMARY KEY,
    contract_date DATE NOT NULL,
    id_simulation INT NOT NULL,
    FOREIGN KEY (id_simulation) REFERENCES TCON_SIMULATION_CONSIGNED (id_simulation)
);
