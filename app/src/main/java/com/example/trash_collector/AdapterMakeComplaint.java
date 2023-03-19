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

public class AdapterMakeComplaint extends RecyclerView.Adapter<AdapterMakeComplaint.HostelViewHolder>{

    private final Context mContext;
    private final List<Bin> myHostelList;
    private int lastPosition = -1;

    public AdapterMakeComplaint(Context mContext, List<Bin> myHostelList) {
        this.mContext = mContext;
        this.myHostelList = myHostelList;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_make_complaint,viewGroup,false);

        return new HostelViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final HostelViewHolder foodViewHolder, @SuppressLint("RecyclerView") int i) {
        // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
      //  foodViewHolder.mId.setText(String.valueOf(foodViewHolder.getAdapterPosition()));
        foodViewHolder.mId.setText("Bin ID:  " +myHostelList.get(i).getKey());
        foodViewHolder.mAddress.setText("Bin Address:  " +myHostelList.get(i).getBinAddress());
        foodViewHolder.mtype.setText("Bin Type:" +myHostelList.get(i).getBinType());
foodViewHolder.mcycle.setText("Bin Cycle:" +myHostelList.get(i).getBinCycle());
        foodViewHolder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          mContext.startActivity(new Intent(mContext , MapsActivity.class)
                        .putExtra("lat",myHostelList.get(i).getBinLat() )
                        .putExtra("lon" , myHostelList.get(i).getBinLong()));


            }
        });

        foodViewHolder.makeComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, SubmitComplaintActivity.class);
                intent.putExtra("binId",myHostelList.get(foodViewHolder.getAdapterPosition()).getKey());
                intent.putExtra("binAddress",myHostelList.get(foodViewHolder.getAdapterPosition()).getBinAddress());
                intent.putExtra("lat" , myHostelList.get(foodViewHolder.getAdapterPosition()).getBinLat());
                intent.putExtra("lon" , myHostelList.get(foodViewHolder.getAdapterPosition()).getBinLong());
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



        TextView mtype,mAddress,mId,mcycle;
        CardView mCardView;
Button viewDetail;
Button makeComplaint;

        public HostelViewHolder(View itemView) {
            super(itemView);
viewDetail=itemView.findViewById(R.id.ViewLocation);

            mAddress = itemView.findViewById(R.id.tvAddress);
            mtype = itemView.findViewById(R.id.tvType);
            mcycle = itemView.findViewById(R.id.tvCycle);
            mId = itemView.findViewById(R.id.tvId);

makeComplaint=itemView.findViewById(R.id.btnMakeComplaint);
        }
    }
}

