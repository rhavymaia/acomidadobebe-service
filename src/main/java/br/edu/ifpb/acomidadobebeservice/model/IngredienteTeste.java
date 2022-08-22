package br.edu.ifpb.acomidadobebeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ingrediente_teste")
public class IngredienteTeste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ingrediente")
    private Integer id;
    @Column(name = "nome_ingrediente")
    private String nome;
    
    @OneToOne
    @JoinColumn(name = "id_grupo_alimentar")
    private GrupoAlimentar grupoAlimentar;

}
