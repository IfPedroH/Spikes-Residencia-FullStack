package com.example.spike_jpa.repository;

import com.example.spike_jpa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

// O JpaRepository já traz; salvar, buscar, listar e apagar, prontos.
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}