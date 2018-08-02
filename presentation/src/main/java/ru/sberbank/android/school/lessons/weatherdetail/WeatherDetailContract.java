package ru.sberbank.android.school.lessons.weatherdetail;

import java.util.List;

import ru.sberbank.android.school.lessons.BasePresenter;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public interface WeatherDetailContract {

    interface View {
        void showHourlyForecastsForDay(List<Hour> forecasts);
    }

    interface Presenter extends BasePresenter {
        void loadHourlyForecastsForDay(Integer dayId);
    }
}
