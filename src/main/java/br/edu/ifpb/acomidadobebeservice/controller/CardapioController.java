package br.edu.ifpb.acomidadobebeservice.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.acomidadobebeservice.model.Cardapio;
import br.edu.ifpb.acomidadobebeservice.model.Refeicao;

import br.edu.ifpb.acomidadobebeservice.repository.CardapioRepository;
import br.edu.ifpb.acomidadobebeservice.repository.RefeicaoRepository;

@RestController
public class CardapioController {
    @Autowired
    private CardapioRepository _cardapioRepository;
    @Autowired
    private RefeicaoRepository _refeicaoRepository;

    // Listar todos
    @RequestMapping(value = "/cardapio", method = RequestMethod.GET)
    public List<Cardapio> Get() {
        return _cardapioRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/cardapio/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cardapio> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Cardapio> cardapio = _cardapioRepository.findById(id);
        if(cardapio.isPresent())
            return new ResponseEntity<Cardapio>(cardapio.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }   

    // Listar refeicoes pelo id do cardapio
    @RequestMapping(value = "/cardapio/{idCardapio}/refeicoes", method = RequestMethod.GET)
    public List<Refeicao> GetByIdCardapio(@PathVariable(value = "idCardapio") Integer idCardapio){
        Query query = _refeicaoRepository.createQuery("select * from Refeicao as r where r.cardapio.id_cardapio = idCardapio");
        List<Refeicao> findByIdCardapio(idCardapio);
        return null;
    }

    // Cadastrar
    @RequestMapping(value = "/cardapio", method =  RequestMethod.POST)
    public Cardapio Post(@RequestBody Cardapio cardapio)
    {
        return _cardapioRepository.save(cardapio);
    }
    // Atualizar
    @RequestMapping(value = "/cardapio/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cardapio> Put(@PathVariable(value = "id") Integer id, @RequestBody Cardapio newCardapio)
    {
        Optional<Cardapio> oldCardapio = _cardapioRepository.findById(id);
        if(oldCardapio.isPresent()){
            Cardapio cardapio = oldCardapio.get();
            cardapio.setPeriodo(newCardapio.getPeriodo());
            cardapio.setData_inicio(newCardapio.getData_inicio());
            cardapio.setData_fim(newCardapio.getData_fim());
            _cardapioRepository.save(cardapio);
            return new ResponseEntity<Cardapio>(cardapio, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/cardapio/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Cardapio> cardapio = _cardapioRepository.findById(id);
        if(cardapio.isPresent()){
            _cardapioRepository.delete(cardapio.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
