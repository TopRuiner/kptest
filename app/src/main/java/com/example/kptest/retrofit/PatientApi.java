package com.example.kptest.retrofit;

import com.example.kptest.model.Patient;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PatientApi {
    @POST("/api/getPatientCard")
    Call<Patient> getPatientCard(@Header("Authorization") String jwtToken);
}
