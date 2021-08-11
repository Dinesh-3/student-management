package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.model.Log;
import com.dinesh.StudentManagementSystem.repository.LogRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogService {
    @Autowired
    private LogRepository repository;

    public void insertLog(Log log) {
        repository.save(log);
    }

    public ResponseEntity<ResponseBody> getLogs() {

        Iterable<Log> logs = repository.findAll();
        ResponseBody responseBody = new ResponseBody(logs);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getLogById(long id) {
        Optional<Log> logs = repository.findById(id);
        ResponseBody responseBody = new ResponseBody(logs);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
