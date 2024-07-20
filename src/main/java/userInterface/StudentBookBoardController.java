package userInterface;

import com.example.fxdemo.Book;
import com.example.fxdemo.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentBookBoardController extends  StudentDashboardController implements Initializable {
    public TableColumn<Book,String> publisher;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> author;
    @FXML
    private TableColumn<Book, String> year;
    @FXML

    private TableColumn<Book, String> serial;

    public static ObservableList<Book> getBookList() throws SQLException {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        Connection conn = new Database().getConn();
        String sql = "SELECT b.name,b.author,b.publisher,b.year,s.serialnumber FROM book b JOIN stoke s ON b.serialnumber =s.serialnumber;";

         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("name");
            String author = rs.getString("author");
            String year = rs.getString("year");
            String publish = rs.getString("publisher");
            String serial = rs.getString("serialnumber");
            Book book = new Book(name, author, year, publish, serial);
            bookList.add(book);
        }

        return bookList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        year.setCellValueFactory(new PropertyValueFactory<Book, String>("year"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        serial.setCellValueFactory(new PropertyValueFactory<Book, String>("serial"));

        try {
            ObservableList<Book> books = getBookList();
            table.setItems(books);
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, show an error dialog to the user
        }
    }
}
