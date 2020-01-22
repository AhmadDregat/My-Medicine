package com.example.mymedicine.data_obj;

import java.io.Serializable;

public class Mersham implements Serializable {

    private String med;
    private String pat;
    private String doc;
    private String price;
    private String freq_of_taking;

    public Mersham() {
    }

    public Mersham(String med, String pat, String doc, String price, String freq_of_taking) {
        this.med = med;
        this.pat = pat;
        this.doc = doc;
        this.price = price;
        this.freq_of_taking = freq_of_taking;

    }

    @Override
    public String toString() {
        return "Mersham{" +
                "med='" + med + '\'' +
                ", pat='" + pat + '\'' +
                ", doc='" + doc + '\'' +
                ", freq_of_taking=" + freq_of_taking +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getFreq_of_taking() {
        return freq_of_taking;
    }

    public void setFreq_of_taking(String freq_of_taking) {
        this.freq_of_taking = freq_of_taking;
    }
}