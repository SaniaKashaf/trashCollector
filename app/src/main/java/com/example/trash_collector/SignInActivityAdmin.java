package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trash_collector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivityAdmin extends AppCompatActivity {

    private EditText SignInMail, SignInPass;

    private Button SignInButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("admins");

    private static final String KEY_LOGGED_IN = "loggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        SharedPreferences sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        // set the view now
        setContentView(R.layout.activity_sign_in_admin);
        SignInMail = (EditText) findViewById(R.id.SignInMail);
        SignInPass = (EditText) findViewById(R.id.SignInPass);
        SignInButton = (Button) findViewById(R.id.SignInButton);
     //   String email = "hostelfinderapp1@gmail.com";
        String userId = "12345";
   // ref.child(userId).child("admin").setValue("admin");
        //Get Firebase auth instance

        TextView toolbartext=(TextView) findViewById(R.id.toolbarText);
        toolbartext.setText("Sign In as Admin");
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //authenticate user
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(SignInActivityAdmin.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (!task.isSuccessful()) {
//                                    // there was an error
//                                    if (password.length() < 8) {
//                                        Toast.makeText(getApplicationContext(),"Password must be more than 8 digit",Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Intent intent = new Intent(SignInActivityAdmin.this, HomeActivityAdmin.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }
//                        });

                ref.child(userId).child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String savedEmail = dataSnapshot.getValue(String.class);
                        // compare savedEmail with given email and log in the user if they match
                        assert savedEmail != null;
                        if (savedEmail.equals("admin")){


                            Intent intent = new Intent(SignInActivityAdmin.this, HomeActivityAdmin.class);
                                  startActivity(intent);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean(KEY_LOGGED_IN, true);
                            editor.apply();
                                 finish();

                        }
                        else {
                            Toast.makeText(SignInActivityAdmin.this, "Invaid Admin", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("TAG", "Failed to read value.", databaseError.toException());
                    }
                });
            }
        });
    }


}