package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.dto.Todo;
import com.dinesh.StudentManagementSystem.dto.User;
import com.dinesh.StudentManagementSystem.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/rest-template")
public class RestTemplatePractice {

    @Autowired
    private RestTemplateService service;

    @GetMapping("/user")
    User[] getUsers() {
        return service.getUsers();
    }

    @GetMapping("/photo")
    Object[] getPhotos() {
        return service.getPhotos();
    }

    @PostMapping("/todo")
    Todo createTodo(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    @PutMapping("/todo/{id}")
    Todo updateTodo(@RequestBody Todo todo, @PathVariable int id) {
        return service.updateTodo(todo, id);
    }

    @DeleteMapping("/todo/{id}")
    Todo deleteTodo(@PathVariable int id) {
        return service.deleteTodo(id);
    }
}
