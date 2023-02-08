package com.example.db_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Clothing Shop");
        stage.setScene(scene);

        stage.setWidth(700);
        stage.setHeight(400);
        stage.setMinWidth(700);
        stage.setMinHeight(450);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}