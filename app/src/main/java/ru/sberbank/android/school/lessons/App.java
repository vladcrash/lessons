package ru.sberbank.android.school.lessons;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.sberbank.android.school.lessons.data.db.WeatherDatabase;
import ru.sberbank.android.school.lessons.data.network.WeatherWebService;

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
    private static Executor executor;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherWebService = retrofit.create(WeatherWebService.class);

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
