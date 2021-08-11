package com.dinesh.StudentManagementSystem.controller;

import com.dinesh.StudentManagementSystem.service.LogService;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/log")
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
