package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.dto.EnrollCourse;
import com.dinesh.StudentManagementSystem.exception.ResourceNotFoundException;
import com.dinesh.StudentManagementSystem.exception.StudentNotFoundException;
import com.dinesh.StudentManagementSystem.model.Course;
import com.dinesh.StudentManagementSystem.model.Enrollment;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.repository.CourseRepository;
import com.dinesh.StudentManagementSystem.repository.EnrollmentRepository;
import com.dinesh.StudentManagementSystem.repository.StudentRepository;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    public Iterable<Student> getAllStudents(Map<String, String> queryParams) {
        Iterable<Student> students;
        if(queryParams.containsKey("page") && queryParams.containsKey("limit")) {
            Map<String, Integer> pageQuery = getPageQuery(queryParams);
            students = repository.paginate(pageQuery.get("offset"), pageQuery.get("limit"));
        }
        else students = repository.findAll();

        return students;
    }

    /**  CACHING
     * Docs ->
     *  1. https://www.baeldung.com/spring-cache-tutorial
     *  2. https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html
     *
     * Cache Providers -> Generic, Redis, Caffeine, Simple, Hazelcast, Infinispan, Couchbase, EhCache 2.x
     * application properties:
     *  1. spring.cache.type
     *  2. spring.cache.type=none
     * @Cacheable
     *  1. It will run only once if the given parameter already executed and stored in cache
     *  2. unless property -> Not Caches if status is not true .
     *  3. If error or exception thrown it will not store in cache
     * Parameters:
     *  condition: for input's
     *  unless: for output
     * @CacheEvict
     *  1. The problem is size. We don't want to populate the cache with values that we don't need often.
     *     Caches can grow quite large, quite fast, and we could be holding on to a lot of stale or unused data.
     *  2. We can use the @CacheEvict annotation to indicate the removal of one or more/all values,
     *     so that fresh values can be loaded into the cache again:
     * @CachePut
     * 1. With the @CachePut annotation, we can update the content of the cache without interfering with the method execution.
     *    That is, the method will always be executed and the result cached.
     * 2. The difference between @Cacheable and @CachePut is that @Cacheable will skip running the method,
     *    whereas @CachePut will actually run the method and then put its results in the cache.
     * @Caching
     * 1.  we can group multiple caching annotations with @Caching, and use it to implement our own customized caching logic.
     *      Ex. @Caching(evict = { @CacheEvict("addresses"), @CacheEvict(value="directory", key="#customer.name") })
     *
     */
//    @Cacheable("getStudent")
    @Cacheable(key = "#id", value = "getStudent", unless = "#result.status != true") // change true to false in return statement to verify
    public ResponseBody getStudent(long id) {
        class Sample{};

        System.out.println("Called: " + new Object(){}.getClass().getEnclosingMethod().getName());
        Student student = repository.findById(id)
                            .orElseThrow(
                                    () -> new StudentNotFoundException("Student id " + id + " Not Found!")
                            );
        return new ResponseBody(true, student);
    }

    public Student createStudent(Student student) {
        Student save = repository.save(student);
        ResponseBody response = new ResponseBody(save);
        return save;
    }

    @CachePut("getStudent")
    public ResponseEntity<ResponseBody> updateStudent(Student student) {
        Student save = repository.save(student);
        ResponseBody response = new ResponseBody(save);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CacheEvict(key = "#id", value = "getStudent")
    public ResponseEntity<ResponseBody> deleteStudent(long id) {
        repository.deleteById(id);
        ResponseBody response = new ResponseBody(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> findStudents(String query) {
        List<Student> students = repository.findByContaining(query);
        ResponseBody response = new ResponseBody(students);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Integer> getPageQuery(Map<String, String> query) {
        Map<String, Integer> hashMap = new HashMap<>();
        try {
            int limit = Integer.parseInt(query.get("limit"));
            int page = Integer.parseInt(query.get("page"));
            hashMap.put("limit", limit);
            hashMap.put("page", page);
            hashMap.put("offset", (page - 1) * limit);
        }catch (Exception e) {
//            throw new IllegalArgumentException("Page and Limit must be an integer");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page and Limit must be an integer");
        }
        return hashMap;
    }

    public ResponseEntity<ResponseBody> enrollCourse(EnrollCourse enrollCourse) {
        Student student = repository.findById(enrollCourse.getStudent_id())
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found !"));
        Course course = courseRepository.findById(enrollCourse.getCourse_id())
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found !"));
//        student.addCourse(course);
//        student.addEnrollment(new Enrollment(student, course));
        Enrollment enrollment = new Enrollment(student, course);
        enrollmentRepository.save(enrollment);
        return new ResponseEntity<>(new ResponseBody(true, "Student Enrolled Successfully"), HttpStatus.OK);
    }

//    public ResponseEntity<ResponseBody> getStudentByName(String firstName, String lastName) {
//        Student student = repository.findByFirst_nameContainsOrLast_nameContains(firstName, lastName)
//                .orElseThrow(() -> new StudentNotFoundException(String.format("Student with name : %s %s Not Found !",firstName, lastName)));
//        return new ResponseEntity<>(new ResponseBody(true, "Success", student), HttpStatus.OK);
//    }
}
