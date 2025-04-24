package com.javafx.hospital;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdminController implements Initializable {

    // Constants
    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final int TIME_UPDATE_DELAY = 1; // seconds
    private AlertMessage alertMessage;
    private Button selectedButton;

    // Thread management
    private ScheduledExecutorService scheduler;

    // FXML Components
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
    private AnchorPane admin_estagiario_form;

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
    private TableView<?> admin_med_table1;

    @FXML
    private TableView<?> admin_med_table11;

    @FXML
    private TableView<?> admin_med_table111;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular1;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular11;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular111;

    @FXML
    private TableColumn<?, ?> admin_med_table_End;

    @FXML
    private TableColumn<?, ?> admin_med_table_End11;

    @FXML
    private TableColumn<?, ?> admin_med_table_End111;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc1;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc11;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc111;

    @FXML
    private TableColumn<?, ?> admin_med_table_ID;

    @FXML
    private TableColumn<?, ?> admin_med_table_ID1;

    @FXML
    private TableColumn<?, ?> admin_med_table_ID11;

    @FXML
    private TableColumn<?, ?> admin_med_table_ID111;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade1;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade11;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade111;

    @FXML
    private TableColumn<?, ?> admin_med_table_Nome;

    @FXML
    private TableColumn<?, ?> admin_med_table_Nome1;

    @FXML
    private TableColumn<?, ?> admin_med_table_Nome11;

    @FXML
    private TableColumn<?, ?> admin_med_table_Nome111;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo1;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo11;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo111;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal1;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal11;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal111;

    @FXML
    private Button admin_medicos;

    @FXML
    private AnchorPane admin_medicos_form;

    @FXML
    private Button admin_pacientes;

    @FXML
    private AnchorPane admin_pacientes_form;

    @FXML
    private Button admin_registar;

    @FXML
    private AnchorPane admin_registar_form;

    @FXML
    private Button admin_relatorio;

    @FXML
    private AnchorPane admin_relatorios_from;

    @FXML
    private Button admin_sair;

    @FXML
    private Button admin_tecnicos;

    @FXML
    private AnchorPane admin_tecnicos_form;

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
    private ComboBox<String> registar_escolha;

    @FXML
    private AnchorPane registar_estagiario;

    @FXML
    private AnchorPane registar_medicos;

    @FXML
    private AnchorPane registar_tecnico;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTimeUpdater();
        setName();
        admin_form_text.setText("Dashboard");
        configurarTabelaMedicos();
        configurarTabelaAltas();
        addUser();

        Platform.runLater(() -> {
            admin_dashboard.getStyleClass().add("selected");
            selectedButton = admin_dashboard;
        });
    }

    private void addUser() {
        ObservableList<String> list = FXCollections.observableArrayList(Users.registar);
        registar_escolha.setItems(list);
        registar_escolha.getSelectionModel().selectFirst();
    }

    public void selectRegister() {
        switch (registar_escolha.getSelectionModel().getSelectedItem()) {
            case "Médico":
                showRegistarMedico();
                break;

            case "Tecnico":
                showRegistarTecnico();
                break;

            case "Estagiário":
                showRegistarEstagiario();
                break;

        }
    }

    private void initTimeUpdater() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            String formattedTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
            Platform.runLater(() -> admin_date.setText(formattedTime));
        }, 0, TIME_UPDATE_DELAY, TimeUnit.SECONDS);
    }

    private void shutdownScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void sair() {
        shutdownScheduler();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginRegister-view.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alpha3 Login");
            stage.setResizable(false);
            stage.show();

            admin_main.getScene().getWindow().hide();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao carregar a tela de login");
        }
    }

    private void configurarTabelaMedicos() {
        admin_med_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        admin_med_table.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
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
    }

    private void configurarTabelaAltas() {
        admin_alta_semanal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        admin_alta_semanal.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                admin_alta_semanal_nomePa.setPrefWidth(newVal.doubleValue() * 0.30);
                admin_alta_semanal_doenca.setPrefWidth(newVal.doubleValue() * 0.25);
                admin_alta_semanal_medico.setPrefWidth(newVal.doubleValue() * 0.25);
                admin_alta_semanal_sexo.setPrefWidth(newVal.doubleValue() * 0.10);
                admin_alta_semanal_quarto.setPrefWidth(newVal.doubleValue() * 0.10);
            }
        });
    }

    public void setName() {
        admin_user.setText(HospitalController.login_name);
    }

    private void handleButtonSelection(Button clickedButton) {
        // Se o botão clicado já está selecionado, não faz nada
        if (selectedButton == clickedButton) {
            return;
        }

        // Remove a classe 'selected' do botão anterior
        if (selectedButton != null) {
            selectedButton.getStyleClass().remove("selected");
        }

        // Adiciona a classe 'selected' ao novo botão
        clickedButton.getStyleClass().add("selected");
        selectedButton = clickedButton;
    }

    public void swapMainForm(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        handleButtonSelection(clickedButton);

        if (clickedButton == admin_dashboard) {
            showDashboard();
        } else if (clickedButton == admin_medicos) {
            showMedicos();
        } else if (clickedButton == admin_pacientes) {
            showPacientes();
        } else if (clickedButton == admin_estagiario) {
            showEstagiario();
        } else if (clickedButton == admin_tecnicos) {
            showTecnico();
        } else if (clickedButton == admin_registar) {
            showRegistar();
        } else if (clickedButton == admin_relatorio) {
            showRelatorios();
        }
        // Adicione outros botões conforme necessário
    }


    private void showDashboard() {
        admin_dashboard_form.setVisible(true);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_from.setVisible(false);
        admin_form_text.setText("Dashboard");
    }

    private void showMedicos() {
        admin_medicos_form.setVisible(true);
        admin_dashboard_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_from.setVisible(false);
        admin_form_text.setText("Médicos");
    }

    private void showPacientes() {
        admin_pacientes_form.setVisible(true);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_from.setVisible(false);
        admin_form_text.setText("Pacientes");
    }

    private void showEstagiario() {
        admin_estagiario_form.setVisible(true);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_from.setVisible(false);
        admin_form_text.setText("Estagiários");
    }

    private void showTecnico() {
        admin_tecnicos_form.setVisible(true);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_from.setVisible(false);
        admin_form_text.setText("Tecnico");
    }

    private void showRegistar() {
        admin_registar_form.setVisible(true);
        admin_relatorios_from.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_form_text.setText("Registar");
    }

    private void showRelatorios() {
        admin_relatorios_from.setVisible(true);
        admin_registar_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_form_text.setText("Relatórios");
    }

    private void showRegistarMedico() {
        registar_medicos.setVisible(true);
        registar_estagiario.setVisible(false);
        registar_tecnico.setVisible(false);
    }

    private void showRegistarTecnico() {
        registar_tecnico.setVisible(true);
        registar_medicos.setVisible(false);
        registar_estagiario.setVisible(false);
    }

    private void showRegistarEstagiario() {
        registar_estagiario.setVisible(true);
        registar_medicos.setVisible(false);
        registar_tecnico.setVisible(false);
    }
}