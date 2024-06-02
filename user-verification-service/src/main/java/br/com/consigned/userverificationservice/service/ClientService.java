package br.com.consigned.userverificationservice.service;

import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.userverificationservice.converter.ClientConverter;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import br.com.consigned.userverificationservice.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static br.com.consigned.consigned_model.util.HashConverter.getHash;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }


    public Client save(ClientRequest clientRequest) {
        ClientEntity clientEntity = clientRepository.save(clientConverter.converter(clientRequest));
        return clientConverter.converter(clientEntity);
    }

    public List<Client> listAll() {
        List<ClientEntity> clientEntityList = clientRepository.findAll();
        return clientEntityList.stream()
                .map(clientConverter::converter)
                .toList();
    }

    public Client clientByDocument(String document) throws NoSuchAlgorithmException {
        ClientEntity clientEntity = clientRepository.findByDocClient(getHash(document));
        return clientEntity != null ? clientConverter.converter(clientEntity) : null;
    }
}
