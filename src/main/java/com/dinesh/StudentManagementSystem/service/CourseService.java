package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service // Not work we need to specify in implementation
public interface CourseService {
    ResponseEntity<ResponseBody> getAllCourses(String search);

    ResponseEntity<ResponseBody> getCourseById(Long id);

    ResponseEntity<ResponseBody> createCourse(Course course);

    ResponseEntity<ResponseBody> deleteCourse(Long id);
}
