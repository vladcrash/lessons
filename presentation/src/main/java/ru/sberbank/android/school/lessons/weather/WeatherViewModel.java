package ru.sberbank.android.school.lessons.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.interactor.Callback;
import ru.sberbank.android.school.lessons.domain.interactor.GetDailyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.data.repository.ForecastRepositoryImpl;

public class WeatherViewModel implements Callback<List<Forecast>>{

    private WeatherAdapter adapter;
    private ObservableField<List<Forecast>> forecasts = new ObservableField<>();
    private ObservableBoolean isLoading = new ObservableBoolean();
    private GetDailyForecasts getDailyForecastsUseCase;

    public WeatherViewModel(MainThread mainThread, WeatherExecutor<Boolean> executor,
                            ForecastRepositoryImpl repository, WeatherAdapter adapter) {
        this.adapter = adapter;
        getDailyForecastsUseCase = new GetDailyForecasts(mainThread, executor,
                this, repository);
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
