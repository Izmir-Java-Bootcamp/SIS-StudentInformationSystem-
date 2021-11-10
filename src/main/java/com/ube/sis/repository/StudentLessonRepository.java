package com.ube.sis.repository;

import com.ube.sis.entity.StudentLesson;
import com.ube.sis.entity.StudentLessonKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, StudentLessonKey> {
  @Query("select s from student_lesson s where s.studentLessonKey.studentId = ?1")
  List<StudentLesson> findAllByStudentId(Long studentId);

  @Query("select s from student_lesson s where s.studentLessonKey.lessonId = ?1")
  List<StudentLesson> findAllByLessonId(Long lessonId);

  @Query("delete from student_lesson s where s.studentLessonKey.lessonId = ?1")
  void deleteByLessonId(Long lessonId);
}