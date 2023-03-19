package com.example.trash_collector;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdapterWorkReport extends RecyclerView.Adapter<AdapterWorkReport.HostelViewHolder>{

    private final Context mContext;
    private final List<Tasks> myHostelList;
    private int lastPosition = -1;

    public AdapterWorkReport(Context mContext, List<Tasks> myHostelList) {
        this.mContext = mContext;
        this.myHostelList = myHostelList;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_work_report,viewGroup,false);

        return new HostelViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final HostelViewHolder foodViewHolder, @SuppressLint("RecyclerView") int i) {
        // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
      //  foodViewHolder.mId.setText(String.valueOf(foodViewHolder.getAdapterPosition()));
        foodViewHolder.binId.setText(myHostelList.get(i).getBinId());
        foodViewHolder.binAddress.setText(myHostelList.get(i).getBinAddress());
        foodViewHolder.TaskId.setText(myHostelList.get(i).getTaskID());
        foodViewHolder.userName.setText(myHostelList.get(i).getDriverName());
        foodViewHolder.complaintStatus.setText(myHostelList.get(i).getCompStatus());



        setAnimation(foodViewHolder.itemView,i);

    }

    public void setAnimation(View viewToAnimate, int position ){

        if(position> lastPosition){

            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;


        }



    }

    @Override
    public int getItemCount() { return myHostelList.size(); }



    static class HostelViewHolder extends RecyclerView.ViewHolder{



        TextView binId,binAddress,userName,TaskId,complaintStatus;


        public HostelViewHolder(View itemView) {
            super(itemView);


            binId = itemView.findViewById(R.id.tvBinId);
            TaskId = itemView.findViewById(R.id.tvtaskId);
            binAddress = itemView.findViewById(R.id.tvBinAddress);
//            complaintId = itemView.findViewById(R.id.tvComplaintID);
            userName = itemView.findViewById(R.id.tvDriverName);

            complaintStatus = itemView.findViewById(R.id.tvComplaintStatus);



        }
    }
}

