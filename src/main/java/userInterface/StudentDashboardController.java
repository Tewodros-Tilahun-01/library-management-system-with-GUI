package userInterface;

import com.example.fxdemo.Main;
import com.example.fxdemo.Operator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StudentDashboardController {
    @FXML
    public TextField serialNumber;
    @FXML
    public TextField username;

    @FXML
    public TextField delId;

    @FXML
    public TextField delSerialNumber;

    public void displayAvailableBooks(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)serialNumber.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("studentBookBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("LIBRARY");
        stage.setScene(scene);

        stage.centerOnScreen();
    }

    public void displayBorrowedBooks(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)serialNumber.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("studentBorrowBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("LIBRARY");
        stage.setScene(scene);

        stage.centerOnScreen();
    }



    public void borrowBook(ActionEvent actionEvent) throws SQLException {
        String i = username.getText();
        String s = serialNumber.getText();

        boolean[]  result = Operator.borrowBook(i,s);
        if(!result[0]){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("student not found");
            alert.setContentText("try again");
            alert.show();
        }
        else if(!result[1]){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("book not found");
            alert.setContentText("try again");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("book successfully borrowed");
            alert.setContentText("I have a great day");
            alert.show();
        }
    }

    public void returnBook(ActionEvent actionEvent) throws SQLException {
        String i = delId.getText();
        String s = delSerialNumber.getText();

        boolean[]  result = Operator.returnBook(i,s);
        if(!result[0]){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("student not borrowed");
            alert.setContentText("try again");
            alert.show();
        }
        else if(!result[1]){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("book not found");
            alert.setContentText("try again");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("book successfully returned");
            alert.setContentText("I have a great day");
            alert.show();
        }

    }
    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)serialNumber.getScene().getWindow();
        stage.setTitle("LIBRARY");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}
