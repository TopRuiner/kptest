package com.example.kptest.retrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kptest.AuthenticateActivity;
import com.example.kptest.MainActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RetrofitService {
    private Retrofit retrofit;
    private String token;

    public RetrofitService() {

        initRetrofit();
    }

    //    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request newRequest  = chain.request().newBuilder()
//                    .addHeader("Authorization", "Bearer " + token)
//                    .build();
//            return chain.proceed(newRequest);
//        }
//    }).build();

    private void initRetrofit() {
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        builder.readTimeout(10, TimeUnit.SECONDS);
//        builder.connectTimeout(5, TimeUnit.SECONDS);
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        builder.addInterceptor(interceptor);
//
//        builder.addInterceptor(chain -> {
//            Request request = chain.request().newBuilder().addHeader("key", "value").build();
//            return chain.proceed(request);
//        });

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setLenient()
                .create();
        Gson gsonDateTime = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .setLenient()
                .create();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8081")
//                .baseUrl("http://192.168.3.37:8081")


//                .addConverterFactory(GsonConverterFactory.create(gsonDateTime))
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
