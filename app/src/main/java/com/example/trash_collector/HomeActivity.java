package com.example.trash_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
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

public class HomeActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Bin> myHostelList;
    ProgressDialog progressDialog;
    AdapterMakeComplaint myAdapter;
//    EditText txt_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(HomeActivity.this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        TextView toolbartext=findViewById(R.id.toolbarText);
        //adding customised toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        toolbartext.setText("All Trashes");
//        txt_Search = (EditText)findViewById(R.id.txt_searchtext);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items ....");
        myHostelList = new ArrayList<>();
        myAdapter  = new AdapterMakeComplaint(HomeActivity.this,myHostelList);
        mRecyclerView.setAdapter(myAdapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BinData");
        progressDialog.show();
        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myHostelList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Bin foodData = itemSnapshot.getValue(Bin.class);
                    assert foodData != null;
                    foodData.setKey(itemSnapshot.getKey());
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
//        txt_Search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
}
//    private void filter(String text) {
//        ArrayList<Bin> filterList = new ArrayList<>();
//        for(Bin item: myHostelList){
//            if(item.getBinAddress().toLowerCase().contains(text.toLowerCase())){
//                filterList.add(item);
//            }
//        }
//        myAdapter.filteredList(filterList);
//    }
    @Override
    protected void onResume() {
        super.onResume();
     //   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}