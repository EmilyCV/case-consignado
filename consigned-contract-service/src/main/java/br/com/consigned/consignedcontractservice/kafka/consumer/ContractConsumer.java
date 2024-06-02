package br.com.consigned.consignedcontractservice.kafka.consumer;

import br.com.consigned.consignedcontractservice.model.ContractRegistration;
import br.com.consigned.consignedcontractservice.service.ContractRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ContractConsumer {
    private final ContractRegistrationService contractRegistrationService;

    @KafkaListener(topics = "${topic.contract.consumer.result-contract}", groupId = "${topic.contract.consumer.group-id}")
    public void receive(ContractRegistration contractRegistration) {
        contractRegistrationService.saveContract(contractRegistration);
    }
}
