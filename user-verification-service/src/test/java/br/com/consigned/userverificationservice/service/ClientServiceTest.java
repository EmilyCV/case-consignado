package br.com.consigned.userverificationservice.service;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.SegmentType;
import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.converter.ClientConverter;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import br.com.consigned.userverificationservice.repository.ClientRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientConverter clientConverter;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSuccess() {
        ClientRequest clientRequest = createClientRequest();
        ClientEntity clientEntity = createClientEntity();
        Client client = createClient();

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        when(clientConverter.converter(any(ClientEntity.class))).thenReturn(client);
        when(clientConverter.converter(any(ClientRequest.class))).thenReturn(clientEntity);

        Client result = clientService.save(clientRequest);

        assertEquals(client, result);
        verify(clientRepository, times(1)).save(clientEntity);
    }

    @Test
    public void listAllSuccess() {
        ClientEntity clientEntity = createClientEntity();
        List<ClientEntity> clientEntityList = new ArrayList<>();
        clientEntityList.add(clientEntity);
        Client client = createClient();

        when(clientRepository.findAll()).thenReturn(clientEntityList);
        when(clientConverter.converter(any(ClientEntity.class))).thenReturn(client);

        List<Client> result = clientService.listAll();

        assertEquals(1, result.size());
        assertEquals(client, result.getFirst());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void listClientByDocumentClientNotFound() throws NoSuchAlgorithmException {
        ClientRequest clientRequest = createClientRequest();
        String hashedDocument = getHash(clientRequest.getDocClient());

        when(clientRepository.findByDocClient(hashedDocument)).thenReturn(null);

        Client result = clientService.clientByDocument(clientRequest.getDocClient());

        assertNull(result);

    }

    @Test
    public void listClientByDocumentClientFound() throws NoSuchAlgorithmException {
        ClientRequest clientRequest = createClientRequest();
        ClientEntity clientEntity = createClientEntity();
        Client client = createClient();
        String docClient = clientRequest.getDocClient();
        String hash = getHash(docClient);

        when(clientRepository.findByDocClient(hash)).thenReturn(clientEntity);
        when(clientConverter.converter(clientEntity)).thenReturn(client);

        Client result = clientService.clientByDocument(docClient);

        assertEquals(client, result);
        verify(clientRepository, times(1)).findByDocClient(hash);

    }

    private String getHash(String document) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(document.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }

    private ClientRequest createClientRequest() {
        return ClientRequest.builder()
                .docClient("123456789")
                .name("Emily")
                .accountHolder(true)
                .segment(1)
                .agreement(1)
                .build();
    }

    private ClientEntity createClientEntity() {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setDocClient("123456789");
        clientEntity.setName("Emily");
        clientEntity.setAccountHolder(true);
        clientEntity.setSegment(1);
        clientEntity.setAgreement(1);

        return clientEntity;
    }

    private Client createClient() {
        return Client.builder()
                .docClient("123456789")
                .name("Emily")
                .accountHolder(true)
                .segment(SegmentType.VAREJO)
                .agreement(AgreementType.EP)
                .build();
    }
}
