package br.com.gerenciadortarefas.controller;

import br.com.gerenciadortarefas.domain.Responsavel;
import br.com.gerenciadortarefas.service.ResponsavelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @PostMapping
    public ResponseEntity<Responsavel> criar(@RequestBody Responsavel responsavel) {
        return ResponseEntity.status(201).body(responsavelService.criar(responsavel));
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> listar() {
        return ResponseEntity.ok(responsavelService.listar());
    }
}
