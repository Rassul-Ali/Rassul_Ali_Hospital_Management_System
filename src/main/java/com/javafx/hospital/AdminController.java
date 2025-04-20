package com.javafx.hospital;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private PieChart admin_Doencas_graph;

    @FXML
    private TableView<?> admin_alta_semanal;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_doenca;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_medico;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_nomePa;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_quarto;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_sexo;

    @FXML
    private Button admin_conta;

    @FXML
    private Button admin_dashboard;

    @FXML
    private AnchorPane admin_dashboard_form;

    @FXML
    private Button admin_estagiario;

    @FXML
    private Label admin_form_text;

    @FXML
    private TableView<?> admin_interna_semanal;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_Medico;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_doenca;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_idade;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_nomePa;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_quarto;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_sexo;

    @FXML
    private StackPane admin_main;

    @FXML
    private AnchorPane admin_main_form;

    @FXML
    private Button admin_medicos;

    @FXML
    private AnchorPane admin_medicos_form;

    @FXML
    private Button admin_pacientes;

    @FXML
    private Button admin_reg_estagiario;

    @FXML
    private Button admin_reg_medicos;

    @FXML
    private Button admin_relatorio;

    @FXML
    private Button admin_sair;

    @FXML
    private Label admin_total_medicos;

    @FXML
    private Label admin_total_paci_falecidos;

    @FXML
    private Label admin_total_paci_internado;

    @FXML
    private Label admin_total_pacientes;

    @FXML
    private Label admin_user;

    @FXML
    private AnchorPane barchart;

    @FXML
    private Label admin_date;

    @FXML
    private BarChart<?, ?> s;


    public void sair() {
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

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a");
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        admin_date.setText(format.format(new Date()));
                    });
                }

            }
        }.start();
    }

    public void setName() {
        admin_user.setText(HospitalController.login_name);
    }

    public void swapMainForm(ActionEvent event) {
        if (event.getSource() == admin_dashboard) {
            admin_dashboard_form.setVisible(true);
            admin_medicos_form.setVisible(false);
            admin_form_text.setText("Dashboard");
        } else if (event.getSource() == admin_medicos) {
            admin_medicos_form.setVisible(true);
            admin_dashboard_form.setVisible(false);
            admin_form_text.setText("Medicos");
        } else if (admin_reg_medicos.isHover()) {

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        setName();
        admin_form_text.setText("Dashboard");
    }
}
