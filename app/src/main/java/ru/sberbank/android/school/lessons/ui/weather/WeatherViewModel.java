package ru.sberbank.android.school.lessons.ui.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import ru.sberbank.android.school.lessons.data.ForecastRepository;
import ru.sberbank.android.school.lessons.data.model.Forecast;

public class WeatherViewModel {

    private WeatherAdapter adapter;
    private ForecastRepository repository;
    private ObservableField<List<Forecast>> forecasts = new ObservableField<>();
    private ObservableBoolean isLoading = new ObservableBoolean();

    public WeatherViewModel(ForecastRepository repository, WeatherAdapter adapter) {
        this.adapter = adapter;
        this.repository = repository;
    }

    public ObservableField<List<Forecast>> getForecasts() {
        return forecasts;
    }

    public WeatherAdapter getAdapter() {
        return adapter;
    }

    public void populateData() {
        repository.getForecasts(forecasts);
    }

    public void onRefresh() {
        isLoading.set(true);
        repository.onRefresh();
    }

    public void setForecasts() {
        repository.getForecasts(forecasts, isLoading);
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }
}
