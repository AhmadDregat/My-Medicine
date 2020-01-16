package com.example.mymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.net.URI;

public class OverTheCounterDrugsActivity extends AppCompatActivity {

    private ImageView pain_in_muscles, rhinitis;
    private ImageView cough, sore_throat;
    private ImageView fever, headache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_the_counter_drugs);

        pain_in_muscles = (ImageView) findViewById(R.id.pain_in_muscles);
        rhinitis = (ImageView) findViewById(R.id.rhinitis);
        cough = (ImageView) findViewById(R.id.cough);
        sore_throat = (ImageView) findViewById(R.id.sore_throat);
        fever = (ImageView) findViewById(R.id.fever);
        headache = (ImageView) findViewById(R.id.headache);

        final Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!920&pageSize=20&orderBy=0&pageNumber=1"));
        final Intent intent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!663&pageSize=20&orderBy=0&pageNumber=1"));
        final Intent intent3 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!983&pageSize=20&orderBy=0&pageNumber=1"));
        final Intent intent4 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!771&pageSize=20&orderBy=0&pageNumber=1"));
        final Intent intent5 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!801&pageSize=20&orderBy=0&pageNumber=1"));
        final Intent intent6 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.drugstore.co.il/otc-medications#/specFilters=400m!#-!716&pageSize=20&orderBy=0&pageNumber=1"));

        pain_in_muscles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1.putExtra("disease", "pain_in_muscles");
                startActivity(intent1);
            }
        });

        rhinitis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2.putExtra("disease", "rhinitis");
                startActivity(intent2);
            }
        });

        cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3.putExtra("disease", "cough");
                startActivity(intent3);
            }
        });

        sore_throat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent4.putExtra("disease", "sore_throat");
                startActivity(intent4);
            }
        });

        fever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent5.putExtra("disease", "fever");
                startActivity(intent5);
            }
        });

        headache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent6.putExtra("disease", "headache");
                startActivity(intent6);
            }
        });
    }
}