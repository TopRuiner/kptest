package com.example.kptest.retrofit;

import com.example.kptest.model.DoctorReferral;
import com.example.kptest.model.ExaminationReferral;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ExaminationReferralApi {
    @GET("/api/examinationReferrals")
    Call<List<ExaminationReferral>> getAllExaminationReferrals(@Header("Authorization") String jwtToken);
    @GET("/api/examinationReferrals/{id}")
    Call<ExaminationReferral> getExaminationReferralById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
