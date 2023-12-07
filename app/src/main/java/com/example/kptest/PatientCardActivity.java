package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kptest.model.Patient;
import com.example.kptest.retrofit.PatientApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

        initComponents();
    }

    private void initComponents() {
        TextView fioTextView = findViewById(R.id.fioTextView);
        TextView birthDateTextView = findViewById(R.id.birthDateTextView);
        TextView polisNumberTextView = findViewById(R.id.polisNumberTextView);
        TextView polisCompanyTextView = findViewById(R.id.polisCompanyTextView);
        TextView polisEndDateTextView = findViewById(R.id.polisEndDateTextView);
        TextView snilsNumberTextView = findViewById(R.id.snilsNumberTextView);
        TextView workPlaceTextView = findViewById(R.id.workPlaceTextView);
        TextView workPositionTextView = findViewById(R.id.workPositionTextView);
        TextView homeAddresTextView = findViewById(R.id.homeAddresTextView);

        RetrofitService retrofitService = new RetrofitService();
        PatientApi patientApi = retrofitService.getRetrofit().create(PatientApi.class);

        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN",MODE_PRIVATE);
        String jwt = sharedPreferences.getString("JWT", "");
        String email = sharedPreferences.getString("email","");

        patientApi.getPatientCard(jwt).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.isSuccessful()){
//                    System.out.println("--------------------");
//                    System.out.println(response.body());
                    Patient patient = response.body();
                    fioTextView.setText(patient.ReturnFIO());
                    birthDateTextView.setText(patient.ReturnBirthDate());
                    polisNumberTextView.setText(patient.getPolisId());
                    polisCompanyTextView.setText(patient.getPoilsCompany());
                    polisEndDateTextView.setText(patient.getStringPolisEndDate());
                    snilsNumberTextView.setText(patient.getSnilsNumber());
                    workPlaceTextView.setText(patient.getWorkPlace());
                    workPositionTextView.setText(patient.getWorkPosition());
                    homeAddresTextView.setText(patient.getHomeAddress());
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Logger.getLogger(DoctorItemActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                startActivity(intent);
            }
        });

    }
}