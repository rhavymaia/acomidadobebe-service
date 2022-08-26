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

import br.edu.ifpb.acomidadobebeservice.model.GrupoAlimentar;
import br.edu.ifpb.acomidadobebeservice.model.Ingrediente;
import br.edu.ifpb.acomidadobebeservice.repository.GrupoAlimentarRepository;
import br.edu.ifpb.acomidadobebeservice.repository.IngredienteRepository;

@RestController
public class IngredienteController {

    @Autowired
    private IngredienteRepository _ingredienteRepository;
    @Autowired
    private GrupoAlimentarRepository _grupoAlimentarRepository;

    // Listar todos
    @RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
    public List<Ingrediente> get() {
        return _ingredienteRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ingrediente> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente> ingrediente = _ingredienteRepository.findById(id);
        if(ingrediente.isPresent())
            return new ResponseEntity<Ingrediente>(ingrediente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/ingrediente", method =  RequestMethod.POST)
    public ResponseEntity<Ingrediente> post(@RequestBody Ingrediente ingrediente)
    {
        Optional<GrupoAlimentar> grupoAlimentarOptional = _grupoAlimentarRepository.findById(ingrediente.getGrupoAlimentar().getId());
        if(grupoAlimentarOptional.isPresent()){
            GrupoAlimentar grupoAlimentar = grupoAlimentarOptional.get();
            ingrediente.setGrupoAlimentar(grupoAlimentar);
            ingrediente.setNome(ingrediente.getNome());
            _ingredienteRepository.save(ingrediente);
            return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar
    @RequestMapping(value = "/ingrediente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Ingrediente> put(@PathVariable(value = "id") Integer id, @RequestBody Ingrediente newIngrediente)
    {
        Optional<Ingrediente> oldIngrediente = _ingredienteRepository.findById(id);
        if(oldIngrediente.isPresent()){
            Ingrediente ingrediente = oldIngrediente.get();
            ingrediente.setNome(newIngrediente.getNome());
            _ingredienteRepository.save(ingrediente);
            return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
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
