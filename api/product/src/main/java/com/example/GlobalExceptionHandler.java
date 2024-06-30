package com.example;


import com.example.api.exception.NotFoundException;
import com.example.api.exception.ResponseError;
import com.example.api.exception.BadRequestionException;
import com.example.api.exception.MethodNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


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

}