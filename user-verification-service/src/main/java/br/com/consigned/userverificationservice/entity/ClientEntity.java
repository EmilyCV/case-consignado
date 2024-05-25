package br.com.consigned.userverificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.consigned.userverificationservice.util.HashConverter.getHash;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TCLI_CLIENT")
public class ClientEntity {

    @Id
    @Column(name = "DOC_CLIENT")
    private String docClient;

    @ManyToOne
    @JoinColumn(name = "ID_DOCUMENT")
    private DocumentTypeEntity documentType;

    @Column(name = "NAME_CLIENT")
    private String name;

    @Column(name = "ACCOUNT_HOLDER")
    private boolean accountHolder;

    @ManyToOne
    @JoinColumn(name = "ID_SEGMENT")
    private SegmentTypeEntity segment;

    @ManyToOne
    @JoinColumn(name = "ID_AGREEMENT")
    private AgreementTypeEntity agreement;

    @Column(name = "INCLUSION_DATE")
    @CreationTimestamp
    private LocalDateTime inclusionDate;

    @PrePersist
    private void hashDocBeforeSave() throws Exception {
        this.docClient = getHash(this.docClient);
    }
}
