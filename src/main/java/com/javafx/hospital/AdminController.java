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
    private Label admin_date;

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
    private TableView<?> admin_med_table;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular;

    @FXML
    private TableColumn<?, ?> admin_med_table_End;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc;

    @FXML
    private TableColumn<?, ?> admin_med_table_ID;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade;

    @FXML
    private TableColumn<?, ?> admin_med_table_Nome;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal;

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

    private void configurarTabelaMedicos() {
        Platform.runLater(() -> {
            admin_med_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            double totalWidth = admin_med_table.getWidth();
            if(totalWidth > 0) {
                admin_med_table_ID.setPrefWidth(totalWidth * 0.08);       // 8%
                admin_med_table_Nome.setPrefWidth(totalWidth * 0.15);     // 15%
                admin_med_table_Sexo.setPrefWidth(totalWidth * 0.08);     // 8%
                admin_med_table_Celular.setPrefWidth(totalWidth * 0.12);  // 12%
                admin_med_table_emal.setPrefWidth(totalWidth * 0.20);      // 20%
                admin_med_table_Espc.setPrefWidth(totalWidth * 0.17);     // 17%
                admin_med_table_End.setPrefWidth(totalWidth * 0.15);      // 15%
                admin_med_table_Idade.setPrefWidth(totalWidth * 0.05);    // 5%
            }

            admin_med_table.widthProperty().addListener((obs, oldVal, newVal) -> {
                if(newVal.doubleValue() > 0) {
                    admin_med_table_ID.setPrefWidth(newVal.doubleValue() * 0.08);
                    admin_med_table_Nome.setPrefWidth(newVal.doubleValue() * 0.15);
                    admin_med_table_Sexo.setPrefWidth(newVal.doubleValue() * 0.08);
                    admin_med_table_Celular.setPrefWidth(newVal.doubleValue() * 0.12);
                    admin_med_table_emal.setPrefWidth(newVal.doubleValue() * 0.20);
                    admin_med_table_Espc.setPrefWidth(newVal.doubleValue() * 0.17);
                    admin_med_table_End.setPrefWidth(newVal.doubleValue() * 0.15);
                    admin_med_table_Idade.setPrefWidth(newVal.doubleValue() * 0.05);
                }
            });
        });
    }


    private void configurarTabelaAltas() {
        Platform.runLater(() -> {
            admin_alta_semanal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            double totalWidth = admin_alta_semanal.getWidth();
            if(totalWidth > 0) {
                admin_alta_semanal_nomePa.setPrefWidth(totalWidth * 0.30);
                admin_alta_semanal_doenca.setPrefWidth(totalWidth * 0.25);
                admin_alta_semanal_medico.setPrefWidth(totalWidth * 0.25);
                admin_alta_semanal_sexo.setPrefWidth(totalWidth * 0.10);
                admin_alta_semanal_quarto.setPrefWidth(totalWidth * 0.10);
            }

            admin_alta_semanal.widthProperty().addListener((obs, oldVal, newVal) -> {
                if(newVal.doubleValue() > 0) {
                    admin_alta_semanal_nomePa.setPrefWidth(newVal.doubleValue() * 0.30);
                    admin_alta_semanal_doenca.setPrefWidth(newVal.doubleValue() * 0.25);
                    admin_alta_semanal_medico.setPrefWidth(newVal.doubleValue() * 0.25);
                    admin_alta_semanal_sexo.setPrefWidth(newVal.doubleValue() * 0.10);
                    admin_alta_semanal_quarto.setPrefWidth(newVal.doubleValue() * 0.10);
                }
            });
        });
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
        configurarTabelaMedicos();
        configurarTabelaAltas();
    }
}
