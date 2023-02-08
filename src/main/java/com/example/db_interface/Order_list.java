package com.example.db_interface;

public class Order_list {
    private int number_of_order;
    private int number_of_goods;
    private int order_id_order;
    private int order_client_id_client;
    private int clothing_id_clothing;



    public Order_list(int id_cl, int kolvo) {
        this.clothing_id_clothing = id_cl;
        this.number_of_goods = kolvo;
    }

    public Order_list(int idClothing) {
        this.clothing_id_clothing = idClothing;
    }


    public int getNumber_of_order() {
        return number_of_order;
    }

    public void setNumber_of_order(int number_of_order) {
        this.number_of_order = number_of_order;
    }

    public int getNumber_of_goods() {
        return number_of_goods;
    }

    public void setNumber_of_goods(int number_of_goods) {
        this.number_of_goods = number_of_goods;
    }

    public int getOrder_id_order() {
        return order_id_order;
    }

    public void setOrder_id_order(int order_id_order) {
        this.order_id_order = order_id_order;
    }

    public int getOrder_client_id_client() {
        return order_client_id_client;
    }

    public void setOrder_client_id_client(int order_client_id_client) {
        this.order_client_id_client = order_client_id_client;
    }

    public int getClothing_id_clothing() {
        return clothing_id_clothing;
    }

    public void setClothing_id_clothing(int clothing_id_clothing) {
        this.clothing_id_clothing = clothing_id_clothing;
    }
}
