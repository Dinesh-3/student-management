package com.dinesh.StudentManagementSystem.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(value = NON_NULL)
public class ResponseBody {
    private boolean status = true;
    private String message = "Success";
    private Object data;

    public ResponseBody() {
    }

    public ResponseBody(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseBody(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ResponseBody(Object data) {
        this.data = data;
    }

    public ResponseBody(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {

        return String.format("{\"status\":%s,\"message\":\"%s\",\"data\":%s}",status,message,data);
    }
}
