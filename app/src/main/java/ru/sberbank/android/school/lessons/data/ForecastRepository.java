package ru.sberbank.android.school.lessons.data;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.data.model.Forecast;
import ru.sberbank.android.school.lessons.data.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.service.ForecastService;

public class ForecastRepository {

    private static final String TAG = "ForecastRepository";
    private WeakReference<Context> context;

    public ForecastRepository(Context context) {
        this.context = new WeakReference<>(context);
    }

    public void getForecasts(final ObservableField<List<Forecast>> forecastList) {
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ForecastDao forecastDao = App.getDatabase().forecastDao();
                List<Forecast> forecasts = forecastDao.getAll();
                if (forecasts != null && forecasts.size() > 0) {
                    forecastList.set(forecasts);
                    Log.wtf(TAG, "db");
                } else {
                    loadForecastsFromNetwork();
                }
            }
        });
    }

    public void getForecasts(final ObservableField<List<Forecast>> forecastList, final ObservableBoolean isLoading) {
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ForecastDao forecastDao = App.getDatabase().forecastDao();
                List<Forecast> forecasts = forecastDao.getAll();
                forecastList.set(forecasts);
                isLoading.set(false);
                Log.wtf(TAG, "setForecastsAsync network");
            }
        });
    }

    public void onRefresh() {
        loadForecastsFromNetwork();
    }

    private void loadForecastsFromNetwork() {
        if (context != null && context.get() != null) {
            context.get().startService(ForecastService.newIntent(context.get()));
            Log.wtf(TAG, "network");
        }
    }
}
