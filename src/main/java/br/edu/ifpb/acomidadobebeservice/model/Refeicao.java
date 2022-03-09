package br.edu.ifpb.acomidadobebeservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
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
@Table(name = "tb_refeicao")
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_refeicao")
    private Integer id;
    @Column(name = "nome_refeicao") // alomoco, jantar...
    private String nome;
    @Column(name = "id_cardapio_refeicao")
    private Integer fk_cardapio;

    // @ManyToOne
    // @JoinColumn(name="id_cardapio", nullable=false)
    // private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cardapio_refeicao")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cardapio cardapio;

    @Column(name = "preparacoes_refeicao")
    @OneToMany(mappedBy = "preparacao", cascade = CascadeType.ALL)
    private Set<Preparacao> preparacao = new HashSet<>();

}
