package br.com.consigned.consignedsimulatorservice.service;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedsimulatorservice.converter.SimulationConverter;
import br.com.consigned.consignedsimulatorservice.entity.SimulationEntity;
import br.com.consigned.consignedsimulatorservice.exception.CustomerNotFoundException;
import br.com.consigned.consignedsimulatorservice.exception.InstallmentsLimitExceededException;
import br.com.consigned.consignedsimulatorservice.kafka.producer.SimulationProducer;
import br.com.consigned.consignedsimulatorservice.model.InformationCalculation;
import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import br.com.consigned.consignedsimulatorservice.repository.SimulationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SimulationService {

    private final RestTemplate restTemplate;
    private final InformationCalculationService informationCalculationService;
    private final SimulationConverter simulationConverter;
    private final SimulationProducer simulationProducer;
    private final SimulationRepository simulationRepository;

    @Value("${consigned.verification.url}")
    public String endpointVerification;

    public SimulationService(RestTemplate restTemplate, InformationCalculationService informationCalculationService, SimulationConverter simulationConverter, SimulationProducer simulationProducer, SimulationRepository simulationRepository) {
        this.restTemplate = restTemplate;
        this.informationCalculationService = informationCalculationService;
        this.simulationConverter = simulationConverter;
        this.simulationProducer = simulationProducer;
        this.simulationRepository = simulationRepository;
    }

    public Client validationClient(String document) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Client> response = restTemplate.exchange(
                endpointVerification + document, HttpMethod.GET, new HttpEntity<>(headers), Client.class);

        if (response.getBody() == null) {
            throw new CustomerNotFoundException();
        }

        return response.getBody();
    }

    public List<Simulation> listSimulations(Integer idSimulation) {
        List<SimulationEntity> simulationList = simulationRepository.listSimulationsByIdAndActiveSimulation(idSimulation);

        return simulationList != null ? simulationList.stream()
                .map(simulationConverter::converter)
                .toList() : Collections.emptyList();
    }

    public Simulation createSimulation(Integer qtdInstallments, BigDecimal valueConsigned, Client client) {
        if (qtdInstallments != null && qtdInstallments > client.getSegment().getInstallment()) {
            throw new InstallmentsLimitExceededException(client.getSegment().name());
        }

        InformationCalculation information = informationCalculationService.findInformation(
                client.getAgreement().getCode(), client.getSegment().getCode(), client.isAccountHolder());

        SimulationRegistration simulation = createSimulationEntity(client, valueConsigned, information, qtdInstallments);

        simulationProducer.sendSimulationRegistration(simulation);

        return simulationConverter.converter(simulation);
    }

    private SimulationRegistration createSimulationEntity(Client client, BigDecimal valueConsigned, InformationCalculation information, Integer qtdInstallments) {
        SimulationRegistration simulation = new SimulationRegistration();
        simulation.setDtSimulation(LocalDateTime.now());
        simulation.setDocClient(client.getDocClient());
        simulation.setIdAgreement(client.getAgreement().getCode());
        simulation.setRequestAmount(valueConsigned);

        BigDecimal rateApplied = calculateRateApplied(information);
        simulation.setRateApplied(rateApplied);

        int installments = qtdInstallments != null ? qtdInstallments : information.getQtdInstallments();
        simulation.setQtdInstallments(installments);

        BigDecimal totalAmountPay = calculateTotalAmountPay(valueConsigned, rateApplied, installments);
        simulation.setTtlAmountPay(totalAmountPay);

        BigDecimal installmentsValue = totalAmountPay.divide(new BigDecimal(installments), RoundingMode.DOWN).setScale(2, RoundingMode.HALF_UP);
        simulation.setInstallmentsValue(installmentsValue);
        simulation.setActiveSimulation(true);
        return simulation;
    }

    private BigDecimal calculateRateApplied(InformationCalculation information) {
        if (information.getDiscount() != null) {
            BigDecimal discount = new BigDecimal(100).subtract(information.getDiscount());
            return discount.multiply(information.getInterestRate()).divide(new BigDecimal(100), RoundingMode.DOWN).setScale(2, RoundingMode.HALF_UP);
        } else {
            return information.getInterestRate();
        }
    }

    private BigDecimal calculateTotalAmountPay(BigDecimal valueConsigned, BigDecimal rateApplied, int installments) {
        BigDecimal rate = (valueConsigned.multiply(rateApplied).multiply(new BigDecimal(installments))).divide(new BigDecimal(100), RoundingMode.DOWN).setScale(2, RoundingMode.HALF_UP);
        return rate.add(valueConsigned);
    }
}
