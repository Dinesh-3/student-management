package com.dinesh.StudentManagementSystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("developer")
public class DeveloperConfig {
    private String mode;
    private String name;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeveloperConfig{" +
                "mode='" + mode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
