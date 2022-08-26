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

import br.edu.ifpb.acomidadobebeservice.model.Ingrediente;
import br.edu.ifpb.acomidadobebeservice.model.Item;
import br.edu.ifpb.acomidadobebeservice.repository.IngredienteRepository;
import br.edu.ifpb.acomidadobebeservice.repository.ItemRepository;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository _itemRepository;
    @Autowired
    private IngredienteRepository _ingredienteRepository;
    
    // Listar todos
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public List<Item> get() {
        return _itemRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Item> item = _itemRepository.findById(id);
        if(item.isPresent())
            return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/item", method =  RequestMethod.POST)
    public ResponseEntity<Item> post(@RequestBody Item item)
    {
        Optional<Ingrediente> ingredienteOptional = _ingredienteRepository.findById(item.getIngrediente().getId());
        if(ingredienteOptional.isPresent()){
            Ingrediente ingrediente = ingredienteOptional.get();
            item.setIngrediente(ingrediente);
            _itemRepository.save(item);
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    
    // Atualizar
    @RequestMapping(value = "/item/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Item> put(@PathVariable(value = "id") Integer id, @RequestBody Item newItem)
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
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
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