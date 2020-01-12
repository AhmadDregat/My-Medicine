package com.example.mymedicine;

import com.example.mymedicine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Users {

    private String email, user, phone, password, permission;
    private Map<String, Product> carts;
    public Users() {
    }

    public Users(String mail, String user, String phone, String password, String permission) {
        this.email = mail;
        this.user = user;
        carts = new HashMap<>();
        this.phone = phone;
        this.password = password;
        this.permission = permission;

    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getpermission() {
        return this.permission;
    }

    public void permission(String permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getpassword() {
        return this.password;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<String, Product> getCarts() {
        return carts;
    }

    public void addToCarts(String num, Product cart) {
        carts.put(num, cart);
    }

}