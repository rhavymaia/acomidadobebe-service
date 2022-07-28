package br.edu.ifpb.acomidadobebeservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_lista_compra")
public class ListaDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_lista_compra")
    private Integer id;
    @Column(name = "nome_lista_compra")
    private String nome;
    
    @ManyToMany
    @JoinTable(
        name = "listacompra_grupo",
        joinColumns = @JoinColumn(name = "id_lista_compra"),
        inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private List<Ingrediente> ingredientes;

    @Column(name = "qtd_alimento_lista_compra") // 1kl, 200g...
    private String qtd_ingrediente;
    
}
