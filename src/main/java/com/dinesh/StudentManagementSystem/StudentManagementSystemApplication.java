package com.dinesh.StudentManagementSystem;

import com.dinesh.StudentManagementSystem.config.DeveloperConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	POM -> Project Object Model for pom.xml
 */

@SpringBootApplication(
//		exclude = { DataSourceAutoConfiguration.class } // Removes Auto Config for DB
)
@EnableAspectJAutoProxy
//@RequestMapping("api/v1") not work
@EnableConfigurationProperties
public class StudentManagementSystemApplication implements CommandLineRunner {
	@Value("${test.name:not_loaded}")
	private String name;

	@Autowired
	private Environment env;

	@Autowired
	private DeveloperConfig developer;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

//	@RequestMapping("api/v1") not work
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("Application Successfully started on PORT: " + env.getProperty("server.port"));
		System.out.println("--- COMMANDLINE RUNNER ---");
		System.out.println(name);

		log.info("Developer " + developer);
	}
}
