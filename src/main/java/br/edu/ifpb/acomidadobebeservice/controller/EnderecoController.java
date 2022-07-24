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

import br.edu.ifpb.acomidadobebeservice.model.Endereco;
import br.edu.ifpb.acomidadobebeservice.repository.EnderecoRepository;

@RestController
public class EnderecoController {
    @Autowired
    private EnderecoRepository _enderecoRepository;
    // Listar todos
    @RequestMapping(value = "/endereco", method = RequestMethod.GET)
    public List<Endereco> Get() {
        return _enderecoRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/endereco/{id}", method = RequestMethod.GET)
    public ResponseEntity<Endereco> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Endereco> endereco = _enderecoRepository.findById(id);
        if(endereco.isPresent())
            return new ResponseEntity<Endereco>(endereco.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/endereco", method =  RequestMethod.POST)
    public Endereco Post(@RequestBody Endereco endereco)
    {
        return _enderecoRepository.save(endereco);
    }
    // Atualizar
    @RequestMapping(value = "/endereco/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Endereco> Put(@PathVariable(value = "id") Integer id, @RequestBody Endereco newEndereco)
    {
        Optional<Endereco> oldEndereco = _enderecoRepository.findById(id);
        if(oldEndereco.isPresent()){
            Endereco endereco = oldEndereco.get();
            endereco.setLogradouro(newEndereco.getLogradouro());
            endereco.setNumero(newEndereco.getNumero());
            endereco.setBairro(newEndereco.getBairro());
            endereco.setCep(newEndereco.getCep());
            endereco.setCidade(newEndereco.getCidade());
            endereco.setEstado(newEndereco.getEstado());
            _enderecoRepository.save(endereco);
            return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/endereco/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Endereco> endereco = _enderecoRepository.findById(id);
        if(endereco.isPresent()){
            _enderecoRepository.delete(endereco.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
