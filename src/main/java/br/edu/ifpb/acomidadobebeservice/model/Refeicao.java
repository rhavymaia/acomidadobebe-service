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
@Table(name = "tb_refeicao")
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_refeicao")
    private Integer id;
<<<<<<< HEAD
    @Column(name = "nome_refeicao") // almoco, jantar...
    private String nome;

}
=======
    @Column(name = "nome_refeicao") // alomoco, jantar...
    private String nome;

}
>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81
