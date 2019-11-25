package com.example.mymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class registerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textView = findViewById(R.id.textView3);

        String text = "Already have an account ? Sign in here ";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                moveifuhaveaccuont();
            }
        };

        ss.setSpan(clickableSpan1, 26,38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);` `
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void moveifuhaveaccuont () {
        Intent intent = new Intent( registerActivity.this,loginActivity.class);
        startActivity(intent);
    }
}