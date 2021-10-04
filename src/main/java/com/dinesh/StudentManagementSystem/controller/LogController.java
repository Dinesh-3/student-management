package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.service.LogService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService service;

    @GetMapping()
    ResponseEntity<ResponseBody> getLogs() {
        return service.getLogs();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseBody> getLogs(@PathVariable long id) {
        return service.getLogById(id);
    }

}
