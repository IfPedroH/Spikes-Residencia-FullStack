package com.example.spike_camadas.repository;

import com.example.spike_camadas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository: camada que acessa o banco
// JpaRepository já traz save, findAll, findById, deleteById...
// <Produto, Long> = entidade e tipo do id
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}