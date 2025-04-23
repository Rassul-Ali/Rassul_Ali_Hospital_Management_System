module com.javafx.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.javafx.hospital to javafx.fxml;
    exports com.javafx.hospital;
}