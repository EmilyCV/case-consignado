package br.com.consigned.consignedsimulatorservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class InformationCalculation {
    private BigDecimal interestRate;
    private int qtdInstallments;
    private BigDecimal discount;
}
