package com.example.kptest.retrofit;

import com.example.kptest.model.Examination;
import com.example.kptest.model.Inspection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ExaminationApi {
    @GET("/api/examinations")
    Call<List<Examination>> getAllExaminations(@Header("Authorization") String jwtToken);
    @GET("/api/examinations/{id}")
    Call<Examination> getExaminationById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
