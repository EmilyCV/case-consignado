package br.com.consigned.userverificationservice.repository;

import br.com.consigned.userverificationservice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    List<ClientEntity> findByDocClient(String document);
}
