package com.ube.sis.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentLessonRecords {
  private Long lessonId;
  private String lessonName;
  private String instructorName;
  private int year;
  private String semester;
  private Double points;
}