package br.com.consigned.userverificationservice.service;

import br.com.consigned.userverificationservice.controller.converter.ClientConverter;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import br.com.consigned.userverificationservice.repository.ClientRepository;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    public ClientResponse save(ClientRequest clientRequest) {
        try {
            ClientEntity clientEntity = clientRepository.save(clientConverter.converter(clientRequest));

            return clientConverter.converter(clientEntity);
        } catch (DataAccessException ex) {
            log.error("Database error while saving client;error={}", ex.getMessage());
            throw new RuntimeException("Database error occurred ", ex);
        } catch (Exception ex) {
            log.error("An error occurred while saving client;error={}", ex.getMessage());
            throw new RuntimeException("An error occurred");
        }
    }

    public List<ClientResponse> listAll() {
        try {
            List<ClientEntity> clientEntityList = clientRepository.findAll();

            return clientEntityList.stream()
                    .map(clientConverter::converter)
                    .toList();
        } catch (DataAccessException ex) {
            log.error("Database error while retrieving clients;error={}", ex.getMessage());
            throw new RuntimeException("Database error occurred ", ex);
        } catch (Exception ex) {
            log.error("An error occurred while retrieving clients;error={}", ex.getMessage());
            throw new RuntimeException("An error occurred");
        }
    }
}
