package br.com.consigned.consignedcontractservice.service;

import br.com.consigned.consignedcontractservice.converter.ContractConverter;
import br.com.consigned.consignedcontractservice.model.ContractRegistration;
import br.com.consigned.consignedcontractservice.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContractRegistrationService {
    private final ContractRepository contractRepository;
    private final ContractConverter contractConverter;

    public ContractRegistrationService(ContractRepository contractRepository, ContractConverter contractConverter) {
        this.contractRepository = contractRepository;
        this.contractConverter = contractConverter;
    }

    public void saveContract(ContractRegistration contractRegistration) {
        contractRepository.save(contractConverter.converter(contractRegistration));

    }
}