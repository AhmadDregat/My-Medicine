package com.example.mymedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;

import com.example.mymedicine.model.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText, passwordText;
    private TextView restpassword;
    private Button loginBtn;
    private ProgressDialog loadingBar;

    private String parentDbName = "users";

    private  Users usersData;

   // private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference RootRef;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    // private Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passText);
        loginBtn = findViewById(R.id.loginButton);
        firebaseAuth = FirebaseAuth.getInstance();
        restpassword=findViewById(R.id.forget_password_link);
        RootRef= FirebaseDatabase.getInstance().getReference();
        loadingBar = new ProgressDialog(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = LoginActivity.this.firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    if(usersData.getpermission() == "users"){
                        Toast.makeText(LoginActivity.this, "your logged in ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, UserActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "your logged in ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, DoctorActivity.class);
                        startActivity(i);
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Please login  ", Toast.LENGTH_SHORT).show();

                }

            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = emailText.getText().toString();
                final String pwd = passwordText.getText().toString();
                if (mail.isEmpty()) {
                    emailText.setError(" please enter email id");
                    emailText.requestFocus();
                } else if (pwd.isEmpty()) {
                    passwordText.setError(" please enter your password");
                    passwordText.requestFocus();
                } else if (pwd.isEmpty() && mail.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields Are Empty! ", Toast.LENGTH_SHORT).show();
                }
                if (!(pwd.isEmpty() && mail.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Error , Please try again ", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                AllowAccessToAccount(mail, pwd);
                            }
                        }
                    });
                }
                else {Toast.makeText(LoginActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show(); }
            }
        });
    }

    private void AllowAccessToAccount(final String email, final String password) {
        RootRef= FirebaseDatabase.getInstance().getReference("users");

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(Users.class);
                if(dataSnapshot.child(parentDbName).child(email).exists()){
                     usersData = dataSnapshot.child(parentDbName).child(email).getValue(Users.class);

                    if(usersData.getEmail().equals(email)){
                        if (parentDbName.equals("Doctors")){
                            Toast.makeText(LoginActivity.this, "Welcome Doctor, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                            startActivity(intent);
                        }
                        else if(parentDbName.equals("users")){
                            Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Account with this " + email + " email do not exists.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}