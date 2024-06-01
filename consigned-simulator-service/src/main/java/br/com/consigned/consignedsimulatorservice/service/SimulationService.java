package br.com.consigned.consignedsimulatorservice.service;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedsimulatorservice.controller.response.SimulationResponse;
import br.com.consigned.consignedsimulatorservice.converter.SimulationConverter;
import br.com.consigned.consignedsimulatorservice.entity.SimulationEntity;
import br.com.consigned.consignedsimulatorservice.kafka.producer.SimulationProducer;
import br.com.consigned.consignedsimulatorservice.model.InformationCalculation;
import br.com.consigned.consignedsimulatorservice.model.SimulationRegistration;
import br.com.consigned.consignedsimulatorservice.repository.SimulationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
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
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<Client> response = restTemplate.exchange(
                    endpointVerification + document, HttpMethod.GET, new HttpEntity<>(headers), Client.class);

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.error("HTTP status code exception occurred during client validation;statusCode={};responseBody={}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException(e);
        } catch (RestClientException e) {
            log.error("Rest client exception occurred during client validation;errorMessage={}", e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("An unexpected error occurred during client validation;errorMessage={}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<Simulation> listSimulations(Integer idSimulation) {
        try {
            List<SimulationEntity> simulationList = simulationRepository.listSimulationsByIdAndActiveSimulation(idSimulation);

            return simulationList != null ? simulationList.stream()
                    .map(simulationConverter::converter)
                    .toList() : Collections.emptyList();
        } catch (DataAccessException ex) {
            log.error("Database error while retrieving simulation;error={}", ex.getMessage());
            throw new RuntimeException("Database error occurred ", ex);
        } catch (Exception ex) {
            log.error("An error occurred while retrieving simulation;error={}", ex.getMessage());
            throw new RuntimeException("An error occurred");
        }
    }

    public Simulation createSimulation(Integer qtdInstallments, BigDecimal valueConsigned, Client client) {
        try {
            if (qtdInstallments != null && qtdInstallments > client.getSegment().getInstallment()) {
                throw new IllegalArgumentException("The number of installments exceeds that allowed for the segment: " + client.getSegment().name());
            }

            InformationCalculation information = informationCalculationService.findInformation(
                    client.getAgreement().getCode(), client.getSegment().getCode(), client.isAccountHolder());

            SimulationRegistration simulation = createSimulationEntity(client, valueConsigned, information, qtdInstallments);

            simulationProducer.sendSimulationRegistration(simulation);

            return simulationConverter.converter(simulation);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
