package com.ube.sis.service;

import com.ube.sis.entity.Instructor;
import com.ube.sis.entity.Lesson;
import com.ube.sis.entity.LessonResearchAssistant;
import com.ube.sis.entity.Student;
import com.ube.sis.entity.StudentLesson;
import com.ube.sis.entity.StudentLessonKey;
import com.ube.sis.entity.User;
import com.ube.sis.exception.NoSuchLessonException;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.response.StudentLessonResponse;
import com.ube.sis.model.response.UserCommResponse;
import com.ube.sis.repository.InstructorRepository;
import com.ube.sis.repository.LessonRepository;
import com.ube.sis.repository.LessonResearchAssistantRepository;
import com.ube.sis.repository.StudentLessonRepository;
import com.ube.sis.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorService {

  private final UserDetailsServiceImpl userDetailsService;
  private final LessonRepository lessonRepository;
  private final InstructorRepository instructorRepository;
  private final LessonResearchAssistantRepository lessonResearchAssistantRepository;
  private final StudentRepository studentRepository;
  private final StudentLessonRepository studentLessonRepository;

  public List<Lesson> getInstructorLessons() {
    User user = userDetailsService.getCurrentUser();
    Instructor instructor = instructorRepository.getByUserId(user.getId());
    return lessonRepository.findAll()
        .stream().filter(it -> it.getInstructor().equals(instructor)).collect(Collectors.toList());
  }

  public UserCommResponse getResearchAssistantsOfLesson(Long lessonId) throws NoSuchLessonException {
    LessonResearchAssistant lessonResearchAssistant = lessonResearchAssistantRepository.findById(lessonId)
        .orElseThrow(() -> new NoSuchLessonException("No such element"));
    Instructor ra = instructorRepository.getById(lessonResearchAssistant.getResearchAssistant().getInstructorId());
    return new UserCommResponse(
        ra.getUser().getFirstName(),
        ra.getUser().getLastName(),
        ra.getUser().getEmail()
    );

  }

  public List<UserCommResponse> getCounseledStudents() {
    User user = userDetailsService.getCurrentUser();
    return studentRepository.findAllByCounselor(user.getId())
        .stream().map(it -> new UserCommResponse(
            it.getUser().getFirstName(),
            it.getUser().getLastName(),
            it.getUser().getEmail())
        ).collect(Collectors.toList());
  }

  public List<UserCommResponse> getLessonStudents(Long lessonId) {
    List<StudentLesson> studentLessons = studentLessonRepository.findAllByLessonId(lessonId);
    List<UserCommResponse> studentComms = new ArrayList<>();
    for (StudentLesson sl : studentLessons) {
      Optional<Student> s = studentRepository.findById(sl.getStudentLessonKey().getStudentId());
      s.ifPresent(student -> studentComms.add(new UserCommResponse(
          student.getUser().getFirstName(),
          student.getUser().getLastName(),
          student.getUser().getEmail()
      )));
    }
    return studentComms;
  }

  public StudentLessonResponse getLessonGradeForStudent(Long studentId, Long lessonId) throws NoSuchUserException, NoSuchLessonException {
    Optional<StudentLesson> x = studentLessonRepository.findById(new StudentLessonKey(studentId, lessonId));
    if (x.isPresent()) {
      Student s = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchUserException("No such student"));
      Lesson l = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchLessonException("No such lesson"));
      return new StudentLessonResponse(s.getStudentId(), s.getUser().getFirstName(), l.getLessonName(), x.get().getPoints());
    }
    throw new NoSuchUserException("This student does not attend to given lesson");
  }

  public StudentLessonResponse updateStudentGrade(Long studentId, Long lessonId, Double points) throws NoSuchUserException, NoSuchLessonException {
    Optional<StudentLesson> x = studentLessonRepository.findById(new StudentLessonKey(studentId, lessonId));
    if (x.isPresent()) {
      Student s = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchUserException("No such student"));
      Lesson l = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchLessonException("No such lesson"));
      x.get().setPoints(points);
      return new StudentLessonResponse(s.getStudentId(), s.getUser().getFirstName(), l.getLessonName(), x.get().getPoints());
    }
    throw new NoSuchUserException("This student does not attend to given lesson");
  }

}
