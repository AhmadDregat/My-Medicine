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

public class loginActivity extends AppCompatActivity {
    EditText Emailid , password ;
    Button login_Button;
    FirebaseAuth myfirebase ;
    TextView textView;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Emailid= findViewById(R.id.editText);
        password= findViewById(R.id.editText2);
        login_Button=  findViewById(R.id.button6);
         textView = findViewById(R.id.textView);
        myfirebase = FirebaseAuth.getInstance();

        mAuthStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= myfirebase.getCurrentUser();
                if(mFirebaseUser !=null ){
                    Toast.makeText(loginActivity.this, "your logged in ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(loginActivity.this,first_Activity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(loginActivity.this, "Please login  ",Toast.LENGTH_SHORT).show();

                }

            }
        };
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = Emailid.getText().toString();
                String pwd = password.getText().toString();
                if (mail.isEmpty()) {
                    Emailid.setError(" please enter email id");
                    Emailid.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError(" please enter password");
                    password.requestFocus();
                } else if ( pwd.isEmpty() && mail.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Fields Are Empty! ", Toast.LENGTH_SHORT).show();
                }
                if (!(pwd.isEmpty() && mail.isEmpty())) {
                    myfirebase.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(loginActivity.this, "Login Error , Please try again ", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent tohome = new Intent(loginActivity.this,first_Activity.class);
                                startActivity(tohome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(loginActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        String text = "Not registered ? sign up Here.";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 17,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onStart() {
        super.onStart();
        myfirebase.addAuthStateListener(mAuthStateListener);
    }
}