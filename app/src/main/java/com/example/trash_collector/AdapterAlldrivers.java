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

public class AdapterAlldrivers extends RecyclerView.Adapter<AdapterAlldrivers.HostelViewHolder>{

    private final Context mContext;
    private final List<DriverInfo> myHostelList;
    private int lastPosition = -1;

    public AdapterAlldrivers(Context mContext, List<DriverInfo> myHostelList) {
        this.mContext = mContext;
        this.myHostelList = myHostelList;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_drivers,viewGroup,false);

        return new HostelViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final HostelViewHolder foodViewHolder, @SuppressLint("RecyclerView") int i) {
        // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
      //  foodViewHolder.mId.setText(String.valueOf(foodViewHolder.getAdapterPosition()));
        foodViewHolder.dId.setText("ID:  " +myHostelList.get(i).getEmpID());
        foodViewHolder.dEmail.setText("Email:  " +myHostelList.get(i).getEmpMail());
        foodViewHolder.dName.setText("Name:  " +myHostelList.get(i).getName());
foodViewHolder.dContact.setText("Contact No:  " +myHostelList.get(i).getPhoneno());
foodViewHolder.dAddress.setText("Address:  " +myHostelList.get(i).getEmpAddress());



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



        TextView dId,dAddress,dName,dContact,dEmail;
        CardView mCardView;
Button viewDetail;

        public HostelViewHolder(View itemView) {
            super(itemView);
viewDetail=itemView.findViewById(R.id.ViewLocation);

            dId = itemView.findViewById(R.id.tvId);
            dAddress = itemView.findViewById(R.id.tvAddress);
            dName = itemView.findViewById(R.id.tvName);
            dContact = itemView.findViewById(R.id.tvContact);
            dEmail = itemView.findViewById(R.id.tvEmail);


        }
    }
}

