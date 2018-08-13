package ru.sberbank.android.school.lessons.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public interface ForecastRepository {

    Single<List<Forecast>> getDailyForecasts(Boolean isRefresh);
    Single<List<Hour>> getHourlyForecasts(Long dayId);
}
