package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Result;
import com.dinesh.StudentManagementSystem.service.ResultService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/result")
public class ResultController {
    @Autowired
    private ResultService service;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResponseBody> getResultsByStudent(@PathVariable long studentId) {
        return service.getResultsByStudent(studentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getResult(@PathVariable long id) {
        return service.getResult(id);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<ResponseBody> createResult(@RequestBody Result result, @PathVariable long studentId) {
        return service.createResult(result, studentId);
    }

    @PutMapping()
    public ResponseEntity<ResponseBody> updateResult(@RequestBody Result result) {
        return service.updateResult(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteResult(@PathVariable long id) {
        return service.deleteResult(id);
    }
}
