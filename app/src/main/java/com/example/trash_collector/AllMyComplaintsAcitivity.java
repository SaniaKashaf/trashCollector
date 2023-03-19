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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllMyComplaintsAcitivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Complaints> myHostelList;
    ProgressDialog progressDialog;
    AdapterAllComplaint myAdapter;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_my_complaints_acitivity);
        mRecyclerView = (RecyclerView)findViewById(R.id.rvMyAllComp);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(AllMyComplaintsAcitivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("My Complaints");
        myHostelList = new ArrayList<>();
        myAdapter  = new AdapterAllComplaint(AllMyComplaintsAcitivity.this,myHostelList,"user");
        mRecyclerView.setAdapter(myAdapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Complaints").child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
        progressDialog.show();
        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myHostelList.clear();
                for (DataSnapshot complaintsSnapshot : dataSnapshot.getChildren()) {

                        Complaints foodData = complaintsSnapshot.getValue(Complaints.class);
                        assert foodData != null;
                        foodData.setKey(complaintsSnapshot.getKey());
                        myHostelList.add(foodData);
                        progressDialog.dismiss();


                }
                myAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

