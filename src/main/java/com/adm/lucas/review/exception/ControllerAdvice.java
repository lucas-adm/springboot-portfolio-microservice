package com.adm.lucas.review.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    private record validationError(String field, String message) {
        public validationError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<validationError>> validateField(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.stream().map(validationError::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> validateNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

}