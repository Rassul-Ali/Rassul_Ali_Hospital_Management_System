package com.javafx.hospital.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tadmin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HospitalApplication.class.getResource("MedicoMainForm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Portal do Medico");
        stage.setScene(scene);
        stage.setMinWidth(1675);
        stage.setMinHeight(900);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.exit(1);
    }
}
