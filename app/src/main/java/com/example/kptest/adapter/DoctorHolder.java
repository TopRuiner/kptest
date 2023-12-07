package com.example.kptest.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;

public class DoctorHolder  extends RecyclerView.ViewHolder {

    TextView fio, speciality, degree, category, id;

    public DoctorHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        fio = itemView.findViewById(R.id.doctorListItem_FIO);
        speciality = itemView.findViewById(R.id.doctorListItem_speciality);
        degree = itemView.findViewById(R.id.doctorListItem_degree);
        category = itemView.findViewById(R.id.doctorListItem_category);
        id = itemView.findViewById(R.id.doctorId);


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
