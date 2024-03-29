package br.edu.ifpb.acomidadobebeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;

    @Column(name = "logradouro_endereco")
    private String logradouro;

    @Column(name = "numero_endereco")
    private Integer numero;

    @Column(name = "bairro_endereco")
    private String bairro;
    
    @Column(name = "complemento_endereco")
    private String complemento;

    @Column(name = "cep_endereco")
    private String cep;

    @Column(name = "cidade_endereco")
    private String cidade;

    @Column(name = "estado_endereco")
    private String estado;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable=false)
    private Usuario usuario;
}