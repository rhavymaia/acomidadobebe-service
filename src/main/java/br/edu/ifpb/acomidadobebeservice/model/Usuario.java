package br.edu.ifpb.acomidadobebeservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
    @Column(name = "id")
    private Integer id;

    private String nome;
    private String sobrenome;
    private String email; // colocar como único 
    private String senha;
    private Date nascimento;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Nutricionista nutricionista;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Responsavel responsavel;
    
=======
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "nome_usuario")
    private String nome;
    @Column(name = "sobrenome_usuario")
    private String sobrenome;
    @Column(name = "email_usuario")
    private String email;
    @Column(name = "senha_usuario")
    private String senha;
    @Column(name = "nascimento_usuario")
    private Date nascimento;

>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81
}
