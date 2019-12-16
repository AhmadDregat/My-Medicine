package com.example.mymedicine;

import java.util.ArrayList;
import java.util.Collections;

public class Users {
    private String name, email, password, phone;
    private My_Cart product;

    public Users(String name, String email, String password, String phone, My_Cart product) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.product = product;
    }

    public My_Cart getProduct() {
        return product;
    }

    public String getname() {
        return name;
    }

    public String getemail() {
        return email;
    }

    public String getpassword() {
        return password;
    }

    public String getphone() {
        return phone;
    }
}
