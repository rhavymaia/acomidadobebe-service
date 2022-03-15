package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Integer>{

    
}
