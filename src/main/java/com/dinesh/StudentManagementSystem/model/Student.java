package com.dinesh.StudentManagementSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotNull
    private LocalDate date_of_birth;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Branch branch;
    @OneToMany(
        mappedBy = "student",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<Result> results = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String first_name, String last_name, LocalDate date_of_birth, Branch branch) {
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setDate_of_birth(date_of_birth);
        setBranch(branch);
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

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }


    public List<Result> getResults() {
        return results;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
