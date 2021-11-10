package com.ube.sis.model.mapstruct;

import com.ube.sis.entity.Lesson;
import com.ube.sis.model.request.LessonCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {InstructorResolver.class})
public interface LessonMapper {
  Lesson dtoToEntity(final LessonCreateRequest lessonCreateRequest);
  LessonCreateRequest entityToDto(final Lesson lesson);
}
