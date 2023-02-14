package com.cgr.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({MethodNotAllowedException.class})
    public ResponseEntity<String> methodArgumentNotValidException(MethodNotAllowedException methodNotAllowedException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cuerpo no es valido, existe un error: " + methodNotAllowedException.getMessage());
    }

    @ExceptionHandler({BadRequestCustom.class})
    public ResponseEntity<String> methodBadRequestCustomException( BadRequestCustom badRequestCustomMessage){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestCustomMessage.getMessage());
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<String> methodBadRequestCustomException( ConflictException conflictExceptionMessage){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictExceptionMessage.getMessage());
    }
}
