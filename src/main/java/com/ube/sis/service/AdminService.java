package com.ube.sis.service;

import com.ube.sis.entity.Instructor;
import com.ube.sis.entity.Lesson;
import com.ube.sis.entity.Student;
import com.ube.sis.entity.StudentLesson;
import com.ube.sis.entity.StudentLessonKey;
import com.ube.sis.exception.NoSuchLessonException;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.mapstruct.LessonMapper;
import com.ube.sis.model.request.LessonCreateRequest;
import com.ube.sis.repository.InstructorRepository;
import com.ube.sis.repository.LessonRepository;
import com.ube.sis.repository.StudentLessonRepository;
import com.ube.sis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  AdminService {

  private final LessonRepository lessonRepository;
  private final InstructorRepository instructorRepository;
  private final StudentLessonRepository studentLessonRepository;
  private final StudentRepository studentRepository;
  private final LessonMapper lessonMapper;

  public void createLesson(LessonCreateRequest lessonCreateRequest) {
    final Lesson lesson = lessonMapper.dtoToEntity(lessonCreateRequest);
    lessonRepository.save(lesson);
  }

  public void addStudentToLesson(Long studentId, Long lessonId) {
    studentLessonRepository.save(new StudentLesson(new StudentLessonKey(studentId, lessonId), 0.0));
  }

  public void deleteLesson(Long lessonId) {
    studentLessonRepository.deleteByLessonId(lessonId);
  }

  public Lesson updateLesson(Lesson req) throws NoSuchLessonException {
    Lesson lesson = lessonRepository.findById(req.getLessonId()).orElseThrow(() -> new NoSuchLessonException("no such lesson"));
    lesson.setLessonName(req.getLessonName());
    lesson.setInstructor(req.getInstructor());
    lesson.setSemester(req.getSemester());
    lesson.setYear(req.getYear());
    return lessonRepository.save(lesson);
  }

  public Student assignCounselor(Long studentId, Long instructorId) throws NoSuchUserException {
    Instructor inst = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchUserException("No such instructor"));
    Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchUserException("No such user"));
    student.setCounselor(inst.getUser());
    return studentRepository.save(student);
  }
}
