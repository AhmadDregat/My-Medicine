package com.example.mymedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymedicine.data_obj.Medicine;
import com.example.mymedicine.data_obj.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllMeds extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference med_db;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser current_user;
    private Users current_user_object;
    private String current_uid;
    private String current_name;
    private Users user_sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meds);

        med_db = FirebaseDatabase.getInstance().getReference().child("Medicines");
        firebaseAuth = FirebaseAuth.getInstance();
        current_user = firebaseAuth.getCurrentUser();
        current_uid = current_user.getUid();

        recyclerView = findViewById(R.id.recycler_menu_meds);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        user_sent = (Users) getIntent().getSerializableExtra("user");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Medicine> options =
                new FirebaseRecyclerOptions.Builder<Medicine>()
                        .setQuery(med_db, Medicine.class)
                        .build();
        FirebaseRecyclerAdapter<Medicine, MedHandler> adapter =
                new FirebaseRecyclerAdapter<Medicine, MedHandler>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MedHandler holder, int position, @NonNull final Medicine model) {
                        holder.medName.setText(model.getName());
                        holder.selectMedBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // ADD THE DOC TO THE USER
                                Intent intent = new Intent(AllMeds.this, AdminAddNewProductActivity.class);
                                intent.putExtra("curr_med", model);
                                intent.putExtra("curr_user", user_sent);
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public MedHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_layout, parent, false);
                        MedHandler holder = new MedHandler(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}

