package com.ube.sis.entity;

import com.ube.sis.model.EStudentType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class LessonConditionKey implements Serializable {
  @Column(name = "lesson_id")
  private Long lessonId;


  @Enumerated(EnumType.STRING)
  @Column(name = "student_type")
  private EStudentType studentType;
}