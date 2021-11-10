package com.ube.sis.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "instructor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
  @Id
  @Column(name = "instructor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long instructorId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;

  @Column(name = "monthly_salary")
  private Double monthlySalary;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "instructor_types", joinColumns = @JoinColumn(name = "instructor_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
  private Set<InstructorType> types = new HashSet<>();
}
