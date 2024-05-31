package br.com.consigned.consignedsimulatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "TSIMU_SIMULATION")
public class SimulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SIMU")
    private int idSimulation;

    @Column(name = "DT_SIMU")
    private LocalDateTime dtSimulation;

    @Column(name = "DOC_CLIENT")
    private String docClient;

    @Column(name = "ID_AGREEMENT")
    private int idAgreement;

    @Column(name = "REQUEST_AMOUNT")
    private BigDecimal requestAmount;

    @Column(name = "RATE_APPLIED")
    private BigDecimal rateApplied;

    @Column(name = "QTD_INSTALLM")
    private int qtdInstallments;

    @Column(name = "TOTAL_AMOUNT_PAY")
    private BigDecimal ttlAmountPay;

    @Column(name = "INSTALLM_VALUE")
    private BigDecimal installmentsValue;

    @Column(name = "ACTIVE_SIMULATION")
    private boolean activeSimulation;

}
