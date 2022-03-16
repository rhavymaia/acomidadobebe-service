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
@Table(name = "tb_lista_compra")
public class ListaDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_lista_compra")
    private Integer id;
    @Column(name = "nome_lista_compra")
    private String nome;
    @Column(name = "ingrediente_lista_compra")
    // List<Ingrediente> ingredientes?
    private String ingrediente;
    @Column(name = "qtd_alimento_lista_compra") // 1kl, 200g...
    private String qtd_ingrediente;
    
<<<<<<< HEAD
}
=======
}
>>>>>>> 2eb9ff54741967073792f44a7b59cc20a1cd8c81
