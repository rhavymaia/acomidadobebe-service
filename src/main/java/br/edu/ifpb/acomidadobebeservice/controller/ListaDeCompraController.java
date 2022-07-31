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

import br.edu.ifpb.acomidadobebeservice.model.ListaDeCompra;
import br.edu.ifpb.acomidadobebeservice.model.Item;
import br.edu.ifpb.acomidadobebeservice.repository.ListaDeCompraRepository;
import br.edu.ifpb.acomidadobebeservice.repository.ItemRepository;

@RestController
public class ListaDeCompraController {
    @Autowired
    private ListaDeCompraRepository _listaCompraRepository;
    @Autowired
    private ItemRepository _itemRepository;

    // Listar todos
    @RequestMapping(value = "/listadecompra", method = RequestMethod.GET)
    public List<ListaDeCompra> Get() {
        return _listaCompraRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/listadecompra/{id}", method = RequestMethod.GET)
    public ResponseEntity<ListaDeCompra> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<ListaDeCompra> lista_compra = _listaCompraRepository.findById(id);
        if(lista_compra.isPresent())
            return new ResponseEntity<ListaDeCompra>(lista_compra.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/listadecompra", method =  RequestMethod.POST)
    public ListaDeCompra Post(@RequestBody ListaDeCompra lista_compra)
    {
        return _listaCompraRepository.save(lista_compra);
    }
    // Cadastrar item em lista de compra
    @RequestMapping(value = "/listadecompra/item/{idItem}/listadecompra/{idListaDeCompra}", method =  RequestMethod.POST)
	public ResponseEntity<ListaDeCompra> postListaDeCompraItem(@PathVariable(value = "idListaDeCompra") Integer idListaDeCompra, @PathVariable(value = "idItem") Integer idItem)
    {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroListaDeCompraItem(idItem, idListaDeCompra));
	}
    // Atualizar
    @RequestMapping(value = "/listadecompra/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<ListaDeCompra> Put(@PathVariable(value = "id") Integer id, @RequestBody ListaDeCompra newListaDeCompra)
    {
        Optional<ListaDeCompra> oldListaDeCompra = _listaCompraRepository.findById(id);
        if(oldListaDeCompra.isPresent()){
            ListaDeCompra lista_compra = oldListaDeCompra.get();
            lista_compra.setNome(newListaDeCompra.getNome());
            _listaCompraRepository.save(lista_compra);
            return new ResponseEntity<ListaDeCompra>(lista_compra, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/listadecompra/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<ListaDeCompra> lista_compra = _listaCompraRepository.findById(id);
        if(lista_compra.isPresent()){
            _listaCompraRepository.delete(lista_compra.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ListaDeCompra cadastroListaDeCompraItem(Integer idItem, Integer idListaDeCompra) {
		Optional<Item> itemExistente = _itemRepository.findById(idItem);
		Optional<ListaDeCompra> listaCompraExistente = _listaCompraRepository.findById(idListaDeCompra);
		if(itemExistente.isPresent() && listaCompraExistente.isPresent()) {
			listaCompraExistente.get().getItens().add(itemExistente.get());
			
			_listaCompraRepository.save(listaCompraExistente.get());
			
			return _listaCompraRepository.save(listaCompraExistente.get());
			
		}
		return null;
	}
}