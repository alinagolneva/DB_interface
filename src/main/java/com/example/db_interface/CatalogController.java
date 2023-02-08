package com.example.db_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CatalogController extends Configs {

    @FXML
    private ChoiceBox<?> choise;

    @FXML
    private Button goToCart;

    @FXML
    private Text id_goods;

    @FXML
    private Button searchButton1;

    @FXML
    private Text resultPants;

    @FXML
    private Text resultTshirt;

    @FXML
    private TextField search;

    @FXML
    private Button searchButton;

    @FXML
    private Text t1;


    Connection dbConnection;
    private ObservableList<ObservableList> data;

    @FXML
    private TableView tableview;

    @FXML
    void initialize() {
        ActionButton();
        t1.setVisible(false);
        id_goods.setVisible(false);
        buildData();

    }

    public void ActionButton() {
        goToCart.setOnAction(event -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("myOrder.fxml"));
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

        searchButton.setOnAction(event -> {String SearchText = search.getText().trim();
            if (!SearchText.equals("")) {
                SearchBy(SearchText);
            } else
                System.out.println("error");});
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void buildData() {

        data = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING ALL
            String SQL = "SELECT * from clothing";
            //ResultSet
            ResultSet rs = getDbConnection().createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableview.getColumns().addAll(col);
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    private void SearchBy(String name) {
        String[] words = name.split("\\s");
        DatabaseHandler dbHandler = new DatabaseHandler();
        Clothing clothing = new Clothing();
        if (words.length != 2) {
            t1.setText("Введите два параметра.");
            t1.setVisible(true);
            id_goods.setVisible(false);
        }
        else {
        clothing.setName(words[0]);
        clothing.setSize(words[1]);
        ResultSet result = dbHandler.getClothing(clothing);

            int counter = 0;

            while (true) {
                try {
                    if (!result.next()) break;
                    id_goods.setText(String.valueOf(result.getInt("id_clothing")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                counter++;
            }

            if (counter >= 1) {
                t1.setText("Данный товар есть в наличии, его id в каталоге -");
                t1.setVisible(true);
                id_goods.setVisible(true);

            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("searchFalse.fxml"));
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
        }
    }
}


