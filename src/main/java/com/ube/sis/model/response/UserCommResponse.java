package com.ube.sis.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCommResponse {
  private String firstName;
  private String lastName;
  private String email;
}
