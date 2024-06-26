package br.com.consigned.consigned_model.model;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.DocumentType;
import br.com.consigned.consigned_model.enums.SegmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Client implements Serializable {
    private String docClient;
    private DocumentType documentType;
    private String name;
    private boolean accountHolder;
    private SegmentType segment;
    private AgreementType agreement;
    private LocalDateTime inclusionDate;
}
