package br.com.consigned.consignedcontractservice.controller;

import br.com.consigned.consigned_model.model.Contract;
import br.com.consigned.consigned_model.model.Simulation;
import br.com.consigned.consignedcontractservice.controller.request.ContractRequest;
import br.com.consigned.consignedcontractservice.controller.response.ContractResponse;
import br.com.consigned.consignedcontractservice.exception.SimulationNotFoundException;
import br.com.consigned.consignedcontractservice.service.ContractService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ResponseEntity<?> requestContract(@Valid @RequestBody ContractRequest contractRequest) {
        Simulation simulation = contractService.getSimulation(contractRequest.getIdSimulation());
        if (simulation == null) {
            throw new SimulationNotFoundException();
        }

        Contract contract = contractService.createContract(simulation);
        return ResponseEntity.status(HttpStatus.OK).body(contract);
    }

    @GetMapping({"/{contract}", "/"})
    public ResponseEntity<?> listSimulations(@PathVariable(required = false) Integer contract) {
        List<ContractResponse> contracts = contractService.listContracts(contract);

        log.info("Contracts retrieved successfully;totalSimulations={}", contracts.size());
        return ResponseEntity.status(HttpStatus.OK).body(contracts);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArrayList<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
