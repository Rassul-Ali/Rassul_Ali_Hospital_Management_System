package com.javafx.hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private Button admin_dashboard;

    @FXML
    private Button admin_definicoes;

    @FXML
    private StackPane admin_main;

    @FXML
    private Button admin_medicos;

    @FXML
    private Button admin_pacientes;

    @FXML
    private Button admin_reg_medicos;

    @FXML
    private Button admin_sair;

    @FXML
    private Label admin_user;

    @FXML
    private Label dasboard_date;


    public void sair(){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginRegister-view.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alpha3 Login do Administrador");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        admin_main.getScene().getWindow().hide();
    }

    public void setDate(){
        Date date = new Date();
        dasboard_date.setText(String.valueOf(new java.sql.Date(date.getTime())));
    }

    public void setName(){
        admin_user.setText(HospitalController.login_name);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        setName();
    }
}
