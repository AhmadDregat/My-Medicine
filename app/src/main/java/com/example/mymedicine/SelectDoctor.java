package com.example.mymedicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedicine.data_obj.Doctor;
import com.example.mymedicine.data_obj.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SelectDoctor extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference doctor_database, user_database;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser current_user;
    private Users current_user_object;
    private String current_uid;
    private String current_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdoc);

        doctor_database = FirebaseDatabase.getInstance().getReference().child("Doctors");
        user_database = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();
        current_user = firebaseAuth.getCurrentUser();
        current_uid = current_user.getUid();

        recyclerView = findViewById(R.id.recycler_menu_doctors);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Doctor> options =
                new FirebaseRecyclerOptions.Builder<Doctor>()
                        .setQuery(doctor_database, Doctor.class)
                        .build();
        FirebaseRecyclerAdapter<Doctor, DoctorHandler> adapter =
                new FirebaseRecyclerAdapter<Doctor, DoctorHandler>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DoctorHandler holder, int position, @NonNull final Doctor model) {
                        holder.doctor_name.setText(model.getDoctor());
                        holder.select_doc.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // ADD THE DOC TO THE USER
                                doctor_database.orderByChild("doctor").equalTo(model.getDoctor()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            String uid = ds.getKey();
                                            final DatabaseReference doc_ref_push = doctor_database.child(uid).child("mypats");
                                            final Map<String, Object> pat_map = new HashMap<>();
                                            user_database.child(current_uid).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    current_user_object = dataSnapshot.getValue(Users.class);
                                                    pat_map.put(current_uid, current_user_object);
                                                    doc_ref_push.updateChildren(pat_map);
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                }
                                            });
                                        }
                                    }


                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }

                                });
                                user_database.child(current_user.getUid()).child("mydoc").setValue(model.getDoctor());
                                Intent intent = new Intent(SelectDoctor.this, UserActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public DoctorHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_layout, parent, false);
                        DoctorHandler holder = new DoctorHandler(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
