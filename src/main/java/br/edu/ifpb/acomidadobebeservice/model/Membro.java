package br.edu.ifpb.acomidadobebeservice.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import br.edu.ifpb.acomidadobebeservice.model.Responsavel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_membros")
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_membro")
    private Integer id;
    @Column(name = "nome_membro")
    private String nome;
    // parentesco com o responsavel da familia, crianca(filho(a)) pai...
    @Column(name = "parentesco_membro")
    private String parentesco;
    @Column(name = "nascimento_membro")
    private Date nascimento;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false) // não pode ser nulo 
    private Responsavel responsavel;

}
