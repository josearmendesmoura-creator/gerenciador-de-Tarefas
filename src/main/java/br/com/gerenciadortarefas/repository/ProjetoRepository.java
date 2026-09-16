package br.com.gerenciadortarefas.repository;

import br.com.gerenciadortarefas.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
