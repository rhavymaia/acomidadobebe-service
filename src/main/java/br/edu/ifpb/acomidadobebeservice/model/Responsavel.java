package br.edu.ifpb.acomidadobebeservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="idUsuario")
@Table(name="tb_responsavel")
public class Responsavel extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_responsavel")
    private Integer id;

}
