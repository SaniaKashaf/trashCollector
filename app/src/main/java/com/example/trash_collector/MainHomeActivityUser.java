package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainHomeActivityUser extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView toolbartext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_user);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.my_drawer_layout);
toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        //toggle button
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
toolbartext.setText("User Dashboard");
        //when an item is selected from menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.contact_us:
                        Toast.makeText(getApplicationContext(),"Contact Us",Toast.LENGTH_SHORT).show();
                        //close drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.profile:
                        Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                        //close drawer
                        Intent inentProfile = new Intent(MainHomeActivityUser.this, ProfileActivity.class);
                        startActivity(inentProfile);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(MainHomeActivityUser.this,"Logout",Toast.LENGTH_SHORT).show();

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainHomeActivityUser.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

    }

    public void MyComplaints(View view) {

        Intent intent = new Intent(MainHomeActivityUser.this, AllMyComplaintsAcitivity.class);
        startActivity(intent);
    }

    public void btnAllTrashes(View view) {
        Intent intent = new Intent(MainHomeActivityUser.this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

}}