package com.example.db_interface;

public class Client {
    private String fullname;
    private String address;
    private String login;
    private String password;
    private int id_client;

    public Client(String fullname, String address, String login, String password) {
        this.fullname = fullname;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public Client() {

    }

    public int getID() {return id_client;}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
