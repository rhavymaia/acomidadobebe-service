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
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public List<Login> get() {
        return _loginRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET)
    public ResponseEntity<Login> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Login> login = _loginRepository.findById(id);
        if(login.isPresent())
            return new ResponseEntity<Login>(login.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* 
    * TODO Lista pelo token
    */ 

    // Lista usuario pelo token
    //@RequestMapping(value = "/login/token/{token}", method = RequestMethod.GET)
    //public List<Login> getByToken(@PathVariable(value = "token") String token) {
    //   return _loginRepository.findByLoginToken(token);
    //}

    // Cadastrar
    @RequestMapping(value = "/login", method =  RequestMethod.POST)
    public ResponseEntity<Login> post(@RequestBody Login login)
    {
        Optional<Usuario> usuarioOptional = _usuarioRepository.findById(login.getUsuario().getId()); // mudar para optional
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            login.setUsuario(usuario);

            // passa a senha do usuario para codificada
            String token = Hashing.sha256()
                .hashString(login.getUsuario().getSenha(), StandardCharsets.UTF_8)
                .toString(); 

            login.setToken(token);
            login.setAtivo(true);
            login.setData_hora(LocalDateTime.now());

            _loginRepository.save(login);

            return new ResponseEntity<Login>(login, HttpStatus.OK);
        
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
              
    }

    // Atualizar
    @RequestMapping(value = "/login/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Login> put(@PathVariable(value = "id") Integer id, @RequestBody Login newLogin)
    {
        Optional<Login> oldLogin = _loginRepository.findById(id);
        if(oldLogin.isPresent()){
            Login login = oldLogin.get();
            login.setAtivo(newLogin.isAtivo());
            _loginRepository.save(login);
            return new ResponseEntity<Login>(login, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Deletar 
    @RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Login> login = _loginRepository.findById(id);
        if(login.isPresent()){
            _loginRepository.delete(login.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

    

    
    
    

