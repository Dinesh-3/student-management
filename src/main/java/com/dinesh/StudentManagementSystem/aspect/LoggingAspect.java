package com.dinesh.StudentManagementSystem.aspect;

import com.dinesh.StudentManagementSystem.model.Student;
import com.dinesh.StudentManagementSystem.util.ResponseBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/*
    AOP -> Aspect Oriented Programming
    Definition -> Cross-Cutting Concern (or) Separation of concern
        Execute a piece code before, after, exception or both before and after a method.
    Application:
        1. This pattern separate's the business logic with logging or security logics
        2. Uses Logging, Security, Monitoring traffic, Analytics like Count api usage by user etc.
        3. Creates Extra layer to separate logic's
        4. Code Re usability and remove's duplicate code
    Disadvantages:
        1. Adding more advices are hard to maintain and understand
        2. Performance will be reduced due to extra layer of code.
            So need to be avoided intensive operations in Aspect advice
 */


@Aspect
@Component
//@Order(1) // These advices are run first, negative numbers are allowed RANGE: Integer.MIN_VALUE to Integer.MAX_VALUE, if order is same it will pick random order between those
final public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // create* (check all the classes methods that are starts with create)
    // * * createStudent() any modifier and return type
    // * createStudent() modifier is optional

    // POINT CUT MATCHING METHOD ARGUMENT
    /*
    createAccount:
        () -> 0 arg
        (${Type}) -> 1 arg with specified type
        (*) -> 1 arg with any type
        (..) -> 1 or more arg with any type
        (${Type}, ..) -> 1 specified type and more args with any type
     */
    // com.dinesh.StudentManagementSystem.service.*.*(..) -> matches all the method in service package classes with one or more args

    // DECLARING A POINT CUT
    /*
        Uses:
            1. Easily Reuse point cut logic
            2. Removes code duplication
        Add or 1 or more pointcut
        USING: &&, ||, !

        "controllerPointcut() && !(getter() || setter())" -> In controller applies advice
            for all the methods excluding getters and setters

     */
//    @Pointcut("com.dinesh.StudentManagementSystem.service.*.*(..)")
//    private void servicePointCut(){};

    @Pointcut("execution(* com.dinesh.StudentManagementSystem.controller.*.*(..))")
    private void controllerPointCut(){};

//    To use Pointcut
//    @Before("com.dinesh.StudentManagementSystem.aspect.Pointcuts.servicePointCut() || controllerPointCut()") // adding multiple point cut
//    public void useServicePointcut(){
//        System.out.println("TO use Pointcut need to add method name with ()");
//    }

//    @Before("execution(public org.springframework.http.ResponseEntity<com.dinesh.StudentManagementSystem.util.ResponseBody> createStudent())")
//    public void addStudent() {
//        System.out.println("BEFORE CREATE STUDENT");
//    }


    /*
        --- JOIN POINTS ---
        To get method args and more...
     */
    @Before("execution(public org.springframework.http.ResponseEntity<com.dinesh.StudentManagementSystem.util.ResponseBody> com.dinesh.StudentManagementSystem.service.StudentService.createStudent(com.dinesh.StudentManagementSystem.model.Student))")
    public void addStudent(JoinPoint joinPoint) {
        System.out.println("BEFORE CREATE STUDENT");
        String kind = joinPoint.getKind();
        System.out.println("kind = " + kind);
        var obj = joinPoint.getArgs()[0];
        if(obj instanceof Student){
            Student student = (Student) obj;
            System.out.println("arg = " + student.getFirst_name());
            long id = student.getId();
            System.out.println("student id = " + id);
        }
        Signature signature = joinPoint.getSignature();
        System.out.println("signature = " + signature);
    }

    @AfterReturning(
            pointcut = "execution(public org.springframework.http.ResponseEntity<com.dinesh.StudentManagementSystem.util.ResponseBody> com.dinesh.StudentManagementSystem.service.StudentService.createStudent(com.dinesh.StudentManagementSystem.model.Student))",
            returning = "result" // need to math with below variable name
    )
    public void afterReturningStudent(JoinPoint joinPoint, ResponseEntity<ResponseBody> result) {
        System.out.println("AFTER RETURNING CREATE STUDENT");
        Object data = result.getBody().getData();
        if(data instanceof Student) {
            Student student = (Student) data;
            long id = student.getId();
            // 🔔 changes will affect in response 🔔
//            student.setFirst_name("Dinesh I");
            System.out.println("student id = " + id);
        }

    }

    /*
        @AfterThrowing

        Runs if specified aspect throws any error
     */

    @AfterThrowing(
            pointcut = "execution(* com.dinesh.StudentManagementSystem.service.*.*(..))",
            throwing = "exception"
    )
    private void afterThrowing(JoinPoint joinPoint, Throwable exception){
        System.out.println("After Thrown ERROR THROWN AT: " + joinPoint.getSignature().toShortString());
        System.out.println("Error: " + exception.getMessage());
//        throw new IllegalArgumentException("Exception thrown in After Thrown");
    };

    /*
        @After
        Runs after method execution runs always success or error thrown similar to finally block

        Points:
            1. Runs before @AfterThrowing (in this version of spring boot runs after @AfterThrowing)
     */
    @After("execution(* com.dinesh.StudentManagementSystem.service.*.*(..))")
    private void afterFinally(JoinPoint joinPoint){
        log.info("Method Executed : " + joinPoint.getSignature().toShortString());
    };

    /*
        @Around
        Combination of @Before and @After
        Points:
            1. If Error thrown it will not run
            2. Runs last after all the advice are executed
            3. If error thrown first @AfterThrowing runs then catch block run
     */
    @Around("execution(* com.dinesh.StudentManagementSystem.service.*.*(..))")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("EXCEPTION HANDLING IN AROUND Error: " + e.getMessage());
            throw e;
        }
        long end = System.currentTimeMillis();
        long timeInMillis = end - start;
        System.out.println("Execution Time in Millis: " + timeInMillis);

        // if we change the return type to primitive or different type
        // except original and Object types it will throw type cast error
        // in controller so be careful
        return result;
    };

}
