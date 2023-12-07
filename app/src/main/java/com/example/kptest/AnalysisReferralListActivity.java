package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.AnalysisReferralAdapter;
import com.example.kptest.adapter.DoctorReferralAdapter;
import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.DoctorReferral;
import com.example.kptest.retrofit.AnalysisReferralApi;
import com.example.kptest.retrofit.DoctorReferralApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisReferralListActivity extends AppCompatActivity implements RecyclerViewInterface{

    private RecyclerView recyclerView;
    private List<AnalysisReferral> analysisReferrals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_referral_list);

        recyclerView = findViewById(R.id.analysisReferralList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAnalysisReferrals();

    }

    private void loadAnalysisReferrals() {
        RetrofitService retrofitService = new RetrofitService();
        AnalysisReferralApi analysisReferralApi = retrofitService.getRetrofit().create(AnalysisReferralApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        analysisReferralApi.getAllAnalysisReferrals(jwt)
                .enqueue(new Callback<List<AnalysisReferral>>() {
                    @Override
                    public void onResponse(Call<List<AnalysisReferral>> call, Response<List<AnalysisReferral>> response) {
                        if (response.isSuccessful()) {
                            analysisReferrals = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<AnalysisReferral>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке списка направлений ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<AnalysisReferral> analysisReferrals) {
        AnalysisReferralAdapter analysisReferralAdapter = new AnalysisReferralAdapter(analysisReferrals, this);
        recyclerView.setAdapter(analysisReferralAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),AnalysisReferralItemActivity.class);
        intent.putExtra("id",String.valueOf(analysisReferrals.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}