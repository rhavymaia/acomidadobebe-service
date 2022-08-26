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

import br.edu.ifpb.acomidadobebeservice.model.Parentesco;
import br.edu.ifpb.acomidadobebeservice.repository.ParentescoRepository;

@RestController
public class ParentescoController {

    @Autowired
    private ParentescoRepository _parentescoRepository;

    // Listar todos
    @RequestMapping(value = "/parentesco", method = RequestMethod.GET)
    public List<Parentesco> get() {
        return _parentescoRepository.findAll();
    }
    
    // Listar pelo id
    @RequestMapping(value = "/parentesco/{id}", method = RequestMethod.GET)
    public ResponseEntity<Parentesco> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Parentesco> parentesco = _parentescoRepository.findById(id);
        if(parentesco.isPresent())
            return new ResponseEntity<Parentesco>(parentesco.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/parentesco", method =  RequestMethod.POST)
    public Parentesco post(@RequestBody Parentesco parentesco)
    {
        return _parentescoRepository.save(parentesco);
    }

    // Atualizar
    @RequestMapping(value = "/parentesco/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Parentesco> put(@PathVariable(value = "id") Integer id, @RequestBody Parentesco newParentesco)
    {
        Optional<Parentesco> oldParentesco = _parentescoRepository.findById(id);
        if(oldParentesco.isPresent()){
            Parentesco parentesco = oldParentesco.get();
            parentesco.setNome(newParentesco.getNome());
            _parentescoRepository.save(parentesco);
            return new ResponseEntity<Parentesco>(parentesco, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/parentesco/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Parentesco> parentesco = _parentescoRepository.findById(id);
        if(parentesco.isPresent()){
            _parentescoRepository.delete(parentesco.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
