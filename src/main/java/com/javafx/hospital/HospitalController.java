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
    private ComboBox<String> login_comboBox;

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

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_email;

    @FXML
    private BorderPane register_form;

    @FXML
    private Hyperlink register_loginAqui;

    @FXML
    private PasswordField register_password;

    @FXML
    private Button register_registar;

    @FXML
    private TextField register_showPassword;

    @FXML
    private TextField register_username;

    //DataBase Variables
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private AlertMessage alertMessage = new AlertMessage();
    private Users users = new Users();

    protected static String login_name;

    public void addUsers() {
        try {
            // Cria uma cópia defensiva da lista de usuários
            ObservableList<String> userList = FXCollections.observableArrayList(Users.users);

            // Configura o ComboBox com a lista de usuários
            login_comboBox.setItems(userList);

            // Seleciona o primeiro item apenas se a lista não estiver vazia
            if (!userList.isEmpty()) {
                login_comboBox.getSelectionModel().selectFirst();
            } else {
                System.err.println("Aviso: Lista de usuários vazia");
                // Opcional: Mostrar alerta para o usuário
                // alertMessage.warningMessage("Nenhum tipo de usuário disponível");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            // Fallback: Limpa o ComboBox em caso de erro
            login_comboBox.setItems(FXCollections.emptyObservableList());
        }
    }

    public void switchPage() {
        try {
            String selectedRole = (String) login_comboBox.getSelectionModel().getSelectedItem();

            if (selectedRole == null) {
                alertMessage.errorMessage("Por favor, selecione um tipo de usuário");
                return;
            }

            String fxmlFile;
            String title;

            switch (selectedRole) {
                case "Administrador":
                    fxmlFile = "loginRegister-view.fxml";
                    title = "Alpha3 Login do Administrador";
                    break;
                case "Medico":
                    fxmlFile = "Doctor-view.fxml";
                    title = "Alpha3 Login do Doctor";
                    break;
                default:
                    alertMessage.errorMessage("Tipo de usuário não reconhecido");
                    return;
            }

            loadAndShowStage(fxmlFile, title);
            closeCurrentWindow();

        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao carregar a página: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
        String sql = "SELECT FIRST_NAME, LAST_NAME FROM login_admin WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection connection = DataBase.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, login_username.getText());
            preparedStatement.setString(2, login_password.getText());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    login_name = resultSet.getString("FIRST_NAME") + " " + resultSet.getString("LAST_NAME");
                    openAdminPortal();
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
            stage.show();
            login_form.getScene().getWindow().hide();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao abrir o portal do administrador");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUsers();
    }
}