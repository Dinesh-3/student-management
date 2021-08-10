package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.service.StudentService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping()
    public ResponseEntity<ResponseBody> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getStudent(@PathVariable long id) {
        return service.getStudent(id);
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

}
