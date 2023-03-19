package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AssignTaskActivity extends AppCompatActivity {
String binKey,binId,binAddress,lat,lon,userKey,complaint,comStatus,driverKey,comKey,comID,driverID,driverName;
TextView driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("Assign Task To Driver");
        Bundle mBundle = getIntent().getExtras();
       binKey=mBundle.getString("binKey");
        binId=mBundle.getString("binId");
        binAddress=mBundle.getString("binAddress");
        lat=mBundle.getString("lat");
        lon=mBundle.getString("lon");
        comKey=mBundle.getString("comKey");
        comID=mBundle.getString("comID");
        userKey=mBundle.getString("userKey");
        complaint=mBundle.getString("complaint") ;
        comStatus=mBundle.getString("compStatus") ;
        driver=findViewById(R.id.tvDriver);

    }


    public void selectOmp(View view) {
        DatabaseReference employeesRef = FirebaseDatabase.getInstance().getReference("drivers");

        employeesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DriverInfo> employees = new ArrayList<>();

                for (DataSnapshot employeeSnapshot : dataSnapshot.getChildren()) {
                    DriverInfo employee = employeeSnapshot.getValue(DriverInfo.class);
                    employee.setEmpKey(employeeSnapshot.getKey());
                    employees.add(employee);

                }

                // Pass the list of employees to the dialog
                showEmployeeListDialog(employees);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });

    }

    public void AssignTask(View view) {
        Random random = new Random();
        int newId = random.nextInt(100);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tasksRef = database.getReference("tasks");
        Tasks driver = new Tasks(String.valueOf(newId), comID, comKey, binId, userKey,binAddress,lon,lat
        ,complaint,comStatus,driverKey,driverID,driverName

        );


        String taskKey = tasksRef.push().getKey();


        assert taskKey != null;


        tasksRef
                .child(taskKey).setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(AssignTaskActivity.this, "Task Assigned", Toast.LENGTH_SHORT).show();



                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AssignTaskActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void showEmployeeListDialog(List<DriverInfo> employees) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an employee");
        // Create a list of employee names
        List<String> employeeNames = new ArrayList<>();
        for (DriverInfo employee : employees) {
            employeeNames.add(employee.getName());
        }
        // Create a list adapter with the employee names and IDs
        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, employeeNames);

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DriverInfo selectedEmployee = employees.get(which);
                driver.setText(String.valueOf(selectedEmployee.getName()));
                driverKey=selectedEmployee.getEmpKey();
                driverID=selectedEmployee.getEmpID();
                driverName=selectedEmployee.getName();
                // Handle selected employee
            }
        });

        builder.show();
    }

}