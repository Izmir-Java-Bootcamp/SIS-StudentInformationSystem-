package com.ube.sis.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentLessonRequest {
  private Long studentId;
  private Long lessonId;
}
