package br.edu.ifpb.acomidadobebeservice.controller;

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

import br.edu.ifpb.acomidadobebeservice.model.Login;
import br.edu.ifpb.acomidadobebeservice.repository.LoginRepository;

@RestController
public class LoginController {
    @Autowired
    private LoginRepository _loginRepository;
    // Listar todos
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public List<Login> Get() {
        return _loginRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET)
    public ResponseEntity<Login> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Login> login = _loginRepository.findById(id);
        if(login.isPresent())
            return new ResponseEntity<Login>(login.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/login", method =  RequestMethod.POST)
    public Login Post(@RequestBody Login login)
    {
        return _loginRepository.save(login);
    }
    // Atualizar
    @RequestMapping(value = "/login/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Login> Put(@PathVariable(value = "id") Integer id, @RequestBody Login newLogin)
    {
        Optional<Login> oldLogin = _loginRepository.findById(id);
        if(oldLogin.isPresent()){
            Login login = oldLogin.get();
            login.setEmail(newLogin.getEmail());
            login.setSenha(newLogin.getSenha());
            login.setToken(newLogin.getToken());
            _loginRepository.save(login);
            return new ResponseEntity<Login>(login, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
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