package br.com.consigned.userverificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TCLI_AGREEMENT_TYPE")
public class AgreementTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGRM")
    private int id;

    @Column(name = "AGMR_TYPE", unique = true)
    private String agreementType;
}
