package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomeActivityAdmin extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;


    private static final String KEY_LOGGED_IN = "loggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        TextView    toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
      Toolbar  toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("Admin Dashboard");
        SharedPreferences sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        //toggle button
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

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



                    case R.id.nav_logout:
                        Toast.makeText(HomeActivityAdmin.this,"Logout",Toast.LENGTH_SHORT).show();
finish();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(KEY_LOGGED_IN, false);
                        editor.apply();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }


    public void CreateBin(View view) {
        startActivity(new Intent(HomeActivityAdmin.this,CreateBinActivity.class));

    }
    public void UpdateBin(View view) {
        startActivity(new Intent(HomeActivityAdmin.this,UpdateBinActivity.class));

    }

    public void ViewComplaints(View view) {
        startActivity(new Intent(HomeActivityAdmin.this,AllComplaintsActivity.class));

    }

    public void AddEmployee(View view) {

        startActivity(new Intent(HomeActivityAdmin.this,AddEmployeeActivity.class));
    }

    public void ViewDrivers(View view) {
        startActivity(new Intent(HomeActivityAdmin.this,AllDriversActivity.class));
    }

    public void WorkReportActivity(View view) {

        startActivity(new Intent(HomeActivityAdmin.this,WorkReportActivity.class));
    }

    public void CreateBin12(View view) {
        startActivity(new Intent(HomeActivityAdmin.this,CreateBinActivity.class));
    }
}