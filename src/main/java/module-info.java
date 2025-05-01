module com.javafx.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires net.sf.jasperreports.core;


    opens com.javafx.hospital to javafx.fxml;
    exports com.javafx.hospital;
}