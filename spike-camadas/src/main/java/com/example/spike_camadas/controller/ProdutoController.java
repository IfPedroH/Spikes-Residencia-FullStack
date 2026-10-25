package com.example.spike_camadas.controller;

import com.example.spike_camadas.model.Produto;
import com.example.spike_camadas.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller: recebe as requisições HTTP e chama o service
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    // o Spring injeta o service aqui
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // POST /produtos -> cadastrar
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return service.cadastrar(produto);
    }

    // GET /produtos -> listar todos
    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    // GET /produtos/1 -> buscar por id
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}