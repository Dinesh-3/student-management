package com.dinesh.StudentManagementSystem.repository;

import com.dinesh.StudentManagementSystem.model.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Result, Long> {
    Iterable<Result> findByStudentId(long id);
}
