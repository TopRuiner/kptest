package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.Doctor;
import com.example.kptest.model.Inspection;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.InspectionApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionItemActivity extends AppCompatActivity {

    private Inspection inspection;
    private TextView type, date, diagnosis, recipe, doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item);

        type = findViewById(R.id.typeTextView);
        date = findViewById(R.id.dateTextView);
        diagnosis = findViewById(R.id.diagnosisTextView);
        recipe = findViewById(R.id.recepieTextView);
        doctor = findViewById(R.id.doctorTextView);

        loadInspection();

    }

    private void loadInspection() {
        RetrofitService retrofitService = new RetrofitService();
        InspectionApi inspectionApi = retrofitService.getRetrofit().create(InspectionApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        inspectionApi.getInspectionById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<Inspection>() {
                    @Override
                    public void onResponse(Call<Inspection> call, Response<Inspection> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Inspection> call, Throwable t) {
                        Toast.makeText(InspectionItemActivity.this, "Ошибка при загрузке доктора", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }

    private void populatePage(Inspection inspection) {
        type.setText(inspection.getType());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(inspection.getDate().format(CUSTOM_FORMATTER));
        diagnosis.setText(inspection.getDiagnosis().getDescription());
        recipe.setText(inspection.getRecipe());
        doctor.setText(inspection.getDoctor().ReturnFIOAndSpeciality());
    }
}