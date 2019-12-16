package com.example.mymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MedicineActivity extends AppCompatActivity {
    private CheckBox akamol_1, akamol_2, dexamol,norfine,advil,ansoline,ledmismel,stripses,bionerca,optalgine;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef;
    private   ArrayList <My_Cart> meds_arr;
    private   My_Cart prod  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        akamol_1 = (CheckBox) findViewById(R.id.akamol_1);
        akamol_2 = (CheckBox) findViewById(R.id.akamol_2);
        dexamol = (CheckBox) findViewById(R.id.deksamol);
        norfine = (CheckBox) findViewById(R.id.norfine);
        advil = (CheckBox) findViewById(R.id.advil);
        ansoline = (CheckBox) findViewById(R.id.ansoline);
        ledmismel = (CheckBox) findViewById(R.id.ledmismel);
        stripses = (CheckBox) findViewById(R.id.stripses);
        bionerca = (CheckBox) findViewById(R.id.bionerca);
        optalgine = (CheckBox) findViewById(R.id.optalgin);
        firebaseAuth = FirebaseAuth.getInstance();

        if(akamol_1.isChecked())
        {
            prod=new My_Cart("a","akamol_1","12"," aa");
           meds_arr.add(prod);
        }
        if(akamol_2.isChecked())
        {
            prod=new My_Cart("b","akamol_2","123","bb");
            meds_arr.add(prod);
        }
        if(dexamol.isChecked())
        {
            prod=new My_Cart("c","dexamol","32","as");
            meds_arr.add(prod);
        }
        if(norfine.isChecked())
        {
            prod=new My_Cart("norfine","norfine","21","sa");
            meds_arr.add(prod);
        }
        if(advil.isChecked())
        {
            prod=new My_Cart("d","advil","21","cs");
            meds_arr.add(prod);
        }
        if(ansoline.isChecked())
        {
            prod=new My_Cart("f","ansoline","23","sa");
            meds_arr.add(prod);
        }
        if(ledmismel.isChecked())
        {
            prod=new My_Cart("g","ledmimsmel","22","ss");
            meds_arr.add(prod);
        }
        if(stripses.isChecked())
        {
            prod=new My_Cart("h","stripsess","233","asa");
            meds_arr.add(prod);
        }
        if(bionerca.isChecked())
        {
            prod=new My_Cart("i","bionerca","321","fdf");
            meds_arr.add(prod);
        }
        if(optalgine.isChecked())
        {
            prod=new My_Cart("k","optalgine","32","sada");
            meds_arr.add(prod);
        }
    }

    public void SaveTODataBase(View view) {
        databaseRef = database.getReference().child("Users");
        databaseRef.setValue(prod);
       // Intent tohome = new Intent(MedicineActivity.this, first_Activity.class);
        //startActivity(tohome);

    }
}
