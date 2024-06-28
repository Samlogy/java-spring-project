package com.example.api.handler;


import com.example.api.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFound(
            NotFoundException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseError.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestionException.class)
    public ResponseEntity<ResponseError> handleBadRequest(
            BadRequestionException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseError.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ResponseError> handleMethodeNotAllowed(
            MethodNotAllowedException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ResponseError.builder()
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        ApiError apiError = new ApiError().builder().status(400).message("Validation failed !").build();

        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiError.addDetail("validationErrors", validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}