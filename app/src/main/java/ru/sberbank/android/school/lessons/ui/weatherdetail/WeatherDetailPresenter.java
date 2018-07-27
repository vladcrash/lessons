package ru.sberbank.android.school.lessons.ui.weatherdetail;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;

public class WeatherDetailPresenter implements WeatherDetailContract.Presenter {

    private WeatherDetailContract.View view;

    public WeatherDetailPresenter(WeatherDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadHourlyForecastsForDay(final Integer dayId) {
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HourDao hourDao = App.getDatabase().hourDao();
                view.showHourlyForecastsForDay(hourDao.getHoursByForecastId(dayId));
            }
        });
    }

    @Override
    public void detach() {
        view = null;
    }
}
