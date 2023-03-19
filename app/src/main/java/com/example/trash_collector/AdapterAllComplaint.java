package com.example.trash_collector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterAllComplaint extends RecyclerView.Adapter<AdapterAllComplaint.HostelViewHolder>{

    private final Context mContext;
    private final List<Complaints> myHostelList;
    private int lastPosition = -1;
String user;
    public AdapterAllComplaint(Context mContext, List<Complaints> myHostelList,String user) {
        this.mContext = mContext;
        this.myHostelList = myHostelList;
        this.user = user;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_complaint,viewGroup,false);

        return new HostelViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final HostelViewHolder foodViewHolder, @SuppressLint("RecyclerView") int i) {
        // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
      //  foodViewHolder.mId.setText(String.valueOf(foodViewHolder.getAdapterPosition()));
        if (user.equalsIgnoreCase("user"))
        {
            foodViewHolder.assignTask.setVisibility(View.INVISIBLE);

        }
        foodViewHolder.binId.setText("Bin ID:  " +myHostelList.get(i).getBinId());
        foodViewHolder.binAddress.setText("Bin Address:  " +myHostelList.get(i).getBinAddress());
        foodViewHolder.complaintId.setText("Complaint ID:  " +myHostelList.get(i).getCompId());
        foodViewHolder.complaint.setText("Complaint:  " +myHostelList.get(i).getComplaint());
        foodViewHolder.complaintStatus.setText("Complaint Status:  " +myHostelList.get(i).getCompStatus());
        foodViewHolder.userMail.setText("User Email:  " +myHostelList.get(i).getUserEmail());
//        foodViewHolder.mtype.setText("Bin Type:" +myHostelList.get(i).getBinType());
//foodViewHolder.mcycle.setText("Bin Cycle:" +myHostelList.get(i).getBinCycle());
        foodViewHolder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          mContext.startActivity(new Intent(mContext , MapsActivity.class)
                        .putExtra("lat",myHostelList.get(i).getBinLat() )
                        .putExtra("lon" , myHostelList.get(i).getBinLong()));


            }
        });

        foodViewHolder.assignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, AssignTaskActivity.class);
                intent.putExtra("binKey",myHostelList.get(foodViewHolder.getAdapterPosition()).getKey());
                intent.putExtra("binId",myHostelList.get(foodViewHolder.getAdapterPosition()).getBinId());
                intent.putExtra("binAddress",myHostelList.get(foodViewHolder.getAdapterPosition()).getBinAddress());
                intent.putExtra("lat" , myHostelList.get(foodViewHolder.getAdapterPosition()).getBinLat());
                intent.putExtra("lon" , myHostelList.get(foodViewHolder.getAdapterPosition()).getBinLong());
                intent.putExtra("userKey" , myHostelList.get(foodViewHolder.getAdapterPosition()).getUserId());
                intent.putExtra("complaint" , myHostelList.get(foodViewHolder.getAdapterPosition()).getComplaint());
                intent.putExtra("compStatus" , myHostelList.get(foodViewHolder.getAdapterPosition()).getCompStatus());
                intent.putExtra("comKey" , myHostelList.get(foodViewHolder.getAdapterPosition()).getKey());
                intent.putExtra("comID" , myHostelList.get(foodViewHolder.getAdapterPosition()).getCompId());
                mContext.startActivity(intent);


            }
        });
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



        TextView binId,binAddress,complaintId,userMail,complaint,complaintStatus;
        CardView mCardView;
Button viewDetail;
Button assignTask;

        public HostelViewHolder(View itemView) {
            super(itemView);
viewDetail=itemView.findViewById(R.id.ViewLocation);

            binId = itemView.findViewById(R.id.tvBinID);
            binAddress = itemView.findViewById(R.id.tvBinAddressCom);
            complaintId = itemView.findViewById(R.id.tvComplaintID);
            userMail = itemView.findViewById(R.id.tvUserEmail);
            complaint = itemView.findViewById(R.id.tvComplaint);
            complaintStatus = itemView.findViewById(R.id.tvComplaintStatus);


assignTask=itemView.findViewById(R.id.btnAssignTask);
        }
    }
}

