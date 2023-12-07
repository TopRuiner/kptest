package com.example.kptest.retrofit;

import com.example.kptest.model.Doctor;
import com.example.kptest.model.appointment.MakeAppointmentRequest;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoctorApi {
    @GET("/api/doctors")
    Call<List<Doctor>> getAllDoctors(@Header("Authorization") String jwtToken);
    @GET("/api/doctors/{id}")
    Call<Doctor> getDoctorById(@Header("Authorization") String jwtToken, @Path("id")long id);
    @GET("/api/doctors/getAllTherapists")
    Call<List<Doctor>> getAllTherapists(@Header("Authorization") String jwtToken);

    @GET("/api/doctors/GetAppointmentTimes/{doctorId}")
    Call<List<String>> getAppointmentTimes(@Header("Authorization")String jwtToken, @Path("doctorId")long id, @Query("dateChosen")Date date);
    @POST("/api/doctors/MakeAppointment")
    Call<Boolean> makeAppointment(@Header("Authorization")String jwtToken, @Body MakeAppointmentRequest makeAppointmentRequest);
}
