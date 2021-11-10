package com.ube.sis.model.mapstruct;

import com.ube.sis.entity.User;
import com.ube.sis.model.request.RegisterRequest;
import com.ube.sis.util.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserRegisterMapper {
  @Mapping(source = "plainPassword", target = "password", qualifiedBy = EncodedMapping.class)
  User dtoToEntity(final RegisterRequest registerRequest);
}

