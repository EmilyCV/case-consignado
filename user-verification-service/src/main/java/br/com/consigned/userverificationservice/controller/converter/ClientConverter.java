package br.com.consigned.userverificationservice.controller.converter;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.DocumentType;
import br.com.consigned.consigned_model.enums.SegmentType;
import br.com.consigned.userverificationservice.controller.request.ClientRequest;
import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static br.com.consigned.consigned_model.enums.DocumentType.CPF;

@Component
public class ClientConverter {

    public ClientResponse converter(ClientEntity client) {
        return ClientResponse.builder()
                .docClient(client.getDocClient())
                .documentType(DocumentType.forCode(client.getDocumentType()))
                .name(client.getName())
                .accountHolder(client.isAccountHolder())
                .segment(SegmentType.forCode(client.getSegment()))
                .agreement(AgreementType.forCode(client.getAgreement()))
                .inclusionDate(client.getInclusionDate())
                .build();
    }

    public ClientEntity converter(ClientRequest client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setDocClient(client.getDocClient());
        clientEntity.setDocumentType(CPF.getCode());
        clientEntity.setName(client.getName());
        clientEntity.setAccountHolder(client.isAccountHolder());
        clientEntity.setSegment(client.getSegment());
        clientEntity.setAgreement(client.getAgreement());
        clientEntity.setInclusionDate(LocalDateTime.now());

        return clientEntity;
    }

}
