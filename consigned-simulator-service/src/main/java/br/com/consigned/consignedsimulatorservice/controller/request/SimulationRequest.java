package br.com.consigned.consignedsimulatorservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Getter
@Setter
public class SimulationRequest {

    @NotNull(message = "The document the client is obligatory")
//    @CPF(message = "Invalid document")
    private String document;

    @Min(value = 1, message = "Invalid number of installments")
    @JsonProperty("quantityInstallments")
    private Integer qtdInstallments;

    @NotNull(message = "The value of consigned is obligatory")
    @Min(value = 1, message = "The consigned value is invalid")
    private BigDecimal valueConsigned;

}
