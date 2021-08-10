package com.dinesh.StudentManagementSystem.util;

public class ResponseBody {
    private boolean status = true;
    private String message = "Success";
    private Object data;

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
}
