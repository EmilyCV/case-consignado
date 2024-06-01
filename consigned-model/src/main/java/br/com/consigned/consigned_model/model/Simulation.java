package br.com.consigned.consigned_model.model;

import br.com.consigned.consigned_model.enums.AgreementType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Simulation {
    private int idSimulation;
    private LocalDateTime dtSimulation;
    private String docClient;
    private BigDecimal requestAmount;
    private String rateApplied;
    private int qtdInstallments;
    private BigDecimal ttlAmountPay;
    private BigDecimal installmentsValue;
    private AgreementType agreement;
}
