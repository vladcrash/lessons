package ru.sberbank.android.school.lessons.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

public class GetDailyForecasts extends UseCase<List<Forecast>, Boolean> {

    private ForecastRepository repository;

    public GetDailyForecasts(ForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Forecast>> run(Boolean isRefresh) {
        return repository.getDailyForecasts(isRefresh);
    }
}
