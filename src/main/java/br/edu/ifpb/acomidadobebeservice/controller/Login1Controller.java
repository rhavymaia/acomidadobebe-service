package br.edu.ifpb.acomidadobebeservice.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

import br.edu.ifpb.acomidadobebeservice.model.Login1;
import br.edu.ifpb.acomidadobebeservice.model.Usuario;
import br.edu.ifpb.acomidadobebeservice.repository.Login1Repository;
import br.edu.ifpb.acomidadobebeservice.repository.UsuarioRepository;

@RestController
public class Login1Controller {

    @Autowired
    private Login1Repository _loginRepository;
    @Autowired
    private UsuarioRepository _usuarioRepository;

    // Listar todos
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public List<Login1> get() {
        return _loginRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET)
    public ResponseEntity<Login1> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Login1> login = _loginRepository.findById(id);
        if(login.isPresent())
            return new ResponseEntity<Login1>(login.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Listar pelo token
    @RequestMapping(value = "/login/token/{token}", method = RequestMethod.GET)
    public ResponseEntity<Login1> getByToken(@PathVariable(value = "token") Integer token)
    {
        Optional<Login1> login = _loginRepository.findByToken(token);
        if(login.isPresent())
            return new ResponseEntity<Login1>(login.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/login", method =  RequestMethod.POST)
    public Login1 post(@RequestBody Login1 login)
    {
        String token = Hashing.sha256()
            .hashString(login.getSenha(), StandardCharsets.UTF_8)
            .toString();

        login.setToken(token);

        Usuario usuario = _usuarioRepository.getById(1); // mudar para optional
        login.setUsuario(usuario);
        return _loginRepository.save(login);
    }

    // Atualizar
    @RequestMapping(value = "/login/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Login1> put(@PathVariable(value = "id") Integer id, @RequestBody Login1 newLogin)
    {
        Optional<Login1> oldLogin = _loginRepository.findById(id);
        if(oldLogin.isPresent()){
            Login1 login = oldLogin.get();
            login.setEmail(newLogin.getEmail());
            login.setSenha(newLogin.getSenha());
            login.setToken(newLogin.getToken());
            _loginRepository.save(login);
            return new ResponseEntity<Login1>(login, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Login1> login = _loginRepository.findById(id);
        if(login.isPresent()){
            _loginRepository.delete(login.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}