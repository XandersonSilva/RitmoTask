package edu.xanderson.ritimoTask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    // Este método será chamado especificamente quando uma AccessDeniedException for lançada
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException e, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Acesso Negado");
        body.put("message", "Você não tem permissão para executar esta ação.");
        body.put("path", request.getDescription(false).replace("uri=", ""));

        // Retornamos um ResponseEntity com o corpo do erro e o status HTTP 403
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}