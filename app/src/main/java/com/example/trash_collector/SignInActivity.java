package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
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

public class SignInActivity extends AppCompatActivity {

    private EditText SignInMail, SignInPass;
    private FirebaseAuth auth;
    private Button SignInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        // set the view now
        setContentView(R.layout.activity_sign_in);
        SignInMail = (EditText) findViewById(R.id.SignInMail);
        SignInPass = (EditText) findViewById(R.id.SignInPass);
        SignInButton = (Button) findViewById(R.id.SignInButton);

TextView toolbartext=(TextView) findViewById(R.id.toolbarText);
        toolbartext.setText("Sign In as User");
        auth = FirebaseAuth.getInstance();

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
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 8) {
                                        Toast.makeText(getApplicationContext(),"Password must be more than 8 digit",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInActivity.this, MainHomeActivityUser.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
//                                    Intent intent = new Intent(getApplicationContext(), MainHomeActivityUser.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
//                                    stackBuilder.addNextIntentWithParentStack(intent);
//                                    try {
//                                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT).send();
//                                    } catch (PendingIntent.CanceledException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                            }
                        });
            }
        });
    }

    public void NavigateSignUp(View v) {
        Intent inent = new Intent(this, SignUpActivity.class);
        startActivity(inent);
    }
    public void NavigateForgetMyPassword(View v) {
        Intent inent = new Intent(this, ResetPasswordActivity.class);
        startActivity(inent);
    }
}