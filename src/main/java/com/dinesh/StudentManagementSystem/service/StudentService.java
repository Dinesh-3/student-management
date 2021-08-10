package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.exception.StudentNotFoundException;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public ResponseEntity<ResponseBody> getAllStudents() {
        ResponseBody response = new ResponseBody(repository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getStudent(long id) {
        boolean isStudentExist = repository.existsById(id);
        if(!isStudentExist) throw new StudentNotFoundException();
        Optional<Student> student = repository.findById(id);
        ResponseBody response = new ResponseBody(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> createStudent(Student student) {
        Student savedStudent = repository.save(student);
        ResponseBody response = new ResponseBody(savedStudent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> deleteStudent(long id) {
        repository.deleteById(id);
        ResponseBody response = new ResponseBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
