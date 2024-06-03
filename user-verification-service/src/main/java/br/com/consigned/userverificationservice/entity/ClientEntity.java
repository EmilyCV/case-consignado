package br.com.consigned.userverificationservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

import static br.com.consigned.consigned_model.util.HashConverter.getHash;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "TCLI_CLIENT")
public class ClientEntity {

    @Id
//    @CPF
    @Column(name = "DOC_CLIENT")
    private String docClient;

    @Column(name = "ID_DOCUMENT")
    private int documentType;

    @Column(name = "NAME_CLIENT")
    private String name;

    @Column(name = "ACCOUNT_HOLDER")
    private boolean accountHolder;

    @Column(name = "ID_SEGMENT")
    private Integer segment;

    @Column(name = "ID_AGREEMENT")
    private int agreement;

    @Column(name = "INCLUSION_DATE")
    @CreationTimestamp
    private LocalDateTime inclusionDate;

    @PrePersist
    private void hashDocBeforeSave() throws Exception {
        this.docClient = getHash(this.docClient);
    }
}
