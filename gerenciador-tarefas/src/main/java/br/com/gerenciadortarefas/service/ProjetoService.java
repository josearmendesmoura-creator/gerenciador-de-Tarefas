package br.com.gerenciadortarefas.service;

import br.com.gerenciadortarefas.domain.Projeto;
import br.com.gerenciadortarefas.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }
}
