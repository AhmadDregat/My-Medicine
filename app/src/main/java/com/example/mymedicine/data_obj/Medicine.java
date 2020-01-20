package com.example.mymedicine.data_obj;

import java.net.MalformedURLException;
import java.net.URL;

public class Medicine {
    private String company, name, manufacturing_date, price, pid;
    private URL pic;

    public Medicine(String company, String name, String manufacturing_date, String price, String pic_link, String pid) throws MalformedURLException {
        this.company = company;
        this.name = name;
        this.manufacturing_date = manufacturing_date;
        this.price = price;
        this.pid = pid;
        this.pic = new URL(pic_link);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public URL getPic() {
        return pic;
    }

    public void setPic(URL pic) {
        this.pic = pic;
    }

    public String getManufacturing_date() {
        return manufacturing_date;
    }

    public void setManufacturing_date(String manufacturing_date) {
        this.manufacturing_date = manufacturing_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
