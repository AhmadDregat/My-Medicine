package com.example.mymedicine.data_obj;

import java.io.Serializable;

public class Mersham implements Serializable {

    private Medicine med;
    private Users pat;
    private Doctor doc;
    private int freq_of_taking;

    public Mersham() {
    }

    public Mersham(Medicine med, Users pat, Doctor doc, int freq_of_taking) {
        this.med = med;
        this.pat = pat;
        this.doc = doc;
        this.freq_of_taking = freq_of_taking;

    }

    public Medicine getMed() {
        return med;
    }

    public void setMed(Medicine med) {
        this.med = med;
    }

    public Users getPat() {
        return pat;
    }

    public void setPat(Users pat) {
        this.pat = pat;
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public int getFreq_of_taking() {
        return freq_of_taking;
    }

    public void setFreq_of_taking(int freq_of_taking) {
        this.freq_of_taking = freq_of_taking;
    }

    @Override
    public String toString() {
        return "Mersham{" +
                "med=" + med +
                ", pat=" + pat +
                ", doc=" + doc +
                ", freq_of_taking=" + freq_of_taking +
                '}';
    }
}