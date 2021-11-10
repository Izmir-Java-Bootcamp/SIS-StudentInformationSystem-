package com.ube.sis.repository;

import com.ube.sis.entity.LessonConditionKey;
import com.ube.sis.entity.LessonConditions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonConditionsRepository extends JpaRepository<LessonConditions, LessonConditionKey> {
}
