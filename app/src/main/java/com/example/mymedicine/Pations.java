package com.example.mymedicine;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pations {
    private String name,email,password,phone;
    public Pations(String a,String b,String c,String d){
        name=a;
        email=b;
        password=c;
        phone=d;
    }
    public String getname(){
        return name;
    }
    public String getemail(){
        return email;
    }

    public  String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String getpassword(){
        return getMd5(password);
    }
    public String getphone(){
        return phone;
    }
}
