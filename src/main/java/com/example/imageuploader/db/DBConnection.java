package com.example.imageuploader.db;

import javax.servlet.ServletOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private static final String dbURL = "jdbc:postgresql://localhost:5432/imageupload_db";
    private static final String dbUserName = "postgres";
    private static final String dbPassword = "sazan1Aa";

    public static Connection getConnection(){

        System.out.println("Start getConnection");

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(dbURL,dbUserName,dbPassword);

            if(connection != null){
                System.out.println("DB Connected !");
                return connection;
            }
            else {
                System.out.println("DB Connection Issue");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is DB Connection==>"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(DBConnection.getConnection());
    }

}
