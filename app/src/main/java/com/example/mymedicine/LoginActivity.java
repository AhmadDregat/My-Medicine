package com.example.mymedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    Button loginBtn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView regText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passText);
        loginBtn = findViewById(R.id.loginButton);
        regText = findViewById(R.id.regText);
        firebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = LoginActivity.this.firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "your logged in ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please login  ", Toast.LENGTH_SHORT).show();

                }

            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = emailText.getText().toString();
                String pwd = passwordText.getText().toString();
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
                                Intent tohome = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(tohome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        String text = "Not registered ? sign up here.";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 17, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        regText.setText(ss);
        regText.setMovementMethod(LinkMovementMethod.getInstance());
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}