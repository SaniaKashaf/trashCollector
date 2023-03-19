package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllDriverTasksActivity extends AppCompatActivity {
String driverKey;

    RecyclerView mRecyclerView;
    List<Tasks> assignedTasks;
    ProgressDialog progressDialog;
    AdapterMyAllTask myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_driver_task);
        driverKey=getIntent().getStringExtra("binKey");
        mRecyclerView = (RecyclerView)findViewById(R.id.rvAllTask);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(AllDriverTasksActivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("My All Tasks");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        assignedTasks = new ArrayList<>();
        myAdapter  = new AdapterMyAllTask(AllDriverTasksActivity.this,assignedTasks);
        mRecyclerView.setAdapter(myAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference employeeRef = database.getReference("drivers").child(driverKey);
        DatabaseReference assignedTasksRef = database.getReference("tasks");
        employeeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DriverInfo employee = dataSnapshot.getValue(DriverInfo.class);
//                String employeeId = employee.getEmpID();

                // Retrieve all tasks assigned to this employee
                assignedTasksRef.orderByChild("assignedDriverKey").equalTo(driverKey)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                                    Tasks task = taskSnapshot.getValue(Tasks.class);
                                    task.setTaskKey(String.valueOf(taskSnapshot.getKey()));
                                    assignedTasks.add(task);
                                }
                                myAdapter.notifyDataSetChanged();
                                // Do something with the list of assigned tasks
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle errors
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

    }
}