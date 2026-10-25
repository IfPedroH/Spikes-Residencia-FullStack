package com.example.spike_hibernate.repository;

import com.example.spike_hibernate.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}