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

import br.edu.ifpb.acomidadobebeservice.model.Responsavel;
import br.edu.ifpb.acomidadobebeservice.repository.ResponsavelRepository;

@RestController
public class ResponsavelController {
    @Autowired
    private ResponsavelRepository _responsavelRepository;
    // Listar todos
    @RequestMapping(value = "/responsavel", method = RequestMethod.GET)
    public List<Responsavel> Get() {
        return _responsavelRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/responsavel/{id}", method = RequestMethod.GET)
    public ResponseEntity<Responsavel> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Responsavel> responsavel = _responsavelRepository.findById(id);
        if(responsavel.isPresent())
            return new ResponseEntity<Responsavel>(responsavel.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/responsavel", method =  RequestMethod.POST)
    public Responsavel Post(@RequestBody Responsavel responsavel)
    {
        return _responsavelRepository.save(responsavel);
    }
    // Atualizar
    @RequestMapping(value = "/responsavel/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Responsavel> Put(@PathVariable(value = "id") Integer id, @RequestBody Responsavel newResponsavel)
    {
        Optional<Responsavel> oldResponsavel = _responsavelRepository.findById(id);
        if(oldResponsavel.isPresent()){
            Responsavel responsavel = oldResponsavel.get();
            _responsavelRepository.save(responsavel);
            return new ResponseEntity<Responsavel>(responsavel, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/responsavel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Responsavel> responsavel = _responsavelRepository.findById(id);
        if(responsavel.isPresent()){
            _responsavelRepository.delete(responsavel.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}