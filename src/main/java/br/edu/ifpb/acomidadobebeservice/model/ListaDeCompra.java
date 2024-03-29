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
@Table(name = "tb_listadecompra")
public class ListaDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_listadecompra")
    private Integer id;
    @Column(name = "nome_listadecompra")
    private String nome;
    
    @ManyToMany
    @JoinTable(
        name = "listadecompra_item",
        joinColumns = @JoinColumn(name = "id_listadecompra"),
        inverseJoinColumns = @JoinColumn(name = "id_item")
    )
    
    private List<Item> itens;
    
}
