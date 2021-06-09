package com.example.whatsweather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private WeatherService myApi;
    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeatherService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(WeatherService.class);
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance = new RetrofitClient();

        }
        return instance;
    }

    public WeatherService getMyApi() {
        return myApi;
    }
}
