package com.ube.sis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity(name = "lesson_research_assistant")
@Data
public class LessonResearchAssistant {
  @Id
  @Column(name = "lesson_id")
  private Long lessonId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="research_assistant")
  private Instructor researchAssistant;
}
