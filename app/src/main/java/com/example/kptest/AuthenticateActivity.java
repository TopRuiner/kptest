package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kptest.model.auth.AuthenticationRequest;
import com.example.kptest.model.auth.AuthenticationResponse;
import com.example.kptest.model.auth.CheckPatientRoleRequest;
import com.example.kptest.retrofit.AuthenticationApi;
import com.example.kptest.retrofit.DoctorApi;
import com.example.kptest.retrofit.RetrofitService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);


        initComponents();

    }

    private void initComponents() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        EditText editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Button authenticateButton = findViewById(R.id.authenticateButton);
        authenticateButton.setOnClickListener(v -> {
            RetrofitService retrofitService = new RetrofitService();
            AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setEmail(editTextEmailAddress.getText().toString());
            authenticationRequest.setPassword(editTextPassword.getText().toString());

            authenticationApi.authenticate(authenticationRequest).enqueue(new Callback<AuthenticationResponse>() {
                @Override
                public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                    if (response.isSuccessful()){
                        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("JWT","Bearer "+response.body().getToken());
                        editor.putString("email",response.body().getEmail());
                        editor.apply();

                        //todo Удалить сообщение с токеном
                        Toast.makeText(AuthenticateActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                        String jwt = sharedPreferences.getString("JWT", "");
                        String email = sharedPreferences.getString("email","");

                        CheckPatientRoleRequest checkPatientRoleRequest = new CheckPatientRoleRequest();
                        checkPatientRoleRequest.setEmail(email);
                        System.out.println("----------------------------------- "+checkPatientRoleRequest.getEmail());
                        authenticationApi.checkPatientRole(jwt,checkPatientRoleRequest).enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                System.out.println(response.isSuccessful());
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

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                                startActivity(intent);
                                Toast.makeText(AuthenticateActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                                System.out.println(t.fillInStackTrace());
                            }
                        });
//                        //todo Возможно лучше на MainActivity для проверки токена отправлять
//                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                        startActivity(intent);
                    }
                    else {
                        System.out.println(response.body());
                        Toast.makeText(AuthenticateActivity.this, "Ошибка при входе в аккаунт", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                    Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                    startActivity(intent);
                    Toast.makeText(AuthenticateActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                    System.out.println(t.fillInStackTrace());

                }
            });

        });
//        Button testButton = findViewById(R.id.testButton);
//        testButton.setOnClickListener(v -> {
////            Toast.makeText(AuthenticateActivity.this, "L", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), DoctorListActivity.class);
//            startActivity(intent);
//        });

    }
}