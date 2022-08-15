package br.edu.ifpb.acomidadobebeservice.repository;

import java.util.Optional;

import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {

    Query createQuery(String string);
    
    Optional<Refeicao> findByIdCardapio(Integer idCardapio);
    
}