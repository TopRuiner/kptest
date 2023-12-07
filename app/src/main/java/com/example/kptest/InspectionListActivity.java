package com.example.kptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kptest.adapter.DoctorAdapter;
import com.example.kptest.adapter.InspectionAdapter;
import com.example.kptest.model.Doctor;
import com.example.kptest.model.Inspection;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.InspectionApi;
import com.example.kptest.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionListActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<Inspection> inspectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_list);

        recyclerView = findViewById(R.id.inspectionList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadInspections();

    }

    private void loadInspections() {
        RetrofitService retrofitService = new RetrofitService();
        InspectionApi inspectionApi = retrofitService.getRetrofit().create(InspectionApi.class);

        SharedPreferences sp = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sp.getString("JWT", "");

        inspectionApi.getAllInspections(jwt)
                .enqueue(new Callback<List<Inspection>>() {
                    @Override
                    public void onResponse(Call<List<Inspection>> call, Response<List<Inspection>> response) {
                        if (response.isSuccessful()) {
                            inspectionList = response.body();
                            populateListView(response.body());
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), AuthenticationFailed.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Inspection>> call, Throwable t) {
                        Toast.makeText(InspectionListActivity.this, "Ошибка при загрузке списка осмотров", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(InspectionListActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateListView(List<Inspection> inspectionList) {
        InspectionAdapter inspectionAdapter = new InspectionAdapter(inspectionList, this);
        recyclerView.setAdapter(inspectionAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(InspectionListActivity.this,InspectionItemActivity.class);
        intent.putExtra("id",String.valueOf(inspectionList.get(position).getId()));
//        System.out.println(doctorList.get(position).getId());
        startActivity(intent);
    }

}