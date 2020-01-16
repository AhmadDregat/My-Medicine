package com.example.mymedicine;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    // CONSTANTS
    private static final String DOC_DB = "Doctors";
    private static final String PAT_DB = "users";
    boolean isDoc = false;

    // UI
    private EditText emailText, passwordText, nameText, phoneText;
    private Button registerBtn;
    private TextView DoctorLink, PatientLink;
    private String singInText = "Already have an account ? Sign in here ";
    private SpannableString ss = new SpannableString(singInText);

    // Firebase
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;

    // Data
    private Users user;
    private String perm = "users";
    private String parentDbName = PAT_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passText);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        DoctorLink = findViewById(R.id.doctor_panel_link);
        PatientLink = findViewById(R.id.Patient_panel_link);
        registerBtn = findViewById(R.id.regButton);

        // Behavior
        DoctorLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtn.setText("Register");
                DoctorLink.setVisibility(View.INVISIBLE);
                PatientLink.setVisibility(View.VISIBLE);
                parentDbName = DOC_DB;
                isDoc = true;
                perm = "Doctors";
            }
        });

        PatientLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtn.setText("Register");
                DoctorLink.setVisibility(View.VISIBLE);
                PatientLink.setVisibility(View.INVISIBLE);
                parentDbName = PAT_DB;
                isDoc = false;
                perm = "users";
            }
        });

        ClickableSpan clickable = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickable, 26, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get credentials
                String email = emailText.getText().toString().trim();
                String pass = passwordText.getText().toString().trim();
                String name = nameText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                user = new Users(email, name, phone, pass, perm);
                if (email.isEmpty()) {
                    emailText.setError(" please enter an email");
                    emailText.requestFocus();
                } else if (pass.isEmpty()) {
                    passwordText.setError(" please enter PassWord");
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
                                databaseRef = database.getReference(parentDbName);
                                databaseRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                                if (isDoc) {
                                    Intent x = new Intent(RegisterActivity.this, DoctorActivity.class);
                                    startActivity(x);
                                    finish();
                                } else {
                                    Intent x = new Intent(RegisterActivity.this, UserActivity.class);
                                    startActivity(x);
                                    finish();
                                }
                            }

                        }
                    });

                } else {
                    Toast.makeText(RegisterActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}