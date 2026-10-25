package com.example.spike_mvc.controller;

import com.example.spike_mvc.model.Tarefa;
import com.example.spike_mvc.service.TarefaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Recebe as chamadas e passa pro service
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private TarefaService service;

    // A Spring entrega o service pronto aqui
    public TarefaController(TarefaService service) {
        this.service = service;
    }

    // POST /tarefas cria uma tarefa
    @PostMapping
    public void criar(@RequestBody Tarefa tarefa) {
        service.criar(tarefa);
    }

    // GET /tarefas lista as tarefas
    @GetMapping
    public List<Tarefa> listar() {
        return service.listar();
    }
}