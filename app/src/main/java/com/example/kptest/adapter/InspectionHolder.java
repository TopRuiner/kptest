package com.example.kptest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;

public class InspectionHolder extends RecyclerView.ViewHolder {

    TextView type, date, diagnosis, id;

    public InspectionHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        type = itemView.findViewById(R.id.inspectionListItem_Type);
        date = itemView.findViewById(R.id.inspectionListItem_date);
        diagnosis = itemView.findViewById(R.id.inspectionListItem_diagnosis);
        id = itemView.findViewById(R.id.inspectionId);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface !=null){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
