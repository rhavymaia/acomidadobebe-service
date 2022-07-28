package br.edu.ifpb.acomidadobebeservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

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
    
    @ManyToMany
    @JoinTable(
        name = "ingrediente_grupo",
        joinColumns = @JoinColumn(name = "id_ingrediente"),
        inverseJoinColumns = @JoinColumn(name = "id_grupo")
    )
    private List<Grupo> grupos;

    @ManyToMany(mappedBy = "ingredientes")
    private List<ListaDeCompra> lista_de_compras;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Preparacao> preparacoes;

}
