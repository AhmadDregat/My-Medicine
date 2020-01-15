package com.example.mymedicine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.mymedicine.model.MyCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toolbar;

public class DoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Doctors");
        user = auth.getCurrentUser();
        setDataView(myRef);
        NavigationView navigationView = findViewById(R.id.nav_view);
        MenuItem menuItem = navigationView.getMenu().getItem(2);
        menuItem.setVisible(true);
        menuItem = navigationView.getMenu().getItem(1);
        menuItem.setVisible(false);
        toolbar = findViewById(R.id.toolbar12);
        setActionBar(toolbar);
    //        menuItem.setVisible(true);
     //   drawer = findViewById(R.id.drawer_layout);
    }

    public void setDataView(DatabaseReference Ref) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users med = dataSnapshot.child(user.getUid()).getValue(Users.class);
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(DoctorActivity.this);
                View header = navigationView.getHeaderView(0);
                TextView text = (TextView) header.findViewById(R.id.username_nav);
                text.setText(med.getUser());
                text.setTextColor(Color.WHITE);
                TextView text2 = (TextView) header.findViewById(R.id.email_nav);
                text2.setText(med.getEmail());
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
            case R.id.nav_home:
                startActivity(new Intent(this, DoctorActivity.class));
                finish();
                break;
            case R.id.nav_admin:

                startActivity(new Intent(this, AdminCategoryActivity.class));
                finish();
                break;
            case R.id.nav_cart:

                startActivity(new Intent(this, MyCart.class));
                finish();
                break;
            case R.id.nav_signout:
                auth.signOut();

                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
      //  drawer.closeDrawer(GravityCompat.START);


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
