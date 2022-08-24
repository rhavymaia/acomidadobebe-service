package br.edu.ifpb.acomidadobebeservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cardapio")
    private Integer id;
    @Column(name = "periodo_cardapio")
    private String periodo; // semanal, quizenal, mensal.
    @Column(name = "data_inicio_cardapio")
    private Date data_inicio;
    @Column(name = "data_fim_cardapio")
    private Date data_fim;

}
