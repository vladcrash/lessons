package com.example.admin.lesson5;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.admin.lesson5.db.WeatherDatabase;
import com.example.admin.lesson5.network.WeatherWebService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static final String DATABASE_NAME = "weather-database";
    private static final String VERSION_API = "v1/";
    private static final String BASE_URL = "https://api.weather.yandex.ru/" + VERSION_API;

    private static WeatherWebService weatherWebService;
    private static WeatherDatabase database;
    private Retrofit mRetrofit;
    private static Executor executor;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherWebService = mRetrofit.create(WeatherWebService.class);

        database = Room.databaseBuilder(getApplicationContext(), WeatherDatabase.class, DATABASE_NAME)
                .build();

        executor = Executors.newSingleThreadExecutor();
    }

    public static WeatherWebService getWeatherWebService() {
        return weatherWebService;
    }

    public static WeatherDatabase getDatabase() {
        return database;
    }

    public static Executor getExecutor() {
        return executor;
    }
}
