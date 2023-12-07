package com.example.kptest.retrofit;

import com.example.kptest.model.Patient;
import com.example.kptest.model.auth.AuthenticationRequest;
import com.example.kptest.model.auth.AuthenticationResponse;
import com.example.kptest.model.auth.CheckPatientRoleRequest;
import com.example.kptest.model.auth.PatientRequest;
import com.example.kptest.model.auth.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthenticationApi {
    @POST("/api/auth/authenticate")
    Call<AuthenticationResponse> authenticate(@Body AuthenticationRequest authenticationRequest);

    @POST("/api/checkAuthentication")
    Call<Boolean> checkAuthentication(@Header("Authorization") String jwtToken);

    @POST("/api/auth/register")
    Call<AuthenticationResponse> register(@Body RegisterRequest registerRequest);

    //todo Email по токену можно
    @POST("/api/checkPatientRole")
    Call<Boolean> checkPatientRole(@Header("Authorization") String jwtToken, @Body CheckPatientRoleRequest checkPatientRoleRequest);

    //todo Email по токену можно
    @POST("/api/setPatientRole")
    Call<Boolean> setPatientRole(@Header("Authorization") String jwtToken, @Body PatientRequest patientRequest);
}
