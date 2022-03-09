package br.edu.ifpb.acomidadobebeservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_preparacao")
public class Preparacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_preparacao")
    private Integer id;
    @Column(name = "nome_preparacao")
    private String nome;
    @Column(name = "fk_alimento_preparacao")
    private Integer fk_alimento;
    @Column(name = "link_receita_preparacao")
    private String link_receita;
    @Column(name = "id_cardapio")
    private Integer fk_cardapio;
    @Column(name = "id_refeicao")
    private Integer fk_refeicao;

    // @ManyToOne
    // @JoinColumn(name="id_cardapio", nullable=false)
    // private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cardapio")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_refeicao")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Refeicao refeicao;

    @Column(name = "ingredientes_preparacao")
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private Set<Ingrediente> ingrediente = new HashSet<>();
}
