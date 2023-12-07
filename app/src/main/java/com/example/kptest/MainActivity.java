package com.example.kptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kptest.model.auth.CheckPatientRoleRequest;
import com.example.kptest.retrofit.AuthenticationApi;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kptest.databinding.ActivityMainBinding;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String jwt = sharedPreferences.getString("JWT", "");
        String email = sharedPreferences.getString("email","");

        //todo Убрать очистку токена при входе
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("JWT");
//        editor.apply();
//        jwt = "";


        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);
        authenticationApi.checkAuthentication(jwt)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.FINE, String.valueOf(response.isSuccessful()));
//                        Toast.makeText(MainActivity.this, String.valueOf(response.isSuccessful()), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, String.valueOf(response.isSuccessful()), Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful() && response.body()==true){
                            CheckPatientRoleRequest checkPatientRoleRequest = new CheckPatientRoleRequest();
                            checkPatientRoleRequest.setEmail(email);
                            authenticationApi.checkPatientRole(jwt,checkPatientRoleRequest).enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    if (response.isSuccessful()){
                                        if (response.body()==true){
                                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Intent intent = new Intent(getApplicationContext(), ConfirmPatientActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    else {
                                        Intent intent = new Intent(getApplicationContext(), LoginRegisterActivity.class);
                                        startActivity(intent);
                                    }
                                }


//                                Intent intent = new Intent(getApplicationContext(), LoginRegisterActivity.class);
//                                startActivity(intent);


                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {
                                    Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                                    System.out.println(t.fillInStackTrace());
                                }
                            });
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), LoginRegisterActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        //todo Страница когда запрос провалился
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                        System.out.println(t.fillInStackTrace());
                    }
                });

//        setContentView(R.layout.login_register);
//
//        initComponents();


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//
//        setContentView(R.layout.create_appointment);
//
//
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void initComponents() {
//        Button testButton = findViewById(R.id.testButton);
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),DoctorListActivity.class);
//                startActivity(intent);
//            }
//        });

//        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.remove("JWT");
//        editor.apply();

        Button authenticateButton = findViewById(R.id.authenticateButton);
        authenticateButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AuthenticateActivity.class);
            startActivity(intent);
        });
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }

}