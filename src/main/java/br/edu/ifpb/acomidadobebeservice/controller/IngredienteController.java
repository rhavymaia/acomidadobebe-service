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
    @RequestMapping(value = "/ingredienteTeste", method = RequestMethod.GET)
    public List<Ingrediente> get() {
        return _ingredienteRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/ingredienteTeste/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ingrediente> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente> ingredienteTeste = _ingredienteRepository.findById(id);
        if(ingredienteTeste.isPresent())
            return new ResponseEntity<Ingrediente>(ingredienteTeste.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/ingredienteTeste", method =  RequestMethod.POST)
    public ResponseEntity<Ingrediente> post(@RequestBody Ingrediente ingredienteTeste)
    {
        Optional<GrupoAlimentar> grupoAlimentarOptional = _grupoAlimentarRepository.findById(ingredienteTeste.getGrupoAlimentar().getId());
        if(grupoAlimentarOptional.isPresent()){
            GrupoAlimentar grupoAlimentar = grupoAlimentarOptional.get();
            ingredienteTeste.setGrupoAlimentar(grupoAlimentar);
            ingredienteTeste.setNome(ingredienteTeste.getNome());
            _ingredienteRepository.save(ingredienteTeste);
            return new ResponseEntity<Ingrediente>(ingredienteTeste, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar
    @RequestMapping(value = "/ingredienteTeste/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Ingrediente> put(@PathVariable(value = "id") Integer id, @RequestBody Ingrediente newIngredienteTeste)
    {
        Optional<Ingrediente> oldIngredienteTeste = _ingredienteRepository.findById(id);
        if(oldIngredienteTeste.isPresent()){
            Ingrediente ingredienteTeste = oldIngredienteTeste.get();
            ingredienteTeste.setNome(newIngredienteTeste.getNome());
            _ingredienteRepository.save(ingredienteTeste);
            return new ResponseEntity<Ingrediente>(ingredienteTeste, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/ingredienteTeste/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente> ingredienteTeste = _ingredienteRepository.findById(id);
        if(ingredienteTeste.isPresent()){
            _ingredienteRepository.delete(ingredienteTeste.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
