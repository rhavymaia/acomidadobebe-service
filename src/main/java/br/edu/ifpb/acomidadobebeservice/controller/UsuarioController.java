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
import br.edu.ifpb.acomidadobebeservice.model.Usuario;
import br.edu.ifpb.acomidadobebeservice.repository.EnderecoRepository;
import br.edu.ifpb.acomidadobebeservice.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository _usuarioRepository;
    @Autowired
    private EnderecoRepository _enderecoRepository;
    
    // Listar todos
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public List<Usuario> get() {
        return _usuarioRepository.findAll();
    }

    // Listar pelo id
    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Integer id)
    {
        Optional<Usuario> usuario = _usuarioRepository.findById(id);
        if(usuario.isPresent())
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Listar endereços pelo id do usuario
    @RequestMapping(value = "/usuario/{idUsuario}/enderecos", method = RequestMethod.GET)
    public List<Endereco> getByIdUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
        return _enderecoRepository.findByUsuarioId(idUsuario);
    }    

    // Cadastrar
    @RequestMapping(value = "/usuario", method =  RequestMethod.POST)
    public Usuario post(@RequestBody Usuario usuario)
    {
        return _usuarioRepository.save(usuario);
    }

    // Atualizar
    @RequestMapping(value = "/usuario/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Usuario> put(@PathVariable(value = "id") Integer id, @RequestBody Usuario newUsuario)
    {
        Optional<Usuario> oldUsuario = _usuarioRepository.findById(id);
        if(oldUsuario.isPresent()){
            Usuario usuario = oldUsuario.get();
            usuario.setNome(newUsuario.getNome());
            usuario.setSobrenome(newUsuario.getSobrenome());
            usuario.setEmail(newUsuario.getEmail());
            usuario.setSenha(newUsuario.getSenha());
            usuario.setNascimento(newUsuario.getNascimento());
            _usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Deletar
    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Usuario> usuario = _usuarioRepository.findById(id);
        if(usuario.isPresent()){
            _usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}