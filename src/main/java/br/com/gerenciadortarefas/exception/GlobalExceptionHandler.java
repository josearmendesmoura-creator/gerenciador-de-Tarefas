package br.com.gerenciadortarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> tratarRecursoNaoEncontrado(
            RecursoNaoEncontradoException ex) {

        return Map.of(
            "erro", ex.getMessage(),
            "momento", LocalDateTime.now()
        );
    }
}
