package com.ube.sis.entity;

import com.ube.sis.model.EInstructorType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_type")
public class InstructorType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_name")
  private EInstructorType name;

  public InstructorType() {

  }

  public InstructorType(EInstructorType name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EInstructorType getName() {
    return name;
  }

  public void setName(EInstructorType name) {
    this.name = name;
  }
}
