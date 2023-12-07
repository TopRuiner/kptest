package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.Examination;
import com.example.kptest.model.Inspection;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private List<Examination> examinationList;

    public ExaminationAdapter(List<Examination> examinationList, RecyclerViewInterface recyclerViewInterface) {
        this.examinationList = examinationList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public ExaminationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.examination_list_item, parent, false);
        return new ExaminationHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationHolder holder, int position) {
        Examination examination = examinationList.get(position);
        holder.type.setText(examination.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        holder.date.setText(examination.getDate().format(CUSTOM_FORMATTER));
//        holder.diagnosis.setText(examination.getDiagnosis().getDescription());
        holder.report.setText(examination.getReport());
        holder.id.setText(Long.toString(examination.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return examinationList.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
