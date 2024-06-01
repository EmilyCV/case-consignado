package br.com.consigned.consigned_model.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Contract {

    @JsonIgnore
    private int idContract;
    private LocalDateTime dtContract;
    private Simulation simulation;
}
