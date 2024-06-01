package br.com.consigned.consignedsimulatorservice.repository;

import br.com.consigned.consignedsimulatorservice.entity.SimulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationEntity, Integer> {

    @Query("SELECT simu FROM SimulationEntity simu WHERE simu.activeSimulation = true " +
            "AND (:idSimulation IS NULL OR simu.idSimulation = :idSimulation)")
    List<SimulationEntity> listSimulationsByIdAndActiveSimulation(@Param("idSimulation") String idSimulation);
}
