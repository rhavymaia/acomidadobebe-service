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
import br.edu.ifpb.acomidadobebeservice.repository.GrupoAlimentarRepository;

@RestController
public class GrupoAlimentarController {

    @Autowired
    private GrupoAlimentarRepository _grupoAlimentarRepository;

    // Listar todos
    @RequestMapping(value = "/grupoAlimentar", method = RequestMethod.GET)
    public List<GrupoAlimentar> get() {
        return _grupoAlimentarRepository.findAll();
    }
    
    // Listar pelo id
    @RequestMapping(value = "/grupoAlimentar/{id}", method = RequestMethod.GET)
    public ResponseEntity<GrupoAlimentar> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<GrupoAlimentar> grupoAlimentar = _grupoAlimentarRepository.findById(id);
        if(grupoAlimentar.isPresent())
            return new ResponseEntity<GrupoAlimentar>(grupoAlimentar.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/grupoAlimentar", method =  RequestMethod.POST)
    public GrupoAlimentar post(@RequestBody GrupoAlimentar grupoAlimentar)
    {
        return _grupoAlimentarRepository.save(grupoAlimentar);
    }

    // Atualizar
    @RequestMapping(value = "/grupoAlimentar/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<GrupoAlimentar> put(@PathVariable(value = "id") Integer id, @RequestBody GrupoAlimentar newGrupoAlimentar)
    {
        Optional<GrupoAlimentar> oldGrupoAlimentar = _grupoAlimentarRepository.findById(id);
        if(oldGrupoAlimentar.isPresent()){
            GrupoAlimentar grupoAlimentar = oldGrupoAlimentar.get();
            grupoAlimentar.setNome(newGrupoAlimentar.getNome());
            _grupoAlimentarRepository.save(grupoAlimentar);
            return new ResponseEntity<GrupoAlimentar>(grupoAlimentar, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/grupoAlimentar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<GrupoAlimentar> grupoAlimentar = _grupoAlimentarRepository.findById(id);
        if(grupoAlimentar.isPresent()){
            _grupoAlimentarRepository.delete(grupoAlimentar.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
