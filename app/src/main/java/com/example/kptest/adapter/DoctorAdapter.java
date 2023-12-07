package com.example.kptest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.DoctorItemActivity;
import com.example.kptest.DoctorListActivity;
import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private List<Doctor> doctorList;

    public DoctorAdapter(List<Doctor> doctorList, RecyclerViewInterface recyclerViewInterface) {
        this.doctorList = doctorList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.fio.setText(doctor.ReturnFIO());
        holder.degree.setText(doctor.getDegree());
        holder.category.setText(doctor.getCategory());
        holder.speciality.setText(doctor.getSpeciality());
        holder.id.setText(Long.toString(doctor.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return doctorList.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
