package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.Examination;
import com.example.kptest.model.Inspection;
import com.example.kptest.retrofit.ExaminationApi;
import com.example.kptest.retrofit.InspectionApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationItemActivity extends AppCompatActivity {
    private Examination examination;
    private TextView type, date, doctor, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_item);

        type = findViewById(R.id.typeTextView);
        date = findViewById(R.id.dateTextView);
        report = findViewById(R.id.reportTextView);
        doctor = findViewById(R.id.doctorTextView);

        loadExamination();

    }

    private void loadExamination() {
        RetrofitService retrofitService = new RetrofitService();
        ExaminationApi examinationApi = retrofitService.getRetrofit().create(ExaminationApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        examinationApi.getExaminationById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<Examination>() {
                    @Override
                    public void onResponse(Call<Examination> call, Response<Examination> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Examination> call, Throwable t) {
                        Toast.makeText(ExaminationItemActivity.this, "Ошибка при загрузке доктора", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }
    private void populatePage(Examination examination) {
        type.setText(examination.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(examination.getDate().format(CUSTOM_FORMATTER));
        report.setText(examination.getReport());
        doctor.setText(examination.getFunctionalDiagnosticsDoctor().ReturnFIO());
    }
}