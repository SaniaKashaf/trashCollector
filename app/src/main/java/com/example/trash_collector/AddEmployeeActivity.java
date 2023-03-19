package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.Random;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText etEmpName,etEmpPhoneNo,etEmpMail,etEmpPsw,etEmpAddress;



  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
      TextView toolbartext=findViewById(R.id.toolbarText);
      //adding customised toolbar
      Toolbar  toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      Objects.requireNonNull(getSupportActionBar()).setTitle(null);
      toolbartext.setText("Add Drivers");

      etEmpName = (EditText)findViewById(R.id.etEmpName);
      etEmpMail = (EditText)findViewById(R.id.etEmpEmail);
      etEmpPsw = (EditText)findViewById(R.id.etEmpPasword);

      etEmpPhoneNo = (EditText)findViewById(R.id.etEmpPhoneNo);
      etEmpAddress = (EditText)findViewById(R.id.etEmpAddress);
//        <!--    public String empID;-->
//<!--    public String empKey;-->
//<!--    public String name;-->
//<!--    public String phoneno;-->
//<!--    public String empMail;-->
//<!--    public String empAddress;-->
//<!--    public String empPassword;-->


    }

    public void AddEmployee1(View view) {

        String name = etEmpName.getText().toString();
        String pass = etEmpPsw.getText().toString();
        String mail = etEmpMail.getText().toString();
        String address = etEmpAddress.getText().toString();
        String phoneNo = etEmpPhoneNo.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),"Please enter Driver Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(),"Please enter Driver Password",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(getApplicationContext(),"Please enter Driver mail",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(address)){
            Toast.makeText(getApplicationContext(),"Please enter Driver Address",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(phoneNo)){
            Toast.makeText(getApplicationContext(),"Please enter Driver PhoneNo",Toast.LENGTH_LONG).show();
        }
else {
            Random random = new Random();
            int newId = random.nextInt(100);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference employeesRef = database.getReference("drivers");
            DriverInfo driver = new DriverInfo(String.valueOf(newId), name, phoneNo, mail, address,pass);


            String employeeKey = employeesRef.push().getKey();


            assert employeeKey != null;


           employeesRef
                    .child(employeeKey).setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(AddEmployeeActivity.this, "Driver Created", Toast.LENGTH_SHORT).show();



                            }



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEmployeeActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }
}