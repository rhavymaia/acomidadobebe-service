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

import br.edu.ifpb.acomidadobebeservice.model.Crianca;
import br.edu.ifpb.acomidadobebeservice.repository.CriancaRepository;

@RestController
public class CriancaController {
    @Autowired
    private CriancaRepository _criancaRepository;

    @RequestMapping(value = "/crianca", method = RequestMethod.GET)
    public List<Crianca> Get(){
        return _criancaRepository.findAll();
    }

    @RequestMapping(value = "/crianca/{id}", method = RequestMethod.GET)
    public ResponseEntity<Crianca> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Crianca> crianca = _criancaRepository.findById(id);
        if(crianca.isPresent())
            return new ResponseEntity<Crianca>(crianca.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/crianca", method =  RequestMethod.POST)
    public Crianca Post(@RequestBody Crianca crianca)
    {
        return _criancaRepository.save(crianca);
    }

    @RequestMapping(value = "/crianca/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Crianca> Put(@PathVariable(value = "id") Integer id, @RequestBody Crianca newCrianca)
    {
        Optional<Crianca> oldCrianca = _criancaRepository.findById(id);
        if(oldCrianca.isPresent()){
            Crianca crianca = oldCrianca.get();
            crianca.setNome(newCrianca.getNome());
            crianca.setNascimento(newCrianca.getNascimento());
            _criancaRepository.save(crianca);
            return new ResponseEntity<Crianca>(crianca, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/crianca/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Crianca> crianca = _criancaRepository.findById(id);
        if(crianca.isPresent()){
            _criancaRepository.delete(crianca.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
