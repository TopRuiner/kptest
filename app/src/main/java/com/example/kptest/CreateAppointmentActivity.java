package com.example.kptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kptest.adapter.DoctorArrayAdapter;
import com.example.kptest.model.Doctor;
import com.example.kptest.model.appointment.MakeAppointmentRequest;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.RetrofitService;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAppointmentActivity extends AppCompatActivity {
    private List<Doctor> doctorList;
    private DoctorArrayAdapter doctorArrayAdapter;
    private Long clickedDoctorId = 0L;
    private Date appointmentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        initComponents();
    }

    private void initComponents() {
        Spinner doctorSpinner = findViewById(R.id.doctorSpinner);
        Spinner appointmentTimeSpinner = findViewById(R.id.appointmentTimeSpinner);
        List<String> emptyAppointmentTimeList = new ArrayList<>();


        CalendarView calendarView = findViewById(R.id.calendarView);
        ZonedDateTime minDate = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime maxDate = ZonedDateTime.of(LocalDateTime.now().plusDays(14), ZoneId.systemDefault());
        calendarView.setMinDate(minDate.toInstant().toEpochMilli());
        calendarView.setMaxDate(maxDate.toInstant().toEpochMilli());
        calendarView.setEnabled(false);
        calendarView.setVisibility(View.INVISIBLE);
        calendarView.setDefaultFocusHighlightEnabled(false);
        calendarView.setFocusedByDefault(false);

        RetrofitService retrofitService = new RetrofitService();
        DoctorApi doctorApi = retrofitService.getRetrofit().create(DoctorApi.class);
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sharedPreferences.getString("JWT", "");
        String email = sharedPreferences.getString("email", "");
        Context currentContext = this;
        doctorList = new ArrayList<>();
        Doctor placeholderDoctor = new Doctor();
        placeholderDoctor.setId(0);
        placeholderDoctor.setFirstName("Выберите доктора");
        placeholderDoctor.setLastName("");
        placeholderDoctor.setMiddleName("");

        List<String> appointmentTimeList = new ArrayList<>();
        appointmentTimeList.add("Выберите время");


        Button createAppointmentButton = findViewById(R.id.createAppointmentButton);
        createAppointmentButton.setEnabled(false);
        createAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeAppointmentRequest appointmentRequest = new MakeAppointmentRequest();
                appointmentRequest.setPatientEmail(email);
                appointmentRequest.setDoctorId(clickedDoctorId);
                appointmentRequest.setAppointmentDate(appointmentDate);
//                appointmentRequest.setAppointmentTime(Time.valueOf(String.valueOf(appointmentTimeSpinner.getSelectedItem())));
                appointmentRequest.setAppointmentTime(String.valueOf(appointmentTimeSpinner.getSelectedItem()));

                System.out.println("--------------------------");
                System.out.println(appointmentRequest.toString());
                List<String> appointmentTimeList = new ArrayList<>();
                appointmentTimeList.add("Выберите время");
                doctorApi.makeAppointment(jwt, appointmentRequest).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful()) {
                            if (response.body() == true) {
                                Toast.makeText(CreateAppointmentActivity.this, "Запись создана", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CreateAppointmentActivity.this, "Запись на данное время уже недоступна", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
//                Toast.makeText(CreateAppointmentActivity.this, String.valueOf(appointmentTimeSpinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
            }
        });


        doctorApi.getAllTherapists(jwt)
                .enqueue(new Callback<List<Doctor>>() {
                    @Override
                    public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                        if (response.isSuccessful()) {
                            doctorList.add(placeholderDoctor);
                            doctorList.addAll(response.body());
//                            System.out.println("---------------------------------");
//                            System.out.println(doctorList);
                            doctorArrayAdapter = new DoctorArrayAdapter(currentContext, doctorList);
                            doctorSpinner.setAdapter(doctorArrayAdapter);
                            doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Doctor clickedDoctor = (Doctor) parent.getItemAtPosition(position);
                                    if (clickedDoctor.getId() == 0) {
//                                        Toast.makeText(CreateAppointmentActivity.this,"Placeholder Selected", Toast.LENGTH_SHORT).show();
                                        calendarView.setVisibility(View.INVISIBLE);
                                        clickedDoctorId = 0L;
                                        List<String> appointmentTimeList = new ArrayList<>();
                                        appointmentTimeList.add("Выберите время");
                                    } else {
                                        clickedDoctorId = clickedDoctor.getId();
                                        calendarView.setVisibility(View.VISIBLE);
                                        Toast.makeText(CreateAppointmentActivity.this, clickedDoctorId + " selected", Toast.LENGTH_SHORT).show();


                                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                            @Override
                                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                                                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                                                LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
                                                appointmentDate = Date.valueOf(LocalDate.of(year, month, dayOfMonth).toString());
//                                                System.out.println("----------------");
//                                                System.out.println(date.toString());

                                                doctorApi.getAppointmentTimes(jwt, clickedDoctorId, appointmentDate)
                                                        .enqueue(new Callback<List<String>>() {
                                                            @Override
                                                            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                                                                if (response.isSuccessful()) {
//                                                                    Toast.makeText(view.getContext(), "Sent", Toast.LENGTH_LONG).show();
                                                                    System.out.println("----------------");
                                                                    System.out.println(response.body());
                                                                    List<String> appointmentTimeList = new ArrayList<>();
                                                                    appointmentTimeList.add("Выберите время");
                                                                    appointmentTimeList.addAll(response.body());


                                                                    ArrayAdapter<String> appointmentTimeAdapter = new ArrayAdapter<>(
                                                                            currentContext,
                                                                            android.R.layout.simple_spinner_dropdown_item,
                                                                            appointmentTimeList
                                                                    );
                                                                    appointmentTimeSpinner.setAdapter(appointmentTimeAdapter);
                                                                    appointmentTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                        @Override
                                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                            String clickedTime = (String) parent.getItemAtPosition(position);
                                                                            if (clickedTime.equals("Выберите время")) {
                                                                            } else {
                                                                                createAppointmentButton.setEnabled(true);
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                                        }
                                                                    });


                                                                } else {
                                                                    System.out.println("----------------");
                                                                    System.out.println(response.raw());
                                                                    Toast.makeText(view.getContext(), "Error " + response.errorBody(), Toast.LENGTH_LONG).show();

                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<List<String>> call, Throwable t) {

                                                            }
                                                        });
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Doctor>> call, Throwable t) {
                        Toast.makeText(CreateAppointmentActivity.this, "Ошибка при загрузке списка врачей", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(DoctorListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });

    }
}