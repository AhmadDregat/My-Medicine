package com.example.mymedicine;

import com.example.mymedicine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private Map<String, Product> carts;
    private String _email, _user, _phone;

    public Users() {
    }

    public Users(String mail, String user, String phone) {
        this._email = mail;
        this._user = user;
        carts = new HashMap<>();
        this._phone = phone;

    }

    public void setEmail(String mail) {
        this._email = mail;
    }

    public void setUser(String user) {
        this._user = user;
    }

    public void setPhone(String phone) {
        this._phone = phone;
    }

    public String getEmail() {
        return this._email;
    }

    public String getUser() {
        return this._user;
    }

    public String getPhone() {
        return this._phone;
    }

    public Map<String, Product> getCarts() {
        return carts;
    }

    public void addToCarts(String num, Product cart) {
        carts.put(num, cart);
    }

    public double totalPrice() {
        double total = 0;
        for (Product i : carts.values()) {
            total += i.getPrice();
        }
        return total;
    }
}