package com.rs.accesslayer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rs.accesslayer.model.ResponseModel;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseModel handleAccessDenied(AccessDeniedException ex) {
        return ResponseModel.failure(ex.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleInvalidInput(InvalidInputException ex) {
        return ResponseModel.failure(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel handleRuntimeException(RuntimeException ex) {
        return ResponseModel.failure("An error occurred: " + ex.getMessage());
    }
}
