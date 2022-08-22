package br.edu.ifpb.acomidadobebeservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_login_teste")
public class LoginTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_login")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // sha256 
    @Column(name = "token")
    private String token;

    // do momento em que foi criado
    @Column(name = "data_hora")
    private LocalDateTime data_hora;

    @Column(name = "isAtivo")
    private boolean isAtivo;

    /**
     * TODO 
     * 
     * ele vem apenas o email e senha pelo usuário OK  
     * tem que gerar o token com sha256 no controller OK
     * persistir no banco e retornar o objeto completo OK
     * criar endpoint login/token com retorno de usuario, ele pesquisa usuario pelo token / login/token/{token}
     */
    
}
