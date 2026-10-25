package com.example.spike_camadas.service;

import com.example.spike_camadas.model.Produto;
import com.example.spike_camadas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service: camada das regras de negócio
@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    // o Spring injeta o repository aqui sozinho
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // cadastrar um produto (com uma regra simples)
    public Produto cadastrar(Produto produto) {
        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser vazio nem negativo");
        }
        return repository.save(produto);
    }

    // listar todos os produtos
    public List<Produto> listar() {
        return repository.findAll();
    }

    // buscar um produto pelo id
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }
}