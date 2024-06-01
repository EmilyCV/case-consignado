package br.com.consigned.consignedsimulatorservice.converter;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedsimulatorservice.controller.response.SimulationResponse;
import br.com.consigned.consignedsimulatorservice.entity.SimulationEntity;
import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import org.springframework.stereotype.Component;

@Component
public class SimulationConverter {

    public Simulation converter(SimulationRegistration simulation) {
        return Simulation.builder()
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

    public SimulationResponse converter(SimulationEntity simulation) {
        return SimulationResponse.builder()
                .idSimulation(simulation.getIdSimulation())
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
