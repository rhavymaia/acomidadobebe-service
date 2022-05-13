package br.edu.ifpb.acomidadobebeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findByKey(String key);
    
}