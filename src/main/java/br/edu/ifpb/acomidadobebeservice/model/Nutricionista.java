package br.edu.ifpb.acomidadobebeservice.model;

<<<<<<< HEAD
public class Nutricionista extends Usuario{
    
    private Integer id; //TODO: Ajustar id composto com usuário.
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_nutricionista")
public class Nutricionista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_nutricionista")
    private Integer id;
    @Column(name = "crn_nutricionista")
    private String crn;
}
