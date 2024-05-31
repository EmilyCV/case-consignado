package br.com.consigned.consignedsimulatorservice.controller.response;

import br.com.consigned.consigned_model.enums.AgreementType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class SimulationResponse {
    private LocalDateTime dtSimulation;
    private String docClient;
    private BigDecimal requestAmount;
    private String rateApplied;
    private int qtdInstallments;
    private BigDecimal ttlAmountPay;
    private BigDecimal installmentsValue;
    private AgreementType agreement;
}
