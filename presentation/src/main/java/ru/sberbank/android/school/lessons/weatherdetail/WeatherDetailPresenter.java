package ru.sberbank.android.school.lessons.weatherdetail;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.interactor.Callback;
import ru.sberbank.android.school.lessons.domain.interactor.GetHourlyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public class WeatherDetailPresenter implements WeatherDetailContract.Presenter, Callback<List<Hour>> {

    private WeatherDetailContract.View view;
    private GetHourlyForecasts getHourlyForecastsUseCase;

    public WeatherDetailPresenter(GetHourlyForecasts getHourlyForecastsUseCase) {
        this.getHourlyForecastsUseCase = getHourlyForecastsUseCase;
        this.getHourlyForecastsUseCase.setCallback(this);
    }

    @Override
    public void loadHourlyForecastsForDay(final Integer dayId) {
        getHourlyForecastsUseCase.execute(dayId);
    }

    @Override
    public void attach(WeatherDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onSuccess(List<Hour> hours) {
        view.showHourlyForecastsForDay(hours);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
