package ru.sberbank.android.school.lessons.weather;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import ru.sberbank.android.school.lessons.domain.interactor.GetDailyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Forecast;

public class WeatherViewModel {

    private WeatherAdapter adapter;
    private GetDailyForecasts getDailyForecastsUseCase;
    private ObservableField<List<Forecast>> forecasts = new ObservableField<>();
    private ObservableBoolean isLoading = new ObservableBoolean();

    public WeatherViewModel(WeatherAdapter adapter, GetDailyForecasts getDailyForecastsUseCase) {
        this.adapter = adapter;
        this.getDailyForecastsUseCase = getDailyForecastsUseCase;
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
        getDailyForecastsUseCase.execute(new DailyForecastsObserver(), false);
    }

    public void onRefresh() {
        isLoading.set(true);
        getDailyForecastsUseCase.execute(new DailyForecastsObserver(), true);
    }

    private class DailyForecastsObserver extends DisposableSingleObserver<List<Forecast>> {

        @Override
        public void onSuccess(List<Forecast> forecastList) {
            forecasts.set(forecastList);
            isLoading.set(false);
        }

        @Override
        public void onError(Throwable e) {

        }
    }
}
