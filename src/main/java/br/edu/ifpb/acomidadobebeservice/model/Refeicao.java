package br.edu.ifpb.acomidadobebeservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_refeicao")
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_refeicao")
    private Integer id;
    @Column(name = "categoria_refeicao") // cafe da manha, lanche, alomoco, jantar.
    private String categoria;

    @ManyToOne
    @JoinColumn(name="id_cardapio", nullable=false)
    private Cardapio cardapio;

    @ManyToMany
    @JoinTable(
        name = "refeicao_preparacao",
        joinColumns = @JoinColumn(name = "id_refeicao"),
        inverseJoinColumns = @JoinColumn(name = "id_preparacao")
    )
    private List<Preparacao> preparacoes;
}
