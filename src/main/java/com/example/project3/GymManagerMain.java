package com.example.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Driver Class to run project 3
 * @author Sihua Zhou
 */
public class GymManagerMain extends Application {
    /**
     * Driver method to start stage and scene.
     * @param stage stage for display.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setResizable(false);
        stage.setTitle("Gym Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to start running project 3.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}