package br.edu.ifpb.acomidadobebeservice.model;

import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ingrediente")
    private Integer id;
    @Column(name = "nome_ingrediente")
    private String nome;
    @Column(name = "grupo_ingrediente")
    private String grupo_ingrediente;
    @Column(name = "id_preparacao_ingrediente")
    private Integer fk_preparacao;
    @Column(name = "id_lista_compra_ingrediente")
    private Integer fk_lista_compra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_preparacao_ingrediente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Preparacao preparacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lista_compra_ingrediente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ListaDeCompra lista_compra;
}
