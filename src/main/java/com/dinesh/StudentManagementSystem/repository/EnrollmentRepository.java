package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Enrollment;
import com.dinesh.StudentManagementSystem.model.EnrollmentId;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, EnrollmentId> {
}
