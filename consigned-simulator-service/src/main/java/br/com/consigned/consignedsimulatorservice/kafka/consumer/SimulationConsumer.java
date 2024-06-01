package br.com.consigned.consignedsimulatorservice.kafka.consumer;

import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import br.com.consigned.consignedsimulatorservice.service.SimulationRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SimulationConsumer {

    private final SimulationRegistrationService simulationRegistrationService;

    @KafkaListener(topics = "${topic.simulation.consumer.result-simulation}", groupId = "${topic.simulation.consumer.group-id}")
    public void receive(SimulationRegistration simulationRegistration) {
        try {
            simulationRegistrationService.saveSimulation(simulationRegistration);
        } catch (Exception e) {
            throw new RuntimeException("Error when consuming message from Kafka {}", e);
        }
    }
}
