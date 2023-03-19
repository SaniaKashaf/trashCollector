package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class SubmitComplaintActivity extends AppCompatActivity {
    Bundle mBundle;
    String binId,binAddress,binLat,binLong;
    EditText txtBinId,txtBinAddress,txtCompId,txtUserEmail,txtComplaint;
    Button uploadComplaint;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("Register Complaint");
    mBundle    = getIntent().getExtras();
    binId=mBundle.getString("binId");
    binAddress=mBundle.getString("binAddress");
    binLat=mBundle.getString("lat");
    binLong=mBundle.getString("lon");
        Random random = new Random();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        int newId = random.nextInt(1000);
        txtBinId = (EditText)findViewById(R.id.etBinId);
        txtCompId = (EditText)findViewById(R.id.etCompId);
        txtComplaint = (EditText)findViewById(R.id.etComplaint);
        txtBinAddress = (EditText)findViewById(R.id.etbinAddress);
        txtUserEmail = (EditText)findViewById(R.id.etUserEmail);
        uploadComplaint=findViewById(R.id.btnUploadComplaint);
        Log.d("binId",binId.toString());

        String CompID=String.valueOf(newId);
    txtCompId.setHint(CompID);
//
  txtUserEmail.setHint(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail());
     txtBinId.setHint(binId);
      txtBinAddress.setHint(binAddress);
uploadComplaint.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String complaint =  txtComplaint.getText().toString().trim();
        if (TextUtils.isEmpty(complaint)) {
            Toast.makeText(getApplicationContext(), "Please enter a complaint", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = firebaseAuth.getCurrentUser().getUid();
        String timestamp = String.valueOf(System.currentTimeMillis());


        Complaints complaints = new Complaints(
                String.valueOf(newId),
              binId,
            firebaseAuth.getCurrentUser().getUid(),
            binAddress,
                firebaseAuth.getCurrentUser().getEmail(),
binLong,
                binLat,

                firebaseAuth.getCurrentUser().getUid(),
           complaint    ,
                "Pending",
                timestamp


        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        //   String newId = databaseRef.push().getKey();
      //  Random random = new Random();
     //   int newId = random.nextInt(1000);
        FirebaseDatabase.getInstance().getReference("Complaints")
                .child(userId).child(timestamp).setValue(complaints).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(SubmitComplaintActivity.this, "Complaint Submitted", Toast.LENGTH_SHORT).show();

                            finish();

                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SubmitComplaintActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
});
    }
}
