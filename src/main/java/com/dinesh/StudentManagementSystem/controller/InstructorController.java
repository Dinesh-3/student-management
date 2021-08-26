package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Instructor;
import com.dinesh.StudentManagementSystem.service.InstructorService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/instructor")
public class InstructorController {

    @Autowired
    private InstructorService service;

    @GetMapping
    ResponseEntity<ResponseBody> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseBody> getById(@NotNull @PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    ResponseEntity<ResponseBody> create(@NotNull @RequestBody Instructor instructor) {
        return service.create(instructor);
    }

    @PutMapping
    ResponseEntity<ResponseBody> update(@NotNull @RequestBody Instructor instructor) {
        return service.create(instructor);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseBody> delete(@NotNull @NotNull @PathVariable("id") Long id) {
        return service.delete(id);
    }
}
