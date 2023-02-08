package com.example.db_interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrController {

    @FXML
    private Button registerButton;

    @FXML
    private TextField setAddress;

    @FXML
    private TextField setLogin;

    @FXML
    private TextField setName;

    @FXML
    private PasswordField setPassword;

    @FXML
    void initialize() {

        registerButton.setOnAction(event -> {
            registerNewClient();
            Stage stage1 = (Stage) registerButton.getScene().getWindow();
            stage1.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("authSign.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void registerNewClient() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        String fullname = setName.getText();
        String address = setAddress.getText();
        String login = setLogin.getText();
        String password = setPassword.getText();

        Client client = new Client(fullname, address, login, password);
        dbHandler.signUpUser(client);
        dbHandler.createOrder(client.getLogin());
    }
}