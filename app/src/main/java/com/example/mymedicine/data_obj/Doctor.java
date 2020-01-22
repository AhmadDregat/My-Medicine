package com.example.mymedicine.data_obj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Doctor implements Serializable {

    public String email, doctor, license_id;
    public Map<String, Users> mypats = new HashMap<>();

    public Doctor() {
    }

    public Doctor(String email, String name, String license_id) {
        this.email = email;
        this.doctor = name;
        this.license_id = license_id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String name) {
        this.doctor = name;
    }

    public String getLicense_id() {
        return license_id;
    }

    public void setLicense_id(String license_id) {
        this.license_id = license_id;
    }

    public Map<String, Users> getMypats() {
        return mypats;
    }

    public void setMypats(Map<String, Users> mypats) {
        this.mypats = mypats;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "email='" + email + '\'' +
                ", doctor='" + doctor + '\'' +
                ", license_id='" + license_id + '\'' +
                ", mypats=" + mypats +
                '}';
    }
}