package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.exception.UserNotFoundException;
import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.model.Instructor;
import com.dinesh.StudentManagementSystem.repository.CourseRepository;
import com.dinesh.StudentManagementSystem.repository.InstructorRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private InstructorRepository instructorRepository;

    public ResponseEntity<ResponseBody> getAllCourses(String search) {

        Iterable<Course> courses = repository.findByNameContaining(search);
//        if(search != null && search.length() > 0) courses = repository.searchByName(search);
//        else courses = repository.findAll();

        ResponseBody response = new ResponseBody(courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getCourseById(Long id) {
        Optional<Course> course = repository.findById(id);
        ResponseBody response = new ResponseBody(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> createCourse(Course course) {
        Long instructorId = course.getInstructor_id();

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(
                        () -> new UserNotFoundException("Instructor Not Found")
                );
        course.setInstructor(instructor);
        Course savedCourse = repository.save(course);
        ResponseBody response = new ResponseBody(savedCourse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> deleteCourse(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new ResponseBody(), HttpStatus.OK);
    }
}
