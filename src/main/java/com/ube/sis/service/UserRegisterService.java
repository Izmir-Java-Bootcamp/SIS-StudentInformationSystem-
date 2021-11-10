package com.ube.sis.service;

import com.ube.sis.entity.Role;
import com.ube.sis.entity.User;
import com.ube.sis.exception.UserExistsException;
import com.ube.sis.model.ERole;
import com.ube.sis.model.mapstruct.UserRegisterMapper;
import com.ube.sis.model.request.RegisterRequest;
import com.ube.sis.repository.RoleRepository;
import com.ube.sis.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final UserRegisterMapper userRegisterMapper;


  public void registerUser(RegisterRequest registerRequest) throws UserExistsException {
    if (userRepository.existsByUsername(registerRequest.getUsername())) {
      throw new UserExistsException("this user exists");
    }

    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      throw new UserExistsException("this user exists");
    }
    final User user = userRegisterMapper.dtoToEntity(registerRequest);

    Set<String> strRoles = registerRequest.getRole();
    Set<Role> roles = new HashSet<>();


    strRoles.forEach(role -> {
      switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "instructor":
          Role modRole = roleRepository.findByName(ERole.ROLE_INSTRUCTOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      }
    });
    user.setRoles(roles);
    userRepository.save(user);
  }

}
