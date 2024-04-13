package com.example.smartjob.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = UserAppException.class)
  public ResponseEntity<SmartJobExceptionResponse> generalException(UserAppException e) {
    SmartJobExceptionResponse smartJobExceptionResponse = new SmartJobExceptionResponse();
    smartJobExceptionResponse.setMessage(e.getErrors().get(0).getMessage());
    return new ResponseEntity<>(smartJobExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
