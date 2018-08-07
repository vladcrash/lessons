package ru.sberbank.android.school.lessons.domain.interactor;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class GetDailyForecasts extends UseCase<Boolean> {

    private ForecastRepository repository;
    private Callback<List<Forecast>> callback;

    public GetDailyForecasts(MainThread mainThread, WeatherExecutor<Boolean> executor,
                             ForecastRepository repository) {
        super(mainThread, executor);
        this.repository = repository;
    }

    public void setCallback(Callback<List<Forecast>> callback) {
        this.callback = callback;
    }

    @Override
    public void run(Boolean isRefresh) {
        List<Forecast> dailyForecasts = repository.getDailyForecasts(isRefresh);
        post(dailyForecasts);
    }

    private void post(final List<Forecast> forecasts) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(forecasts);
            }
        });
    }
}
