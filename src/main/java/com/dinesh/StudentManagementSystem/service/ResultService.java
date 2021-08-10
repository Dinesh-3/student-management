package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.exception.ResultNotFoundException;
import com.dinesh.StudentManagementSystem.exception.StudentNotFoundException;
import com.dinesh.StudentManagementSystem.model.Result;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.repository.ResultRepository;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<ResponseBody> getResultsByStudent(long student_id) {
        Iterable<Result> results = repository.findByStudentId(student_id);
        ResponseBody response = new ResponseBody(results);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getResult(long id) {
        Optional<Result> result = repository.findById(id);
        if(result.isEmpty()) throw new ResultNotFoundException("Result Not Found");
        ResponseBody response = new ResponseBody(result.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> createResult(Result result, long student_id) {
        Optional<Student> student = studentRepository.findById(student_id);
        if(student.isEmpty()) throw new StudentNotFoundException("Student Not Found");
        result.setStudent(student.get());
        Result saved = repository.save(result);
        ResponseBody response = new ResponseBody(saved);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> updateResult(Result result) {
        Result updated = repository.save(result);
        ResponseBody response = new ResponseBody(updated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> deleteResult(long id) {
        repository.deleteById(id);
        ResponseBody response = new ResponseBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
