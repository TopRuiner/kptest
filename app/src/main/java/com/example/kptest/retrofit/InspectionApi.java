package com.example.kptest.retrofit;

import com.example.kptest.model.Doctor;
import com.example.kptest.model.Inspection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InspectionApi {
    @GET("/api/inspections")
    Call<List<Inspection>> getAllInspections(@Header("Authorization") String jwtToken);
    @GET("/api/inspections/{id}")
    Call<Inspection> getInspectionById(@Header("Authorization") String jwtToken, @Path("id")long id);
}
