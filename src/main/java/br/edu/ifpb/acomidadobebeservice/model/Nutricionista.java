package br.edu.ifpb.acomidadobebeservice.model;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_nutricionista") // criar id para nutricionista e um referente ao usuario
public class Nutricionista{
    @Id
    @Column(name = "id_usuario")
    private Integer id;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
