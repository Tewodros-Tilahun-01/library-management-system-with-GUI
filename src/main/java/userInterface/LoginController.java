package userInterface;

import com.example.fxdemo.Database;
import com.example.fxdemo.Main;
import com.example.fxdemo.Operator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    public Button button;
    @FXML
    public TextField librarianName;
    @FXML
    public PasswordField librarianPassword;


    @FXML
    public TextField studentName;
    @FXML
    public PasswordField studentPassword;



    Connection conn;
    public LoginController() throws SQLException {
         conn = new Database().getConn();

    }

    public void onLIbLoginButtonClick(ActionEvent actionEvent) throws SQLException {
        String username = librarianName.getText();
        String password = librarianPassword.getText();

        if (conn.isClosed()) {
            conn = new Database().getConn();
        }

        String sql = "SELECT * FROM librarian WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {


            // User found
            Stage stage = (Stage)button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("librarianAvailableBooks.fxml"));
            Scene root = null;

            try {
                root = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("load error");
            }
            stage.setTitle("librarian");
            stage.setScene(root);
            stage.centerOnScreen();


        } else {
            // User not found
           librarianName.setStyle("-fx-border-color: red");
           librarianPassword.setStyle("-fx-border-color: red");
        }

        // Close the resources
        resultSet.close();
        preparedStatement.close();
    }

    public void onStdLoginButtonClick(ActionEvent actionEvent) throws SQLException {

        String username = studentName.getText();
        String password = studentPassword.getText();

        if (conn.isClosed()) {
            conn = new Database().getConn();
        }

        String sql = "SELECT * FROM student WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Operator.login = studentName.getText();

            // User found
            Stage stage = (Stage)button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("studentAvailableBook.fxml"));
            Scene root = null;

            try {
                root = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("load error");
            }
            stage.setTitle("student");
            stage.setScene(root);
            stage.centerOnScreen();

        } else {
            // User not found
            studentName.setStyle("-fx-border-color: red");
            studentPassword.setStyle("-fx-border-color: red");

        }

        // Close the resources
        resultSet.close();
        preparedStatement.close();
    }


}
