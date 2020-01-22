package com.example.mymedicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mymedicine.data_obj.Doctor;
import com.example.mymedicine.data_obj.Medicine;
import com.example.mymedicine.data_obj.Mersham;
import com.example.mymedicine.data_obj.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private Medicine med;
    private Users user_sent;
    private Doctor current_doctor_object;
    private String current_doctor_uid;
    private String current_user_uid;

    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText Frequency_of_taking;

    private FirebaseAuth auth;
    private DatabaseReference doctor_db, user_db;
    private FirebaseUser current_user;


    private String frequencytaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        auth = FirebaseAuth.getInstance();
        current_user = auth.getCurrentUser();
        current_doctor_uid = current_user.getUid();

        user_db = FirebaseDatabase.getInstance().getReference().child("users");
        doctor_db = FirebaseDatabase.getInstance().getReference("Doctors");

        med = (Medicine) getIntent().getSerializableExtra("curr_med");
        System.out.println(med);
        user_sent = (Users) getIntent().getSerializableExtra("curr_user");
        System.out.println(user_sent);


        doctor_db.child(current_doctor_uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current_doctor_object = dataSnapshot.getValue(Doctor.class);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        user_db.orderByChild("user").equalTo(user_sent.getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Users> temp_map = (HashMap<String, Users>) dataSnapshot.getValue();
                Map.Entry<String, Users> entry = temp_map.entrySet().iterator().next();
                current_user_uid = entry.getKey();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });


        AddNewProductButton = findViewById(R.id.add_new_product);
        Frequency_of_taking = findViewById(R.id.Frequency_of);
        InputProductImage = findViewById(R.id.prod_img);

        Glide.with(getApplicationContext()).load(med.getImage()).into(InputProductImage);

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequencytaking = Frequency_of_taking.getText().toString().trim();

                Mersham mersham = new Mersham(med.getName(), user_sent.getUser(), current_doctor_object.getDoctor(), med.getPrice(), frequencytaking, med.getPid(),med.getImage());

                final DatabaseReference doc_ref_push = user_db.child(current_user_uid).child("mymeds");

                final Map<String, Object> mymeds = new HashMap<>();
                mymeds.put(med.getPid(), mersham);
                doc_ref_push.updateChildren(mymeds);

                Intent x = new Intent(AdminAddNewProductActivity.this, DoctorActivity.class);
                startActivity(x);
                finish();
            }
        });
    }
}