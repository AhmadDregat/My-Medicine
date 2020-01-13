package com.example.mymedicine.model;

public class medicine {
    private String company ,name , Manufacturing_date, price ,Frequency_of_taking;

    public medicine(String company, String name, String manufacturing_date, String price, String frequency_of_taking) {
        this.company = company;
        this.name = name;
        Manufacturing_date = manufacturing_date;
        this.price = price;
        Frequency_of_taking = frequency_of_taking;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturing_date() {
        return Manufacturing_date;
    }

    public void setManufacturing_date(String manufacturing_date) {
        Manufacturing_date = manufacturing_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFrequency_of_taking() {
        return Frequency_of_taking;
    }

    public void setFrequency_of_taking(String frequency_of_taking) {
        Frequency_of_taking = frequency_of_taking;
    }
}
