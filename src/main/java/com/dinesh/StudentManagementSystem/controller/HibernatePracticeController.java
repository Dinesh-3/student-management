package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.practice.HibernatePractice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/hibernate")
public class HibernatePracticeController {
    @Autowired
    private HibernatePractice hibernatePractice;
    @GetMapping
    String practice() {
        hibernatePractice.start();
        return "Practiced";
    }
}
