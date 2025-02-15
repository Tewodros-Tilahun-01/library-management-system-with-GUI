package userInterface.librarian;

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
import java.util.Objects;

public class LibrarianDashboardController {
    @FXML
    public TextField libUserName;
    @FXML

    public TextField libPassword;
    @FXML

    public TextField stdId;
    @FXML

    public TextField stdUserName;
    @FXML

    public TextField stdPassword;
    @FXML

    public TextField bookName;
    @FXML

    public TextField publisher;
    @FXML

    public TextField author;
    @FXML

    public TextField year;
    @FXML

    public TextField serianumber;
    @FXML

    public TextField delSerialNumber;




    @FXML
    public void addLIbrarian(ActionEvent actionEvent) throws SQLException {
        String userName = libUserName.getText();
        String password = libPassword.getText();

        boolean valid = true;
        if(Objects.equals(userName, "")){
            libUserName.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            libUserName.setStyle("-fx-border-color: none");
        }
        if(Objects.equals(password, "")){
           libPassword.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            libPassword.setStyle("-fx-border-color: none");
        }

        if(valid) {
            Operator.addLibrarian(userName,password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            alert.setHeaderText("librarian successfully added");
            alert.setContentText("I have a great day");
            alert.show();
            libUserName.setText("");
            libPassword.setText("");
        }

    }
    @FXML
    public void addStudent(ActionEvent actionEvent) throws SQLException {
        String id = stdId.getText();
        String userName = stdUserName.getText();
        String password = stdPassword.getText();

        boolean valid = true;
        if(Objects.equals(id, "")){
            stdId.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            stdId.setStyle("-fx-border-color: none");

        }
        if(Objects.equals(userName, "")){
            stdUserName.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            stdUserName.setStyle("-fx-border-color: none");
        }
        if(Objects.equals(password, "")){
            stdPassword.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            stdPassword.setStyle("-fx-border-color: none");
        }

        if(valid) {
            boolean res = Operator.addStudent(id, userName, password);
            if (res) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText("student successfully added");
                alert.setContentText("I have a great day");
                alert.show();
                stdId.setText("");
                stdUserName.setText("");
                stdPassword.setText("");
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText("username is taken");
                alert.setContentText("try again");
                alert.show();
            }
        }

    }
@FXML
    public void addBook(ActionEvent actionEvent) throws SQLException {
        String b =  bookName.getText();
        String p =  publisher.getText();
        String a = author.getText();
        String y = year.getText();
        String s = serianumber.getText();

        boolean valid = true;
        if(Objects.equals(b, "")){
            bookName.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            bookName.setStyle("-fx-border-color: none");

        }

        if(Objects.equals(p, "")){
            publisher.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            publisher.setStyle("-fx-border-color: none");

        }

        if(Objects.equals(a, "")){
            author.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            author.setStyle("-fx-border-color: none");

        }
        if(Objects.equals(y, "")){
            year.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            year.setStyle("-fx-border-color: none");

        }
        if(Objects.equals(s, "")){
            serianumber.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            serianumber.setStyle("-fx-border-color: none");

        }


        if(valid) {
           boolean res =  Operator.addBook(b,p,a,y,s);
            if(res) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText("book successfully added");
                alert.setContentText("I have a great day");
                alert.show();
                bookName.setText("");
                publisher.setText("");
                author.setText("");
                year.setText("");
                serianumber.setText("");
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText("book with the same serialnumber exit");
                alert.setContentText("please try again");
                alert.show();
            }
        }
    }
    @FXML
    public void deleteBook(ActionEvent actionEvent) throws SQLException {
        String serial = delSerialNumber.getText();
        boolean valid = true;

        if(Objects.equals(serial, "")){
            delSerialNumber.setStyle("-fx-border-color: red");
            valid = false;
        }else {
            delSerialNumber.setStyle("-fx-border-color: none");

        }

        if(valid){
           boolean result =  Operator.deleteBook(serial);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("message");
            if(result){
                alert.setHeaderText("book successfully deleted");
            }else {
                alert.setHeaderText("book not found ");
            }
            alert.setContentText("I have a great day");
            alert.show();
            delSerialNumber.setText("");

        }
    }
    public void displayAvalibleBooks(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)serianumber.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("librarianAvailableBooks.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("LIBRARY");
        stage.setScene(scene);

        stage.centerOnScreen();

    }
    @FXML
    public void displayBorrowedStudnts(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)serianumber.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("librarianBorrowBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("LIBRARY");
        stage.setScene(scene);

        stage.centerOnScreen();

    }
    @FXML
    public void displayStudent(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)serianumber.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("librarianStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("LIBRARY");
        stage.setScene(scene);
        stage.centerOnScreen();

    }
    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)serianumber.getScene().getWindow();
        stage.setTitle("LIBRARY");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}
