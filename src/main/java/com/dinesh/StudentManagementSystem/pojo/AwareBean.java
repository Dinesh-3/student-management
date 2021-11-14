package com.dinesh.StudentManagementSystem.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * We can access context, bean by implementing this interface
 * The class must be defined as spring bean in xml or annotation
 */
public class AwareBean implements ApplicationContextAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationEventPublisherAware {


    private ApplicationContext context;

    public AwareBean() {
        System.out.println(" Aware Bean Default constructor called ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext = " + applicationContext);
        String[] beansName = applicationContext.getBeanDefinitionNames();
        System.out.println("beansName = " + Arrays.toString(beansName));
        this.context = applicationContext;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("Bean Name = " + s);
    }

    @Override
    @PostConstruct // corresponding afterPropertiesSet annotation
    public void afterPropertiesSet() throws Exception {
        System.out.println("Started initializing Bean... ");
    }

    @Override
    @PreDestroy // corresponding destroy annotation
    public void destroy() throws Exception {
        System.out.println(" Aware Bean is going to be destroyed ");
    }

    public void customInit() {
        System.out.println("Custom init method was called... ");
    }

    public void customCleanup() {
        System.out.println("Cleaning up on destroying...");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(this);
    }
}
