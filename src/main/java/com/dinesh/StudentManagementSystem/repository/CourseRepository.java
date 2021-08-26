package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
