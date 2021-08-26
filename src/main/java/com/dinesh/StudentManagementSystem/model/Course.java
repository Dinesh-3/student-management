package com.dinesh.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "instructor_id",
            referencedColumnName = "id"
    )
    @JsonManagedReference
//    @JsonIgnore
    private Instructor instructor;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long instructor_id;

//    @ManyToMany(
//            mappedBy = "courses",
//            fetch = FetchType.LAZY,
//            cascade = { CascadeType.PERSIST }
//    )
//    @JsonIgnore
//    private Set<Student> students;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    @JsonIgnore
    private Set<Enrollment> enrollments;

    public Course() {
    }

    public Course(Long id, String name, Instructor instructor, Long instructor_id) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.instructor_id = instructor_id;
    }

    public Course(String name, Long instructor_id) {
        this.name = name;
        this.instructor_id = instructor_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

//    public Set<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }

    public Long getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(Long instructor_id) {
        this.instructor_id = instructor_id;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

}
