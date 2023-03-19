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

public class AllDriversActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<DriverInfo> allBinList;
    ProgressDialog progressDialog;
    AdapterAlldrivers myAdapter;
    EditText txt_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drivers);
        allBinList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(AllDriversActivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
//        txt_Search = (EditText) findViewById(R.id.txt_searchtext);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        progressDialog.show();
        myAdapter = new AdapterAlldrivers(AllDriversActivity.this, allBinList);
        mRecyclerView.setAdapter(myAdapter);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("All Drivers Detail");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("drivers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allBinList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    DriverInfo binData = itemSnapshot.getValue(DriverInfo.class);
                    assert binData != null;
                    binData.setEmpKey(itemSnapshot.getKey());
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