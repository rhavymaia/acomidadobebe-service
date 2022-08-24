package br.edu.ifpb.acomidadobebeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Login1;

@Repository
public interface Login1Repository extends JpaRepository<Login1, Integer> {

    Optional<Login1> findByToken(Integer token);
    
}