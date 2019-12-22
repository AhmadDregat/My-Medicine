package com.example.mymedicine.model;

public class Product {

    private String pid, name, description;
    private String  price ;
    public Product(){}
    public Product(String pid, String name, String description, String price) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
