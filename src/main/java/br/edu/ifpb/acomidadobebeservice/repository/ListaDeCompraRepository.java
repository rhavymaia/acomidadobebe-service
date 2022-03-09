package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.acomidadobebeservice.model.ListaDeCompra;

@Repository
public interface ListaDeCompraRepository extends JpaRepository<ListaDeCompra, Integer> {
    
}