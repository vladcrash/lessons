package ru.sberbank.android.school.lessons.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.sberbank.android.school.lessons.data.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;
import ru.sberbank.android.school.lessons.domain.model.Weather;

@Database(entities = {Weather.class, Forecast.class, Hour.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract ForecastDao forecastDao();
    public abstract HourDao hourDao();
}
