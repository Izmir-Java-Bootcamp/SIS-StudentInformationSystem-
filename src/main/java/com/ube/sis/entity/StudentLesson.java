package com.ube.sis.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "student_lesson")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLesson {
  @EmbeddedId
  private StudentLessonKey studentLessonKey;

  @Column(name = "points")
  private Double points;
}
