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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HospitalController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private ComboBox<?> login_comboBox;

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
        List<String> list = new ArrayList<>();
        for (String lisd : Users.users) {
            list.add(lisd);
        }
        ObservableList listdata = FXCollections.observableList(list);
        login_comboBox.setItems(listdata);
        login_comboBox.getSelectionModel().selectFirst();

    }

    public void switchPge() throws IOException {
        if (login_comboBox.getSelectionModel().getSelectedItem().equals("Administrador")) {
            Parent root = FXMLLoader.load(getClass().getResource("loginRegister-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Alpha3 Login do Administrador");
            stage.show();

        } else if (login_comboBox.getSelectionModel().getSelectedItem().equals("Medico")) {
            Parent root = FXMLLoader.load(getClass().getResource("Doctor-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Alpha3 Login do Doctor");
            stage.show();
        }
        login_form.getScene().getWindow().hide();
    }

    public void loginAccount() throws SQLException {

        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alertMessage.errorMessage("Nome do usuario ou palavara passe incorreta");
        } else {
            String sql = "SELECT * FROM login_admin WHERE USERNAME = ? AND PASSWORD = ?";
            connection = DataBase.connectDB();

            if (!login_showPassword.isVisible()) {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_showPassword.setText(login_password.getText());
                }
            } else {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_password.setText(login_showPassword.getText());
                }
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login_username.getText());
            preparedStatement.setString(2, login_password.getText());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                login_name = resultSet.getString("FIRST_NAME") +" "+ resultSet.getString("LAST_NAME");
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMainForm-view.fxml")));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Portal do Administardor");
                    stage.show();
                    login_form.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                alertMessage.errorMessage("Nome do usuario ou palavara passe incorreta"); // Se imitira um alerta
            }

        }
    }

    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }


    //Metodo pra registar um Administardor
    /*   //Metodo para mudar de forma
    public void switchForm(ActionEvent event) {
        //Muda para Registar
        if (event.getSource() == login_inscreverAqui) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginAqui) { // Muda para Login
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }

    public void registerAccount() throws SQLException {
        if (register_email.getText().isEmpty()
                || register_password.getText().isEmpty()
                || register_username.getText().isEmpty()) {
            alertMessage.errorMessage("Por Favor Prencha os espacos vazios");
        } else {
            if (!register_showPassword.isVisible()) {
                if (!register_showPassword.getText().equals(register_password.getText())) {
                    register_showPassword.setText(register_password.getText());
                }
            } else {
                if (!register_showPassword.getText().equals(register_password.getText())) {
                    register_password.setText(register_showPassword.getText());
                }
            }

            String checkUserName = "SELECT * FROM login_admin WHERE USERNAME = ? ";
            connection = DataBase.connectDB();
            preparedStatement = connection.prepareStatement(checkUserName);
            preparedStatement.setString(1, register_username.getText());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                alertMessage.errorMessage("O nome do usuario ja existe: " + register_username.getText());
            } else if (register_password.getText().length() < 8) { //Verifica se o password < 8
                alertMessage.errorMessage("O tamanho do Password inavlido, minimo 8 caracters");
            } else {
                //Para Inserir um usuario a base de dados
                String insertData = "INSERT INTO login_admin (USERNAME,PASSWORD,EMAIL,CREAT_DATE) VALUES (?,?,?,?)";

                Date data = new Date();
                java.sql.Date sqlDate = new java.sql.Date(data.getTime());

                preparedStatement = connection.prepareStatement(insertData);
                preparedStatement.setString(1, register_username.getText());
                preparedStatement.setString(2, register_password.getText());
                preparedStatement.setString(3, register_email.getText());
                preparedStatement.setString(4, String.valueOf(sqlDate));
                preparedStatement.executeUpdate();

                alertMessage.successMessage("Usuario Registado com Sucesso");
                registerClear();
                //Muda a forma para login
                login_form.setVisible(true);
                register_form.setVisible(false);
            }
        }
    }

    public void registerClear() {
        register_password.clear();
        register_username.clear();
        register_showPassword.clear();
        register_email.clear();
    }


    public void registerShowPassword() {
        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_password.setVisible(false);
            register_showPassword.setVisible(true);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUsers();
    }
}