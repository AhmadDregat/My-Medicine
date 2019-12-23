package com.example.mymedicine.model;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toolbar;

        import com.example.mymedicine.LoginActivity;
        import com.example.mymedicine.R;
        import com.example.mymedicine.Users;
        import com.google.android.material.navigation.NavigationView;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class MyCart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView first, second;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user_firebase;
    private Users c_user;
    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        first = findViewById(R.id.firstStuff);
        second = findViewById(R.id.SecondStuff);
        auth = FirebaseAuth.getInstance();
        user_firebase = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        toolbar =findViewById(R.id.toolbar);
        s = user_firebase.getUid();
        setActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        setDataView(myRef);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                c_user = dataSnapshot.child(user_firebase.getUid()).getValue(Users.class);
                if(c_user.getCarts()!= null ) {
                    if (c_user.getCarts().size() > 0) {
                        if (c_user.getCarts().get("akamol_mid") != null) {
                            first.setVisibility(View.VISIBLE);
                        }
                        if (c_user.getCarts().get("ansolin_mid") != null) {
                            second.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                Users med = dataSnapshot.child(user_firebase.getUid()).getValue(Users.class);
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(MyCart.this);
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
}
