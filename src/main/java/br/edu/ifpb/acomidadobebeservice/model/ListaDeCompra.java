package br.edu.ifpb.acomidadobebeservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;

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

    @Column(name = "ingredientes_lista_compra")
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private Set<Ingrediente> ingrediente = new HashSet<>();

    @Column(name = "qtd_alimento_lista_compra") // 1kl, 200g...
    private String qtd_ingrediente;
    
}
