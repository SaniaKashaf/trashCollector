package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
TextView studentLogin,adminLogin;
    private static final String KEY_LOGGED_IN = "loggedIn";
    private static final String KEY_LOGGED_IND = "loggedInD";
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentLogin=findViewById(R.id.signInStudent);
        adminLogin=findViewById(R.id.signInAdmin);
   sharedPref    = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.addAuthStateListener(authStateListenerStudent);
            }
        });
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPref.getBoolean(KEY_LOGGED_IN, false)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivityAdmin.class);
                    startActivity(intent);
                    // User is logged in, show appropriate UI
                } else {
                    Intent intent = new Intent(MainActivity.this, SignInActivityAdmin.class);
                    startActivity(intent);
                    // User is not logged in, show appropriate UI
                }


            }
        });
        //Check current user

    }
    FirebaseAuth.AuthStateListener authStateListenerStudent= new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            if (firebaseUser == null) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
            if (firebaseUser != null) {
                Intent intent = new Intent(MainActivity.this, MainHomeActivityUser.class);
                startActivity(intent);


            }
        }
    };


    public void SignInDriver(View view) {
        if (sharedPref.getBoolean(KEY_LOGGED_IND, false)) {
          String empkey =  sharedPref.getString("DKey",null);
            Intent intent = new Intent(MainActivity.this, HomeDriver.class);
            intent.putExtra("binKey",String.valueOf(empkey));
            startActivity(intent);
            // User is logged in, show appropriate UI
        } else {
            Intent intent = new Intent(MainActivity.this, SignInActivityDriver.class);
            startActivity(intent);
            // User is not logged in, show appropriate UI
        }


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }
}
