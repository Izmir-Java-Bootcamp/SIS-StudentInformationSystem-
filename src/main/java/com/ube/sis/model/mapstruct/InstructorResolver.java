package com.ube.sis.model.mapstruct;

import com.ube.sis.entity.Instructor;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.request.LessonCreateRequest;
import com.ube.sis.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructorResolver {

  private final InstructorRepository instructorRepository;

  @ObjectFactory
  public Instructor resolve(LessonCreateRequest dto, @TargetType Class<Instructor> type) throws NoSuchUserException {
    return dto != null && dto.getInstructorId() != null ? instructorRepository.findById(dto.getInstructorId()).orElseThrow(() -> new NoSuchUserException("No such instructor")) : new Instructor();
  }

}