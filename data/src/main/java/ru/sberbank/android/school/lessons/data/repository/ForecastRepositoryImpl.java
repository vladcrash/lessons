package ru.sberbank.android.school.lessons.data.repository;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ru.sberbank.android.school.lessons.data.App;
import ru.sberbank.android.school.lessons.data.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;
import ru.sberbank.android.school.lessons.data.network.WeatherWebService;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;
import ru.sberbank.android.school.lessons.domain.model.Weather;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class ForecastRepositoryImpl implements ForecastRepository {

    private static final String TAG = "ForecastRepositoryImpl";

    public ForecastRepositoryImpl() {

    }

    @Override
    public List<Forecast> getDailyForecasts(Boolean isRefresh) {
        List<Forecast> forecasts = loadForecastsFromDB();
        if (forecasts.size() == 0 || isRefresh) {
            Log.wtf(TAG, "network");
            loadForecastsFromNetwork();
            forecasts = loadForecastsFromDB();
        }

        return forecasts;
    }

    @Override
    public List<Hour> getHourlyForecasts(Integer dayId) {
        HourDao hourDao = App.getDatabase().hourDao();
        return hourDao.getHoursByForecastId(dayId);
    }

    private void loadForecastsFromNetwork() {
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

    private List<Forecast> loadForecastsFromDB() {
        ForecastDao forecastDao = App.getDatabase().forecastDao();
        return forecastDao.getAll();
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
    }
}
