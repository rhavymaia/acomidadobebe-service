package br.edu.ifpb.acomidadobebeservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {

    //Optional<Refeicao> findByIdCardapio(Integer idCardapio);
    List<Refeicao> findByCardapioId(Integer id);
    
}