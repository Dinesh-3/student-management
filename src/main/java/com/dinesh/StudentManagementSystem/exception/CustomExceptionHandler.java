package com.dinesh.StudentManagementSystem.exception;

import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class CustomExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, UnexpectedTypeException.class, ConstraintViolationException.class, IllegalArgumentException.class})
    public ResponseEntity<ResponseBody> handleValidationExceptions( Exception ex) {
        ResponseBody responseBody = new ResponseBody(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNotFoundException.class, ResultNotFoundException.class})
    public ResponseEntity<ResponseBody> handleUnexpectedTypeException( Exception ex) {
        ResponseBody responseBody = new ResponseBody(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody> handleException(Exception ex) {
        ResponseBody responseBody = new ResponseBody(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
