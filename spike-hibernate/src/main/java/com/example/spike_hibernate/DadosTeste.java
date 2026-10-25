package com.example.spike_hibernate;

import com.example.spike_hibernate.model.Categoria;
import com.example.spike_hibernate.model.Tarefa;
import com.example.spike_hibernate.repository.CategoriaRepository;
import com.example.spike_hibernate.repository.TarefaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// roda sozinha quando a aplicacao sobe
@Component
public class DadosTeste implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final TarefaRepository tarefaRepository;

    public DadosTeste(CategoriaRepository categoriaRepository, TarefaRepository tarefaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public void run(String... args) {
        // se ja tem dados, nao salva de novo
        if (categoriaRepository.count() == 0) {
            Categoria estudos = new Categoria("Estudos");
            Tarefa t1 = new Tarefa("Estudar Hibernate", "Ler sobre ORM", false, estudos);
            Tarefa t2 = new Tarefa("Fazer a spike", "Criar as entidades", true, estudos);
            estudos.getTarefas().add(t1);
            estudos.getTarefas().add(t2);

            Categoria casa = new Categoria("Casa");
            Tarefa t3 = new Tarefa("Lavar a louca", "Depois do almoco", false, casa);
            casa.getTarefas().add(t3);

            // o cascade salva as tarefas junto com a categoria
            categoriaRepository.save(estudos);
            categoriaRepository.save(casa);
        }

        // le do banco para mostrar que foi salvo
        for (Tarefa t : tarefaRepository.findAll()) {
            System.out.println("Tarefa: " + t.getTitulo()
                    + " | Categoria: " + t.getCategoria().getNome());
        }
    }
}