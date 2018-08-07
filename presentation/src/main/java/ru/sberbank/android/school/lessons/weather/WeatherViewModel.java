package ru.sberbank.android.school.lessons.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.interactor.Callback;
import ru.sberbank.android.school.lessons.domain.interactor.GetDailyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Forecast;

public class WeatherViewModel implements Callback<List<Forecast>>{

    private WeatherAdapter adapter;
    private GetDailyForecasts getDailyForecastsUseCase;
    private ObservableField<List<Forecast>> forecasts = new ObservableField<>();
    private ObservableBoolean isLoading = new ObservableBoolean();

    public WeatherViewModel(WeatherAdapter adapter, GetDailyForecasts getDailyForecastsUseCase) {
        this.adapter = adapter;
        this.getDailyForecastsUseCase = getDailyForecastsUseCase;
        this.getDailyForecastsUseCase.setCallback(this);
    }

    public ObservableField<List<Forecast>> getForecasts() {
        return forecasts;
    }

    public WeatherAdapter getAdapter() {
        return adapter;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void populateData() {
        getDailyForecastsUseCase.execute(false);
    }

    public void onRefresh() {
        isLoading.set(true);
        getDailyForecastsUseCase.execute(true);
    }

    @Override
    public void onSuccess(List<Forecast> forecasts) {
        this.forecasts.set(forecasts);
        isLoading.set(false);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
