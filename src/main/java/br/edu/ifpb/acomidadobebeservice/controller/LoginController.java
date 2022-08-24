package br.edu.ifpb.acomidadobebeservice.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

import br.edu.ifpb.acomidadobebeservice.model.Login;
import br.edu.ifpb.acomidadobebeservice.model.Usuario;
import br.edu.ifpb.acomidadobebeservice.repository.LoginRepository;
import br.edu.ifpb.acomidadobebeservice.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {

    @Autowired
    private LoginRepository _loginRepository;
    @Autowired
    private UsuarioRepository _usuarioRepository;

    // Listar todos
    @RequestMapping(value = "/loginTeste", method = RequestMethod.GET)
    public List<Login> Get() {
        return _loginRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/loginTeste/{id}", method = RequestMethod.GET)
    public ResponseEntity<Login> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Login> loginTeste = _loginRepository.findById(id);
        if(loginTeste.isPresent())
            return new ResponseEntity<Login>(loginTeste.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* 
    * TODO Lista pelo token
    */ 

    // Lista usuario pelo token
    //@RequestMapping(value = "/loginTeste/token/{token}", method = RequestMethod.GET)
    //public List<Usuario> getByToken(@PathVariable(value = "token") String token) {
    //   return _usuarioRepository.findByLoginTesteToken(token);
    //}

    // Cadastrar
    @RequestMapping(value = "/loginTeste", method =  RequestMethod.POST)
    public ResponseEntity<Login> Post(@RequestBody Login loginTeste)
    {
        Optional<Usuario> usuarioOptional = _usuarioRepository.findById(loginTeste.getUsuario().getId()); // mudar para optional
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            loginTeste.setUsuario(usuario);

            // passa a senha do usuario para codificada
            String token = Hashing.sha256()
                .hashString(loginTeste.getUsuario().getSenha(), StandardCharsets.UTF_8)
                .toString(); 

            loginTeste.setToken(token);
            loginTeste.setAtivo(true);
            loginTeste.setData_hora(LocalDateTime.now());

            _loginRepository.save(loginTeste);

            return new ResponseEntity<Login>(loginTeste, HttpStatus.OK);
        
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
              
    }

    /**
     * TODO: criar o endpoint put para atualizar
     * 
     * @param id
     * @return 
     */


    // Deletar 
    @RequestMapping(value = "/loginTeste/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Login> loginTeste = _loginRepository.findById(id);
        if(loginTeste.isPresent()){
            _loginRepository.delete(loginTeste.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

    

    
    
    

