package com.dinesh.StudentManagementSystem.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException() {
        super("Student Not Found");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
