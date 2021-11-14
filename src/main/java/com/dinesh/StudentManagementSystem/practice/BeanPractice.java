package com.dinesh.StudentManagementSystem.practice;

import com.dinesh.StudentManagementSystem.model.Student;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class BeanPractice {

    public void start() {
        beanPractise();
    }

    private void beanPractise() {
		BeanFactory xmlBeanFactory = new XmlBeanFactory(new FileSystemResource(Paths.get("").toAbsolutePath() + "/src/main/resources/beans.xml"));
		Student dinesh = xmlBeanFactory.getBean("dinesh", Student.class);
        System.out.println("dinesh = " + dinesh);

        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student dinesh1 = classPathXmlApplicationContext.getBean("dinesh", Student.class);
//        String developerName = classPathXmlApplicationContext.getMessage("developer.name", null, "Default Value", null);
//        System.out.println("developerName = " + developerName);
        System.out.println("dinesh1 = " + dinesh1);

//        AbstractApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        pathXmlApplicationContext.registerShutdownHook(); // this will shutdown all the beans when beanPractise method ends
    }
}
