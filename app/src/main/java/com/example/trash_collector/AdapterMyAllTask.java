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

public class AdapterMyAllTask extends RecyclerView.Adapter<AdapterMyAllTask.HostelViewHolder>{

    private final Context mContext;
    private final List<Tasks> myHostelList;
    private int lastPosition = -1;

    public AdapterMyAllTask(Context mContext, List<Tasks> myHostelList) {
        this.mContext = mContext;
        this.myHostelList = myHostelList;
    }

    @NonNull
    @Override
    public HostelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_my_task,viewGroup,false);

        return new HostelViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final HostelViewHolder foodViewHolder, @SuppressLint("RecyclerView") int i) {
        // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
      //  foodViewHolder.mId.setText(String.valueOf(foodViewHolder.getAdapterPosition()));
        foodViewHolder.binId.setText("Bin ID:  " +myHostelList.get(i).getBinId());
        foodViewHolder.binAddress.setText("Bin Address:  " +myHostelList.get(i).getBinAddress());
        foodViewHolder.TaskId.setText("Complaint ID:  " +myHostelList.get(i).getTaskID());
        foodViewHolder.complaint.setText("Complaint:  " +myHostelList.get(i).getComplaint());
        foodViewHolder.complaintStatus.setText("Complaint Status:  " +myHostelList.get(i).getCompStatus());
//        foodViewHolder.userMail.setText("User Email:  " +myHostelList.get(i).getUserEmail());
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

                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.update_status_dialog);



                // Retrieve the employee's data from Firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference employeeRef = database.getReference("Complaints").
                        child(String.valueOf(myHostelList.get(i).getUserKey())).
                        child(String.valueOf(myHostelList.get(i).getCompKey()));
                DatabaseReference tasksRef = database.getReference("tasks");
                employeeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get the current status from the employee's data
                        String currentStatus = dataSnapshot.child("compStatus").getValue(String.class);

                        // Display the current status in an EditText in the dialog box
                        EditText statusEditText = dialog.findViewById(R.id.editTextStatus);
                        statusEditText.setHint(currentStatus);

                        // Add a "Submit" button to the dialog box
                        Button submitButton = dialog.findViewById(R.id.buttonUpdate);
                        submitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Retrieve the updated status from the EditText
                                String updatedStatus = statusEditText.getText().toString();

                                // Update the employee's data in Firebase with the new status
                                employeeRef.child("compStatus").setValue(updatedStatus);

                                tasksRef.child(String.valueOf(myHostelList.get(i).getTaskKey())).child("compStatus").setValue(updatedStatus);
                                // Dismiss the dialog box
                                dialog.dismiss();
                            }
                        });

                        // Show the dialog box
                        dialog.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database error
                    }
                });


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



        TextView binId,binAddress,userMail,complaint,complaintStatus,TaskId;
        CardView mCardView;
Button viewDetail;
Button assignTask;

        public HostelViewHolder(View itemView) {
            super(itemView);
viewDetail=itemView.findViewById(R.id.ViewLocation);

            binId = itemView.findViewById(R.id.tvBinID);
            TaskId = itemView.findViewById(R.id.tvtaskID);
            binAddress = itemView.findViewById(R.id.tvBinAddressCom);
//            complaintId = itemView.findViewById(R.id.tvComplaintID);
            userMail = itemView.findViewById(R.id.tvUserEmail);
            complaint = itemView.findViewById(R.id.tvComplaint);
            complaintStatus = itemView.findViewById(R.id.tvComplaintStatus);


assignTask=itemView.findViewById(R.id.btnUpdateTask);
        }
    }
}

