package ru.sberbank.android.school.lessons.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.activity.MainActivity;
import ru.sberbank.android.school.lessons.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.db.dao.HourDao;
import ru.sberbank.android.school.lessons.model.Forecast;
import ru.sberbank.android.school.lessons.model.Hour;
import ru.sberbank.android.school.lessons.model.Weather;
import ru.sberbank.android.school.lessons.network.WeatherWebService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ForecastService extends IntentService {

    public static final String IS_SUCCESS = "ru.sberbank.android.school.lessons.service.ForecastService.IS_SUCCESS";

    public static final Intent newIntent(Context context) {
        return new Intent(context, ForecastService.class);
    }

    public ForecastService() {
        super(ForecastService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        loadWeather();
    }

    private void loadWeather() {
        WeatherWebService service = App.getWeatherWebService();
        Call<Weather> weather = service.getWeather();
        try {
            Response<Weather> response = weather.execute();
            if (response.isSuccessful() && response.body() != null) {
                saveForecasts(response.body().getForecasts());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveForecasts(final List<Forecast> forecasts) {
        ForecastDao forecastDao = App.getDatabase().forecastDao();
        HourDao hourDao = App.getDatabase().hourDao();
        forecastDao.deleteAll();
        forecastDao.insertAll(forecasts);
        List<Forecast> listWithIds = forecastDao.getAll();
        for (int i = 0; i < forecasts.size(); i++) {
            List<Hour> hours = forecasts.get(i).getHours();
            for (Hour hour : hours) {
                hour.setForecastId(listWithIds.get(i).getId());
            }
            hourDao.insertAll(hours);
        }
        sendResult();
    }

    private void sendResult() {
        Intent intent = new Intent(MainActivity.FORECASTS_SAVED);
        intent.putExtra(IS_SUCCESS, true);
        LocalBroadcastManager.getInstance(ForecastService.this).sendBroadcast(intent);
        Log.wtf("ForecastService", "sendResult");
    }
}
