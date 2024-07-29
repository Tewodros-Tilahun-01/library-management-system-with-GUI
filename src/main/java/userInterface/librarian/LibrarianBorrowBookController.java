package userInterface.librarian;


import com.example.fxdemo.BorrowedBook;
import com.example.fxdemo.Database;
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

public class LibrarianBorrowBookController extends  LibrarianDashboardController implements Initializable {
    @FXML
    private TableView<BorrowedBook> table;
    @FXML
    private TableColumn<BorrowedBook, String> coluserName;
    @FXML
    private TableColumn<BorrowedBook, String> colserialnumber;



    public static ObservableList<BorrowedBook> getBookList() throws SQLException {
        ObservableList<BorrowedBook> bookList = FXCollections.observableArrayList();
        Connection conn = new Database().getConn();
        String sql = "SELECT * from borrowedbooks";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String username = rs.getString("username");
            String serialnumber = rs.getString("serialnumber");
            BorrowedBook book = new BorrowedBook(username,serialnumber);
            bookList.add(book);
        }

        return bookList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coluserName.setCellValueFactory(new PropertyValueFactory<BorrowedBook, String>("username"));
        colserialnumber.setCellValueFactory(new PropertyValueFactory<BorrowedBook, String>("serialnumber"));


        try {
            ObservableList<BorrowedBook> books = getBookList();
            table.setItems(books);
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, show an error dialog to the user
        }
    }


}
