package com.ube.sis.service;

import com.ube.sis.entity.Lesson;
import com.ube.sis.entity.Student;
import com.ube.sis.entity.StudentLesson;
import com.ube.sis.entity.User;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.response.StudentLessonRecords;
import com.ube.sis.model.response.UserCommResponse;
import com.ube.sis.repository.LessonRepository;
import com.ube.sis.repository.StudentLessonRepository;
import com.ube.sis.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final UserDetailsServiceImpl userDetailsService;

  private final StudentRepository studentRepository;

  private final StudentLessonRepository studentLessonRepository;

  private final LessonRepository lessonRepository;

  public List<StudentLessonRecords> getLessonsWithGrading() throws NoSuchUserException {
    User currUser = userDetailsService.getCurrentUser();
    Student currStudent = studentRepository.findByUserId(currUser.getId()).orElseThrow(() -> new NoSuchUserException("No such user"));
    List<StudentLesson> allLessonsOfStudent = studentLessonRepository.findAllByStudentId(currStudent.getStudentId());
    List<StudentLessonRecords> studentLessonRecords = new ArrayList<>();
    for (StudentLesson sl : allLessonsOfStudent) {
      Optional<Lesson> l = lessonRepository.findById(sl.getStudentLessonKey().getLessonId());
      if (l.isPresent()) {
        Lesson lesson = l.get();
        studentLessonRecords.add(
            new StudentLessonRecords(
                lesson.getLessonId(),
                lesson.getLessonName(),
                lesson.getInstructor().getUser().getFirstName() + ' ' + lesson.getInstructor().getUser().getLastName(),
                lesson.getYear().getYear(),
                lesson.getSemester().name(),
                sl.getPoints()
            )
        );
      }
    }
    return studentLessonRecords;
  }

  public UserCommResponse getCounselor() throws NoSuchUserException {
    User currUser = userDetailsService.getCurrentUser();
    Student currStudent = studentRepository.findByUserId(currUser.getId()).orElseThrow(() -> new NoSuchUserException("No such user"));
    return new UserCommResponse(
        currStudent.getCounselor().getFirstName(),
        currStudent.getCounselor().getLastName(),
        currStudent.getCounselor().getEmail()
    );
  }
}
