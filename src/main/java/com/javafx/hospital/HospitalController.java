package com.javafx.hospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HospitalController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private BorderPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private TextField login_username;

    @FXML
    private StackPane main_form;

    //DataBase Variables
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private AlertMessage alertMessage = new AlertMessage();

    protected static String login_name;


    private void loadAndShowStage(String fxmlFile, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    private void closeCurrentWindow() {
        ((Stage) login_form.getScene().getWindow()).close();
    }

    public void loginAccount() throws SQLException {
        // Validação de campos vazios
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alertMessage.errorMessage("Nome de usuário ou senha incorreta");
            return;
        }

        // Sincronização entre campos de senha visível/invisível
        if (!login_showPassword.isVisible()) {
            if (!login_showPassword.getText().equals(login_password.getText())) {
                login_showPassword.setText(login_password.getText());
            }
        } else {
            if (!login_showPassword.getText().equals(login_password.getText())) {
                login_password.setText(login_showPassword.getText());
            }
        }

        // Autenticação
        String sql = "SELECT FIRST_NAME, LAST_NAME, CATEGORY FROM login WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection connection = DataBase.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, login_username.getText());
            preparedStatement.setString(2, login_password.getText());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    login_name = resultSet.getString("FIRST_NAME") + " " + resultSet.getString("LAST_NAME");
                    switch (resultSet.getString("CATEGORY")){
                        case "Administrador":
                            openAdminPortal();
                            break;
                        case "Medico":
                            openMedicoPortal();
                            break;

                    }

                } else {
                    alertMessage.errorMessage("Nome de usuário ou senha incorreta");
                }
            }
        } catch (SQLException e) {
            alertMessage.errorMessage("Erro ao conectar com o banco de dados");
            throw e;
        }
    }

    public void loginShowPassword() {
        try {
            boolean showPassword = login_checkBox.isSelected();

            // Garante que ambos campos tenham o mesmo valor antes de alternar
            String currentPassword = showPassword
                    ? login_password.getText()
                    : login_showPassword.getText();

            // Atualiza ambos campos para manter sincronização
            login_showPassword.setText(currentPassword);
            login_password.setText(currentPassword);

            // Alterna visibilidade
            login_showPassword.setVisible(showPassword);
            login_password.setVisible(!showPassword);

            // Foca no campo visível para melhor UX
            if (showPassword) {
                login_showPassword.requestFocus();
            } else {
                login_password.requestFocus();
            }

        } catch (Exception e) {
            System.err.println("Erro ao alternar visibilidade da senha: " + e.getMessage());
            // Fallback: mostra ambos campos em caso de erro
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    private void openAdminPortal() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMainForm-view.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Portal do Administrador");
            stage.setMinWidth(1675);
            stage.setMinHeight(900);
            stage.setMaximized(true);
            stage.show();
            closeCurrentWindow();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao abrir o portal do administrador");
            e.printStackTrace();
        }
    }

    private void openMedicoPortal() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MedicoMainPortal.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Portal do Medico");
            stage.setMinWidth(1675);
            stage.setMinHeight(900);
            stage.setMaximized(true);
            stage.show();
            closeCurrentWindow();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao abrir o portal do Medico");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}