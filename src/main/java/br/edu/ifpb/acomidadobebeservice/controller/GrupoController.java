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

import br.edu.ifpb.acomidadobebeservice.model.Grupo;
import br.edu.ifpb.acomidadobebeservice.repository.GrupoRepository;

@RestController
public class GrupoController {
    @Autowired
    private GrupoRepository _grupoRepository;
    // Listar todos
    @RequestMapping(value = "/grupo", method = RequestMethod.GET)
    public List<Grupo> Get() {
        return _grupoRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/grupo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Grupo> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Grupo> grupo = _grupoRepository.findById(id);
        if(grupo.isPresent())
            return new ResponseEntity<Grupo>(grupo.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/grupo", method =  RequestMethod.POST)
    public Grupo Post(@RequestBody Grupo ingrediente)
    {
        return _grupoRepository.save(ingrediente);
    }
    // Atualizar
    @RequestMapping(value = "/grupo/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Grupo> Put(@PathVariable(value = "id") Integer id, @RequestBody Grupo newGrupo)
    {
        Optional<Grupo> oldGrupo = _grupoRepository.findById(id);
        if(oldGrupo.isPresent()){
            Grupo grupo = oldGrupo.get();
            grupo.setNome(newGrupo.getNome());
            _grupoRepository.save(grupo);
            return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/grupo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Grupo> grupo = _grupoRepository.findById(id);
        if(grupo.isPresent()){
            _grupoRepository.delete(grupo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
