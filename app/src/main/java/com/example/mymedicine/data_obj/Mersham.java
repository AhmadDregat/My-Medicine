package com.example.mymedicine.data_obj;

public class Mersham {

    private String name_of_med;
    private Users pat;
    private Doctor doc;
    private int freq_of_taking;

    public Mersham(String name_of_med, Users pat, Doctor doc, int freq_of_taking) {
        this.name_of_med = name_of_med;
        this.pat = pat;
        this.doc = doc;
        this.freq_of_taking = freq_of_taking;

    }

    public String getName_of_med() {
        return name_of_med;
    }

    public void setName_of_med(String name_of_med) {
        this.name_of_med = name_of_med;
    }

    public Users getPat() {
        return pat;
    }

    public void setPat(Users pat) {
        this.pat = pat;
    }

    public int getFreq_of_taking() {
        return freq_of_taking;
    }

    public void setFreq_of_taking(int freq_of_taking) {
        this.freq_of_taking = freq_of_taking;
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }
}