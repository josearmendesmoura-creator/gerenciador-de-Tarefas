package br.com.gerenciadortarefas.controller;

import br.com.gerenciadortarefas.domain.Status;
import br.com.gerenciadortarefas.domain.Tarefa;
import br.com.gerenciadortarefas.service.TarefaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa criada = tarefaService.criar(tarefa);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criada.getId())
                .toUri();

        return ResponseEntity.created(location).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long projeto) {
        return ResponseEntity.ok(tarefaService.listar(status, projeto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(
            @PathVariable Long id,
            @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.atualizar(id, tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tarefaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
