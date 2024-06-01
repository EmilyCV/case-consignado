package br.com.consigned.consignedcontractservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/contract")
public class ContractController {

    @PostMapping
    public ResponseEntity<?> requestContract() {
        return ResponseEntity.ok().build();
    }
}
