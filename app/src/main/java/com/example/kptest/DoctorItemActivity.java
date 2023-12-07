package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.Doctor;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorItemActivity extends AppCompatActivity {

    private Doctor doctor;
    private TextView doctorFIO, doctorSpeciality, doctorCategory, doctorDegree, cabinet, schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_item);

        doctorFIO = findViewById(R.id.doctorFIO);
        doctorSpeciality = findViewById(R.id.doctorSpeciality);
        doctorCategory = findViewById(R.id.doctorCategory);
        doctorDegree = findViewById(R.id.doctorDegree);
        cabinet = findViewById(R.id.cabinet);
        schedule = findViewById(R.id.schedule);

        loadDoctor();

    }

    private void loadDoctor() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorApi doctorApi = retrofitService.getRetrofit().create(DoctorApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        doctorApi.getDoctorById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Doctor> call, Throwable t) {
                        Toast.makeText(DoctorItemActivity.this, "Ошибка при загрузке доктора", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }

    private void populatePage(Doctor doctor) {
        doctorFIO.setText(doctor.ReturnFIO());
        doctorSpeciality.setText(doctor.getSpeciality());
        doctorDegree.setText(doctor.getDegree());
        doctorCategory.setText(doctor.getCategory());
        cabinet.setText(doctor.getCabinet().ReturnNameAndNumber());
        schedule.setText(doctor.getCabinet().getSchedule());

    }

}