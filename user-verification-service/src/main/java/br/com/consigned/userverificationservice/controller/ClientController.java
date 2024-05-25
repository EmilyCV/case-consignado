package br.com.consigned.userverificationservice.controller;

import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import br.com.consigned.userverificationservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientEntity client) {
        ClientResponse clientResponse = clientService.save(client);
        return ResponseEntity.ok(clientResponse);
    }
}
