package com.example.admin.lesson5.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.admin.lesson5.db.dao.ForecastDao;
import com.example.admin.lesson5.db.dao.HourDao;
import com.example.admin.lesson5.model.Forecast;
import com.example.admin.lesson5.model.Hour;
import com.example.admin.lesson5.model.Weather;

@Database(entities = {Weather.class, Forecast.class, Hour.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract ForecastDao forecastDao();
    public abstract HourDao hourDao();
}
