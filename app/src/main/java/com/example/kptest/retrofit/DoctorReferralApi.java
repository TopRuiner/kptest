package com.example.kptest.retrofit;

import com.example.kptest.model.DoctorReferral;
import com.example.kptest.model.Examination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DoctorReferralApi {
    @GET("/api/doctorReferrals")
    Call<List<DoctorReferral>> getAllDoctorReferrals(@Header("Authorization") String jwtToken);
    @GET("/api/doctorReferrals/{id}")
    Call<DoctorReferral> getDoctorReferralById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
