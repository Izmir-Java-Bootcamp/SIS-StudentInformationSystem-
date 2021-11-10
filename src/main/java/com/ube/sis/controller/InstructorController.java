package com.ube.sis.controller;

import com.ube.sis.entity.Lesson;
import com.ube.sis.exception.NoSuchLessonException;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.request.SLGrade;
import com.ube.sis.model.request.StudentLessonRequest;
import com.ube.sis.model.response.StudentLessonResponse;
import com.ube.sis.model.response.UserCommResponse;
import com.ube.sis.service.InstructorService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

  private final InstructorService instructorService;


  public InstructorController(InstructorService instructorService) {
    this.instructorService = instructorService;
  }

  @GetMapping("/instructor_lessons")
  @CrossOrigin
  public List<Lesson> getInstructorLessons(){
    return instructorService.getInstructorLessons();
  }

  @GetMapping("/lesson_assistant/{id}")
  @CrossOrigin
  public UserCommResponse getLessonAssistant(@PathVariable Long lessonId) throws NoSuchLessonException {
    return instructorService.getResearchAssistantsOfLesson(lessonId);
  }

  @GetMapping("/counseled_students")
  @CrossOrigin
  public List<UserCommResponse> getCounseledStudents(){
    return instructorService.getCounseledStudents();
  }

  @GetMapping("/lesson_students")
  @CrossOrigin
  public List<UserCommResponse> getLessonStudents(@PathVariable Long lessonId) {
    return instructorService.getLessonStudents(lessonId);
  }

  @GetMapping("/student_grade")
  @CrossOrigin
  public StudentLessonResponse getGradeForStudent(@RequestBody StudentLessonRequest req) throws NoSuchLessonException, NoSuchUserException {
    return instructorService.getLessonGradeForStudent(req.getStudentId(), req.getLessonId());
  }

  @PutMapping("/student_grade")
  @CrossOrigin
  public StudentLessonResponse updateGrade(SLGrade slGrade) throws NoSuchLessonException, NoSuchUserException {
    return instructorService.updateStudentGrade(slGrade.getStudentId(), slGrade.getLessonId(), slGrade.getGrade());
  }




}
