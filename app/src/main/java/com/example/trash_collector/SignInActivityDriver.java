package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignInActivityDriver extends AppCompatActivity {
    private EditText SignInMail, SignInPass;
    private static final String KEY_LOGGED_IN = "loggedInD";
    private Button SignInButton;
    SharedPreferences sharedPref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_driver);
      sharedPref   = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        SignInMail = (EditText) findViewById(R.id.SignInMail);
        SignInPass = (EditText) findViewById(R.id.SignInPass);
        SignInButton = (Button) findViewById(R.id.SignInButton);


    }

    public void SignInDriver(View view) {

        String email = SignInMail.getText().toString();
        final String password = SignInPass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("drivers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean driverFound = false;

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    DriverInfo binData = itemSnapshot.getValue(DriverInfo.class);
                    Objects.requireNonNull(binData).setEmpKey(itemSnapshot.getKey());
                    assert binData != null;


                    if (binData.empMail.equalsIgnoreCase(email)){
                        Intent intent = new Intent(SignInActivityDriver.this, HomeDriver.class);
                        intent.putExtra("binKey",String.valueOf(binData.empKey));
                      // Toast.makeText(SignInActivityDriver.this, binData.getEmpKey().toString(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(KEY_LOGGED_IN, true);
                        editor.putString("DKey", String.valueOf(binData.empKey));
                        editor.apply();
                        finish();

                        driverFound = true;
                        break; // break out of the loop if driver is found
                    }
                }

                if (!driverFound) {
                    Toast.makeText(SignInActivityDriver.this, "Invalid Driver", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}