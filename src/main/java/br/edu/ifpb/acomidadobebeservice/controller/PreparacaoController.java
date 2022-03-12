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

import br.edu.ifpb.acomidadobebeservice.model.Preparacao;
import br.edu.ifpb.acomidadobebeservice.repository.PreparacaoRepository;

@RestController
public class PreparacaoController {
    @Autowired
    private PreparacaoRepository _preparacaoRepository;
    // Listar todos
    @RequestMapping(value = "/preparacao", method = RequestMethod.GET)
    public List<Preparacao> Get() {
        return _preparacaoRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/preparacao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Preparacao> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Preparacao> preparacao = _preparacaoRepository.findById(id);
        if(preparacao.isPresent())
            return new ResponseEntity<Preparacao>(preparacao.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/preparacao", method =  RequestMethod.POST)
    public Preparacao Post(@RequestBody Preparacao preparacao)
    {
        return _preparacaoRepository.save(preparacao);
    }
    // Atualizar
    @RequestMapping(value = "/preparacao/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Preparacao> Put(@PathVariable(value = "id") Integer id, @RequestBody Preparacao newPreparacao)
    {
        Optional<Preparacao> oldPreparacao = _preparacaoRepository.findById(id);
        if(oldPreparacao.isPresent()){
            Preparacao preparacao = oldPreparacao.get();
            preparacao.setNome(newPreparacao.getNome());
            preparacao.setLink_receita(newPreparacao.getLink_receita());
            _preparacaoRepository.save(preparacao);
            return new ResponseEntity<Preparacao>(preparacao, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/preparacao/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Preparacao> preparacao = _preparacaoRepository.findById(id);
        if(preparacao.isPresent()){
            _preparacaoRepository.delete(preparacao.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}