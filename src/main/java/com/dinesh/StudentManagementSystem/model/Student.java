package com.dinesh.StudentManagementSystem.model;

import com.dinesh.StudentManagementSystem.validation.UserName;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity(name = "student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonIgnoreProperties(ignoreUnknown = false)
public class Student extends Auditable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @JsonProperty("student_id")
    @Positive
    @Column(unique = true, updatable = false, nullable = false)
    private long id;
    @NotBlank(message = "First Name is Required")
    private String first_name; // in springboot private transient String first_name; will throw error while running the program
    @UserName
    private String last_name;
    private String email;

//    @ManyToMany(
//            fetch = FetchType.EAGER
//    )
//    @JoinTable(
//            name = "student_course",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private Set<Course> courses;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student",
            fetch = FetchType.EAGER
    )
//    @JsonManagedReference // Collection, Map, Array or enumeration not support JsonBackReference
    private Set<Enrollment> enrollments;

    public Student() {
    }

    public Student(String first_name, String last_name, Set<Course> courses) {
        this.first_name = first_name;
        this.last_name = last_name;
//        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

//    @Required // to make use this setter value added in beans xml
    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//
//    public void addCourse(Course course) {
//        courses.add(course);
//    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", enrollments=" + enrollments +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
