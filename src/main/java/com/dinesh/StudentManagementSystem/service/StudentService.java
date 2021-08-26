package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.dto.EnrollCourse;
import com.dinesh.StudentManagementSystem.exception.ResourceNotFoundException;
import com.dinesh.StudentManagementSystem.exception.StudentNotFoundException;
import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.model.Enrollment;
import com.dinesh.StudentManagementSystem.model.EnrollmentId;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.repository.CourseRepository;
import com.dinesh.StudentManagementSystem.repository.EnrollmentRepository;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Stream;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    public ResponseEntity<ResponseBody> getAllStudents(Map<String, String> queryParams) {
        Iterable<Student> students;
        if(queryParams.containsKey("page") && queryParams.containsKey("limit")) {
            Map<String, Integer> pageQuery = getPageQuery(queryParams);
            students = repository.paginate(pageQuery.get("offset"), pageQuery.get("limit"));
        }
        else students = repository.findAll();

        ResponseBody response = new ResponseBody(students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getStudent(long id) {
        Optional<Student> student = repository.findById(id);
        if(student.isEmpty()) throw new StudentNotFoundException();
        ResponseBody response = new ResponseBody(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> createStudent(Student student) {
        Student save = repository.save(student);
        ResponseBody response = new ResponseBody(save);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    public ResponseEntity<ResponseBody> updateStudent(Student student) {
//        Student save = repository.save(student);
//        ResponseBody response = new ResponseBody(save);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    public ResponseEntity<ResponseBody> deleteStudent(long id) {
        repository.deleteById(id);
        ResponseBody response = new ResponseBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> findStudents(String query) {
        List<Student> students = repository.findByContaining(query);
        ResponseBody response = new ResponseBody(students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Integer> getPageQuery(Map<String, String> query) {
        Map<String, Integer> hashMap = new HashMap<>();
        try {
            int limit = Integer.parseInt(query.get("limit"));
            int page = Integer.parseInt(query.get("page"));
            hashMap.put("limit", limit);
            hashMap.put("page", page);
            hashMap.put("offset", (page - 1) * limit);
        }catch (Exception e) {
            throw new IllegalArgumentException("Page and Limit must be an integer");
        }
        return hashMap;
    }

    public ResponseEntity<ResponseBody> enrollCourse(EnrollCourse enrollCourse) {
        Student student = repository.findById(enrollCourse.getStudent_id())
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found !"));
        Course course = courseRepository.findById(enrollCourse.getCourse_id())
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found !"));
//        student.addCourse(course);
//        student.addEnrollment(new Enrollment(student, course));
        Enrollment enrollment = new Enrollment(student, course);
        enrollmentRepository.save(enrollment);
        return new ResponseEntity<>(new ResponseBody(true, "Student Enrolled Successfully"), HttpStatus.OK);
    }
}
