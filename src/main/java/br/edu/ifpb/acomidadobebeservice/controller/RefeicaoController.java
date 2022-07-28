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

import br.edu.ifpb.acomidadobebeservice.model.Refeicao;
import br.edu.ifpb.acomidadobebeservice.model.Cardapio;
import br.edu.ifpb.acomidadobebeservice.repository.RefeicaoRepository;
import br.edu.ifpb.acomidadobebeservice.repository.CardapioRepository;

@RestController
public class RefeicaoController {
    @Autowired
    private RefeicaoRepository _refeicaoRepository;
    @Autowired
    private CardapioRepository _cardapioRepository;

    // Listar todos
    @RequestMapping(value = "/refeicao", method = RequestMethod.GET)
    public List<Refeicao> Get() {
        return _refeicaoRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/refeicao/{id}", method = RequestMethod.GET)
    public ResponseEntity<Refeicao> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Refeicao> refeicao = _refeicaoRepository.findById(id);
        if(refeicao.isPresent())
            return new ResponseEntity<Refeicao>(refeicao.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/refeicao", method =  RequestMethod.POST)
    public ResponseEntity<Refeicao> Post(@RequestBody Refeicao refeicao)
    {
        Optional<Cardapio> cardapioOptional = _cardapioRepository.findById(refeicao.getCardapio().getId());
        if(cardapioOptional.isPresent()){
            Cardapio cardapio = cardapioOptional.get();
            refeicao.setCardapio(cardapio);
            _refeicaoRepository.save(refeicao);
            return new ResponseEntity<Refeicao>(refeicao, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Atualizar
    @RequestMapping(value = "/refeicao/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Refeicao> Put(@PathVariable(value = "id") Integer id, @RequestBody Refeicao newRefeicao)
    {
        Optional<Refeicao> oldRefeicao = _refeicaoRepository.findById(id);
        if(oldRefeicao.isPresent()){
            Refeicao refeicao = oldRefeicao.get();
            refeicao.setCategoria(newRefeicao.getCategoria());
            _refeicaoRepository.save(refeicao);
            return new ResponseEntity<Refeicao>(refeicao, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/refeicao/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Refeicao> refeicao = _refeicaoRepository.findById(id);
        if(refeicao.isPresent()){
            _refeicaoRepository.delete(refeicao.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}