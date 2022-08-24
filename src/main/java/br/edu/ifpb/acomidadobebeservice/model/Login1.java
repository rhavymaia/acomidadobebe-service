package br.edu.ifpb.acomidadobebeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_login")
public class Login1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "token")
    private String token;
    
    @Column(name = "id_usuario")
    private Usuario usuario;

    // ele vem apenas o email e senha v

    //

    // tem que gerar o token com sha256 no controller v

    // persistir no banco e retornar o objeto completo

    // criar endpoint login/token com retorno de usuario, ele pesquisa usuario pelo token / login/token/{token}
}
