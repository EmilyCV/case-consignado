package br.com.consigned.consignedsimulatorservice.service;

import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimulationRegistrationService {

    private final SimpleJdbcCall jdbc;

    public SimulationRegistrationService(SimpleJdbcCall jdbc) {
        this.jdbc = jdbc;
    }

    public void saveSimulation(SimulationRegistration simulationRegistration) {
        try {
            SqlParameterSource sql = new MapSqlParameterSource()
                    .addValue("param_dtSimulation", simulationRegistration.getDtSimulation())
                    .addValue("param_docClient", simulationRegistration.getDocClient())
                    .addValue("param_idAgreement", simulationRegistration.getIdAgreement())
                    .addValue("param_requestAmount", simulationRegistration.getRequestAmount())
                    .addValue("param_rateApplied", simulationRegistration.getRateApplied())
                    .addValue("param_qtdInstallments", simulationRegistration.getQtdInstallments())
                    .addValue("param_ttlAmountPay", simulationRegistration.getTtlAmountPay())
                    .addValue("param_installmentsValue", simulationRegistration.getInstallmentsValue())
                    .addValue("param_activeSimulation", simulationRegistration.isActiveSimulation() ? 1 : 0);

            jdbc.withProcedureName("PROC_REGISTRATION_SIMULATION");
            jdbc.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving simulation", e);
        }
    }
}
