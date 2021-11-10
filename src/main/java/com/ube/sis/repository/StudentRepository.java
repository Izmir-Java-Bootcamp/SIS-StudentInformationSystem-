package com.ube.sis.repository;

import com.ube.sis.entity.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByUserId(int user_id);

  @Query("select s from student s where s.counselor.id = ?1 and s.isGraduated = false")
  List<Student> findAllByCounselor(Integer userId);
}
