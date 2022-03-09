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

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cardapio")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cardapio")
    private Integer id;
    @Column(name = "data_inicio_cardapio")
    private Date data_inicio;
    @Column(name = "data_fim_cardapio")
    private Date data_fim;

    // @Column(name = "refeicoes_cardapio")
    // @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL)
    // private Set<Refeicao> refeicoes;

    @Column(name = "refeicoes_cardapio")
    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL)
    private Set<Refeicao> refeicao = new HashSet<>();

    @Column(name = "preparacoes_cardapio")
    @OneToMany(mappedBy = "preparacao", cascade = CascadeType.ALL)
    private Set<Preparacao> preparacao = new HashSet<>();

}
