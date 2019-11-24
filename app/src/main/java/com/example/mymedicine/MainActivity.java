package com.example.mymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_Doctor, parent, Parmacist, drugs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_Doctor = findViewById(R.id.button1);
        parent = findViewById(R.id.button2);
        Parmacist = findViewById(R.id.button3);
        drugs = findViewById(R.id.button4);
        button_Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetologinactivity();
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetologinactivity();
            }
        });
        Parmacist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetologinactivity();
            }
        });
        drugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetologinactivity();
            }
        });

    }
    public void movetologinactivity () {
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);
    }
}
