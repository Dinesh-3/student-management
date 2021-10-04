package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.dto.EnrollCourse;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.service.StudentService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseBody> getAllStudents(@RequestParam Map<String,String> queryParams) {
        return service.getAllStudents(queryParams);
    }

    @RequestMapping(params = {"student_id"})
    public ResponseEntity<ResponseBody> getStudentById(@RequestParam("student_id") long studentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("developer", "Dinesh I");
        return new ResponseEntity<>(service.getStudent(studentId), headers, HttpStatus.OK);
    }

    /**
     *      ^
     *      Two separate methods for handling different request params
     *      |
     */
    @RequestMapping(params = {"first_name", "last_name"})
    public ResponseEntity<ResponseBody> getStudentByName(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        return service.getStudentByName(firstName, lastName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getStudent(@PathVariable long id) {
        return new ResponseEntity<>(service.getStudent(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseBody> createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @PutMapping()
    public ResponseEntity<ResponseBody> updateStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable long id) {
        return service.deleteStudent(id);
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseBody> searchStudents(@RequestParam String query) {
        return service.findStudents(query);
    }

    @PostMapping("/enroll")
    public ResponseEntity<ResponseBody> enrollCourse(@NotNull @RequestBody EnrollCourse enroll) {
        return service.enrollCourse(enroll);
    }

}
