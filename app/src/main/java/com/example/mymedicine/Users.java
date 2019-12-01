package com.example.mymedicine;

public class Users {
    private String name, email, password, phone;

    public Users(String a, String b, String c, String d) {
        name = a;
        email = b;
        password = c;
        phone = d;
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
