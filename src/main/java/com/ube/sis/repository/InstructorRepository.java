package com.ube.sis.repository;

import com.ube.sis.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
  Instructor getByUserId(int id);
}
