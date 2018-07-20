package com.example.admin.lesson5.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.lesson5.App;
import com.example.admin.lesson5.db.dao.ForecastDao;
import com.example.admin.lesson5.db.dao.HourDao;
import com.example.admin.lesson5.R;
import com.example.admin.lesson5.adapter.WeatherAdapter;
import com.example.admin.lesson5.network.WeatherWebService;
import com.example.admin.lesson5.model.Forecast;
import com.example.admin.lesson5.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.weather_list);
        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemListener(new WeatherAdapter.OnClickItemListener() {
            @Override
            public void onClick(Integer forecastId) {
                Intent intent = DetailInfoActivity.newIntent(MainActivity.this, forecastId);
                startActivity(intent);
            }
        });

        getForecasts();
    }

    private void loadWeather() {
        WeatherWebService service = App.getWeatherWebService();
        Call<Weather> weather = service.getWeather();
        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setForecastList(response.body().getForecasts());
                    saveForecasts(response.body().getForecasts());
                    refreshLayout.setRefreshing(false);
                    Log.wtf("MainActivity", "retrofit network");
                }
                Log.wtf("MainActivity", "onResponse");
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.wtf("MainActivity", "onFailure");
            }
        });
    }

    private void saveForecasts(final List<Forecast> forecasts) {
        final ForecastDao forecastDao = App.getDatabase().forecastDao();
        final HourDao hourDao = App.getDatabase().hourDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                forecastDao.deleteAll();
                forecastDao.insertAll(forecasts);
                for (Forecast forecast : forecasts) {
                    hourDao.insertAll(forecast.getHours());
                }
            }
        }).start();
    }

    private void setForecastList(List<Forecast> forecasts) {
        adapter.setForecasts(forecasts);
    }

    private void getForecasts() {
        final ForecastDao forecastDao = App.getDatabase().forecastDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Forecast> forecasts = forecastDao.getAll();
                if (forecasts != null && forecasts.size() > 0) {
                    setForecastList(forecasts);
                    Log.wtf("MainActivity", "db");
                } else {
                    loadWeather();
                    Log.wtf("MainActivity", "network");
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        loadWeather();
    }
}
