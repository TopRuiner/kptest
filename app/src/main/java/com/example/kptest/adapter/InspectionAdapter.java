package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.Doctor;
import com.example.kptest.model.Inspection;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class InspectionAdapter extends RecyclerView.Adapter<InspectionHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private List<Inspection> inspectionList;

    public InspectionAdapter(List<Inspection> inspectionList, RecyclerViewInterface recyclerViewInterface) {
        this.inspectionList = inspectionList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public InspectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inspection_list_item, parent, false);
        return new InspectionHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionHolder holder, int position) {
        Inspection inspection = inspectionList.get(position);
        holder.type.setText(inspection.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        holder.date.setText(inspection.getDate().format(CUSTOM_FORMATTER));
        holder.diagnosis.setText(inspection.getDiagnosis().getDescription());
        holder.id.setText(Long.toString(inspection.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return inspectionList.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
