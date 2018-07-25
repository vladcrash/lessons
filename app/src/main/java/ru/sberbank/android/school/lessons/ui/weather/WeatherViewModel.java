package ru.sberbank.android.school.lessons.ui.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import ru.sberbank.android.school.lessons.data.ForecastRepository;
import ru.sberbank.android.school.lessons.data.model.Forecast;

public class WeatherViewModel {

    private ObservableField<List<Forecast>> forecasts = new ObservableField<>();
    private WeatherAdapter adapter;
    private WeatherAdapter.OnItemClickListener onItemClickListener;
    private ObservableBoolean isLoading = new ObservableBoolean();
    private ForecastRepository repository;

    public WeatherViewModel(ForecastRepository repository) {
        adapter = new WeatherAdapter();
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

    public WeatherAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(WeatherAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void onRefresh() {
        repository.onRefresh();
    }

    public void setForecasts() {
        repository.getForecasts(forecasts, isLoading);
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.set(isLoading);
    }
}
