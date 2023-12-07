package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.DoctorReferral;
import com.example.kptest.model.Examination;
import com.example.kptest.retrofit.DoctorReferralApi;
import com.example.kptest.retrofit.ExaminationApi;
import com.example.kptest.retrofit.RetrofitService;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorReferralItemActivity extends AppCompatActivity {

    private DoctorReferral doctorReferral;
    private TextView speciality, cabinet, doctorTarget, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_referral_item);

        speciality = findViewById(R.id.specialityTextView);
        cabinet = findViewById(R.id.cabinetTextView);
        doctorTarget = findViewById(R.id.doctorTargetTextView);
        date = findViewById(R.id.dateTextView);

        loadDoctorReferral();

    }

    private void loadDoctorReferral() {
        RetrofitService retrofitService = new RetrofitService();
        DoctorReferralApi doctorReferralApi = retrofitService.getRetrofit().create(DoctorReferralApi.class);
        System.out.println(getIntent().getStringExtra("id"));

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        doctorReferralApi.getDoctorReferralById(jwt,Long.parseLong(getIntent().getStringExtra("id")))
                .enqueue(new Callback<DoctorReferral>() {
                    @Override
                    public void onResponse(Call<DoctorReferral> call, Response<DoctorReferral> response) {
                        if (response.isSuccessful()) {
                            populatePage(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<DoctorReferral> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка при загрузке направления ко врачу", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);

                    }
                });
    }
    private void populatePage(DoctorReferral doctorReferral) {
        speciality.setText(doctorReferral.getDoctorTarget().getSpeciality());
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date.setText(doctorReferral.getDateOfTaking().format(CUSTOM_FORMATTER));
        doctorTarget.setText(doctorReferral.getDoctorTarget().ReturnFIO());
        cabinet.setText(doctorReferral.getDoctorTarget().getCabinet().getNumber());
    }
}