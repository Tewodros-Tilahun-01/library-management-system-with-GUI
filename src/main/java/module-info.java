module com.example.fxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.fxdemo to javafx.fxml;
    exports com.example.fxdemo;
    exports userInterface;
    opens userInterface to javafx.fxml;
}