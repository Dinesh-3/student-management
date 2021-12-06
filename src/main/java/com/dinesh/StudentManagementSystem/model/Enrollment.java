package com.dinesh.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property = "id")

public class Enrollment extends Auditable{

    @EmbeddedId
    @JsonUnwrapped
    private EnrollmentId id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_student_id_fk"
            )
    )
    @JsonBackReference
    private Student student;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_course_id_fk"
            )
    )
    @JsonBackReference
    private Course course;

    public Enrollment(EnrollmentId id,
                      Student student,
                      Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public Enrollment(Student student,
                      Course course) {
        this.id = new EnrollmentId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
    }

    public Enrollment() {
    }

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}

