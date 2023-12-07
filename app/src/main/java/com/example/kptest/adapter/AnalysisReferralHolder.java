package com.example.kptest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;

public class AnalysisReferralHolder extends RecyclerView.ViewHolder {
    TextView id, date, cabinetName, cabinetNumber;

    public AnalysisReferralHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        cabinetName = itemView.findViewById(R.id.analysisReferralListItem_CabinetName);
        date = itemView.findViewById(R.id.analysisReferralListItem_date);
        id = itemView.findViewById(R.id.analysisReferralId);
        cabinetNumber = itemView.findViewById(R.id.analysisReferralListItem_CabinetNumber);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
