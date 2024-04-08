package com.example.TP_ISI2.banque;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        // Gestion des exceptions de validation
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(errors);
        }

        // Gestion des exceptions de type incorrect des arguments de méthode
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<Object> handleTypeMismatchExceptions(MethodArgumentTypeMismatchException ex) {
            String message = "Type incorrect pour l'argument: " + ex.getName();
            return ResponseEntity.badRequest().body(message);
        }

        // Gestion de toutes les autres exceptions
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
            String errorMessage = "Une erreur s'est produite: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

}
