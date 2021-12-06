package com.dinesh.StudentManagementSystem.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCPractice {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/tutor_app";
        String user = "root";
        String pass = "root";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!!!");
            Statement statement = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String getUserByNameQuery = """
                        SELECT * FROM student
                        WHERE first_name like "%dinesh%"
                        """;
            ResultSet resultSet = statement.executeQuery(getUserByNameQuery);
            System.out.println("resultSet = " + resultSet);


            while (resultSet.next()){
                System.out.println(String.format("%s, %s", resultSet.getString("first_name"), resultSet.getString("last_name")));
            }

            myConn.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
