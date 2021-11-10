package com.ube.sis.repository;

import com.ube.sis.entity.GraduationConditions;
import com.ube.sis.model.EStudentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationConditionsRepository extends JpaRepository<GraduationConditions, EStudentType> {
}
