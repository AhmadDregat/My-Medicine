package com.example.mymedicine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedicine.data_obj.Doctor;
import com.example.mymedicine.data_obj.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    private FirebaseAuth auth;
    private DatabaseReference doctor_db, user_db;
    private FirebaseUser current_user;

    private String current_uid;
    private String current_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        auth = FirebaseAuth.getInstance();

        current_user = auth.getCurrentUser();
        current_uid = current_user.getUid();

        user_db = FirebaseDatabase.getInstance().getReference().child("users");
        doctor_db = FirebaseDatabase.getInstance().getReference("Doctors");

        setDataView(doctor_db);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        toolbar = findViewById(R.id.toolbar12);
        setActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(user_db, Users.class)
                        .build();
        FirebaseRecyclerAdapter<Users, UserHandler> adapter =
                new FirebaseRecyclerAdapter<Users, UserHandler>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final UserHandler holder, int position, @NonNull final Users model) {
                        doctor_db.child(current_uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Doctor doc = dataSnapshot.getValue(Doctor.class);
                                Map<String, String> mypats = doc.getMypats();
                                // TODO
                                holder.userName.setText(model.getUser());
                                holder.emailuser.setText(model.getEmail());
                                holder.addnew.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(DoctorActivity.this, AdminCategoryActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public UserHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
                        UserHandler holder = new UserHandler(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    public void setDataView(DatabaseReference Ref) {
        doctor_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Doctor current_user_object = dataSnapshot.child(current_uid).getValue(Doctor.class);
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(DoctorActivity.this);
                View header = navigationView.getHeaderView(0);

                TextView text = header.findViewById(R.id.doctorname_nav);
                text.setText(current_user_object.getName());
                text.setTextColor(Color.WHITE);

                TextView text2 = header.findViewById(R.id.emaildoc_nav);
                text2.setText(current_user_object.getEmail());
                text2.setTextColor(Color.WHITE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_signout:
                auth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
