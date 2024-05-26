package br.com.consigned.useroperationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/simulation")
public class SimulationController {

    @PostMapping
    public ResponseEntity<?> requestSimulation(){
        return ResponseEntity.ok().build();
    }
}
