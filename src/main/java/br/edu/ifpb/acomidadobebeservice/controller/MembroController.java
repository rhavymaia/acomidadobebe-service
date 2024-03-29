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

import br.edu.ifpb.acomidadobebeservice.model.Membro;
import br.edu.ifpb.acomidadobebeservice.model.Parentesco;
import br.edu.ifpb.acomidadobebeservice.model.Responsavel;
import br.edu.ifpb.acomidadobebeservice.repository.MembroRepository;
import br.edu.ifpb.acomidadobebeservice.repository.ParentescoRepository;
import br.edu.ifpb.acomidadobebeservice.repository.ResponsavelRepository;

@RestController
public class MembroController {

    @Autowired
    private MembroRepository _membroRepository;
    @Autowired
    private ParentescoRepository _parentescoRepository;
    @Autowired
    private ResponsavelRepository _responsavelRepository;

    // Listar todos
    @RequestMapping(value = "/membro", method = RequestMethod.GET)
    public List<Membro> get() {
        return _membroRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/membro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membro> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Membro> membro = _membroRepository.findById(id);
        if(membro.isPresent())
            return new ResponseEntity<Membro>(membro.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/membro", method =  RequestMethod.POST)
    public ResponseEntity<Membro> post(@RequestBody Membro membro)
    {
        Optional<Responsavel> responsavelOptional = _responsavelRepository.findById(membro.getResponsavel().getId());
        Optional<Parentesco> parentescoOptional = _parentescoRepository.findById(membro.getParentesco().getId());
        if(responsavelOptional.isPresent() && parentescoOptional.isPresent()){
            Responsavel responsavel = responsavelOptional.get();
            Parentesco parentesco = parentescoOptional.get();
            membro.setResponsavel(responsavel);
            membro.setParentesco(parentesco);
            _membroRepository.save(membro);
            return new ResponseEntity<Membro>(membro, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    }
    
    // Atualizar
    @RequestMapping(value = "/membro/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Membro> put(@PathVariable(value = "id") Integer id, @RequestBody Membro newMembro)
    {
        Optional<Membro> oldMembro = _membroRepository.findById(id);
        if(oldMembro.isPresent()){
            Membro membro = oldMembro.get();
            membro.setNome(newMembro.getNome());
            membro.setNascimento(newMembro.getNascimento());
            _membroRepository.save(membro);
            return new ResponseEntity<Membro>(membro, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/membro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Membro> membro = _membroRepository.findById(id);
        if(membro.isPresent()){
            _membroRepository.delete(membro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}