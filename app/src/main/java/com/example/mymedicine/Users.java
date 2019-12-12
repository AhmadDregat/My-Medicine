package com.example.mymedicine;

import java.util.ArrayList;
import java.util.Collections;

public class Users {
    private String name, email, password, phone;
    private ArrayList<String>med;

    public Users(String a, String b, String c, String d, ArrayList<String>arr) {
        name = a;
        email = b;
        password = c;
        phone = d;
         med = new ArrayList<String>(arr.size());
        for (String i : arr) {
            med.add(new String(i));
        }
        }
    public ArrayList<String> getMed() { return med; }

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
