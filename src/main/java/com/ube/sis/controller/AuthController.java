package com.ube.sis.controller;

import com.ube.sis.configuration.jwt.JwtUtils;
import com.ube.sis.exception.UserExistsException;
import com.ube.sis.model.request.RegisterRequest;
import com.ube.sis.model.response.JwtResponse;
import com.ube.sis.model.request.LoginRequest;
import com.ube.sis.service.UserDetailsImpl;
import com.ube.sis.service.UserRegisterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;


  private final UserRegisterService userRegisterService;

  private final JwtUtils jwtUtils;

  public AuthController(AuthenticationManager authenticationManager, UserRegisterService userRegisterService, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.userRegisterService = userRegisterService;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/signin")
  @CrossOrigin
  public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/registerUser")
  @CrossOrigin
  public void registerUser(@RequestBody RegisterRequest registerRequest) throws UserExistsException {
    userRegisterService.registerUser(registerRequest);
  }
}