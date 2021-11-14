package com.dinesh.StudentManagementSystem.pojo;

import org.springframework.context.ApplicationEvent;

public class InitializeEvent extends ApplicationEvent {
    public InitializeEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "InitializeEvent occurred{}";
    }
}
