package br.edu.ifpb.acomidadobebeservice.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_crianca")
public class Crianca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_crianca")
    private Integer id;

    private String nome;
    private Date nascimento;

    //private Integer id_responsavel;


    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "id_responsavel")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //private Responsavel responsavel;


}
