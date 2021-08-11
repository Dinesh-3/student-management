package com.dinesh.StudentManagementSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class Log extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String path;

    private int status;

    public Log() {
    }

    public Log(String path, int status) {
        this.path = path;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }
}
