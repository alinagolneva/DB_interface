package com.example.db_interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController extends DatabaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField loginfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    void initialize() {
        assert authSignInButton != null : "fx:id=\"authSignInButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert loginSignUpButton != null : "fx:id=\"loginSignUpButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert loginfield != null : "fx:id=\"loginfield\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert passwordfield != null : "fx:id=\"passwordfield\" was not injected: check your FXML file 'hello-view.fxml'.";

        authSignInButton.setOnAction(event -> {
                    String loginText = loginfield.getText().trim();
                    String loginPassword = passwordfield.getText().trim();

                    if (!loginText.equals("") && !loginPassword.equals(""))
                        loginUser(loginText,loginPassword);
                    else
                        System.out.println("Login and password is empty");
                });

        loginSignUpButton.setOnAction(event -> {

            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("authRegistr.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

    private void loginUser(String loginText, String loginPassword) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        Client client = new Client();
        client.setLogin(loginText);
        client.setPassword(loginPassword);
        ResultSet result = dbHandler.getClient(client);
        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }

        if (counter >=1) {
            createOrder(client.getLogin());
            loginSignUpButton.getScene().getWindow().hide();

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
            stage.showAndWait();
        }
        else {
            Shake clientLoginAnim = new Shake(loginfield);
            Shake clientPasswAnim = new Shake(passwordfield);
            clientLoginAnim.playAnim();
            clientPasswAnim.playAnim();
        }
        }
    }

