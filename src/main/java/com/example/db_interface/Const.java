package com.example.db_interface;

public class Const {
    public static final String CLIENT_TABLE = "client";
    public static final String CLIENT_ID = "id_client";
    public static final String CLIENT_FULLNAME = "full_name";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_LOGIN = "login";
    public static final String CLIENT_PASSWORD = "password";


    public static final String CLOTHING_TABLE = "clothing";
    public static final String CLOTHING_NAME = "name";
    public static final String CLOTHING_SIZE = "size";
    public static final String CLOTHING_SEASON = "season_id_season";
    public static final String CLOTHING_MATERIAL = "material_id_material";
    public static final String CLOTHING_COLOR = "color_id_color";
    public static final String CLOTHING_PRICE = "price_cl";
    public static final String CLOTHING_RATING = "rating";

    public static final String ORDER_TABLE = "order_of_client";
    public static final String ORDER_ID_ORDER = "id_order";
    public static final String DATE_OF_ORDER = "date_of_order";
    public static final String ORDER_PRICE = "price";
    public static final String ORDER_ID_CLIENT = "client_id_client";
    public static final String ORDER_EMPLOYEE = "employee_id_employee";
    public static final String ORDER_STATUS = "order_status_id_status";

    public static final String ORDER_LIST_TABLE = "order_list";
    public static final String NUMBER_OF_GOODS = "number_of_goods";
    public static final String NUMBER_OF_ORDER = "number_of_order";
    public static final String ORDER_LIST_ID_ORDER = "order_id_order";
    public static final String ORDER_CLIENT_ID_CLIENT = "order_client_id_client";
    public static final String ORDER_LIST_CLOTHING_ID_CLOTHING = "clothing_id_clothing";

}