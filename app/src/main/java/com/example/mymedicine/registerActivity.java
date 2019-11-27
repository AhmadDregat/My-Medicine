package com.example.mymedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class registerActivity extends AppCompatActivity {
    private EditText Emailid , password,name,phone ;
    private Button Register_Button;
    private FirebaseAuth myfirebase ;
    private TextView textView;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private Pations pat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView = findViewById(R.id.textView3);
        myfirebase = FirebaseAuth.getInstance();
        Emailid= findViewById(R.id.editText4);
        password= findViewById(R.id.editText2);
        name=findViewById(R.id.editText3);
        phone=findViewById(R.id.editText5);
        Register_Button=  findViewById(R.id.button);
        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = Emailid.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String thename = name.getText().toString().trim();
                String thephone = phone.getText().toString().trim();
                pat=new Pations(thename,mail,pwd,thephone);
                if (mail.isEmpty()) {
                    Emailid.setError(" please enter email id");
                    Emailid.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError(" please enter password");
                    password.requestFocus();
                }  else if ( pwd.isEmpty() && mail.isEmpty()) {
                    Toast.makeText(registerActivity.this, "Fields Are Empty! ", Toast.LENGTH_SHORT).show();
                }
                if (!(pwd.isEmpty() && mail.isEmpty())) {
                    myfirebase.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(registerActivity.this, "Sign up Unsuccessful ,Please Try Again  ", Toast.LENGTH_SHORT).show();

                            } else {
                                startActivity(new Intent(registerActivity.this, first_Activity.class));
                            }
                        }
                    });
                    myRef = database.getReference("user");
                    myRef.setValue(pat);
                }
                else{
                    Toast.makeText(registerActivity.this, "Error Ocurred! ", Toast.LENGTH_SHORT).show();
                }
              //  converttohash(pwd);

            }

        });
        // TextView
        String text = "Already have an account ? Sign in here ";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent( registerActivity.this,loginActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 26,38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
//    public void converttohash (String password){
//        try {
//            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
//            digest.update(password.getBytes());
//            byte messageDigest[] = digest.digest();
//            StringBuffer MD5Hash = new StringBuffer();
//            for (int i = 0; i <= messageDigest.length; i++) {
//                String h = Integer.toHexString(0XFF & messageDigest[i]);
//                while (h.length() < 2) {
//                    h = "0" + h;
//                    MD5Hash.append(h);
//                }
////              result.setText(MD5Hash);
//
//            }
//        }
//            catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//    }

}