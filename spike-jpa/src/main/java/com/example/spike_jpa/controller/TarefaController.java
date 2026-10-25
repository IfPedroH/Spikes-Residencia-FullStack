package com.example.spike_jpa.controller;

import com.example.spike_jpa.model.Tarefa;
import com.example.spike_jpa.repository.TarefaRepository;
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

import java.util.List;
import java.util.Optional;

// A Api de tarefas, agora os dados ficam no banco postgres
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private TarefaRepository repository;

    // O Spring entrega o repository pronto aqui
    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    // GET /tarefas lista todas
    @GetMapping
    public List<Tarefa> listar() {
        return repository.findAll();
    }

    // GET /tarefas/1 busca uma
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        Optional<Tarefa> tarefa = repository.findById(id);
        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(tarefa.get()); // 200
    }

    // POST /tarefas cria uma
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        tarefa.setId(null); // garante que é uma tarefa nova
        Tarefa salva = repository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva); // 201
    }

    // PUT /tarefas/1 atualiza uma
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa dados) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        dados.setId(id);
        Tarefa atualizada = repository.save(dados);
        return ResponseEntity.ok(atualizada); // 200
    }

    // DELETE /tarefas/1 apaga uma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}