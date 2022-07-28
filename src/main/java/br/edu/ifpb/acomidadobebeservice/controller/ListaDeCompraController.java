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
import br.edu.ifpb.acomidadobebeservice.model.Ingrediente;
import br.edu.ifpb.acomidadobebeservice.repository.ListaDeCompraRepository;
import br.edu.ifpb.acomidadobebeservice.repository.IngredienteRepository;

@RestController
public class ListaDeCompraController {
    @Autowired
    private ListaDeCompraRepository _listaCompraRepository;
    @Autowired
    private IngredienteRepository _ingredienteRepository;

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
    /*
     * Cadastrar listaCompraIngrediente
	 * EXPLICACAO URI:
	 * 
	 * 	/listadecompraingrediente -> nome da tabela associativa
	 * 	/ingredientes -> nome da lista de ingredientes dentro da classe ListaDeCompra
	 * 	/listacompras -> nome da lista de compras dentro da classe Ingrediente
	 * 
	 * */
    @RequestMapping(value = "/listadecompraingrediente/ingredientes{idIngrediente}/listadecompras/{idListaCompra}", method =  RequestMethod.POST)
	public ResponseEntity<ListaDeCompra> postListaCompraIngrediente(@PathVariable(value = "idListaCompra") Integer idListaCompra, @PathVariable(value = "idIngrediente") Integer idIngrediente)
    {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroListaDeCompraIngrediente(idListaCompra, idIngrediente));
	}
    // Atualizar
    @RequestMapping(value = "/listadecompra/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<ListaDeCompra> Put(@PathVariable(value = "id") Integer id, @RequestBody ListaDeCompra newListaDeCompra)
    {
        Optional<ListaDeCompra> oldListaDeCompra = _listaCompraRepository.findById(id);
        if(oldListaDeCompra.isPresent()){
            ListaDeCompra lista_compra = oldListaDeCompra.get();
            lista_compra.setNome(newListaDeCompra.getNome());
            lista_compra.setIngredientes(newListaDeCompra.getIngredientes());
            lista_compra.setQtd_ingrediente(newListaDeCompra.getQtd_ingrediente());
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

    public ListaDeCompra cadastroListaDeCompraIngrediente(Integer idListaDeCompra, Integer idIngrediente) {
		Optional<ListaDeCompra> listaCompraExistente = _listaCompraRepository.findById(idListaDeCompra);
		Optional<Ingrediente> ingredienteExistente = _ingredienteRepository.findById(idIngrediente);
		if(ingredienteExistente.isPresent() && ingredienteExistente.isPresent()) {
			ingredienteExistente.get().getLista_de_compras().add(listaCompraExistente.get());
			
			_listaCompraRepository.save(listaCompraExistente.get());
			
			return _listaCompraRepository.save(listaCompraExistente.get());
			
		}
		return null;
	}
}