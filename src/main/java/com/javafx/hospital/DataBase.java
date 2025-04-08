package com.javafx.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    public static Connection connectDB() throws SQLException {

        String url = "jdbc:mysql://127.0.0.1/HOSPITAL";
        String user = "root";
        String password = "@Rassul963";

        return DriverManager.getConnection(url, user, password);
    }
}
