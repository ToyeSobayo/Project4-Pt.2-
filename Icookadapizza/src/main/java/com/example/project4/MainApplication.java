package com.example.project4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        try {
            var scene = createScene();
            setupStage(stage, scene);
        } catch (IOException e) {
            System.err.println("Error loading the FXML file: " + e.getMessage());
            // Handle exception or log it
        }
    }

    private Scene createScene() throws IOException {
        var fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-menu.fxml"));
        return new Scene(fxmlLoader.load(), 600, 400);
    }

    private void setupStage(Stage stage, Scene scene) {
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}