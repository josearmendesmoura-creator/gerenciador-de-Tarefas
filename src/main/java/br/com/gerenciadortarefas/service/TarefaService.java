package br.com.gerenciadortarefas.service;

import br.com.gerenciadortarefas.domain.*;
import br.com.gerenciadortarefas.exception.RecursoNaoEncontradoException;
import br.com.gerenciadortarefas.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final ResponsavelRepository responsavelRepository;

    public TarefaService(
            TarefaRepository tarefaRepository,
            ProjetoRepository projetoRepository,
            ResponsavelRepository responsavelRepository) {
        this.tarefaRepository = tarefaRepository;
        this.projetoRepository = projetoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    public Tarefa criar(Tarefa tarefa) {
        Long projetoId = tarefa.getProjeto().getId();

        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Projeto nao encontrado: " + projetoId));

        tarefa.setProjeto(projeto);

        if (tarefa.getResponsavel() != null &&
                tarefa.getResponsavel().getId() != null) {

            Long responsavelId = tarefa.getResponsavel().getId();

            Responsavel responsavel = responsavelRepository.findById(responsavelId)
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Responsavel nao encontrado: " + responsavelId));

            tarefa.setResponsavel(responsavel);
        }

        tarefa.setStatus(Status.NOVA);
        tarefa.setCriadaEm(LocalDateTime.now());
        tarefa.setConcluidaEm(null);

        if (tarefa.getPrioridade() == null) {
            tarefa.setPrioridade(Prioridade.MEDIA);
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listar(Status status, Long projetoId) {
        if (status != null && projetoId != null) {
            return tarefaRepository.findByStatusAndProjetoId(status, projetoId);
        }
        if (status != null) {
            return tarefaRepository.findByStatus(status);
        }
        if (projetoId != null) {
            return tarefaRepository.findByProjetoId(projetoId);
        }
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Tarefa nao encontrada: " + id));
    }

    public Tarefa atualizar(Long id, Tarefa dados) {
        Tarefa tarefa = buscarPorId(id);

        tarefa.setTitulo(dados.getTitulo());
        tarefa.setDescricao(dados.getDescricao());
        tarefa.setPrioridade(dados.getPrioridade());
        tarefa.setPrazo(dados.getPrazo());
        tarefa.setStatus(dados.getStatus());

        if (dados.getProjeto() != null && dados.getProjeto().getId() != null) {
            Long projetoId = dados.getProjeto().getId();

            Projeto projeto = projetoRepository.findById(projetoId)
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Projeto nao encontrado: " + projetoId));

            tarefa.setProjeto(projeto);
        }

        if (dados.getResponsavel() != null &&
                dados.getResponsavel().getId() != null) {

            Long responsavelId = dados.getResponsavel().getId();

            Responsavel responsavel = responsavelRepository.findById(responsavelId)
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Responsavel nao encontrado: " + responsavelId));

            tarefa.setResponsavel(responsavel);
        } else {
            tarefa.setResponsavel(null);
        }

        if (tarefa.getStatus() == Status.CONCLUIDA) {
            if (tarefa.getConcluidaEm() == null) {
                tarefa.setConcluidaEm(LocalDateTime.now());
            }
        } else {
            tarefa.setConcluidaEm(null);
        }

        return tarefaRepository.save(tarefa);
    }

    public void remover(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefaRepository.delete(tarefa);
    }
}
