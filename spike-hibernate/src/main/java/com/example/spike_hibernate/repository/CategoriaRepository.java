package com.example.spike_hibernate.repository;

import com.example.spike_hibernate.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}