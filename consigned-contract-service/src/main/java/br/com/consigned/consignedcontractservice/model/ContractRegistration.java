package br.com.consigned.consignedcontractservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ContractRegistration {
    private LocalDateTime dtContract;
    private int idSimulation;
    private boolean activeContract;
}
