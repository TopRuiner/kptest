package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.AnalysisAdapter;
import com.example.kptest.adapter.DoctorReferralAdapter;
import com.example.kptest.model.Analysis;
import com.example.kptest.model.DoctorReferral;
import com.example.kptest.retrofit.AnalysisApi;
import com.example.kptest.retrofit.DoctorReferralApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorReferralListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<DoctorReferral> doctorReferrals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_referral_list);

        recyclerView = findViewById(R.id.doctorReferralList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDoctorReferrals();

    }

    private void loadDoctorReferrals() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorReferralApi doctorReferralApi = retrofitService.getRetrofit().create(DoctorReferralApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        doctorReferralApi.getAllDoctorReferrals(jwt)
                .enqueue(new Callback<List<DoctorReferral>>() {
                    @Override
                    public void onResponse(Call<List<DoctorReferral>> call, Response<List<DoctorReferral>> response) {
                        if (response.isSuccessful()) {
                            doctorReferrals = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<DoctorReferral>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке списка направлений ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<DoctorReferral> doctorReferralList) {
        DoctorReferralAdapter doctorReferralAdapter = new DoctorReferralAdapter(doctorReferralList, this);
        recyclerView.setAdapter(doctorReferralAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),DoctorReferralItemActivity.class);
        intent.putExtra("id",String.valueOf(doctorReferrals.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}