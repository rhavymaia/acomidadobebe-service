package br.edu.ifpb.acomidadobebeservice.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import br.edu.ifpb.acomidadobebeservice.model.LoginTeste;

public interface LoginTesteRepository extends JpaRepository<LoginTeste, Integer>{

    /**
     * 
     * @param token
     * @return usuario
     * 
     * TODO: retorna vazio. olhar se o tipo de retorno está certo e o formato da consulta e se é nessa classe
     *
     */
    
    //@Query("FROM LoginTeste l WHERE l.usuario.token = :token ")
    //List<LoginTeste> findUsuarioByLoginTesteToken(String token);
    
}
