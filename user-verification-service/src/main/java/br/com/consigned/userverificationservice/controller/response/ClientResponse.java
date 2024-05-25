package br.com.consigned.userverificationservice.controller.response;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.DocumentType;
import br.com.consigned.consigned_model.enums.SegmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class ClientResponse {
    private String docClient;
    private DocumentType documentType;
    private String name;
    private boolean accountHolder;
    private SegmentType segment;
    private AgreementType agreement;
    private LocalDateTime inclusionDate;
}
