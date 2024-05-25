package br.com.consigned.userverificationservice.controller.converter;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.DocumentType;
import br.com.consigned.consigned_model.enums.SegmentType;
import br.com.consigned.userverificationservice.controller.response.ClientResponse;
import br.com.consigned.userverificationservice.entity.ClientEntity;

public class ClientConverter {

    public ClientResponse converter(ClientEntity client){
        return ClientResponse.builder()
                .docClient(client.getDocClient())
                .documentType(DocumentType.forCode(client.getDocumentType().getId()))
                .name(client.getName())
                .accountHolder(client.isAccountHolder())
                .segment(SegmentType.forCode(client.getSegment().getId()))
                .agreement(AgreementType.forCode(client.getAgreement().getId()))
                .inclusionDate(client.getInclusionDate())
                .build();
    }
}
