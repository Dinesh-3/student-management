package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.dto.EnrollCourse;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.service.StudentService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//
//        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }

    @Operation(summary = "This is to fetch All the Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched All the Students",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "NOt Available",
                    content = @Content)
    })
    @GetMapping(consumes = {}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<ResponseBody> getAllStudents(@RequestParam Map<String,String> queryParams) {
        Iterable<Student> students = service.getAllStudents(queryParams);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody(students));
    }

    @RequestMapping(params = {"student_id"})
    public void getStudentById(@RequestParam("student_id") long studentId, HttpServletResponse response) throws IOException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("developer", "Dinesh I");
//        headers.add("Content-Type", "application/json");

        response.addHeader("developer", "Dinesh I");
        response.addHeader("Content-Type", "application/json");
        System.out.println("response = " + response);

        PrintWriter writer = response.getWriter();
        ResponseBody student = service.getStudent(studentId);
        writer.write(student.toString());
        writer.close();
//        return "";
//        System.out.println("headers = " + headers);
//        return new ResponseEntity<>(, headers, HttpStatus.OK);
    }

    /**
     *      ^
     *      Two separate methods for handling different request params
     *      |
     */
//    @RequestMapping(params = {"first_name", "last_name"})
//    public ResponseEntity<ResponseBody> getStudentByName(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
//        return service.getStudentByName(firstName, lastName);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getStudent(@PathVariable long id) {
        return new ResponseEntity<>(service.getStudent(id), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseBody> createStudent(@RequestBody Student student) {
        System.out.println("student = " + student);
//        Student result = service.createStudent(student);
        return ResponseEntity.ok(new ResponseBody(new Object()));
    }

    @PutMapping()
    public ResponseEntity<ResponseBody> updateStudent(@RequestBody Student student) {
        Student result = service.createStudent(student);
        return ResponseEntity.ok(new ResponseBody(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable long id) {
        return service.deleteStudent(id);
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseBody> searchStudents(@RequestParam String query) {
        return service.findStudents(query);
    }

    @PostMapping("/enroll")
    public ResponseEntity<ResponseBody> enrollCourse(@NotNull @RequestBody EnrollCourse enroll) {
        return service.enrollCourse(enroll);
    }

}
