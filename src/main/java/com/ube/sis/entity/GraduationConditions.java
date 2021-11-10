package com.ube.sis.entity;

import com.ube.sis.model.EStudentType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "graduation_condition")
@Data
@NoArgsConstructor
public class GraduationConditions {

  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "student_type")
  private EStudentType name;

  @Column(name = "minimum_success_lesson_count")
  private int minimumSuccessLessonCount;

  @Column(name = "thesis_required")
  private boolean thesisRequired;

  @Column(name = "project_required")
  private boolean projectRequired;

}
