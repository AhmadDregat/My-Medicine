package com.example.mymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class first_Activity extends AppCompatActivity {
    Button signout ;
    FirebaseAuth myfirebase ;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_);
        signout= findViewById(R.id.button1);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent X =new Intent(first_Activity.this,MainActivity.class);

                startActivity(X);

            }
        });

    }
}
