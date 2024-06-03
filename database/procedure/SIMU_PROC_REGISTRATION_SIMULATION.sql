CREATE PROCEDURE PROC_REGISTRATION_SIMULATION(
	param_dtSimulation DATETIME,
    param_docClient VARCHAR(255),
    param_idAgreement INT,
    param_requestAmount DECIMAL(38,2),
    param_rateApplied DECIMAL(38,2),
    param_qtdInstallments INT,
    param_ttlAmountPay DECIMAL(38,2),
    param_installmentsValue DECIMAL(38,2),
    param_activeSimulation TINYINT(1)
)
BEGIN
	DECLARE ID_EXIST INT DEFAULT 0;
    
    -- Verifica se já existe uma simulação com parâmetros iguais e ativa
    SELECT ID_SIMU INTO ID_EXIST
    FROM TSIMU_SIMULATION
		WHERE DOC_CLIENT = param_docClient
		AND ID_AGREEMENT = param_idAgreement
		AND REQUEST_AMOUNT = param_requestAmount
		AND RATE_APPLIED = param_rateApplied
		AND QTD_INSTALLM = param_qtdInstallments
		AND TOTAL_AMOUNT_PAY = param_ttlAmountPay
		AND INSTALLM_VALUE = param_installmentsValue
		AND ACTIVE_SIMULATION = param_activeSimulation
    LIMIT 1;
    
    -- Se uma simulação foi encontrada, desativa ela
    UPDATE TSIMU_SIMULATION
	SET ACTIVE_SIMULATION = 0
	WHERE ID_SIMU = IF(ID_EXIST IS NOT NULL, ID_EXIST, NULL);
    
    INSERT INTO TSIMU_SIMULATION (
        DT_SIMU,
        DOC_CLIENT,
        ID_AGREEMENT,
        REQUEST_AMOUNT,
        RATE_APPLIED,
        QTD_INSTALLM,
        TOTAL_AMOUNT_PAY,
        INSTALLM_VALUE,
        ACTIVE_SIMULATION) 
	VALUES (
		param_dtSimulation,
		param_docClient,
		param_idAgreement,
		param_requestAmount,
		param_rateApplied,
		param_qtdInstallments,
		param_ttlAmountPay,
		param_installmentsValue,
		param_activeSimulation);
	
    SELECT IF(ROW_COUNT() > 0, 1, 0) AS success;
END