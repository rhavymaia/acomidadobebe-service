package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Ingrediente1;

@Repository
public interface Ingrediente1Repository extends JpaRepository<Ingrediente1, Integer> {
    
}
