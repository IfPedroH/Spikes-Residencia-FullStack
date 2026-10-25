package com.example.spike_mvc.service;

import com.example.spike_mvc.model.Tarefa;
import com.example.spike_mvc.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Aqui ficam as regras das tarefas
@Service
public class TarefaService {

    private TarefaRepository repository;

    // A Spring entrega o repository pronto aqui
    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public void criar(Tarefa tarefa) {
        // Regra: a tarefa precisa ter título
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new IllegalArgumentException("A tarefa precisa ter um título");
        }
        // Toda tarefa nova começa como não concluída
        tarefa.setConcluida(false);
        repository.salvar(tarefa);
    }

    public List<Tarefa> listar() {
        return repository.listar();
    }
}