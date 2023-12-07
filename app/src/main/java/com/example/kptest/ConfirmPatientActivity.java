package com.example.kptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kptest.model.Patient;
import com.example.kptest.model.auth.PatientRequest;
import com.example.kptest.retrofit.AuthenticationApi;
import com.example.kptest.retrofit.RetrofitService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.SimpleTimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_patient);

        initComponents();

    }

    private void initComponents() {
        EditText editTextPolisEndDate = findViewById(R.id.editTextPolisEndDate);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextMiddleName = findViewById(R.id.editTextMiddleName);
        EditText editTextBirthDate = findViewById(R.id.editTextBirthDate);
        EditText editTextPolisId = findViewById(R.id.editTextPolisId);
        EditText editTextPoilsCompany = findViewById(R.id.editTextPoilsCompany);
        EditText editTextSnilsNumber = findViewById(R.id.editTextSnilsNumber);
        EditText editTextHomeAddress = findViewById(R.id.editTextHomeAddress);
        EditText editTextWorkPlace = findViewById(R.id.editTextWorkPlace);
        EditText editTextWorkPosition = findViewById(R.id.editTextWorkPosition);
        Button registerAsPatientButton = findViewById(R.id.registerAsPatientButton);

        registerAsPatientButton.setOnClickListener(v -> {
            Boolean everythingIsCorrect = true;
            String snilsNumberRegEx = "^\\d{3}-\\d{3}-\\d{3} \\d{2}$";
            String dateRegEx = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
            Date polisEndDate = null;
            if (TextUtils.isEmpty(editTextFirstName.getText())) {
                editTextFirstName.setError("Имя не может быть пустым");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextLastName.getText())) {
                editTextLastName.setError("Фамилия не может быть пуста");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextBirthDate.getText())) {
                editTextBirthDate.setError("Дата рождения не может быть пуста");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextPolisId.getText())) {
                editTextPolisId.setError("Номер полиса не может быть пустым");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextPoilsCompany.getText())) {
                editTextPoilsCompany.setError("Страховая организация не может быть пустым");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextSnilsNumber.getText())) {
                editTextSnilsNumber.setError("Номер СНИЛС не может быть пустым");
                everythingIsCorrect = false;
            }

            if (!TextUtils.isEmpty(editTextSnilsNumber.getText()) && !editTextSnilsNumber.getText().toString().matches(snilsNumberRegEx)) {
                editTextSnilsNumber.setError("СНИЛС должен быть в формате '123-123-123 12'");
                everythingIsCorrect = false;
            }
            if (!TextUtils.isEmpty(editTextPolisId.getText()) && editTextPolisId.getText().toString().length() != 16) {
                editTextPolisId.setError("Полис должен состоять из 16 цифр");
                everythingIsCorrect = false;
            }
            if (!TextUtils.isEmpty(editTextBirthDate.getText()) && !editTextBirthDate.getText().toString().matches(dateRegEx)) {
                editTextBirthDate.setError("Введите корректную дату рождения в формате 'дд.мм.ГГГГ'");
                everythingIsCorrect = false;
            }
            if (TextUtils.isEmpty(editTextPolisEndDate.getText())) {
                polisEndDate = null;
            }
            if (everythingIsCorrect) {
                Patient patient = new Patient();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                patient.setLastName(editTextLastName.getText().toString());
                patient.setFirstName(editTextFirstName.getText().toString());
                patient.setMiddleName(editTextMiddleName.getText().toString());
                java.util.Date birthDate = null;
                try {
                    birthDate = dateFormat.parse(editTextBirthDate.getText().toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                patient.setBirthDate(new java.sql.Date(birthDate.getTime()));
                patient.setPolisId(editTextPolisId.getText().toString());
                patient.setPoilsCompany(editTextPoilsCompany.getText().toString());
                patient.setPolisEndDate(polisEndDate);
                patient.setSnilsNumber(editTextSnilsNumber.getText().toString());
                patient.setHomeAddress(editTextHomeAddress.getText().toString());
                patient.setWorkPlace(editTextWorkPlace.getText().toString());
                patient.setWorkPosition(editTextWorkPosition.getText().toString());
                System.out.println(patient.toString());

                SharedPreferences sharedPreferences = getSharedPreferences("TOKEN",MODE_PRIVATE);
                String jwt = sharedPreferences.getString("JWT", "");
                String email = sharedPreferences.getString("email","");
                RetrofitService retrofitService = new RetrofitService();
                AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);

                PatientRequest patientRequest = new PatientRequest(patient,email);
                authenticationApi.setPatientRole(jwt,patientRequest).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(intent);
                            Toast.makeText(ConfirmPatientActivity.this, "Не удалось присвоить роль пациента", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(ConfirmPatientActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                        System.out.println(t.fillInStackTrace());
                        Intent intent = new Intent(getApplicationContext(), ServerDownActivity.class);
                        startActivity(intent);
                    }
                });

            }

            System.out.println(editTextBirthDate.getText().toString());

        });


    }
}