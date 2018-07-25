package ru.sberbank.android.school.lessons.ui.weatherdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.db.dao.HourDao;

public class WeatherDetailActivity extends AppCompatActivity {

    private static final String ID = "ru.sberbank.android.school.lessons.ui.weatherdetail.WeatherDetailActivity.ID";

    private RecyclerView detailRecyclerView;
    private WeatherDetailAdapter adapter;

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
        displayData();
    }

    private void init() {
        detailRecyclerView = findViewById(R.id.detail_recycler_view);
        adapter = new WeatherDetailAdapter();
        detailRecyclerView.setAdapter(adapter);
    }

    private void displayData() {
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Integer forecastId = getIntent().getIntExtra(ID, 0);
                HourDao hourDao = App.getDatabase().hourDao();
                adapter.setHours(hourDao.getHoursByForecastId(forecastId));
            }
        });
    }
}
