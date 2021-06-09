package com.example.whatsweather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button findBtn;
    private TextView weatherData;
    private EditText cityName;
    private String appID="fc662edd9b422f3b8f98a61b809bf6ba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBtn = (Button)findViewById(R.id.findBtn);
        weatherData = (TextView) findViewById(R.id.showBtn);
        cityName = (EditText) findViewById(R.id.adress);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
//        FontUtils fontUtils = new FontUtils();
//        fontUtils.applyFontToView(weatherData, typeface);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }

            private void getCurrentData() {
              String q = cityName.getText().toString();
                Call<WeatherResponse> call = RetrofitClient.getInstance().getMyApi().getCurrentWeatherData(q,appID);
                call.enqueue(new Callback<WeatherResponse>(){

                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.code() == 200) {
                            WeatherResponse weatherResponse = response.body();
                            assert weatherResponse != null;

                            String stringBuilder = "Country: " +
                                    weatherResponse.sys.country +
                                    "\n" +
                                    "Temperature: " +
                                    weatherResponse.main.temp +
                                    "\n" +
                                    "Temperature(Min): " +
                                    weatherResponse.main.temp_min +
                                    "\n" +
                                    "Temperature(Max): " +
                                    weatherResponse.main.temp_max +
                                    "\n" +
                                    "Humidity: " +
                                    weatherResponse.main.humidity +
                                    "\n" +
                                    "Pressure: " +
                                    weatherResponse.main.pressure;

                            weatherData.setText(stringBuilder);

                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        weatherData.setText(t.getMessage());
                    }
                });
            }
        });

    }
}