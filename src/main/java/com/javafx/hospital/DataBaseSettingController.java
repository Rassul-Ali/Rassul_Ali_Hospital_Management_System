package com.javafx.hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

public class DataBaseSettingController implements Initializable {

    @FXML
    private Button DBCancelar;

    @FXML
    private PasswordField DBPassword;

    @FXML
    private TextField DBUrl;

    @FXML
    private TextField DBUser;

    @FXML
    private Button DBValidar;

    @FXML
    private StackPane DBform;

    private final String urlPath = "src/main/java/com/javafx/hospital/DBfiles/URL.dat";
    private final String userPath = "src/main/java/com/javafx/hospital/DBfiles/USER.dat";
    private final String passwordPath ="src/main/java/com/javafx/hospital/DBfiles/PASSWORD.dat";

    private AlertMessage alertMessage = new AlertMessage();

    public void validar() throws IOException {
        File url = new File(urlPath);
        File user = new File(userPath);
        File password = new File(passwordPath);

        RandomAccessFile urlD = new RandomAccessFile(url, "rw");
        urlD.writeUTF(DBUrl.getText());
        urlD.close();

        RandomAccessFile userD = new RandomAccessFile(user, "rw");
        userD.writeUTF(DBUser.getText());
        userD.close();

        RandomAccessFile passwordD = new RandomAccessFile(password, "rw");
        passwordD.writeUTF(DBPassword.getText());
        passwordD.close();


        if(alertMessage.confirmMessage(DBUrl.getText() + "," + DBUser.getText() + "," + DBPassword.getText()))
            closeCurrentWindow();

    }

    private void closeCurrentWindow() {
        ((Stage) DBform.getScene().getWindow()).close();
    }

    public void cancelar(){
        closeCurrentWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
