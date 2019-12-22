package com.example.mymedicine;

import com.example.mymedicine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Users {

    public Users()
    { }


    private String email, user, phone;
    private Map<String, Product> carts;

    public Users(String mail, String user, String phone) {
        this.email = mail;
        this.user = user;
        carts = new HashMap<>();
        this.phone = phone;
    }
    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUser() {
        return this.user;
    }

    public String getPhone() {
        return this.phone;
    }

    public Map<String, Product> getCarts() {
        return carts;
    }

    public void addToCarts(String num, Product cart) {
        carts.put(num, cart);
    }

//    public double totalPrice() {
//        double total = 0;
//        for (Product i : carts.values()) {
//            total += i.getPrice();
//        }
//        return total;
//    }
}