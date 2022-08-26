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

import br.edu.ifpb.acomidadobebeservice.model.Nutricionista;
import br.edu.ifpb.acomidadobebeservice.repository.NutricionistaRepository;

@RestController
public class NutricionistaController {
    @Autowired
    private NutricionistaRepository _nutricionistaRepository;
    // Listar todos
    @RequestMapping(value = "/nutricionista", method = RequestMethod.GET)
    public List<Nutricionista> get() {
        return _nutricionistaRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/nutricionista/{id}", method = RequestMethod.GET)
    public ResponseEntity<Nutricionista> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Nutricionista> nutricionista = _nutricionistaRepository.findById(id);
        if(nutricionista.isPresent())
            return new ResponseEntity<Nutricionista>(nutricionista.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/nutricionista", method =  RequestMethod.POST)
    public Nutricionista post(@RequestBody Nutricionista nutricionista)
    {
        return _nutricionistaRepository.save(nutricionista);
    }
    // Atualizar
    @RequestMapping(value = "/nutricionista/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Nutricionista> put(@PathVariable(value = "id") Integer id, @RequestBody Nutricionista newNutricionista)
    {
        Optional<Nutricionista> oldNutricionista = _nutricionistaRepository.findById(id);
        if(oldNutricionista.isPresent()){
            Nutricionista nutricionista = oldNutricionista.get();
            nutricionista.setNome(newNutricionista.getNome());
            nutricionista.setSobrenome(newNutricionista.getSobrenome());
            nutricionista.setEmail(newNutricionista.getEmail());
            nutricionista.setSenha(newNutricionista.getSenha());
            nutricionista.setNascimento(newNutricionista.getNascimento());
            nutricionista.setCrn(newNutricionista.getCrn());
            _nutricionistaRepository.save(nutricionista);
            return new ResponseEntity<Nutricionista>(nutricionista, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/nutricionista/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Nutricionista> nutricionista = _nutricionistaRepository.findById(id);
        if(nutricionista.isPresent()){
            _nutricionistaRepository.delete(nutricionista.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}