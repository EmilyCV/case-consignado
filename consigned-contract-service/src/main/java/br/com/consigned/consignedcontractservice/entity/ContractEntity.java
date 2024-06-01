package br.com.consigned.consignedcontractservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "TCON_CONTRACT")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTR")
    private int idContract;

    @Column(name = "DT_CONTR")
    private LocalDateTime dtContract;

    @Column(name = "ID_SIMULATION")
    private int idSimulation;

    @Column(name = "ACTIVE_CONTR")
    private boolean activeContract;
}
