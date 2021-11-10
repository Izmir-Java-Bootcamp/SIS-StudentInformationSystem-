package com.ube.sis.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentLessonResponse {
  private Long studentId;
  private String studentName;
  private String lessonName;
  private Double points;
}
