package com.ube.sis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResult {
  private LocalDateTime dateTime;
  private String message;
  private String exceptionType;
  private HttpStatus status;
}
