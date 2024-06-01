package br.com.consigned.consignedcontractservice.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ContractResponse {
    private int idContract;
    private LocalDateTime dtContract;
    private int idSimulation;
}
