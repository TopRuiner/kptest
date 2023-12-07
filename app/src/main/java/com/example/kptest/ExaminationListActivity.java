package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.ExaminationAdapter;
import com.example.kptest.adapter.InspectionAdapter;
import com.example.kptest.model.Examination;
import com.example.kptest.model.Inspection;
import com.example.kptest.retrofit.ExaminationApi;
import com.example.kptest.retrofit.InspectionApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationListActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private List<Examination> examinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_list);

        recyclerView = findViewById(R.id.examinationList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadExaminations();

    }

    private void loadExaminations() {
        RetrofitService retrofitService = new RetrofitService();
        ExaminationApi examinationApi = retrofitService.getRetrofit().create(ExaminationApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        examinationApi.getAllExaminations(jwt)
                .enqueue(new Callback<List<Examination>>() {
                    @Override
                    public void onResponse(Call<List<Examination>> call, Response<List<Examination>> response) {
                        if (response.isSuccessful()) {
                            examinationList = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Examination>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке списка осмотров", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<Examination> examinationList) {
        ExaminationAdapter examinationAdapter = new ExaminationAdapter(examinationList, this);
        recyclerView.setAdapter(examinationAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),ExaminationItemActivity.class);
        intent.putExtra("id",String.valueOf(examinationList.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}