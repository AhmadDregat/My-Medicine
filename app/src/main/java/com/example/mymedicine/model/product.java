package com.example.mymedicine.model;

public class My_Cart {

    private String pid, name, price, description;

    public My_Cart(String pid, String name, String price, String description) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public String getPid() {
        return pid;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String description() {
        return description;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void description(String description) {this.description = description; }

}
