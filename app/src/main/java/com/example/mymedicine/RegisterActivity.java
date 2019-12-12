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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailText, passwordText, nameText, phoneText;
    private Button registerBtn;
    private FirebaseAuth firebaseAuth;
    private TextView signinText;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef;
    private Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signinText = findViewById(R.id.signinText);
        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passText);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        registerBtn = findViewById(R.id.regButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arr=null;
                String email = emailText.getText().toString().trim();
                String pass = passwordText.getText().toString().trim();
                String name = nameText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                user = new Users(name, email, pass, phone,arr);
                if (email.isEmpty()) {
                    emailText.setError(" please enter email id");
                    emailText.requestFocus();
                } else if (pass.isEmpty()) {
                    passwordText.setError(" please enter passwordText");
                    passwordText.requestFocus();
                } else if (pass.isEmpty() && email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty! ", Toast.LENGTH_SHORT).show();
                }
                if (!(pass.isEmpty() && email.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Sign up Unsuccessful ,Please Try Again  ", Toast.LENGTH_SHORT).show();

                            } else {
                                startActivity(new Intent(RegisterActivity.this, first_Activity.class));
                            }
                        }
                    });
                    databaseRef = database.getReference("user");
                    databaseRef.setValue(user);
                } else {
                    Toast.makeText(RegisterActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();
                }

            }

        });
        // TextView
        String text = "Already have an account ? Sign in here ";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 26, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signinText.setText(ss);
        signinText.setMovementMethod(LinkMovementMethod.getInstance());
    }


}