package com.example.mymedicine;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymedicine.data_obj.Users;
import com.example.mymedicine.data_obj.Doctor;
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
    // UI
    private EditText emailText, passwordText, nameText, license_id;
    private Button registerBtn;
    private Switch isDocSwitch;
    private String singInText = "Already have an account ? Sign in here";
    private SpannableString ss = new SpannableString(singInText);
    // Firebase
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;
    // Data
    private String parentDbName = PAT_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        // UI
        setContentView(R.layout.activity_register);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passText);
        nameText = findViewById(R.id.nameText);
        isDocSwitch = findViewById(R.id.isDoc);
        registerBtn = findViewById(R.id.regButton);
        license_id = findViewById(R.id.license_id);

        ClickableSpan clickable = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickable, 26, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        isDocSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                {
                    registerBtn.setText("Register as a patient");
                    license_id.setEnabled(false);
                    parentDbName = PAT_DB;

                }
                else{
                    registerBtn.setText("Register as a doctor");
                    license_id.setEnabled(true);
                    parentDbName = DOC_DB;

                }
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get credentials
                if (isEmpty(emailText) || isEmpty(passwordText) || isEmpty(nameText)) {
                    Toast.makeText(RegisterActivity.this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    final String email = emailText.getText().toString().trim();
                    final String pass = passwordText.getText().toString().trim();
                    final String name = nameText.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Sign up Unsuccessful ,Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                if (isDocSwitch.isChecked()) {
                                    if (!isEmpty(license_id))
                                    {
                                        final String lic_doc = license_id.getText().toString().trim();
                                        Doctor doc = new Doctor(email,name, lic_doc);
                                        databaseRef = database.getReference(parentDbName);
                                        databaseRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(doc);
                                        Intent x = new Intent(RegisterActivity.this, DoctorActivity.class);
                                        startActivity(x);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Please provide your license ID", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Users user = new Users(email,name);
                                    databaseRef = database.getReference(parentDbName);
                                    databaseRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                                    Intent x = new Intent(RegisterActivity.this, SelectDoctor.class);
                                    startActivity(x);
                                    finish();
                                }
                            }

                        }
                    });
                }
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}