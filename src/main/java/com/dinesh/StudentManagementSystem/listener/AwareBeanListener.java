package com.dinesh.StudentManagementSystem.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import javax.servlet.annotation.WebListener;

@WebListener
public class AwareBeanListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("applicationEvent = " + applicationEvent);
    }
}
