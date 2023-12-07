package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initComponents();
    }

    private void initComponents() {
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