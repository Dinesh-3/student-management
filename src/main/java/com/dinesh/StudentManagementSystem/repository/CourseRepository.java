package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query(
            value = "SELECT * FROM course WHERE course.name LIKE %?1%",
            nativeQuery = true
    )
    Iterable<Course> searchByName(String search);

    Iterable<Course> findByNameContaining(String name);
}
