package br.edu.ifpb.acomidadobebeservice.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membro")
    private Integer id;
    @Column(name = "nome_membro")
    private String nome;
    @Column(name = "sobrenome_membro")
    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "id_parentesco")
    private Parentesco parentesco;
    
    @Column(name = "nascimento_membro")
    private Date nascimento;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false) 
    private Responsavel responsavel;

}
