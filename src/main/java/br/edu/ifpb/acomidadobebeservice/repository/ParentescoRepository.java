package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Parentesco;

@Repository
public interface ParentescoRepository extends JpaRepository<Parentesco, Integer>{
    
}
