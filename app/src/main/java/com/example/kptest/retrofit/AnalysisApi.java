package com.example.kptest.retrofit;

import com.example.kptest.model.Analysis;
import com.example.kptest.model.Examination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AnalysisApi {
    @GET("/api/analyses")
    Call<List<Analysis>> getAllAnalyses(@Header("Authorization") String jwtToken);
    @GET("/api/analyses/{id}")
    Call<Analysis> getAnalysisById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
