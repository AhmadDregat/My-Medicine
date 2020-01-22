package com.example.mymedicine.data_obj;

import java.io.Serializable;

public class Medicine implements Serializable {
    private String company, name, man_date, price, pid, image;

    public Medicine(){}

    public Medicine(String company, String name, String man_date, String price, String image, String pid){
        this.company = company;
        this.name = name;
        this.man_date = man_date;
        this.price = price;
        this.pid = pid;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", man_date='" + man_date + '\'' +
                ", price='" + price + '\'' +
                ", pid='" + pid + '\'' +
                ", image='" + image + '\'' +
                '}';
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

    public String getMan_date() {
        return man_date;
    }

    public void setMan_date(String man_date) {
        this.man_date = man_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
