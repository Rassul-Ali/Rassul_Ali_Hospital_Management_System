package com.javafx.hospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class DoctorPageController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private ComboBox<?> login_comboBox;

    @FXML
    private TextField login_doctorID;

    @FXML
    private BorderPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private StackPane main_form;


    private Users users = new Users();
    private AlertMessage alertMessage = new AlertMessage();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void addUsers() {
        List<String> list = new ArrayList<>();
        for (String lisd : Users.users) {
            list.add(lisd);
        }
        ObservableList listdata = FXCollections.observableList(list);
        login_comboBox.setItems(listdata);
        login_comboBox.getSelectionModel().select(list.indexOf("Medico"));
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
            stage.setTitle("Alpha3 Login do Medico");
            stage.show();
        }
        login_form.getScene().getWindow().hide();
    }

    public void loginAccount(ActionEvent event) throws SQLException, IOException {
        if (login_doctorID.getText().isEmpty() || login_password.getText().isEmpty()) {
            alertMessage.errorMessage("ID ou palavara passe incorreta");
        } else {
            if (!login_showPassword.isVisible()) {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_showPassword.setText(login_password.getText());
                }
            } else {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_password.setText(login_showPassword.getText());
                }
            }

            connection = DataBase.connectDB();
            String sql = "SELECT * FROM doctor WHERE DOCTOR_ID = ? AND PASSWORD = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login_doctorID.getText());
            preparedStatement.setString(2, login_password.getText());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //foi correta imitra uma alerta
                alertMessage.successMessage("Login com Sucesso");
            } else {
                alertMessage.errorMessage("Nome do usuario ou palavara passe incorreta"); // Se imitira um alerta
            }
        }
    }

    public void loginShowPassword(){
        if(login_checkBox.isSelected()){
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        }else{
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUsers();
    }
}
