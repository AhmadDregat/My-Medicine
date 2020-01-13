package com.example.mymedicine.model;

public class Doctor_model {
    private String first_name,last_name ,Id ,phone ,email ;

    public Doctor_model(String first_name, String last_name, String id, String phone, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        Id = id;
        this.phone = phone;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
