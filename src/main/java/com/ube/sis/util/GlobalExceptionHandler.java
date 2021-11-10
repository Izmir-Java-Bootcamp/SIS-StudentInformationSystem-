package com.ube.sis.util;

import com.ube.sis.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  public ErrorResult handleAllExceptions(Exception e) {
    ErrorResult error = new ErrorResult(
        LocalDateTime.now(),
        "Internal Server Error",
        e.getClass().getSimpleName(),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
    log.error(e.getMessage(), e);
    return error;
  }
}
