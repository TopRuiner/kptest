package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.ExaminationReferral;
import com.example.kptest.retrofit.AnalysisReferralApi;
import com.example.kptest.retrofit.ExaminationReferralApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationReferralItemActivity extends AppCompatActivity {

    private ExaminationReferral examinationReferral;
    private TextView whatToResearch, diagnosis, date, cabinetSchedule, cabinetNumber, cabinetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_referral_item);

        whatToResearch = findViewById(R.id.whatToResearchTextView);
        diagnosis = findViewById(R.id.diagnosisTextView);
        cabinetSchedule = findViewById(R.id.cabinetScheduleTextView);
        cabinetNumber = findViewById(R.id.cabinetNumberTextView);
        cabinetName = findViewById(R.id.cabinetNameTextView);
        date = findViewById(R.id.dateTextView);

        loadExaminationReferral();

    }

    private void loadExaminationReferral() {
        RetrofitService retrofitService = new RetrofitService();
        ExaminationReferralApi examinationReferralApi = retrofitService.getRetrofit().create(ExaminationReferralApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        examinationReferralApi.getExaminationReferralById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<ExaminationReferral>() {
                    @Override
                    public void onResponse(Call<ExaminationReferral> call, Response<ExaminationReferral> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ExaminationReferral> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке направления ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }
    private void populatePage(ExaminationReferral examinationReferral) {
        whatToResearch.setText(examinationReferral.getWhatToResearch());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(examinationReferral.getDateOfIssue().format(CUSTOM_FORMATTER));
        diagnosis.setText(examinationReferral.getDiagnosis().ReturnIdAndDescription());
        cabinetSchedule.setText(examinationReferral.getCabinet().getSchedule());
        cabinetNumber.setText(examinationReferral.getCabinet().getNumber());
        cabinetName.setText(examinationReferral.getCabinet().getName());
    }
}