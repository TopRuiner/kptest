package com.example.kptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kptest.R;
import com.example.kptest.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorArrayAdapter extends ArrayAdapter<Doctor> {
    public DoctorArrayAdapter(Context context, List<Doctor> doctorList){
        super(context,0,doctorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return ititView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return ititView(position,convertView,parent);
    }
    private View ititView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.doctor_spinner_row, parent, false
            );
        }
        TextView doctorFIO = convertView.findViewById(R.id.doctorFIOTextView);

        Doctor currentDoctor = getItem(position);

        if(currentDoctor != null) {
            doctorFIO.setText(currentDoctor.ReturnFIO());
        }
        return convertView;
    }

}
