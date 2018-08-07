package ru.sberbank.android.school.lessons.domain.interactor;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class GetHourlyForecasts extends UseCase<Integer> {

    private ForecastRepository repository;
    private Callback<List<Hour>> callback;

    public GetHourlyForecasts(MainThread mainThread, WeatherExecutor<Integer> executor,
                              ForecastRepository repository) {
        super(mainThread, executor);
        this.repository = repository;
    }

    public void setCallback(Callback<List<Hour>> callback) {
        this.callback = callback;
    }

    @Override
    public void run(Integer dayId) {
        List<Hour> forecasts = repository.getHourlyForecasts(dayId);
        post(forecasts);
    }

    private void post(final List<Hour> forecasts) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(forecasts);
            }
        });
    }
}
