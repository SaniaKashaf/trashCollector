package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.trash_collector.R;

public class Splash extends AppCompatActivity {

    Button btn_getstart;
    SharedPreferences sharedPref;
    boolean isPermissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView animationView = findViewById(R.id.splash_animation_view);
        animationView.addAnimatorUpdateListener((animation) -> {
            // Do something.
        });
        animationView.playAnimation();

        if (animationView.isAnimating()) {
            // Do something.
        }

        btn_getstart = findViewById(R.id.btn_getstart);
        btn_getstart.setEnabled(false);

        sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE);
        isPermissionGranted = sharedPref.getBoolean("is_permission_granted", false);
        if (isPermissionGranted) {
            // permission is already granted
            btn_getstart.setEnabled(true);
        } else {
            // permission is not granted, request for it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        btn_getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission granted
                Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
                btn_getstart.setEnabled(true);

                // save the permission status in SharedPreferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("is_permission_granted", true);
                editor.apply();
            } else {
                // permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                btn_getstart.setEnabled(false);
            }
        }
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
