package com.ube.sis.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "lesson_conditions")
public class LessonConditions {
  @EmbeddedId
  private LessonConditionKey lessonConditionKey;

  @Column(name = "minimum_point")
  private Double minimumPoint;

}
