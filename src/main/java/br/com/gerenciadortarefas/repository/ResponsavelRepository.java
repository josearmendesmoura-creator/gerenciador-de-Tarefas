package br.com.gerenciadortarefas.repository;

import br.com.gerenciadortarefas.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
