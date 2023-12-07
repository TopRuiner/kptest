package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kptest.model.auth.AuthenticationResponse;
import com.example.kptest.model.auth.RegisterRequest;
import com.example.kptest.retrofit.AuthenticationApi;
import com.example.kptest.retrofit.RetrofitService;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }

    private void initComponents() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Button registerButton = findViewById(R.id.registerButton);
        EditText editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        registerButton.setOnClickListener(v -> {
            RetrofitService retrofitService = new RetrofitService();
            AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setEmail(editTextEmailAddress.getText().toString());
            registerRequest.setPassword(editTextPassword.getText().toString());

            authenticationApi.register(registerRequest).enqueue(new Callback<AuthenticationResponse>() {
                @Override
                public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                    if (response.isSuccessful()) {

                        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("JWT","Bearer "+response.body().getToken());
                        editor.putString("email",response.body().getEmail());
                        editor.apply();

                        Toast.makeText(RegisterActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        System.out.println(response.body());
                        Toast.makeText(RegisterActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                    Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                    startActivity(intent);
                }
            });
        });
    }
}