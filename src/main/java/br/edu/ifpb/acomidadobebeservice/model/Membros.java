package br.edu.ifpb.acomidadobebeservice.model;

//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_membros")
public class Membros {
    @Id
    @Column(name = "id_responsavel")
    private Integer id;
    @Column(name = "nome_membro")
    private String nome;
    // parentesco do membro da familia com a crianca
    @Column(name = "parentesco_membro")
    private String parentesco;

    // @ManyToOne
    // @JoinColumn(name="id_responsavel", nullable=false)
    // private Responsavel responsavel;

    @ManyToOne //(fetch = FetchType.LAZY, optional = false, reclama pedindo -> insert = false, update = false)
    @JoinColumn(name = "id_responsavel")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Responsavel responsavel;
}
