package com.example.fxdemo;

import java.sql.*;


public class Database {
    private   Connection conn;
    public Connection getConn()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
//            System.out.print(conn);
            System.out.println("Database connected");
        }catch (Exception e){
            System.out.println("something wrong");
        }
        return conn;
    }
}
