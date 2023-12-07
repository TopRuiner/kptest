package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.DoctorReferral;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnalysisReferralAdapter extends RecyclerView.Adapter<AnalysisReferralHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<AnalysisReferral> analysisReferrals;

    public AnalysisReferralAdapter(List<AnalysisReferral> analysisReferrals, RecyclerViewInterface recyclerViewInterface) {
        this.analysisReferrals = analysisReferrals;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public AnalysisReferralHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.analysis_referral_list_item, parent, false);
        return new AnalysisReferralHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalysisReferralHolder holder, int position) {
        AnalysisReferral analysisReferral = analysisReferrals.get(position);
        holder.cabinetName.setText(analysisReferral.getCabinet().getName());
        if (analysisReferral.getDateOfIssue() != null){
            DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            holder.date.setText(analysisReferral.getDateOfIssue().format(CUSTOM_FORMATTER));
        }
//        holder.diagnosis.setText(examination.getDiagnosis().getDescription());
        holder.cabinetNumber.setText(analysisReferral.getCabinet().getNumber());
        holder.id.setText(Long.toString(analysisReferral.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return analysisReferrals.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
