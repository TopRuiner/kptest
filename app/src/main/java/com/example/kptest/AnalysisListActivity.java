package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kptest.adapter.AnalysisAdapter;
import com.example.kptest.adapter.ExaminationAdapter;
import com.example.kptest.model.Analysis;
import com.example.kptest.model.Examination;
import com.example.kptest.retrofit.AnalysisApi;
import com.example.kptest.retrofit.ExaminationApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<Analysis> analysisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_list);

        recyclerView = findViewById(R.id.analysisList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAnalyses();

    }

    private void loadAnalyses() {
        RetrofitService retrofitService = new RetrofitService();
        AnalysisApi analysisApi = retrofitService.getRetrofit().create(AnalysisApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        analysisApi.getAllAnalyses(jwt)
                .enqueue(new Callback<List<Analysis>>() {
                    @Override
                    public void onResponse(Call<List<Analysis>> call, Response<List<Analysis>> response) {
                        if (response.isSuccessful()) {
                            analysisList = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Analysis>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке списка осмотров", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<Analysis> analysisList) {
        AnalysisAdapter analysisAdapter = new AnalysisAdapter(analysisList, this);
        recyclerView.setAdapter(analysisAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),AnalysisItemActivity.class);
        intent.putExtra("id",String.valueOf(analysisList.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }
}