package com.ube.sis.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ube.sis.entity.Instructor;
import com.ube.sis.entity.Lesson;
import com.ube.sis.entity.Student;
import com.ube.sis.entity.User;
import com.ube.sis.exception.NoSuchLessonException;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.ESemester;
import com.ube.sis.model.mapstruct.LessonMapper;
import com.ube.sis.model.request.LessonCreateRequest;
import com.ube.sis.repository.InstructorRepository;
import com.ube.sis.repository.LessonRepository;
import com.ube.sis.repository.StudentLessonRepository;
import com.ube.sis.repository.StudentRepository;
import com.ube.sis.service.AdminService;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminServiceUnitTests {
  LessonRepository lessonRepository;

  InstructorRepository instructorRepository;

  StudentLessonRepository studentLessonRepository;

  StudentRepository studentRepository;

  LessonMapper lessonMapper;

  AdminService adminService;


  @BeforeAll
  public void setup() {
    lessonRepository = mock(LessonRepository.class);
    instructorRepository = mock(InstructorRepository.class);
    studentLessonRepository = mock(StudentLessonRepository.class);
    studentRepository = mock(StudentRepository.class);
    lessonMapper = mock(LessonMapper.class);

    adminService = new AdminService(lessonRepository,
        instructorRepository,
        studentLessonRepository,
        studentRepository,
        lessonMapper);
  }

  @Test
  void shouldSaveLessonWithGivenParameters() {
    LessonCreateRequest lessonCreateRequest = new LessonCreateRequest("deneme", 1L, LocalDate.now(), "FALL");
    Lesson lesson = new Lesson(1L, "test", new Instructor(), LocalDate.now(), ESemester.FALL);
    when(lessonRepository.save(any())).thenReturn(lesson);
    when(lessonMapper.dtoToEntity(any())).thenReturn(lesson);
    adminService.createLesson(lessonCreateRequest);
    verify(lessonRepository, times(1)).save(any());
  }


  @Test
  void shouldUpdateSuccessfully() throws NoSuchLessonException {
    Lesson lesson = new Lesson(1L, "test", new Instructor(), LocalDate.now(), ESemester.FALL);
    when(lessonRepository.findById(any())).thenReturn(java.util.Optional.of(lesson));
    Lesson lessonUpdated = new Lesson(1L, "test", new Instructor(), LocalDate.now(), ESemester.SPRING);
    when(lessonRepository.save(lesson)).thenReturn(lessonUpdated);
    Lesson testLesson = adminService.updateLesson(lesson);
    assertEquals(ESemester.SPRING, testLesson.getSemester());
  }

  @Test
  void shouldAssignCounselorSuccessfully() throws NoSuchUserException {
    Instructor instructor = new Instructor(1L, new User(), 1000.0, new HashSet<>());
    Student student = new Student(1L, new User(), null, false, new HashSet<>());
    Student assignedStudent = new Student(1L, new User(), instructor.getUser(), false, new HashSet<>());
    when(instructorRepository.findById(any())).thenReturn(java.util.Optional.of(instructor));
    when(studentRepository.findById(any())).thenReturn(java.util.Optional.of(student));
    when(studentRepository.save(any())).thenReturn(assignedStudent);
    Student res = adminService.assignCounselor(1L, 1L);
    assertEquals(instructor.getUser(), res.getCounselor());
  }
}
