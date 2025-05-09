package com.javafx.hospital.DAO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final static  String urlPath = "src/main/java/com/javafx/hospital/DBfiles/URL.dat";
    private final static  String userPath = "src/main/java/com/javafx/hospital/DBfiles/USER.dat";
    private final static  String passwordPath ="src/main/java/com/javafx/hospital/DBfiles/PASSWORD.dat";

    public static Connection connectDB() throws SQLException, IOException {
        File url = new File(urlPath);
        File user = new File(userPath);
        File password = new File(passwordPath);

        RandomAccessFile urlD = new RandomAccessFile(url, "r");
        String urlE = urlD.readUTF();
        urlD.close();

        RandomAccessFile userD = new RandomAccessFile(user, "r");
        String userE = userD.readUTF();
        userD.close();

        RandomAccessFile passwordD = new RandomAccessFile(password, "r");
        String passwordE = passwordD.readUTF();
        passwordD.close();

        return DriverManager.getConnection(urlE, userE, passwordE);
    }
}
