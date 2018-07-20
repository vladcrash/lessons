package com.example.admin.lesson5.network;

import com.example.admin.lesson5.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface WeatherWebService {

    @Headers("X-Yandex-API-Key: " + ApiKey.KEY)
    @GET("forecast?lat=55.75396&lon=37.620393&hours=true&limit=7")
    Call<Weather> getWeather();
}
