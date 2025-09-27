package quantum_code.petsaber.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import quantum_code.petsaber.controller.exception.StandardError;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Erro de validação: " + e.getClass())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        

        return ResponseEntity.status(status).body(error);
    }
}
