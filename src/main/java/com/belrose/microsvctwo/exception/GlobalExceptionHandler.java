package com.belrose.microsvctwo.exception;

import com.belrose.microsvctwo.model.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlerPersonNotFound(PersonNotFoundException ex){
    ErrorResponse errorResponse =ErrorResponse.builder()
        .code(HttpStatus.NOT_FOUND)
        .timestamp(LocalDateTime.now())
        .message(ex.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

}
