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
import br.edu.ifpb.acomidadobebeservice.model.Responsavel;
import br.edu.ifpb.acomidadobebeservice.repository.MembroRepository;
import br.edu.ifpb.acomidadobebeservice.repository.ResponsavelRepository;

@RestController
public class MembroController {
    @Autowired
    private MembroRepository _membroRepository;

    @Autowired
    private ResponsavelRepository _responsavelRepository;

    // Listar todos
    @RequestMapping(value = "/membro", method = RequestMethod.GET)
    public List<Membro> Get() {
        return _membroRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/membro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membro> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Membro> membro = _membroRepository.findById(id);
        if(membro.isPresent())
            return new ResponseEntity<Membro>(membro.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/membro", method =  RequestMethod.POST)
    public ResponseEntity<Membro> Post(@RequestBody Membro membro)
    {
        Optional<Responsavel> responsavelOptional = _responsavelRepository.findById(membro.getResponsavel().getId());
        if(responsavelOptional.isPresent()){
            Responsavel responsavel = responsavelOptional.get();
            membro.setResponsavel(responsavel);
            _membroRepository.save(membro);
            return new ResponseEntity<Membro>(membro, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    }
    //    Responsavel responsavel = responsavelOptional.get();
    //    membro.setResponsavel(responsavel);
    //    _membrosRepository.save(membro);
    //    return new ResponseEntity<Membro>(membro, HttpStatus.OK);
    //}
    // Atualizar
    @RequestMapping(value = "/membro/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Membro> Put(@PathVariable(value = "id") Integer id, @RequestBody Membro newMembro)
    {
        Optional<Membro> oldMembro = _membroRepository.findById(id);
        if(oldMembro.isPresent()){
            Membro membro = oldMembro.get();
            membro.setNome(newMembro.getNome());
            membro.setParentesco(newMembro.getParentesco());
            membro.setNascimento(newMembro.getNascimento());
            _membroRepository.save(membro);
            return new ResponseEntity<Membro>(membro, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/membro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
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