package br.edu.ifpb.acomidadobebeservice.model;

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
@Table(name = "tb_preparacao")
public class Preparacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_preparacao")
    private Integer id;
    @Column(name = "nome_preparacao")
    private String nome;
    @Column(name = "link_receita_preparacao")
    private String link_receita;

}