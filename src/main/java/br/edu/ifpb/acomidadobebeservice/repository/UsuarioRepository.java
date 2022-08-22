package br.edu.ifpb.acomidadobebeservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.edu.ifpb.acomidadobebeservice.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //List<Endereco> findByUsuarioId(Integer id);
}
