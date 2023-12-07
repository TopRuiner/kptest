package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.AnalysisReferralAdapter;
import com.example.kptest.adapter.ExaminationReferralAdapter;
import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.ExaminationReferral;
import com.example.kptest.retrofit.AnalysisReferralApi;
import com.example.kptest.retrofit.ExaminationReferralApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationReferralListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<ExaminationReferral> examinationReferrals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_referral_list);

        recyclerView = findViewById(R.id.examinationReferralList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadExaminationReferrals();

    }

    private void loadExaminationReferrals() {
        RetrofitService retrofitService = new RetrofitService();
        ExaminationReferralApi examinationReferralApi = retrofitService.getRetrofit().create(ExaminationReferralApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        examinationReferralApi.getAllExaminationReferrals(jwt)
                .enqueue(new Callback<List<ExaminationReferral>>() {
                    @Override
                    public void onResponse(Call<List<ExaminationReferral>> call, Response<List<ExaminationReferral>> response) {
                        if (response.isSuccessful()) {
                            examinationReferrals = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ExaminationReferral>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке списка направлений ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<ExaminationReferral> examinationReferrals) {
        ExaminationReferralAdapter examinationReferralAdapter = new ExaminationReferralAdapter(examinationReferrals, this);
        recyclerView.setAdapter(examinationReferralAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),ExaminationReferralItemActivity.class);
        intent.putExtra("id",String.valueOf(examinationReferrals.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}