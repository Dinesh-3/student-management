package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query(
        value = "SELECT * FROM student WHERE first_name LIKE %?1% OR last_name LIKE %?1% ORDER BY first_name, last_name",
        nativeQuery = true
    )
    List<Student> findByContaining(String contain);

    @Query(value = "SELECT * FROM student LIMIT ?1, ?2", nativeQuery = true)
    Iterable<Student> paginate(int offset, int limit);
}
