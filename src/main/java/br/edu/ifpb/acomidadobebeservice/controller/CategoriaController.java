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

import br.edu.ifpb.acomidadobebeservice.model.Categoria;
import br.edu.ifpb.acomidadobebeservice.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository _categoriaRepository;

     // Listar todos
     @RequestMapping(value = "/categoria", method = RequestMethod.GET)
     public List<Categoria> get() {
         return _categoriaRepository.findAll();
     }

     // Listar pelo id
    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Categoria> categoria = _categoriaRepository.findById(id);
        if(categoria.isPresent())
            return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cadastrar
    @RequestMapping(value = "/categoria", method =  RequestMethod.POST)
    public Categoria post(@RequestBody Categoria categoria)
    {
        return _categoriaRepository.save(categoria);
    }

    // Atualizar
    @RequestMapping(value = "/categoria/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<CardapioController> put(@PathVariable(value = "id") Integer id, @RequestBody Categoria newCategoria)
    {
        Optional<Categoria> oldCategoria = _categoriaRepository.findById(id);
        if(oldCategoria.isPresent()){
            Categoria categoria = oldCategoria.get();
            categoria.setNome(newCategoria.getNome());
            _categoriaRepository.save(categoria);
            return new ResponseEntity<CardapioController>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar
    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Categoria> categoria = _categoriaRepository.findById(id);
        if(categoria.isPresent()){
            _categoriaRepository.delete(categoria.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
