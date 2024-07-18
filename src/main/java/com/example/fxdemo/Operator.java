package com.example.fxdemo;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operator {
    public static void addLibrarian(String userName,String password) throws SQLException {
        Connection conn = new Database().getConn();
        String sql = "insert into librarian values( ?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        conn.close();
    }
    public static void addStudent(String id ,String userName,String password) throws SQLException {
        Connection conn = new Database().getConn();
        String sql = "insert into student values( ?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, userName);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        conn.close();
    }

    public static void addBook(String bookName, String publisher, String author, String year, String serialnumber) throws SQLException {
        Connection conn = new Database().getConn();
        String sql = "insert into book values( ?,?,?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, bookName);
        preparedStatement.setString(2, publisher);
        preparedStatement.setString(3, author);
        preparedStatement.setString(4, year);
        preparedStatement.setString(5, serialnumber);
        preparedStatement.executeUpdate();

        String sql2 = "insert into stoke values(?)";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
        preparedStatement2.setString(1, serialnumber);
        preparedStatement2.executeUpdate();

        conn.close();
    }

    public static boolean deleteBook(String serialNumber) throws SQLException {
        Connection conn = new Database().getConn();
        boolean valid = false;

            String sql3 = "SELECT * FROM book WHERE serialnumber = ?";
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement3.setString(1, serialNumber);
            ResultSet resultSet = preparedStatement3.executeQuery();
            if(resultSet.next()){
                valid = true;
            }

        if(true) {
            String sql = "DELETE FROM book WHERE serialnumber = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, serialNumber);
            preparedStatement.executeUpdate();
            String sql2 = "DELETE FROM stoke WHERE serialnumber = ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1, serialNumber);
            preparedStatement2.executeUpdate();
        }
        return valid;
    }
    public  static  boolean[]  borrowBook(String id , String serialnumber) throws SQLException {
        Connection conn = new Database().getConn();
        boolean valid1 = false;
        boolean valid2 = false;

        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            valid1 = true;
        }
        if(valid1) {
            String sql2 = "SELECT * FROM stoke WHERE serialnumber = ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1, serialnumber);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                valid2 = true;
            }
        }

        if(valid1 && valid2){
            String sql3 = "DELETE FROM stoke WHERE serialnumber = ?";
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement3.setString(1, serialnumber);
            preparedStatement3.executeUpdate();

            String sql4 = "insert into borrowedbooks values(?,?)";
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, id);
            preparedStatement4.setString(2, serialnumber);
            preparedStatement4.executeUpdate();

        }
        boolean[]  result =   new boolean[2];
        result[0]= valid1;
        result[1] = valid2;
        return result;
    }

    public static boolean[] returnBook(String id, String serialnumber) throws SQLException {
        Connection conn = new Database().getConn();
        boolean valid1 = false;
        boolean valid2 = false;

        String sql = "SELECT * FROM borrowedbooks WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            valid1 = true;
        }
        if(valid1) {
            String sql2 = "SELECT * FROM borrowedbooks WHERE serialnumber = ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1, serialnumber);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                valid2 = true;
            }
        }

        if(valid1 && valid2){
            String sql3 = "DELETE FROM borrowedbooks WHERE id = ?  and serialnumber = ?";
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement3.setString(1, id);
            preparedStatement3.setString(2, serialnumber);
            preparedStatement3.executeUpdate();

            String sql4 = "insert into stoke values(?)";
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, serialnumber);
            preparedStatement4.executeUpdate();

        }
        boolean[]  result =   new boolean[2];
        result[0]= valid1;
        result[1] = valid2;
        return result;
    }
}
