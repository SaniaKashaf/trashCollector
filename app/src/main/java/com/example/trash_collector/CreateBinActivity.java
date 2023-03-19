package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.trash_collector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class CreateBinActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;


    LocationManager locationManager;
    String latitude, longitude;



    EditText txt_bin_address,txt_bin_type,txt_bin_cycle;

    EditText txt_binLong,txt_binLat;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bin);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("Create Trash");
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);




        (  btn = findViewById(R.id.slectLocation))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivityForResult( new Intent(CreateBinActivity.this , SetLocation.class) , 2);
                    }
                });



        txt_bin_address = (EditText)findViewById(R.id.txt_bin_address);
        txt_bin_type = (EditText)findViewById(R.id.text_bin_type);
        txt_bin_cycle = (EditText)findViewById(R.id.text_bin_cycle);

        txt_binLong = (EditText)findViewById(R.id.text_binLong);
        txt_binLat = (EditText)findViewById(R.id.text_binLat);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (requestCode == 2)
            {

                try {
                    assert data != null;
                    txt_binLat.setText( data.getStringExtra("Lattitude")+"");
                    txt_binLong.setText(data.getStringExtra("Longitude"));
                    Toast.makeText(this, data.getStringExtra("Longitude") + "", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }


        }

    }




    public void CreateBin1(View view) {

    uploadHostel();

    }

    public void uploadHostel(){



        Bin BinData = new Bin(
                txt_bin_address.getText().toString(),
                txt_bin_type.getText().toString(),
                txt_bin_cycle.getText().toString(),


                txt_binLong.getText().toString(),
                txt_binLat.getText().toString()

        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
     //   String newId = databaseRef.push().getKey();
        Random random = new Random();
        int newId = random.nextInt(1000);
        FirebaseDatabase.getInstance().getReference("BinData")
                .child(Objects.requireNonNull(String.valueOf(newId))).setValue(BinData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(CreateBinActivity.this, "Bin Created", Toast.LENGTH_SHORT).show();

                            finish();

                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateBinActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });




    }


    public void btnSetLocation(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                CreateBinActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                CreateBinActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                txt_binLong.setText(longitude);
                txt_binLat.setText(latitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}