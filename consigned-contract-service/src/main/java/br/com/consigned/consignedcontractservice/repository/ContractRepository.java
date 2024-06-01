package br.com.consigned.consignedcontractservice.repository;

import br.com.consigned.consignedcontractservice.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Integer> {
    @Query("SELECT cont FROM ContractEntity cont WHERE (:idContract IS NULL OR cont.idContract = :idContract)")
    List<ContractEntity> listContract(@Param("idContract") Integer idContract);
}
