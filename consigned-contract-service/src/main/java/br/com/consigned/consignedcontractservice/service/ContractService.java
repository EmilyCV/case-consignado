package br.com.consigned.consignedcontractservice.service;

import br.com.consigned.consigned_model.model.Contract;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedcontractservice.controller.response.ContractResponse;
import br.com.consigned.consignedcontractservice.converter.ContractConverter;
import br.com.consigned.consignedcontractservice.entity.ContractEntity;
import br.com.consigned.consignedcontractservice.kafka.producer.ContractProducer;
import br.com.consigned.consignedcontractservice.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ContractService {

    private final RestTemplate restTemplate;
    private final ContractProducer contractProducer;
    private final ContractConverter contractConverter;
    private final ContractRepository contractRepository;

    @Value("${consigned.simulation.url}")
    public String endpointSimulation;

    public ContractService(RestTemplate restTemplate, ContractProducer contractProducer, ContractConverter contractConverter,
                           ContractRepository contractRepository) {
        this.restTemplate = restTemplate;
        this.contractProducer = contractProducer;
        this.contractConverter = contractConverter;
        this.contractRepository = contractRepository;
    }

    public Simulation getSimulation(Integer simulation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Simulation>> response = restTemplate.exchange(
                endpointSimulation + simulation, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {
                });

        return Objects.requireNonNull(response.getBody()).stream().findFirst().orElse(null);
    }

    public Contract createContract(Simulation simulation) {
        Contract contract = Contract.builder()
                .dtContract(LocalDateTime.now())
                .simulation(simulation)
                .build();

        contractProducer.sendContractRegistration(contractConverter.converter(contract));
        return contract;
    }

    public List<ContractResponse> listContracts(Integer idContract) {
        List<ContractEntity> contractList = contractRepository.listContract(idContract);

        return contractList != null ? contractList.stream()
                .map(contractConverter::converter)
                .toList() : Collections.emptyList();
    }
}
