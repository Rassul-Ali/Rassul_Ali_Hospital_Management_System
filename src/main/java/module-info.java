module com.javafx.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires net.sf.jasperreports.core;


    exports com.javafx.hospital.Controller;
    opens com.javafx.hospital.Controller to javafx.fxml;
    exports com.javafx.hospital.DAO;
    opens com.javafx.hospital.DAO to javafx.fxml;
    exports com.javafx.hospital.Alerts;
    opens com.javafx.hospital.Alerts to javafx.fxml;
    exports com.javafx.hospital.Main;
    opens com.javafx.hospital.Main to javafx.fxml;
    exports com.javafx.hospital.Model;
    opens com.javafx.hospital.Model to javafx.fxml;
}