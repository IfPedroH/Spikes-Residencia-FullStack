package com.example.spike_rest.controller;

import com.example.spike_rest.model.Tarefa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Api rest de tarefas, os dados ficam numa lista na memória
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Long proximoId = 1L;

    // GET /tarefas lista todas
    @GetMapping
    public List<Tarefa> listar() {
        return tarefas;
    }

    // GET /tarefas/1 busca uma
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        Tarefa tarefa = procurar(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(tarefa); // 200
    }

    // POST /tarefas cria uma
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }
        tarefa.setId(proximoId);
        proximoId++;
        tarefas.add(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa); // 201
    }

    // PUT /tarefas/1 atualiza uma
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa dados) {
        Tarefa tarefa = procurar(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        if (dados.getTitulo() == null || dados.getTitulo().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setDescricao(dados.getDescricao());
        tarefa.setConcluida(dados.isConcluida());
        return ResponseEntity.ok(tarefa); // 200
    }

    // DELETE /tarefas/1 apaga uma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        Tarefa tarefa = procurar(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        tarefas.remove(tarefa);
        return ResponseEntity.noContent().build(); // 204
    }

    // Procura uma tarefa pelo id, devolve null se não achar
    private Tarefa procurar(Long id) {
        for (Tarefa t : tarefas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}