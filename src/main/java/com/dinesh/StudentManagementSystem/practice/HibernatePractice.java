package com.dinesh.StudentManagementSystem.practice;

import com.dinesh.StudentManagementSystem.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * Main Interfaces:
 * 1. Session Factory
 * 2. Session
 * 3. Transaction
 */
@Component
public class HibernatePractice {

    public void start() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            /** CREATE QUERY
             *
             */
            System.out.println("### CREATE START ###");
            User user = new User("Hibernate", "Practice", "hibernate@gmail.com");

            // start a transaction
            Transaction transaction = session.beginTransaction();

            // save the student object
//            session.save(user);

            System.out.println("### CREATE END ###");

            System.out.println("### READ START ###");

//            User savedStudent = session.get(User.class, user.getUserId());
//            System.out.println("Get Student By Id = " + savedStudent);

            List<User> theStudents = session.createQuery("from User").getResultList();
            System.out.println("Get All students = " + theStudents);

            theStudents = session.createQuery("from User s where"
                            + " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            System.out.println("Students who have last name of Doe OR first name Daffy: " + theStudents);

            theStudents = session.createQuery("from User s where"
                            + " s.email LIKE '%gmail.com'").getResultList();
            System.out.println("\nStudents whose email ends with gmail.com: " + theStudents);

            System.out.println("### READ END ###");

            System.out.println("### UPDATE START ###");

            session.createQuery("update User set email='hibernate@gmail.com' where firstName like '%hibernate'")
                    .executeUpdate();
//            savedStudent.setLastName("Paratice Lastname");
            transaction.commit(); // it will update

            System.out.println("### UPDATE END ###");

            System.out.println("### DELETE START ###");
//            session.createQuery("delete from Student where id=2").executeUpdate();
            System.out.println("### DELETE END ###");


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student tempStudent : students) {
            System.out.println(tempStudent);
        }
    }
}