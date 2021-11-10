package com.ube.sis.util;

import com.ube.sis.model.mapstruct.EncodedMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

  private final PasswordEncoder passwordEncoder;

  @EncodedMapping
  public String encode(String value) {
    return passwordEncoder.encode(value);
  }
}