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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeDriver extends AppCompatActivity {
String driverKey;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView toolbartext;
    private static final String KEY_LOGGED_IN = "loggedInD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);
driverKey=getIntent().getStringExtra("binKey");
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        SharedPreferences sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        //toggle button
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbartext.setText("Trash Collector Dashboard");
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
                        Toast.makeText(HomeDriver.this,"Logout",Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(this, String.valueOf(driverKey), Toast.LENGTH_SHORT).show();
    }

    public void MyTasks(View view) {
        Intent intent = new Intent(getApplicationContext(), AllDriverTasksActivity.class);
        intent.putExtra("binKey",String.valueOf(driverKey));
        // Toast.makeText(SignInActivityDriver.this, binData.getEmpKey().toString(), Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}