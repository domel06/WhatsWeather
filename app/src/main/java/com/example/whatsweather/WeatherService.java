package com.example.whatsweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    String BASE_URL = "http://api.openweathermap.org/";
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q") String CityName,  @Query("APPID") String app_id);


}
