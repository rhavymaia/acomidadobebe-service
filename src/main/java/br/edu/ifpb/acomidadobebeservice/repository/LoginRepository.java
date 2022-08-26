package br.edu.ifpb.acomidadobebeservice.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import br.edu.ifpb.acomidadobebeservice.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

    //List<Login> findByLoginToken(String token);

    
    //@Query("FROM LoginTeste l WHERE l.usuario.token = :token ")
    //List<LoginTeste> findUsuarioByLoginTesteToken(String token);
    
}
