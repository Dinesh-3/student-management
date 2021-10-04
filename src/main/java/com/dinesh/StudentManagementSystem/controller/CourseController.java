package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.service.CourseService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    ResponseEntity<ResponseBody> getCourses(@RequestParam(name = "search",required = false, defaultValue = "") String search) {
        return service.getAllCourses(search);
    }

    @GetMapping("/{courseId}")
    ResponseEntity<ResponseBody> getCourseById(@NotNull @PathVariable("courseId") Long id) {
        return service.getCourseById(id);
    }

    @PostMapping
    ResponseEntity<ResponseBody> createCourse(@NotNull @RequestBody Course course) {
        return service.createCourse(course);
    }

    @PutMapping
    ResponseEntity<ResponseBody> updateCourse(@NotNull @RequestBody Course course) {
        return service.createCourse(course);
    }

    @DeleteMapping("/{courseId}")
    ResponseEntity<ResponseBody> deleteCourse(@NotNull @PathVariable("courseId") Long id) {
        return service.deleteCourse(id);
    }
}
