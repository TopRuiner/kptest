package com.example.kptest;


import static org.assertj.core.api.Assertions.assertThat;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;


import com.example.kptest.model.auth.AuthenticationRequest;
import com.example.kptest.model.auth.AuthenticationResponse;
import com.example.kptest.model.auth.CheckPatientRoleRequest;
import com.example.kptest.retrofit.AuthenticationApi;
import com.example.kptest.retrofit.RetrofitService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String jwt;

    @Test
    public void incorrectLogin() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("incorrectEmail@gmail.com");
        authenticationRequest.setPassword("123");
        Call<AuthenticationResponse> authenticationResponseCall = authenticationApi.authenticate(authenticationRequest);
        try {
            Response<AuthenticationResponse> response = authenticationResponseCall.execute();
            AuthenticationResponse authenticationResponse = response.body();
            assertThat(response.isSuccessful()).isEqualTo(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void incorrectPassword() {
        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("123");
        Call<AuthenticationResponse> authenticationResponseCall = authenticationApi.authenticate(authenticationRequest);
        try {
            Response<AuthenticationResponse> response = authenticationResponseCall.execute();
            AuthenticationResponse authenticationResponse = response.body();
            assertThat(response.isSuccessful()).isEqualTo(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void authenticateAndCheckJwt() {
        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@gmail.com");
        authenticationRequest.setPassword("1234");
        jwt = "Bearer ";

        Call<AuthenticationResponse> authenticationResponseCall = authenticationApi.authenticate(authenticationRequest);
        try {
            Response<AuthenticationResponse> response = authenticationResponseCall.execute();
            AuthenticationResponse authenticationResponse = response.body();
            jwt += authenticationResponse.getToken();
            assertThat(response.isSuccessful()).isEqualTo(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Call<Boolean> checkJwtResponseCall = authenticationApi.checkAuthentication(jwt);
        try {
            Response<Boolean> response = checkJwtResponseCall.execute();
            Boolean checkJwtResponse = response.body();
            assertThat(response.isSuccessful()).isEqualTo(true);
            assertThat(checkJwtResponse).isEqualTo(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void authenticateAsPatient(){
        RetrofitService retrofitService = new RetrofitService();
        AuthenticationApi authenticationApi = retrofitService.getRetrofit().create(AuthenticationApi.class);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        String patientEmail = "testPatient@gmail.com";
        authenticationRequest.setEmail(patientEmail);
        authenticationRequest.setPassword("1234");
        jwt = "Bearer ";
        Call<AuthenticationResponse> authenticationResponseCall = authenticationApi.authenticate(authenticationRequest);
        try {
            Response<AuthenticationResponse> response = authenticationResponseCall.execute();
            AuthenticationResponse authenticationResponse = response.body();
            jwt += authenticationResponse.getToken();
            assertThat(response.isSuccessful()).isEqualTo(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CheckPatientRoleRequest checkPatientRoleRequest = new CheckPatientRoleRequest();
        checkPatientRoleRequest.setEmail(patientEmail);
        Call<Boolean> checkPatientRoleCall = authenticationApi.checkPatientRole(jwt,checkPatientRoleRequest);
        try {
            Response<Boolean> response = checkPatientRoleCall.execute();
            Boolean isPatientResponse = response.body();
            assertThat(response.isSuccessful()).isEqualTo(true);
            assertThat(isPatientResponse).isEqualTo(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}