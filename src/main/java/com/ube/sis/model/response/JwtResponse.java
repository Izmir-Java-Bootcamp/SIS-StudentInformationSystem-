package com.ube.sis.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
  private String token;
  private final String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private List<String> roles;
}
