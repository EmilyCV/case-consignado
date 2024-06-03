package br.com.consigned.consignedcontractservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ContractRequest {

    @NotNull(message = "Identification of the simulation is mandatory")
    @JsonProperty("simulation")
    private Integer idSimulation;
}
