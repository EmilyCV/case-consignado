package br.com.consigned.userverificationservice.controller;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("createClient");
        String currentTaskName = stopWatch.currentTaskName();

        Client client = null;
        try {
            client = clientService.save(clientRequest);

            log.info("Client created successfully;client={}", client.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (Exception ex) {
            log.error("An error occurred while creating the client;error={};totalTime={}", ex, stopWatch.getTotalTimeSeconds());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } finally {
            log.info("Operation completed;idOperation={};totalTime={};clientResponse={}", currentTaskName, stopWatch.getTotalTimeSeconds(),
                    client != null ? client.toString() : null);
            stopWatch.stop();
        }
    }

    @GetMapping
    public ResponseEntity<?> listAllClients() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("listAllClients");
        String currentTaskName = stopWatch.currentTaskName();

        List<Client> clients = null;
        try {
            clients = clientService.listAll();

            log.info("Clients retrieved successfully;totalClients={}", clients.size());
            return ResponseEntity.status(HttpStatus.OK).body(clients);
        } catch (Exception ex) {
            log.error("An error occurred while retrieving clients;error={};totalTime={}", ex, stopWatch.getTotalTimeSeconds());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("Operation completed;idOperation={};totalTime={};clientResponses={}", currentTaskName, stopWatch.getTotalTimeSeconds(),
                    clients != null ? clients.toString() : null);
        }
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> getClientByDocument(@PathVariable String document) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("getClientByDocument");
        String currentTaskName = stopWatch.currentTaskName();

        Client client = null;
        try {
            client = clientService.clientByDocument(document);

            return ResponseEntity.status(HttpStatus.OK).body(client);

        } catch (Exception ex) {
            log.error("An error occurred while retrieving client;error={};totalTime={}", ex, stopWatch.getTotalTimeSeconds());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("Operation completed;idOperation={};totalTime={};clientResponse={}", currentTaskName, stopWatch.getTotalTimeSeconds(),
                    client != null ? client.toString() : null);
        }
    }
}
