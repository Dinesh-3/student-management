package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.repository.LogRepository;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(StudentController.class)
@ExtendWith(RestDocumentationExtension.class)
//@ComponentScan("com.dinesh.StudentManagementSystem")
public class StudentContrsdollerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    LogRepository logRepository;

    @Test
    void getAllStudents() throws Exception {
        mockMvc.perform(get("/api/v1/student")).andExpect(status().isOk());
    }

}
