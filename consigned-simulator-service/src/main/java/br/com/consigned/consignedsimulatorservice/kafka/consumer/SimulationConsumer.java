package br.com.consigned.consignedsimulatorservice.kafka.consumer;

import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import br.com.consigned.consignedsimulatorservice.service.SimulationRegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SimulationConsumer {

    private static final Logger log = LoggerFactory.getLogger(SimulationConsumer.class);
    private final SimulationRegistrationService simulationRegistrationService;

    @KafkaListener(topics = "${topic.simulation.consumer.result-simulation}", groupId = "${topic.simulation.consumer.group-id}")
    public void receive(SimulationRegistration simulationRegistration) {
        log.info("Simulation record received: {}", simulationRegistration.toString());
        simulationRegistrationService.saveSimulation(simulationRegistration);
    }
}
