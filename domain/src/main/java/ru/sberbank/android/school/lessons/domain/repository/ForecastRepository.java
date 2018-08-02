package ru.sberbank.android.school.lessons.domain.repository;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public interface ForecastRepository {

    List<Forecast> getDailyForecasts(Boolean isRefresh);
    List<Hour> getHourlyForecasts(Integer dayId);
}
