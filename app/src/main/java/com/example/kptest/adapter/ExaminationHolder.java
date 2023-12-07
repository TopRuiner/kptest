package com.example.kptest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;

public class ExaminationHolder extends RecyclerView.ViewHolder {

    TextView type, date, report, id;

    public ExaminationHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        type = itemView.findViewById(R.id.examinationListItem_Type);
        date = itemView.findViewById(R.id.examinationListItem_date);
        report = itemView.findViewById(R.id.examinationListItem_report);
        id = itemView.findViewById(R.id.examinationId);


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
