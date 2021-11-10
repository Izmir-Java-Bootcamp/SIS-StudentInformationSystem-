package com.ube.sis.controller;

import com.ube.sis.entity.Lesson;
import com.ube.sis.exception.NoSuchLessonException;
import com.ube.sis.exception.NoSuchUserException;
import com.ube.sis.model.StudentInstructorReq;
import com.ube.sis.model.request.LessonCreateRequest;
import com.ube.sis.model.request.StudentLessonRequest;
import com.ube.sis.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("ADMIN")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping("/createlesson")
  @CrossOrigin
  public void createLesson(@RequestBody LessonCreateRequest lessonCreateRequest) throws NoSuchUserException {
    adminService.createLesson(lessonCreateRequest);
  }

  @PostMapping("/registerstudent")
  @CrossOrigin
  public void registerStudentLesson(@RequestBody StudentLessonRequest studentLessonRequest){
    adminService.addStudentToLesson(studentLessonRequest.getStudentId(), studentLessonRequest.getLessonId());
  }

  @DeleteMapping("/deletelesson/{lessonId}")
  @CrossOrigin
  public void deleteLesson(@PathVariable Long lessonId){
    adminService.deleteLesson(lessonId);
  }

  @PutMapping("/updatelesson")
  @CrossOrigin
  public void updateLesson(@RequestBody Lesson req) throws NoSuchLessonException {
    adminService.updateLesson(req);
  }

  @PostMapping("/assignCounselor")
  @CrossOrigin
  public void assignCounselor(@RequestBody StudentInstructorReq studentInstructorReq) throws NoSuchUserException {
    adminService.assignCounselor(studentInstructorReq.getStudentId(), studentInstructorReq.getInstructorId());

  }

}
