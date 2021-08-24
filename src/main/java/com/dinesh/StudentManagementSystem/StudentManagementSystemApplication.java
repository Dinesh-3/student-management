package com.dinesh.StudentManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAspectJAutoProxy
//@RequestMapping("api/v1") not work
public class StudentManagementSystemApplication {

//	@RequestMapping("api/v1") not work
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}
