package br.com.consigned.consignedsimulatorservice.converter;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consignedsimulatorservice.controller.response.SimulationResponse;
import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import org.springframework.stereotype.Component;

@Component
public class SimulationConverter {

    public SimulationResponse converter(SimulationRegistration simulation) {
        return SimulationResponse.builder()
                .dtSimulation(simulation.getDtSimulation())
                .docClient(simulation.getDocClient())
                .requestAmount(simulation.getRequestAmount())
                .rateApplied(simulation.getRateApplied() + "%")
                .qtdInstallments(simulation.getQtdInstallments())
                .ttlAmountPay(simulation.getTtlAmountPay())
                .installmentsValue(simulation.getInstallmentsValue())
                .agreement(AgreementType.forCode(simulation.getIdAgreement()))
                .build();
    }
}
