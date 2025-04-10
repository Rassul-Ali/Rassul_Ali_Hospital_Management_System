package com.javafx.hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
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


    public void sair(){
        admin_sair.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
