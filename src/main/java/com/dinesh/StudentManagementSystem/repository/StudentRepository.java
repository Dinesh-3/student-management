package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
