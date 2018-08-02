package ru.sberbank.android.school.lessons.weatherdetail;

import java.util.List;

import ru.sberbank.android.school.lessons.data.App;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;
import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.interactor.Callback;
import ru.sberbank.android.school.lessons.domain.interactor.GetHourlyForecasts;
import ru.sberbank.android.school.lessons.domain.model.Hour;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class WeatherDetailPresenter implements WeatherDetailContract.Presenter, Callback<List<Hour>> {

    private WeatherDetailContract.View view;
    private GetHourlyForecasts getHourlyForecastsUseCase;

    public WeatherDetailPresenter(MainThread mainThread, WeatherExecutor<Integer> executor,
                                  ForecastRepository repository, WeatherDetailContract.View view) {
        this.view = view;
        getHourlyForecastsUseCase = new GetHourlyForecasts(mainThread, executor, this, repository);
    }

    @Override
    public void loadHourlyForecastsForDay(final Integer dayId) {
        getHourlyForecastsUseCase.execute(dayId);
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
