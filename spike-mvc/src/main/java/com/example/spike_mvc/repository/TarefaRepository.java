package com.example.spike_mvc.repository;

import com.example.spike_mvc.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Guarda as tarefas numa lista, sem banco de dados.
@Repository
public class TarefaRepository {

    private List<Tarefa> tarefas = new ArrayList<>();

    // Coloca uma tarefa na lista
    public void salvar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    // Devolve todas as tarefas
    public List<Tarefa> listar() {
        return tarefas;
    }
}