package br.edu.ifpb.acomidadobebeservice.model;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

<<<<<<< HEAD

=======
>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name = "tb_responsavel")
public class Responsavel {
    @Id
    @Column(name = "id_usuario")
    private Integer id;

    //@OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    //private Set<Crianca> crianca = new HashSet<>();

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    
    
=======
@Table(name="tb_responsavel")
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_responsavel")
    private Integer id;
>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81
}
