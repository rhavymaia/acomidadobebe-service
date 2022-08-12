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

import br.edu.ifpb.acomidadobebeservice.model.Item;
import br.edu.ifpb.acomidadobebeservice.repository.ItemRepository;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository _itemRepository;
    
    // Listar todos
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public List<Item> Get() {
        return _itemRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Item> item = _itemRepository.findById(id);
        if(item.isPresent())
            return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/item", method =  RequestMethod.POST)
    public Item Post(@RequestBody Item item)
    {
        return _itemRepository.save(item);
    }
    
    // Atualizar
    @RequestMapping(value = "/item/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Item> Put(@PathVariable(value = "id") Integer id, @RequestBody Item newItem)
    {
        Optional<Item> oldItem = _itemRepository.findById(id);
        if(oldItem.isPresent()){
            Item item = oldItem.get();
            item.setIngrediente(newItem.getIngrediente());
            item.setQtd_ingrediente(newItem.getQtd_ingrediente());
            _itemRepository.save(item);
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Item> item = _itemRepository.findById(id);
        if(item.isPresent()){
            _itemRepository.delete(item.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}