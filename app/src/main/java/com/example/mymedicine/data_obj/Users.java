package com.example.mymedicine.data_obj;

import java.util.HashMap;
import java.util.Map;

public class Users {

    private String email, user;
    private String mydoc = null;
    private Map<String, Medicine> mymeds;
    public Users(){}
    public Users(String mail, String user) {
        this.email = mail;
        this.user = user;
        mymeds = new HashMap<>();
    }

    public String getMydoc() {
        return mydoc;
    }

    public void setMydoc(String mydoc) {
        this.mydoc = mydoc;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, Medicine> getCarts() {
        return mymeds;
    }

    public void addToCarts(String num, Medicine cart) {
        mymeds.put(num, cart);
    }

}