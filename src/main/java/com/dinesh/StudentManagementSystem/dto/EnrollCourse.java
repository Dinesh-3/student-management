package com.dinesh.StudentManagementSystem.dto;

import javax.validation.constraints.NotNull;

public class EnrollCourse {
    @NotNull
    private Long student_id;
    @NotNull
    private Long course_id;

    public EnrollCourse() {
    }

    public EnrollCourse(Long student_id, Long course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }
}
