package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkReportActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Tasks> allBinList;
    ProgressDialog progressDialog;
    AdapterWorkReport myAdapter;
    EditText txt_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_report);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("Work Report");
        allBinList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rvWorkReport);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(WorkReportActivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
//        txt_Search = (EditText) findViewById(R.id.txt_searchtext);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        progressDialog.show();
        myAdapter = new AdapterWorkReport(WorkReportActivity.this, allBinList);
        mRecyclerView.setAdapter(myAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allBinList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Tasks binData = itemSnapshot.getValue(Tasks.class);
                    assert binData != null;

                    allBinList.add(binData);
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
}