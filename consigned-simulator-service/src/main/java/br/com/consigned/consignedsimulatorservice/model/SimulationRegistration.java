package br.com.consigned.consignedsimulatorservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SimulationRegistration {
    private int idSimulation;
    private LocalDateTime dtSimulation;
    private String docClient;
    private int idAgreement;
    private BigDecimal requestAmount;
    private BigDecimal rateApplied;
    private int qtdInstallments;
    private BigDecimal ttlAmountPay;
    private BigDecimal installmentsValue;
    private boolean activeSimulation;
}
