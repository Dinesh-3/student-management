package com.dinesh.StudentManagementSystem;

import com.dinesh.StudentManagementSystem.config.DeveloperConfig;
import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.practice.BeanPractice;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableCaching
@OpenAPIDefinition // /v3/api-docs/swagger-config, /v3/api-docs
@EnableScheduling
@ImportResource("classpath:beans.xml") // import bean xml
public class StudentManagementSystemApplication implements CommandLineRunner, ExitCodeGenerator {
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
		BeanPractice beanPractice = new BeanPractice();
		beanPractice.start();
	}



	@Override
	public int getExitCode() {
		return 0; // Process finished with exit code 0 : for success
	}

	/**
	 * Bean Scopes
	 * singleton -> Scopes a single bean definition to a single object instance per Spring IoC container.
	 * prototype -> Scopes a single bean definition to any number of object instances.
	 *
	 * Web aware scope
	 * request -> Scopes a single bean definition to the lifecycle of a single HTTP request;
	 * 			  that is each and every HTTP request will have its own instance of a bean created off the back of a single bean definition.
	 * 			  Only valid in the context of a web-aware Spring ApplicationContext.
	 * session -> Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.
	 * global session -> Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically only valid when used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.
	 */
	@Bean
	@Scope("singleton")
	ExitCodeExceptionMapper exitCodeExceptionMapper(){
		return exception -> {
			if(exception.getCause() instanceof NullPointerException) return 10;
			if(exception.getCause() instanceof NumberFormatException) return 11;
			return 0;
		};
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			String name = null; // Inorder to throw NullPointerException
//			name.charAt(0); // Process finished with exit code 10
//		};
//	}

	@Bean
	ExitCodeListener initializeExitCodeListener(){
		return new ExitCodeListener();
	}

	public static class ExitCodeListener{
		@EventListener
		public void exitListener(ExitCodeEvent event) {
			/**
			 * Uncomment runner method to check  
			 */
			System.out.println();
			System.out.println("Application EXITED with Code : " + event.getExitCode());
			System.out.println();
		}
	}
}
