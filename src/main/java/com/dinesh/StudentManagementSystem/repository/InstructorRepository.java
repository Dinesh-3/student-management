package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
