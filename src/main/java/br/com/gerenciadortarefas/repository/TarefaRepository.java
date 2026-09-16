package br.com.gerenciadortarefas.repository;

import br.com.gerenciadortarefas.domain.Status;
import br.com.gerenciadortarefas.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(Status status);
    List<Tarefa> findByProjetoId(Long projetoId);
    List<Tarefa> findByStatusAndProjetoId(Status status, Long projetoId);
}
