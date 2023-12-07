package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.Analysis;
import com.example.kptest.model.Examination;
import com.example.kptest.retrofit.AnalysisApi;
import com.example.kptest.retrofit.ExaminationApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisItemActivity extends AppCompatActivity {

    private Analysis analysis;
    private TextView type, date, doctor, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_item);

        type = findViewById(R.id.typeTextView);
        date = findViewById(R.id.dateTextView);
        report = findViewById(R.id.reportTextView);
        doctor = findViewById(R.id.doctorTextView);

        loadAnalysis();

    }

    private void loadAnalysis() {
        RetrofitService retrofitService = new RetrofitService();
        AnalysisApi analysisApi = retrofitService.getRetrofit().create(AnalysisApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        analysisApi.getAnalysisById(jwt, Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<Analysis>() {
                    @Override
                    public void onResponse(Call<Analysis> call, Response<Analysis> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        } else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Analysis> call, Throwable t) {
                        Toast.makeText(AnalysisItemActivity.this, "Ошибка при загрузке доктора", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(AnalysisItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }

    private void populatePage(Analysis analysis) {
        type.setText(analysis.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(analysis.getDate().format(CUSTOM_FORMATTER));
        report.setText(analysis.getReport());
        doctor.setText(analysis.getAssistant().ReturnFIO());
    }
}