package com.assolid.ewm_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assolid.ewm_android.activities.ArchiveActivity;
import com.assolid.ewm_android.activities.CheckYourselfActivity;
import com.assolid.ewm_android.activities.MainActivity;
import com.assolid.ewm_android.activities.WorkWithCardActivity;
import com.assolid.ewm_android.database.DBHandler;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView engText;
    public TextView ruText;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = getPosition() + 1;
                return false;
            }
        });
        engText = (TextView)itemView.findViewById(R.id.engText);
        ruText = (TextView) itemView.findViewById(R.id.ruText);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "LongClick to delete", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onLongClick(View view){
//
//    }
}