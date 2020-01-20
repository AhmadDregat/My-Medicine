package com.example.mymedicine.data_obj;

import java.util.HashMap;
import java.util.Map;

public class Doctor {

    private String email, name, license_id;
    private Map<String, String> mypats = new HashMap<>();

    public Doctor() {
    }

    public Doctor(String email, String name, String license_id) {
        this.email = email;
        this.name = name;
        this.license_id = license_id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense_id() {
        return license_id;
    }

    public void setLicense_id(String license_id) {
        this.license_id = license_id;
    }

    public Map<String, String> getMypats() {
        return mypats;
    }

    public void setMypats(Map<String, String> mypats) {
        this.mypats = mypats;
    }
}