package com.ube.sis.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLessonKey implements Serializable {
  @Column(name="student_id")
  private Long studentId;

  @Column(name = "lesson_id")
  private Long lessonId;
}
