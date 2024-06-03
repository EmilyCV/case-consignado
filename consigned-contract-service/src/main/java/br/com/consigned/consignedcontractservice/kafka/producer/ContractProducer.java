package br.com.consigned.consignedcontractservice.kafka.producer;

import br.com.consigned.consignedcontractservice.model.ContractRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContractProducer {

    @Value("${topic.contract.producer.result-contract}")
    private String topic;

    @Autowired
    private final KafkaTemplate<String, ContractRegistration> kafkaTemplate;

    public ContractProducer(KafkaTemplate<String, ContractRegistration> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendContractRegistration(ContractRegistration contractRegistration) {
        kafkaTemplate.send(topic, contractRegistration);

    }
}
