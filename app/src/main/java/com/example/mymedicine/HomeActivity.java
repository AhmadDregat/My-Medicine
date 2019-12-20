package com.example.mymedicine;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.mymedicine.R;
import com.example.mymedicine.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public HomeActivity(){}
   private Button addTocard, addTocard1;
    private FirebaseAuth auth;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        addTocard = findViewById(R.id.addtocard_btn);
        addTocard1 = findViewById(R.id.addtocard_btn2);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user.getUid().equals("WQhWwZThuYdFIRta2jOcnXtku0p2")){
            MenuItem menuItem = navigationView.getMenu().getItem(2);
            menuItem.setVisible(true);
        }
        myRef = database.getReference("Client");
        setDataView(myRef);
//        toolbar =findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        addTocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "add to card!", Toast.LENGTH_LONG).show();

                myRef.child(user.getUid()).child("carts").child("akamol_mid").setValue(new Product("akamol_mid", null,null,50.0)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Stuff added to your card", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Stuff already on your card", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        addTocard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "add to card!", Toast.LENGTH_LONG).show();
                myRef.child(user.getUid()).child("carts").child("ansolin_mid").setValue(new Product("ansolin_mid", null,null,50.0)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Stuff added to your card", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Stuff already on your card", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_home:

                startActivity(new Intent(this, LoginActivity.class));
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
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    public void setDataView(DatabaseReference Ref){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users med = dataSnapshot.child(user.getUid()).getValue(Users.class);
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(HomeActivity.this);
                View header = navigationView.getHeaderView(0);
//                TextView text = (TextView) header.findViewById(R.id.username_nav);
//                text.setText(med.getUser());
//                text.setTextColor(Color.WHITE);
//                TextView text2 = (TextView) header.findViewById(R.id.email_nav);
//                text2.setText(med.getEmail());
//                text2.setTextColor(Color.WHITE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
