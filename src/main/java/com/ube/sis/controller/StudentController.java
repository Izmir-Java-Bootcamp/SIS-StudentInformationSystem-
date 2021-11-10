package com.ube.sis.controller;

import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.response.StudentLessonRecords;
import com.ube.sis.model.response.UserCommResponse;
import com.ube.sis.service.StudentService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('STUDENT')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

  private final StudentService service;

  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/getstudentgrades")
  public List<StudentLessonRecords> getGrades() throws NoSuchUserException {
    return service.getLessonsWithGrading();
  }

  @GetMapping("/getcounselor")
  public UserCommResponse getCounselor() throws NoSuchUserException {
    return service.getCounselor();
  }
}
