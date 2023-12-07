package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.DoctorAdapter;
import com.example.kptest.model.Doctor;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<Doctor> doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recyclerView = findViewById(R.id.doctorList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDoctors();

    }

    private void loadDoctors() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorApi doctorApi = retrofitService.getRetrofit().create(DoctorApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        doctorApi.getAllDoctors(jwt)
                .enqueue(new Callback<List<Doctor>>() {
                    @Override
                    public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                        if (response.isSuccessful()) {
                            doctorList = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Doctor>> call, Throwable t) {
                        Toast.makeText(DoctorListActivity.this, "Ошибка при загрузке списка докторов", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<Doctor> doctorList) {
        DoctorAdapter doctorAdapter = new DoctorAdapter(doctorList, this);
        recyclerView.setAdapter(doctorAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(DoctorListActivity.this,DoctorItemActivity.class);
        intent.putExtra("id",String.valueOf(doctorList.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}