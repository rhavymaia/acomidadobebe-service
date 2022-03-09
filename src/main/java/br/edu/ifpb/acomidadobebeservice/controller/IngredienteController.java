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

import br.edu.ifpb.acomidadobebeservice.model.Ingrediente;
import br.edu.ifpb.acomidadobebeservice.repository.IngredienteRepository;

@RestController
public class IngredienteController {
    @Autowired
    private IngredienteRepository _ingredienteRepository;
    // Listar todos
    @RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
    public List<Ingrediente> Get() {
        return _ingredienteRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ingrediente> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente> ingrediente = _ingredienteRepository.findById(id);
        if(ingrediente.isPresent())
            return new ResponseEntity<Ingrediente>(ingrediente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/ingrediente", method =  RequestMethod.POST)
    public Ingrediente Post(@RequestBody Ingrediente ingrediente)
    {
        return _ingredienteRepository.save(ingrediente);
    }
    // Atualizar
    @RequestMapping(value = "/ingrediente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Ingrediente> Put(@PathVariable(value = "id") Integer id, @RequestBody Ingrediente newIngrediente)
    {
        Optional<Ingrediente> oldIngrediente = _ingredienteRepository.findById(id);
        if(oldIngrediente.isPresent()){
            Ingrediente ingrediente = oldIngrediente.get();
            ingrediente.setNome(newIngrediente.getNome());
            ingrediente.setGrupo_ingrediente(newIngrediente.getGrupo_ingrediente()); 
            ingrediente.setFk_preparacao(newIngrediente.getFk_preparacao());
            ingrediente.setFk_lista_compra(newIngrediente.getFk_lista_compra());
            _ingredienteRepository.save(ingrediente);
            return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente> ingrediente = _ingredienteRepository.findById(id);
        if(ingrediente.isPresent()){
            _ingredienteRepository.delete(ingrediente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}