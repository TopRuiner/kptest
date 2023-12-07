package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.Analysis;
import com.example.kptest.model.Examination;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<Analysis> analysisList;

    public AnalysisAdapter(List<Analysis> analysisList, RecyclerViewInterface recyclerViewInterface) {
        this.analysisList = analysisList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public AnalysisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.analysis_list_item, parent, false);
        return new AnalysisHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalysisHolder holder, int position) {
        Analysis analysis = analysisList.get(position);
        holder.type.setText(analysis.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        holder.date.setText(analysis.getDate().format(CUSTOM_FORMATTER));
//        holder.diagnosis.setText(examination.getDiagnosis().getDescription());
        holder.report.setText(analysis.getReport());
        holder.id.setText(Long.toString(analysis.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return analysisList.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
