package ru.sberbank.android.school.lessons.weatherdetail;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import ru.sberbank.android.school.lessons.domain.interactor.GetHourlyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public class WeatherDetailPresenter implements WeatherDetailContract.Presenter {

    private WeatherDetailContract.View view;
    private GetHourlyForecasts getHourlyForecastsUseCase;

    public WeatherDetailPresenter(GetHourlyForecasts getHourlyForecastsUseCase) {
        this.getHourlyForecastsUseCase = getHourlyForecastsUseCase;
    }

    @Override
    public void loadHourlyForecastsForDay(final Long dayId) {
        getHourlyForecastsUseCase.execute(new HourlyForecastsObserver(), dayId);
    }

    @Override
    public void attach(WeatherDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
        getHourlyForecastsUseCase.dispose();
    }

    private class HourlyForecastsObserver extends DisposableSingleObserver<List<Hour>> {

        @Override
        public void onSuccess(List<Hour> hours) {
            view.showHourlyForecastsForDay(hours);
        }

        @Override
        public void onError(Throwable e) {

        }
    }
}
