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

import br.edu.ifpb.acomidadobebeservice.model.Ingrediente1;
import br.edu.ifpb.acomidadobebeservice.model.Grupo;
import br.edu.ifpb.acomidadobebeservice.repository.Ingrediente1Repository;
import br.edu.ifpb.acomidadobebeservice.repository.GrupoRepository;

@RestController
public class Ingrediente1Controller {
    @Autowired
    private Ingrediente1Repository _ingredienteRepository;
    @Autowired
    private GrupoRepository _grupoRepository;

    // Listar todos
    @RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
    public List<Ingrediente1> Get() {
        return _ingredienteRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ingrediente1> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente1> ingrediente = _ingredienteRepository.findById(id);
        if(ingrediente.isPresent())
            return new ResponseEntity<Ingrediente1>(ingrediente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/ingrediente", method =  RequestMethod.POST)
    public Ingrediente1 Post(@RequestBody Ingrediente1 ingrediente)
    {
        return _ingredienteRepository.save(ingrediente);
    }
    // Cadastrar grupo em ingrediente
    @RequestMapping(value = "/ingrediente/grupo/{idGrupo}/ingrediente/{idIngrediente}", method =  RequestMethod.POST)
	public ResponseEntity<Ingrediente1> postIngredienteGrupo(@PathVariable(value = "idGrupo") Integer idGrupo, @PathVariable(value = "idIngrediente") Integer idIngrediente)
    {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroIngredienteGrupo(idGrupo, idIngrediente));
	}
    // Atualizar
    @RequestMapping(value = "/ingrediente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Ingrediente1> Put(@PathVariable(value = "id") Integer id, @RequestBody Ingrediente1 newIngrediente)
    {
        Optional<Ingrediente1> oldIngrediente = _ingredienteRepository.findById(id);
        if(oldIngrediente.isPresent()){
            Ingrediente1 ingrediente = oldIngrediente.get();
            ingrediente.setNome(newIngrediente.getNome());
            ingrediente.setGrupos(newIngrediente.getGrupos());
            _ingredienteRepository.save(ingrediente);
            return new ResponseEntity<Ingrediente1>(ingrediente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Ingrediente1> ingrediente = _ingredienteRepository.findById(id);
        if(ingrediente.isPresent()){
            _ingredienteRepository.delete(ingrediente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Ingrediente1 cadastroIngredienteGrupo(Integer idGrupo, Integer idIngrediente) {
		Optional<Grupo> grupoExistente = _grupoRepository.findById(idGrupo);
		Optional<Ingrediente1> ingredienteExistente = _ingredienteRepository.findById(idIngrediente);
		if(grupoExistente.isPresent() && ingredienteExistente.isPresent()) {
			ingredienteExistente.get().getGrupos().add(grupoExistente.get());
			
			_ingredienteRepository.save(ingredienteExistente.get());
			
			return _ingredienteRepository.save(ingredienteExistente.get());
			
		}
		return null;
	}
}