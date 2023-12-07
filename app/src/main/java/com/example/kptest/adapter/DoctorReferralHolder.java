package com.example.kptest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;

public class DoctorReferralHolder extends RecyclerView.ViewHolder {
    TextView id, date, doctorTarget, doctorSpeciality, doctorCabinet;

    public DoctorReferralHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        doctorTarget = itemView.findViewById(R.id.doctorReferralListItem_doctorTarget);
        date = itemView.findViewById(R.id.doctorReferralListItem_date);
        id = itemView.findViewById(R.id.doctorReferralId);
        doctorSpeciality = itemView.findViewById(R.id.doctorReferralListItem_doctorSpeciality);
        doctorCabinet = itemView.findViewById(R.id.doctorReferralListItem_cabinet);

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
