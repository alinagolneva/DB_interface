package com.example.db_interface;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.*;
import java.util.Calendar;
import java.util.Objects;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public int signUpUser(Client client) {

        String insert = "INSERT INTO " + Const.CLIENT_TABLE + "(" +
                Const.CLIENT_FULLNAME + "," + Const.CLIENT_ADDRESS + " , " +
                Const.CLIENT_LOGIN + ", " + Const.CLIENT_PASSWORD + ")"
                + "VALUES (?, ?, ?, ?)";

        try {
            int id_user = -1;
            PreparedStatement prSt = getDbConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            prSt.setString(1, client.getFullname());
            prSt.setString(2, client.getAddress());
            prSt.setString(3, client.getLogin());
            prSt.setString(4, client.getPassword());
            prSt.executeUpdate();
            ResultSet rs = prSt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                 System.out.println(id_user);
                 return id_user = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet getClient(Client client) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " +
                Const.CLIENT_LOGIN + "=? AND " + Const.CLIENT_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, client.getLogin());
            prSt.setString(2, client.getPassword());
             resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getIDClient(client.getLogin());

        return resSet;
    }

    public int getIDClient(String login) {
        try {
            //SQL FOR SELECTING ALL
            String SQL = "SELECT login from client";
            //ResultSet
            ResultSet rs = getDbConnection().createStatement().executeQuery(SQL);
            int count = 0;
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    count++;
                    //Iterate Column
                    if (Objects.equals(login, rs.getString(i)))
                    {
                        return count;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return 0;
    }

    public int getIDClothing(String name) {
        try {
            //SQL FOR SELECTING ALL
            String SQL = "SELECT name from clothing";
            //ResultSet
            ResultSet rs = getDbConnection().createStatement().executeQuery(SQL);
            int count = 0;
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    count++;

                    //Iterate Column
                    if (name.equalsIgnoreCase(rs.getString(i)))
                    {
                        return count;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return 0;
    }

    public int getIDOrder() {
        try {
            //SQL FOR SELECTING ALL
            String SQL = "SELECT * from order_of_client";
            //ResultSet
            ResultSet rs = getDbConnection().createStatement().executeQuery(SQL);
            int count = 0;
            while (rs.next()) {
                count++;
            }
            System.out.println(count);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return 0;
    }

    public int getIDClientOrder() {
        int count = 0;
        try {
            //SQL FOR SELECTING ALL
            String SQL = "SELECT * from order_of_client";
            //ResultSet
            ResultSet rs = getDbConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        String SQL1 = "SELECT client_id_client from order_of_client WHERE id_order = " +  count + " ;";
        ResultSet rs2 = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(SQL1);
            rs2 = prSt.executeQuery();
            if (rs2 != null && rs2.next()) {
                System.out.println(rs2.getInt(1));
                return rs2.getInt(1);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Date getNowDateToDate(){
        String date = "";
        Calendar calendar = Calendar.getInstance();

        date += calendar.get(Calendar.YEAR)+"-";
        if ((calendar.get(Calendar.MONTH)+1)<10){
            date += "0" + (calendar.get(Calendar.MONTH)+1);
        }else {
            date += (calendar.get(Calendar.MONTH)+1);
        }
        if (calendar.get(Calendar.DAY_OF_MONTH)<10){
            date += "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        }else{
            date += "-" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        return Date.valueOf(date);
    }

    public int createOrder(String login){
        String insertOrd = "INSERT INTO " + Const.ORDER_TABLE + "(" +
                Const.ORDER_ID_CLIENT + " , " + Const.DATE_OF_ORDER + " , " + Const.ORDER_EMPLOYEE + " , "
                + Const.ORDER_STATUS
                + " ) VALUES (? , ? , ? , ?)";
        try {
            int id_order = -1;
            PreparedStatement pS = getDbConnection().prepareStatement(insertOrd, Statement.RETURN_GENERATED_KEYS);
            pS.setInt(1, getIDClient(login));
            pS.setObject (2 , getNowDateToDate());
            pS.setInt(3,1);
            pS.setInt(4,1);
            pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return id_order = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createOrderList(int ClientId, int OrderId, Order_list order_list){
        String insertOrderList = "INSERT INTO " + Const.ORDER_LIST_TABLE + "(" +
                Const.ORDER_LIST_ID_ORDER + " , " + Const.ORDER_CLIENT_ID_CLIENT + " , "
                + Const.ORDER_LIST_CLOTHING_ID_CLOTHING + "," + Const.NUMBER_OF_GOODS + " ) VALUES (? , ? , ? , ?)";
        try {
            int id_order_list = -1;
            PreparedStatement pS = getDbConnection().prepareStatement(insertOrderList, Statement.RETURN_GENERATED_KEYS);
            pS.setInt(1, OrderId);
            pS.setInt(2, ClientId);
            pS.setInt(3, order_list.getClothing_id_clothing());
            pS.setInt(4, order_list.getNumber_of_goods());
            pS.executeUpdate();
            ResultSet rs = pS.getGeneratedKeys();
            if (rs != null && rs.next()) {
                System.out.println(id_order_list);
                return id_order_list = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteFromOrderList(int OrderId, Order_list order_list){
        String deleteOrderList = "DELETE FROM " + Const.ORDER_LIST_TABLE + " WHERE " +
                Const.ORDER_LIST_ID_ORDER + " = " + OrderId + " AND " + Const.ORDER_LIST_CLOTHING_ID_CLOTHING
                + " = " + order_list.getClothing_id_clothing() + " ;" ;

        int i = 0;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(deleteOrderList);
            i = prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getClothing(Clothing clothing) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.CLOTHING_TABLE + " WHERE " +
                Const.CLOTHING_NAME + " LIKE '%" + clothing.getName() +"%' " + " AND "
                + Const.CLOTHING_SIZE + " LIKE '%" + clothing.getSize()+ "%'" ;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            /*prSt.setString(1, clothing.getName());
            prSt.setString(2, clothing.getSize());*/
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public int getSum(int idOrder) {
        ResultSet resSet = null;

        String select = "SELECT price FROM " + Const.ORDER_TABLE + " WHERE " +
                Const.ORDER_ID_ORDER + " = " + idOrder ;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            if (resSet != null && resSet.next()) {
                return resSet.getInt("price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       return 0;
    }


    public boolean getClothingOrd(Clothing clothing) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.CLOTHING_TABLE + " WHERE " +
                Const.CLOTHING_NAME + " LIKE '%" + clothing.getName() +"%' ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            if (resSet != null && resSet.next()) {
                if (resSet.getString("name").equalsIgnoreCase(clothing.getName())) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return false;
    }


}
