package ru.sberbank.android.school.lessons.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public class WeatherDetailActivity extends AppCompatActivity implements WeatherDetailContract.View {

    private static final String ID = "ru.sberbank.android.school.lessons.ui.weatherdetail.WeatherDetailActivity.ID";

    @Inject
    WeatherDetailAdapter adapter;

    @Inject
    WeatherDetailContract.Presenter presenter;
    private RecyclerView detailRecyclerView;

    public static final Intent newIntent(Context context, Long forecastId) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putExtra(ID, forecastId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        setup();
        init();
        load();
    }

    private void setup() {
        App.get(this)
            .plusWeatherDetailComponent()
            .inject(this);
    }

    private void init() {
        detailRecyclerView = findViewById(R.id.detail_recycler_view);
        detailRecyclerView.setAdapter(adapter);
        presenter.attach(this);
    }

    private void load() {
        Long dayId = getIntent().getLongExtra(ID, 0);
        presenter.loadHourlyForecastsForDay(dayId);
    }

    @Override
    public void showHourlyForecastsForDay(List<Hour> forecasts) {
        adapter.setHours(forecasts);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        App.get(this).releaseWeatherDetailComponent();
    }
}
