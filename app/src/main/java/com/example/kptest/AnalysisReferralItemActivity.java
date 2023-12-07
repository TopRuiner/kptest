package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.DoctorReferral;
import com.example.kptest.retrofit.AnalysisReferralApi;
import com.example.kptest.retrofit.DoctorReferralApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisReferralItemActivity extends AppCompatActivity {
    private AnalysisReferral analysisReferral;
    private TextView whatToResearch, diagnosis, date, cabinetSchedule, cabinetNumber, cabinetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_referral_item);

        whatToResearch = findViewById(R.id.whatToResearchTextView);
        diagnosis = findViewById(R.id.diagnosisTextView);
        cabinetSchedule = findViewById(R.id.cabinetScheduleTextView);
        cabinetNumber = findViewById(R.id.cabinetNumberTextView);
        cabinetName = findViewById(R.id.cabinetNameTextView);
        date = findViewById(R.id.dateTextView);

        loadAnalysisReferral();

    }

    private void loadAnalysisReferral() {
        RetrofitService retrofitService = new RetrofitService();
        AnalysisReferralApi analysisReferralApi = retrofitService.getRetrofit().create(AnalysisReferralApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        analysisReferralApi.getAnalysisReferralById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<AnalysisReferral>() {
                    @Override
                    public void onResponse(Call<AnalysisReferral> call, Response<AnalysisReferral> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<AnalysisReferral> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке направления ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }
    private void populatePage(AnalysisReferral analysisReferral) {
        whatToResearch.setText(analysisReferral.getWhatToResearch());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(analysisReferral.getDateOfIssue().format(CUSTOM_FORMATTER));
        diagnosis.setText(analysisReferral.getDiagnosis().ReturnIdAndDescription());
        cabinetSchedule.setText(analysisReferral.getCabinet().getSchedule());
        cabinetNumber.setText(analysisReferral.getCabinet().getNumber());
        cabinetName.setText(analysisReferral.getCabinet().getName());
    }
}