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
        Optional<Student> student = repository.findById(id);
        if(student.isEmpty()) throw new StudentNotFoundException();
        ResponseBody response = new ResponseBody(repository.findAll());
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
}
