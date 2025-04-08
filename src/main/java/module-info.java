module com.javafx.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.javafx.hospital to javafx.fxml;
    exports com.javafx.hospital;
}