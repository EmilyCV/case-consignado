package br.com.consigned.consignedsimulatorservice.service;

import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimulationRegistrationService {

    public void saveSimulation(SimulationRegistration simulationRegistration) {
        log.info("Mensagem recebida: {}", simulationRegistration.toString());

    }
}
