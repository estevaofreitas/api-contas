package br.com.nextcomputing.api.contas.application.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.nextcomputing.api.contas.application.persistence.entity.ContaEntity;

@Repository
public interface ContaRepository extends CrudRepository<ContaEntity, Long>{
    
}
