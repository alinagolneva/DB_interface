package com.example.db_interface;

public class Order_of_client {
    private int id_order;
    private String date_of_order;
    private int price;
    private int client_id_client;
    private int employee_id_employee;
    private int order_status_id_status;


    public Order_of_client() {
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(String date_of_order) {
        this.date_of_order = date_of_order;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getClient_id_client() {
        return client_id_client;
    }

    public void setClient_id_client(int client_id_client) {
        this.client_id_client = client_id_client;
    }

    public int getEmployee_id_employee() {
        return employee_id_employee;
    }

    public void setEmployee_id_employee(int employee_id_employee) {
        this.employee_id_employee = employee_id_employee;
    }

    public int getOrder_status_id_status() {
        return order_status_id_status;
    }

    public void setOrder_status_id_status(int order_status_id_status) {
        this.order_status_id_status = order_status_id_status;
    }
}