package br.com.consigned.userverificationservice.controller;

import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Void> validateClient(@RequestParam String cpf) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("createClient");

        ClientResponse clientResponse = null;
        try {
            clientResponse = clientService.save(clientRequest);
            log.info("Client created successfully;client={}", clientResponse.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
        } catch (Exception ex) {
            log.error("An unexpected error occurred while creating the client;error={};totalTime={}", ex, stopWatch.getTotalTimeSeconds());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } finally {
            log.info("Operation completed;idOperation={};totalTime={};clientResponse={}", stopWatch.currentTaskName(), stopWatch.getTotalTimeSeconds(),
                    clientResponse != null ? clientResponse.toString() : null);
            stopWatch.stop();
        }
    }
}
