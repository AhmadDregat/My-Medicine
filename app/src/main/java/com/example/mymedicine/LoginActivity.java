package com.example.mymedicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymedicine.data_obj.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText, passwordText;
    private TextView restpassword;
    private Button loginBtn;

    private String parentDbName = "users";

    private Users usersData;

    // private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference doctor_database;
    private DatabaseReference user_database;
    private String user_uid;

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

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = LoginActivity.this.firebaseAuth.getCurrentUser();
//                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    System.out.println("failed here");

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
                            } else {
                                user = firebaseAuth.getCurrentUser();
                                user_uid = user.getUid();
                                doctor_database = FirebaseDatabase.getInstance().getReference().child("Doctors");
                                user_database = FirebaseDatabase.getInstance().getReference("users");
                                user_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.child(user_uid).exists()) {
                                            System.out.println("imhere2");
                                            Intent x = new Intent(LoginActivity.this, UserActivity.class);
                                            startActivity(x);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                doctor_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.child(user_uid).exists()) {
                                            System.out.println("imhere1");
                                            Intent x = new Intent(LoginActivity.this, DoctorActivity.class);
                                            startActivity(x);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}