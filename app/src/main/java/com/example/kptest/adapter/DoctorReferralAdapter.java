package com.example.kptest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.R;
import com.example.kptest.RecyclerViewInterface;
import com.example.kptest.model.DoctorReferral;
import com.example.kptest.model.Examination;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorReferralAdapter extends RecyclerView.Adapter<DoctorReferralHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<DoctorReferral> doctorReferralList;

    public DoctorReferralAdapter(List<DoctorReferral> doctorReferrals, RecyclerViewInterface recyclerViewInterface) {
        this.doctorReferralList = doctorReferrals;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public DoctorReferralHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_referral_list_item, parent, false);
        return new DoctorReferralHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorReferralHolder holder, int position) {
        DoctorReferral doctorReferral = doctorReferralList.get(position);
        holder.doctorTarget.setText(doctorReferral.getDoctorTarget().ReturnFIO());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        holder.date.setText(doctorReferral.getDateOfTaking().format(CUSTOM_FORMATTER));
//        holder.diagnosis.setText(examination.getDiagnosis().getDescription());
        holder.doctorSpeciality.setText(doctorReferral.getDoctorTarget().getSpeciality());
        holder.doctorCabinet.setText(doctorReferral.getDoctorTarget().getCabinet().getNumber());
        holder.id.setText(Long.toString(doctorReferral.getId()));
    }

    public void onItemClick (int position){
//        Doctor doctor = doctorList.get(position);
//        Intent intent = new Intent(DoctorListActivity.class, DoctorItemActivity.class);
    }

    @Override
    public int getItemCount() {
        try {
            return doctorReferralList.size();
        }
        catch (Exception e){
            return 0;
        }

    }
}
