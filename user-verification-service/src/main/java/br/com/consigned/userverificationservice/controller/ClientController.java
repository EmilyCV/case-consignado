package br.com.consigned.userverificationservice.controller;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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
        Client client = clientService.save(clientRequest);

        log.info("Client created successfully;client={}", client.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping
    public ResponseEntity<?> listAllClients() {
        List<Client> clients = clientService.listAll();

        log.info("Clients retrieved successfully;totalClients={}", clients.size());
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> getClientByDocument(@PathVariable String document) throws NoSuchAlgorithmException {
        Client client = clientService.clientByDocument(document);
        return ResponseEntity.status(HttpStatus.OK).body(client);

    }
}
