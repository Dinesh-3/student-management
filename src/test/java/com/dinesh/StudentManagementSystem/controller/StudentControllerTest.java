package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Dinesh", "I", null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStudentById() throws Exception {
//        Mockito.when(service.getStudent(1L))
//                .thenReturn(student);

        mockMvc.perform(get("api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.departmentName").
//                        value(department.getDepartmentName()))
        ;
    }

    @Test
    void getStudentByName() {
    }

    @Test
    void getStudent() {
    }
}