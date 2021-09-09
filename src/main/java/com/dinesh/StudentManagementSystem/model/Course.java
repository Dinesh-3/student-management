package com.dinesh.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "course", indexes = {
        @Index(name = "idx_course_instructor_id", columnList = "instructor_id")
})
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Course extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(
            name = "instructor_id"
//            referencedColumnName = "id"
    )
    @JsonBackReference
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
    @JsonBackReference
    private Set<Enrollment> enrollments;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Category category;

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

    public String getCategory() {
        return category.getCategory();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
