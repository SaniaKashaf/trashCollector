package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllComplaintsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Complaints> myHostelList;
    ProgressDialog progressDialog;
    AdapterAllComplaint myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_complaints);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(AllComplaintsActivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("All Complaints");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        myHostelList = new ArrayList<>();
        myAdapter  = new AdapterAllComplaint(AllComplaintsActivity.this,myHostelList,"admin");
        mRecyclerView.setAdapter(myAdapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Complaints");
        progressDialog.show();
        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myHostelList.clear();
                for (DataSnapshot complaintsSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot complaintSnapshot : complaintsSnapshot.getChildren()) {
                        Complaints foodData = complaintSnapshot.getValue(Complaints.class);
                        assert foodData != null;
                        foodData.setKey(complaintSnapshot.getKey());
                        myHostelList.add(foodData);
                        progressDialog.dismiss();
                    }

                }
                myAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


    }
}