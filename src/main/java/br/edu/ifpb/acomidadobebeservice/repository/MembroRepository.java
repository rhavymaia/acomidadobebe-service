package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Integer> {
    
}