package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.model.Instructor;
import com.dinesh.StudentManagementSystem.repository.InstructorRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;

    public ResponseEntity<ResponseBody> getAll() {
        Iterable<Instructor> instructors = repository.findAll();
        ResponseBody response = new ResponseBody(instructors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getById(Long id) {
        Optional<Instructor> instructor = repository.findById(id);
        ResponseBody response = new ResponseBody(instructor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> create(Instructor instructor) {
        Instructor saveInstructor = repository.save(instructor);
        ResponseBody response = new ResponseBody(saveInstructor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new ResponseBody(), HttpStatus.OK);
    }
}
