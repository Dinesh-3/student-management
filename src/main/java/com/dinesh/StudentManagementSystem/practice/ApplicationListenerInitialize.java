package com.dinesh.StudentManagementSystem.practice;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerInitialize implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("ApplicationListener applicationEvent = " + applicationEvent);
    }
}
