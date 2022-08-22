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
import br.edu.ifpb.acomidadobebeservice.model.IngredienteTeste;
import br.edu.ifpb.acomidadobebeservice.repository.GrupoAlimentarRepository;
import br.edu.ifpb.acomidadobebeservice.repository.IngredienteTesteRepository;

@RestController
public class IngredienteTesteController {

    @Autowired
    private IngredienteTesteRepository _ingredienteTesteRepository;
    @Autowired
    private GrupoAlimentarRepository _grupoAlimentarRepository;

    // Listar todos
    @RequestMapping(value = "/ingredienteTeste", method = RequestMethod.GET)
    public List<IngredienteTeste> get() {
        return _ingredienteTesteRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/ingredienteTeste/{id}", method = RequestMethod.GET)
    public ResponseEntity<IngredienteTeste> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<IngredienteTeste> ingredienteTeste = _ingredienteTesteRepository.findById(id);
        if(ingredienteTeste.isPresent())
            return new ResponseEntity<IngredienteTeste>(ingredienteTeste.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/ingredienteTeste", method =  RequestMethod.POST)
    public ResponseEntity<IngredienteTeste> post(@RequestBody IngredienteTeste ingredienteTeste)
    {
        Optional<GrupoAlimentar> grupoAlimentarOptional = _grupoAlimentarRepository.findById(ingredienteTeste.getGrupoAlimentar().getId());
        if(grupoAlimentarOptional.isPresent()){
            GrupoAlimentar grupoAlimentar = grupoAlimentarOptional.get();
            ingredienteTeste.setGrupoAlimentar(grupoAlimentar);
            ingredienteTeste.setNome(ingredienteTeste.getNome());
            _ingredienteTesteRepository.save(ingredienteTeste);
            return new ResponseEntity<IngredienteTeste>(ingredienteTeste, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar
    @RequestMapping(value = "/ingredienteTeste/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<IngredienteTeste> put(@PathVariable(value = "id") Integer id, @RequestBody IngredienteTeste newIngredienteTeste)
    {
        Optional<IngredienteTeste> oldIngredienteTeste = _ingredienteTesteRepository.findById(id);
        if(oldIngredienteTeste.isPresent()){
            IngredienteTeste ingredienteTeste = oldIngredienteTeste.get();
            ingredienteTeste.setNome(newIngredienteTeste.getNome());
            
            _ingredienteTesteRepository.save(ingredienteTeste);
            return new ResponseEntity<IngredienteTeste>(ingredienteTeste, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/ingredienteTeste/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<IngredienteTeste> ingredienteTeste = _ingredienteTesteRepository.findById(id);
        if(ingredienteTeste.isPresent()){
            _ingredienteTesteRepository.delete(ingredienteTeste.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
