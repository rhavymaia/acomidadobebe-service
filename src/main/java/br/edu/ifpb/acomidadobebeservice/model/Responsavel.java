package br.edu.ifpb.acomidadobebeservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_responsavel")
public class Responsavel {
    @Id
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "membros_responsavel")
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Set<Membros> membros = new HashSet<>();

    // @Column(name = "criancas_responsavel")
    // @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    // private Set<Crianca> criancas;

    @Column(name = "criancas_responsavel")
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Set<Crianca> crianca = new HashSet<>();

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
