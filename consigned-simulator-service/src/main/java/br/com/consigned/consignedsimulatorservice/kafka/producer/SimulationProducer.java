package br.com.consigned.consignedsimulatorservice.kafka.producer;

import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimulationProducer {

    @Value("${topic.simulation.producer.result-simulation}")
    private String topic;

    @Autowired
    private final KafkaTemplate<String, SimulationRegistration> kafkaTemplate;

    public SimulationProducer(KafkaTemplate<String, SimulationRegistration> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSimulationRegistration(SimulationRegistration simulationRegistration){
        try{
            kafkaTemplate.send(topic, simulationRegistration);
        } catch (Exception e) {
            throw new RuntimeException("Error producing kafka message");
        }
    }
}
