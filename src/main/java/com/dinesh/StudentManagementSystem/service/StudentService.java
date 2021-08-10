package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.exception.StudentNotFoundException;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

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

}
