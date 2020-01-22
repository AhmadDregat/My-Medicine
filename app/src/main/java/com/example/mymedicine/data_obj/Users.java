package com.example.mymedicine.data_obj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Users implements Serializable {

    private String email, user;
    private String mydoc = null;
    private Map<String, Medicine> mymeds;

    public Users() {
    }

    public Users(String mail, String user) {
        this.email = mail;
        this.user = user;
        mymeds = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", mydoc='" + mydoc + '\'' +
                ", mymeds=" + mymeds +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMydoc() {
        return mydoc;
    }

    public void setMydoc(String mydoc) {
        this.mydoc = mydoc;
    }

    public Map<String, Medicine> getMymeds() {
        return mymeds;
    }

    public void setMymeds(Map<String, Medicine> mymeds) {
        this.mymeds = mymeds;
    }
}