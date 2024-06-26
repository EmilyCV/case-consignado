package br.com.consigned.consignedsimulatorservice.controller;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedsimulatorservice.controller.request.SimulationRequest;
import br.com.consigned.consignedsimulatorservice.service.SimulationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("api/v1/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping
    public ResponseEntity<?> requestSimulation(@Valid @RequestBody SimulationRequest simulationRequest) {

        String document = simulationRequest.getDocument().replaceAll("[^0-9]", "");

        Client client = simulationService.validationClient(document);

        Simulation simulation = simulationService.createSimulation(simulationRequest.getQtdInstallments(), simulationRequest.getValueConsigned(), client);
        return ResponseEntity.status(HttpStatus.OK).body(simulation);
    }

    @GetMapping({"/{simulation}", "/"})
    public ResponseEntity<?> listSimulations(@PathVariable(required = false) Integer simulation) {
        List<Simulation> simulations = simulationService.listSimulations(simulation);

        log.info("Simulations retrieved successfully;totalSimulations={}", simulations.size());
        return ResponseEntity.status(HttpStatus.OK).body(simulations);
    }
}
