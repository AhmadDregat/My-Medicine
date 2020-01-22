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

import com.example.mymedicine.data_obj.Mersham;
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

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    //variables
    private DrawerLayout drawer;
    private Toolbar toolbar;
    //firebase
    private FirebaseAuth auth;
    private DatabaseReference doctor_db, user_db;
    private FirebaseUser current_user;
    private String current_uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth = FirebaseAuth.getInstance();

        current_user = auth.getCurrentUser();
        current_uid = current_user.getUid();

        user_db = FirebaseDatabase.getInstance().getReference().child("users");
        doctor_db = FirebaseDatabase.getInstance().getReference("Doctors");

        setDataView(user_db);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        toolbar = findViewById(R.id.toolbar1);

        recyclerView = findViewById(R.id.recycler_menu_user);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Mersham> options =
                new FirebaseRecyclerOptions.Builder<Mersham>()
                        .setQuery(user_db.child(current_uid).child("mymeds"), Mersham.class)
                        .build();
        FirebaseRecyclerAdapter<Mersham, MershamHandler> adapter =
                new FirebaseRecyclerAdapter<Mersham, MershamHandler>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final MershamHandler holder, int position, @NonNull final Mersham model) {
                        System.out.println(model);
                        holder.merName.setText(model.getMed());
                        holder.merFreq.setText(model.getFreq_of_taking());
                        holder.merPrice.setText(model.getPrice());

                    }

                    @NonNull
                    @Override
                    public MershamHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mesham_layout, parent, false);
                        MershamHandler holder = new MershamHandler(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    public void setDataView(DatabaseReference Ref) {
        user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users current;
                current = dataSnapshot.child(current_uid).getValue(Users.class);
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(UserActivity.this);
                View header = navigationView.getHeaderView(0);

                TextView fullname = header.findViewById(R.id.username_nav);
                fullname.setText(current.getUser());
                fullname.setTextColor(Color.WHITE);

                TextView email = header.findViewById(R.id.email_nav);
                email.setText(current.getEmail());
                email.setTextColor(Color.WHITE);

                TextView doc_name = header.findViewById(R.id.doc_name);
                doc_name.setText(current.getMydoc());
                doc_name.setTextColor(Color.WHITE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
