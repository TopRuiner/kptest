package com.example.kptest.retrofit;

import com.example.kptest.model.AnalysisReferral;
import com.example.kptest.model.DoctorReferral;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AnalysisReferralApi {
    @GET("/api/analysisReferrals")
    Call<List<AnalysisReferral>> getAllAnalysisReferrals(@Header("Authorization") String jwtToken);
    @GET("/api/analysisReferrals/{id}")
    Call<AnalysisReferral> getAnalysisReferralById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
