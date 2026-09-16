package br.com.gerenciadortarefas.service;

import br.com.gerenciadortarefas.domain.Responsavel;
import br.com.gerenciadortarefas.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public Responsavel criar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> listar() {
        return responsavelRepository.findAll();
    }
}
