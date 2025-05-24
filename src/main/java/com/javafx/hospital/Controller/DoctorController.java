package com.javafx.hospital.Controller;

import com.javafx.hospital.Alerts.AlertMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final int TIME_UPDATE_DELAY = 1; // seconds
    private AlertMessage alertMessage;
    private Button selectedButton;
    @FXML
    private Button med_consulta;

    @FXML
    private AnchorPane med_consulta_form;

    @FXML
    private Button med_conta;

    @FXML
    private AnchorPane med_conta_form;

    @FXML
    private StackPane med_main;

    @FXML
    private Button med_relatorios;

    @FXML
    private AnchorPane med_paciente_form;

    @FXML
    private Button med_pacientes;

    @FXML
    private Button med_principal;

    @FXML
    private AnchorPane med_principal_form;

    @FXML
    private AnchorPane med_registar_form;

    @FXML
    private Button med_registar_paciente;

    @FXML
    private AnchorPane med_relatorios_form;

    @FXML
    private Button med_sair;



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

        if (clickedButton == med_principal) {
            showPrincipal();
        } else if (clickedButton == med_consulta) {
            showConsulta();
        } else if (clickedButton == med_pacientes) {
            showPaciente();
        } else if (clickedButton == med_registar_paciente) {
            showPaciente();
        } else if (clickedButton == med_relatorios) {
            showRelatorios();
        } else if (clickedButton == med_conta) {
            showConta();
        }
        // Adicione outros botões conforme necessário
    }

    private void showPrincipal() {
        med_principal_form.setVisible(true);
        med_consulta_form.setVisible(false);
        med_paciente_form.setVisible(false);
        med_registar_form.setVisible(false);
        med_relatorios_form.setVisible(false);
        med_conta_form.setVisible(false);
    }

    private void showPaciente() {
        med_paciente_form.setVisible(true);
        med_principal_form.setVisible(false);
        med_consulta_form.setVisible(false);
        med_registar_form.setVisible(false);
        med_relatorios_form.setVisible(false);
        med_conta_form.setVisible(false);
    }

    private void showConsulta() {
        med_consulta_form.setVisible(true);
        med_paciente_form.setVisible(false);
        med_principal_form.setVisible(false);
        med_registar_form.setVisible(false);
        med_relatorios_form.setVisible(false);
        med_conta_form.setVisible(false);
    }

    private void showRegistar() {
        med_registar_form.setVisible(true);
        med_consulta_form.setVisible(false);
        med_paciente_form.setVisible(false);
        med_principal_form.setVisible(false);
        med_relatorios_form.setVisible(false);
        med_conta_form.setVisible(false);
    }

    private void showRelatorios() {
        med_relatorios_form.setVisible(true);
        med_registar_form.setVisible(false);
        med_consulta_form.setVisible(false);
        med_paciente_form.setVisible(false);
        med_principal_form.setVisible(false);
        med_conta_form.setVisible(false);
    }

    private void showConta() {
        med_conta_form.setVisible(true);
        med_relatorios_form.setVisible(false);
        med_registar_form.setVisible(false);
        med_consulta_form.setVisible(false);
        med_paciente_form.setVisible(false);
        med_principal_form.setVisible(false);
    }



    public void sair() {
        //shutdownScheduler();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/javafx/hospital/Views/loginRegister-view.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alpha3 Login");
            stage.setResizable(false);
            stage.show();

            med_main.getScene().getWindow().hide();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao carregar a tela de login");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            med_principal.getStyleClass().add("selected");
            selectedButton = med_principal;
        });
    }
}
