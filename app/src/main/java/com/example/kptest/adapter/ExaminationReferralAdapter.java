package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.ExaminationReferral;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExaminationReferralAdapter extends RecyclerView.Adapter<ExaminationReferralHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<ExaminationReferral> examinationReferrals;

    public ExaminationReferralAdapter(List<ExaminationReferral> examinationReferrals, RecyclerViewInterface recyclerViewInterface) {
        this.examinationReferrals = examinationReferrals;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public ExaminationReferralHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.examination_referral_list_item, parent, false);
        return new ExaminationReferralHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ExaminationReferralHolder holder, int position) {
        ExaminationReferral examinationReferral = examinationReferrals.get(position);
        holder.cabinetName.setText(examinationReferral.getCabinet().getName());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        holder.date.setText(examinationReferral.getDateOfTaking().format(CUSTOM_FORMATTER));
//        holder.diagnosis.setText(examination.getDiagnosis().getDescription());
        holder.cabinetNumber.setText(examinationReferral.getCabinet().getNumber());
        holder.id.setText(Long.toString(examinationReferral.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return examinationReferrals.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
