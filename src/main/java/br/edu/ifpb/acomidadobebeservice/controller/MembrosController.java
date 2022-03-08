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

import br.edu.ifpb.acomidadobebeservice.model.Membros;
import br.edu.ifpb.acomidadobebeservice.repository.MembrosRepository;

@RestController
public class MembrosController {
    @Autowired
    private MembrosRepository _membrosRepository;
    // Listar todos
    @RequestMapping(value = "/membros", method = RequestMethod.GET)
    public List<Membros> Get() {
        return _membrosRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/membros/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membros> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Membros> membros = _membrosRepository.findById(id);
        if(membros.isPresent())
            return new ResponseEntity<Membros>(membros.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/membros", method =  RequestMethod.POST)
    public Membros Post(@RequestBody Membros membros)
    {
        return _membrosRepository.save(membros);
    }
    // Atualizar
    @RequestMapping(value = "/membros/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Membros> Put(@PathVariable(value = "id") Integer id, @RequestBody Membros newMembros)
    {
        Optional<Membros> oldMembros = _membrosRepository.findById(id);
        if(oldMembros.isPresent()){
            Membros membros = oldMembros.get();
            membros.setNome(newMembros.getNome());
            membros.setParentesco(newMembros.getParentesco());
            _membrosRepository.save(membros);
            return new ResponseEntity<Membros>(membros, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/membros/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Membros> membros = _membrosRepository.findById(id);
        if(membros.isPresent()){
            _membrosRepository.delete(membros.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}