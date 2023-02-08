package com.example.db_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.*;

public class OrderController extends DatabaseHandler {


        @FXML
        private TextField chioseord;

        @FXML
        private TextField countord;

        @FXML
        private Text check;

        @FXML
        private TextField delord;

        @FXML
        private Button minus;

        @FXML
        private Button plus;

        @FXML
        private Text sum;



        @FXML
        void initialize() {
                check.setVisible(false);
                plus.setOnAction(event -> {
                        addToCartGoods();

                });
                minus.setOnAction(event -> {
                        deleteToCartGoods();
                });
        }

        public void addToCartGoods() {
                DatabaseHandler dbHandler = new DatabaseHandler();
                String name = chioseord.getText();
                Clothing clothing = new Clothing();
                String regex = "\\d+";
                clothing.setName(name);
                if (getClothingOrd(clothing) && countord.getText().matches(regex)) {
                        check.setVisible(false);
                        int kolvo = Integer.valueOf(countord.getText());
                        Order_list order_list = new Order_list(getIDClothing(name), kolvo);
                        dbHandler.createOrderList(getIDClientOrder(), getIDOrder(), order_list);
                        String procName = "{call new_procedure()}";
                        CallableStatement cs = null;
                        int i = 0;
                        try {
                                cs = getDbConnection().prepareCall(procName);
                                i = cs.executeUpdate();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }
                        sum.setVisible(true);
                        sum.setText(String.valueOf(getSum(getIDOrder())));
                }
                else check.setVisible(true);
        }

        public void deleteToCartGoods() {
                DatabaseHandler dbHandler = new DatabaseHandler();
                String name = delord.getText();
                Clothing clothing = new Clothing();
                clothing.setName(name);
                Order_list order_list = new Order_list(getIDClothing(name));

                if (getClothingOrd(clothing)) {
                        check.setVisible(false);
                        dbHandler.deleteFromOrderList(getIDOrder(), order_list);
                        String procName = "{call new_procedure()}";
                        String proc = "{call new_id_status()}";
                        CallableStatement cs = null;
                        CallableStatement mn = null;
                        int i = 0;
                        int c = 0;
                        try {
                                cs = getDbConnection().prepareCall(procName);
                                mn = getDbConnection().prepareCall(proc);
                                i = cs.executeUpdate();
                                c = mn.executeUpdate();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }
                        sum.setVisible(true);
                        sum.setText(String.valueOf(getSum(getIDOrder())));
                }
                else check.setVisible(true);
        }

    }

