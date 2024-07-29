package userInterface.librarian;


import com.example.fxdemo.Database;
import com.example.fxdemo.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LibrarianStudentController extends LibrarianDashboardController implements Initializable {
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> coluserName;
    @FXML
    private TableColumn<Person, String> colid;


    public static ObservableList<Person> getBookList() throws SQLException {
        ObservableList<Person> bookList = FXCollections.observableArrayList();
        Connection conn = new Database().getConn();
        String sql = "SELECT * from student";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String username = rs.getString("username");
            String serialnumber = rs.getString("id");
            Person person = new Person(username, serialnumber);
            bookList.add(person);
        }

        return bookList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coluserName.setCellValueFactory(new PropertyValueFactory<Person, String>("username"));
        colid.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));


        try {
            ObservableList<Person> persons = getBookList();
            table.setItems(persons);
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, show an error dialog to the user
        }

    }
}
