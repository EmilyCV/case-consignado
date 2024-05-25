package br.com.consigned.userverificationservice.service;

import br.com.consigned.userverificationservice.controller.converter.ClientConverter;
import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import br.com.consigned.userverificationservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter converter;

    public ClientService(ClientRepository clientRepository, ClientConverter converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    public ClientResponse save(ClientEntity client) {
        ClientEntity clientEntity = clientRepository.save(client);
        return converter.converter(clientEntity);
    }
}
