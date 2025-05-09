package com.javafx.hospital.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HospitalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HospitalApplication.class.getResource("/com/javafx/hospital/Views/loginRegister-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Alpha3 Login");
        stage.setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.exit(1);
    }
}