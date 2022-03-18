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
    private MembroRepository _membrosRepository;

    @Autowired
    private ResponsavelRepository _responsavelRepository;

    // Listar todos
    @RequestMapping(value = "/membros", method = RequestMethod.GET)
    public List<Membro> Get() {
        return _membrosRepository.findAll();
    }
    // Listar pelo id
    @RequestMapping(value = "/membros/{id}", method = RequestMethod.GET)
    public ResponseEntity<Membro> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Membro> membros = _membrosRepository.findById(id);
        if(membros.isPresent())
            return new ResponseEntity<Membro>(membros.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Cadastrar
    @RequestMapping(value = "/membros", method =  RequestMethod.POST)
    public ResponseEntity<Membro> Post(@RequestBody Membro membro)
    {
        Optional<Responsavel> responsavelOptional = _responsavelRepository.findById(membro.getResponsavel().getId());
        // tratar com if
        Responsavel responsavel = responsavelOptional.get();
        membro.setResponsavel(responsavel);
        _membrosRepository.save(membro);
        return new ResponseEntity<Membro>(membro, HttpStatus.OK);
    }
    // Atualizar
    @RequestMapping(value = "/membros/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Membro> Put(@PathVariable(value = "id") Integer id, @RequestBody Membro newMembros)
    {
        Optional<Membro> oldMembros = _membrosRepository.findById(id);
        if(oldMembros.isPresent()){
            Membro membros = oldMembros.get();
            membros.setNome(newMembros.getNome());
            membros.setParentesco(newMembros.getParentesco());
            membros.setNascimento(newMembros.getNascimento());
            _membrosRepository.save(membros);
            return new ResponseEntity<Membro>(membros, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Deletar
    @RequestMapping(value = "/membros/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Membro> membros = _membrosRepository.findById(id);
        if(membros.isPresent()){
            _membrosRepository.delete(membros.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}