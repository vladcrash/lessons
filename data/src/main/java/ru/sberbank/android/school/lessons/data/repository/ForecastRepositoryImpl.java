package ru.sberbank.android.school.lessons.data.repository;

import android.util.Log;

import java.util.List;

import io.reactivex.Single;
import ru.sberbank.android.school.lessons.data.db.WeatherDatabase;
import ru.sberbank.android.school.lessons.data.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;
import ru.sberbank.android.school.lessons.data.network.WeatherWebService;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class ForecastRepositoryImpl implements ForecastRepository {

    private static final String TAG = "ForecastRepositoryImpl";

    private WeatherWebService weatherWebService;
    private WeatherDatabase weatherDatabase;

    public ForecastRepositoryImpl(WeatherWebService weatherWebService, WeatherDatabase weatherDatabase) {
        this.weatherWebService = weatherWebService;
        this.weatherDatabase = weatherDatabase;
    }

    @Override
    public Single<List<Forecast>> getDailyForecasts(Boolean isRefresh){
        ForecastDao forecastDao = weatherDatabase.forecastDao();
        return forecastDao.getAll().flatMap(forecasts -> {
            if (forecasts.size() == 0 || isRefresh) {
                Log.wtf(TAG, "network");
                return weatherWebService.getWeather()
                        .flatMap(weather -> Single.just(saveForecasts(weather.getForecasts())));
            } else {
                Log.wtf(TAG, "database");
                return Single.just(forecasts);
            }
        });
    }


    @Override
    public Single<List<Hour>> getHourlyForecasts(Long dayId) {
        HourDao hourDao = weatherDatabase.hourDao();
        return hourDao.getHoursByForecastId(dayId);
    }

    private List<Forecast> saveForecasts(List<Forecast> forecasts) {
        ForecastDao forecastDao = weatherDatabase.forecastDao();
        HourDao hourDao = weatherDatabase.hourDao();
        forecastDao.deleteAll();
        long[] ids = forecastDao.insertAll(forecasts);
        for (int i = 0; i < forecasts.size(); i++) {
            Forecast forecast = forecasts.get(i);
            forecast.setId(ids[i]);
            List<Hour> hours = forecast.getHours();
            for (Hour hour : hours) {
                hour.setForecastId(ids[i]);
            }
            hourDao.insertAll(hours);
        }

        return forecasts;
    }
}
