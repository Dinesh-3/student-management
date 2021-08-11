package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.dto.User;
import com.dinesh.StudentManagementSystem.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
