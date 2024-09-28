package com.example.pixelinterpolation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PixelInterpolation extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PixelInterpolation.class.getResource("pixel-interpolation.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Pixel Interpolation");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}