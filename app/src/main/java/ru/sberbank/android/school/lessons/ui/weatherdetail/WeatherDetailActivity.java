package ru.sberbank.android.school.lessons.ui.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.model.Hour;

public class WeatherDetailActivity extends AppCompatActivity implements WeatherDetailContract.View {

    private static final String ID = "ru.sberbank.android.school.lessons.ui.weatherdetail.WeatherDetailActivity.ID";

    private RecyclerView detailRecyclerView;
    private WeatherDetailAdapter adapter;
    private WeatherDetailContract.Presenter presenter;

    public static final Intent newIntent(Context context, Integer forecastId) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putExtra(ID, forecastId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        init();
        load();
    }

    private void init() {
        detailRecyclerView = findViewById(R.id.detail_recycler_view);
        adapter = new WeatherDetailAdapter();
        detailRecyclerView.setAdapter(adapter);
        presenter = new WeatherDetailPresenter(this);
    }

    private void load() {
        Integer dayId = getIntent().getIntExtra(ID, 0);
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
    }
}
