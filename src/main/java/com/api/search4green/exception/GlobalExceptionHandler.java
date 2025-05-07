package com.api.search4green.exception;

import java.lang.reflect.Executable;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException exception) {
    Map<String, String> errors = exception.getBindingResult().getFieldErrors()
        .stream()
        .collect(Collectors.toMap(
            fe -> fe.getField(),
            fe -> fe.getDefaultMessage(),
            (msg1, msg2) -> msg1));
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> handleNotFound(EntityNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleOther(Exception exception) {
    return ResponseEntity.status(500).body("Erro interno do servidor: " + exception.getMessage());
  }
}
