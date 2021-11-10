package com.ube.sis.model.request;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private Set<String> role;
  private String plainPassword;
  private String nationalId;
  private LocalDate birthDate;
}