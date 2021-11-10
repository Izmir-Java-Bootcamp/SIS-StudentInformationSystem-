package com.ube.sis.model.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonCreateRequest {
  private String lessonName;
  private Long instructorId;
  private LocalDate year;
  private String semester;
}
