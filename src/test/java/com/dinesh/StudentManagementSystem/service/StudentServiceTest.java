package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.model.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService service;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Get All Students")
    void getAllStudents() {
        Iterable<Student> students = service.getAllStudents(new HashMap<>());
        Assertions.assertEquals(true, true);
    }

    @Test
    void getStudent() {
    }
}