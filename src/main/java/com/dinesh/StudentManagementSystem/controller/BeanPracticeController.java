package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.pojo.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/practice/bean")
@RestController
public class BeanPracticeController {
    private final ApplicationContext xmlContext = new ClassPathXmlApplicationContext("beans.xml");;
    public BeanPracticeController() {
        System.out.println("No args constructor Called");
    }

    @GetMapping
    private String get() {
        Movie movie = xmlContext.getBean("movie", Movie.class);
        System.out.println("movie = " + movie);
        return "asdf";
    }
}
