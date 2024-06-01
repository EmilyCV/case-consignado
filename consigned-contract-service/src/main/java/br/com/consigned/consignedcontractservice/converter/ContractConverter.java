package br.com.consigned.consignedcontractservice.converter;

import br.com.consigned.consigned_model.model.Contract;
import br.com.consigned.consignedcontractservice.controller.response.ContractResponse;
import br.com.consigned.consignedcontractservice.entity.ContractEntity;
import br.com.consigned.consignedcontractservice.model.ContractRegistration;
import org.springframework.stereotype.Component;

@Component
public class ContractConverter {

    public ContractEntity converter(ContractRegistration contractRegistration) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setDtContract(contractRegistration.getDtContract());
        contractEntity.setIdSimulation(contractRegistration.getIdSimulation());
        contractEntity.setActiveContract(contractRegistration.isActiveContract());
        return contractEntity;
    }

    public ContractRegistration converter(Contract contract) {
        return ContractRegistration.builder()
                .dtContract(contract.getDtContract())
                .idSimulation(contract.getSimulation().getIdSimulation())
                .activeContract(true)
                .build();
    }

    public ContractResponse converter(ContractEntity contractEntity) {
        return ContractResponse.builder()
                .idContract(contractEntity.getIdContract())
                .dtContract(contractEntity.getDtContract())
                .idSimulation(contractEntity.getIdSimulation())
                .build();
    }
}
